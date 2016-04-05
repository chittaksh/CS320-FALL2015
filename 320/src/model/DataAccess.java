package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import helper.SQLHelper;
import helper.connectionObject;

public class DataAccess
{
	// Array list to Load Data
	static ArrayList<ApartmentDetail> apartDetails;
	static ArrayList<AppointmentDetail> appointDetails;
	static ArrayList<RentDetail> rentDetails;
	static ArrayList<RentLog> rentLog;
	
	static RentLog rentLo;

	// Connection Object
	static connectionObject connObj = new connectionObject(
			//"jdbc:mysql://cs3.calstatela.edu:3306/cs320stu115", "cs320stu115", "bDXMTF**");
			"jdbc:mysql://localhost/cs320stu115", "root", "cartoon");;
	static Connection c = null;

	public static UserDetail tryLogin(String userEmail, String password)
			throws Exception
	{
		UserDetail userD = null;

		try
		{
			c = SQLHelper.getConnection(connObj);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");

			while (rs.next())
			{
				if (rs.getString("EMAIL").equals(userEmail)
						&& rs.getString("PASS").equals(password))
				{
					// Username and Password Verified.
					userD = new UserDetail(rs.getInt("ID"),
							Roles.getRole(rs.getInt("ROLE")),
							rs.getString("USERNAME"), rs.getString("PASS"),
							rs.getString("EMAIL"), rs.getString("CONTACT"),
							rs.getInt("NOOFPEOPLE"),
							Occupation.getOccupation(rs.getInt("OCCUPATION")),
							rs.getString("APTTYPE"),
							rs.getString("PREFERENCES"),
							rs.getTimestamp("NEEDFROM"));
					break;
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}
		return userD;
	}

	public static ArrayList<ApartmentDetail> getApartments() throws Exception
	{
		apartDetails = new ArrayList<ApartmentDetail>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT ID, APARTMENTNO, APTTYPE, FACILITIES, MAXPEOPLE, RENT, DEPOSIT, VACANT, UPDATEDONE FROM APARTMENTDETAILS;");

			while (rs.next())
			{
				ApartmentDetail temp = new ApartmentDetail(rs.getInt("ID"),
						rs.getInt("APARTMENTNO"), rs.getString("APTTYPE"),
						rs.getString("FACILITIES"), rs.getInt("MAXPEOPLE"),
						rs.getInt("RENT"), rs.getInt("DEPOSIT"),
						rs.getBoolean("VACANT"), rs.getTimestamp("UPDATEDONE"));
				apartDetails.add(temp);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return apartDetails;
	}

	public static ArrayList<ApartmentDetail> getVacantApartments()
			throws Exception
	{
		apartDetails = new ArrayList<ApartmentDetail>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT ID, APARTMENTNO, APTTYPE, FACILITIES, MAXPEOPLE, RENT, DEPOSIT, VACANT, UPDATEDONE FROM APARTMENTDETAILS WHERE VACANT=TRUE;");

			while (rs.next())
			{
				ApartmentDetail temp = new ApartmentDetail(rs.getInt("ID"),
						rs.getInt("APARTMENTNO"), rs.getString("APTTYPE"),
						rs.getString("FACILITIES"), rs.getInt("MAXPEOPLE"),
						rs.getInt("RENT"), rs.getInt("DEPOSIT"),
						rs.getBoolean("VACANT"), rs.getTimestamp("UPDATEDONE"));
				apartDetails.add(temp);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return apartDetails;
	}

	public static ArrayList<AppointmentDetail> getAppointments()
			throws Exception
	{
		appointDetails = new ArrayList<AppointmentDetail>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			String SQLstatement = "SELECT * FROM APPOINTMENTDETAILS APPD INNER JOIN APARTMENTDETAILS APTD ON APPD.APTID = APTD.ID INNER JOIN USERS U ON APPD.USERID = U.ID "
					+ "WHERE APTD.UPDATEDONE <= APPD.APPOINTCREATED;";

			PreparedStatement stmt = c.prepareStatement(SQLstatement);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				AppointmentDetail temp = new AppointmentDetail(rs.getInt("ID"),
						new ApartmentDetail(rs.getInt("APTID"),
								rs.getInt("APARTMENTNO"),
								rs.getString("APTTYPE"),
								rs.getString("FACILITIES"),
								rs.getInt("MAXPEOPLE"), rs.getInt("RENT"),
								rs.getInt("DEPOSIT"), rs.getBoolean("VACANT"),
								rs.getTimestamp("UPDATEDONE")),
						new UserDetail(rs.getInt("ID"),
								Roles.getRole(rs.getInt("ROLE")),
								rs.getString("USERNAME"), rs.getString("PASS"),
								rs.getString("EMAIL"), rs.getString("CONTACT"),
								rs.getInt("NOOFPEOPLE"),
								Occupation
										.getOccupation(rs.getInt("OCCUPATION")),
								rs.getString("APTTYPE"),
								rs.getString("PREFERENCES"),
								rs.getTimestamp("NEEDFROM")),
						Status.getStatus(rs.getInt("STATUS")),
						rs.getTimestamp("APPOINTMENTDATE"),
						rs.getTimestamp("APPOINTCREATED"));

				Statement stm = c.createStatement();
				ResultSet rs1 = stm.executeQuery(
						"SELECT DECLINEDDATE FROM APPOINTMENTDECLINED WHERE APPOINTID="
								+ rs.getInt("ID") + ";");

				while (rs1.next())
				{
					temp.setRuledOutDates(rs1.getTimestamp("DECLINEDDATE"));
				}

				appointDetails.add(temp);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return appointDetails;
	}

	public static ArrayList<AppointmentDetail> getAppointmentsHistory()
			throws Exception
	{
		appointDetails = new ArrayList<AppointmentDetail>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			String SQLstatement = "SELECT * FROM APPOINTMENTDETAILS APPD INNER JOIN APARTMENTDETAILS APTD ON APPD.APTID = APTD.ID INNER JOIN USERS U ON APPD.USERID = U.ID "
					+ "WHERE APTD.UPDATEDONE > APPD.APPOINTCREATED;";

			PreparedStatement stmt = c.prepareStatement(SQLstatement);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				AppointmentDetail temp = new AppointmentDetail(rs.getInt("ID"),
						new ApartmentDetail(rs.getInt("APTID"),
								rs.getInt("APARTMENTNO"),
								rs.getString("APTTYPE"),
								rs.getString("FACILITIES"),
								rs.getInt("MAXPEOPLE"), rs.getInt("RENT"),
								rs.getInt("DEPOSIT"), rs.getBoolean("VACANT"),
								rs.getTimestamp("UPDATEDONE")),
						new UserDetail(rs.getInt("ID"),
								Roles.getRole(rs.getInt("ROLE")),
								rs.getString("USERNAME"), rs.getString("PASS"),
								rs.getString("EMAIL"), rs.getString("CONTACT"),
								rs.getInt("NOOFPEOPLE"),
								Occupation
										.getOccupation(rs.getInt("OCCUPATION")),
								rs.getString("APTTYPE"),
								rs.getString("PREFERENCES"),
								rs.getTimestamp("NEEDFROM")),
						Status.getStatus(rs.getInt("STATUS")),
						rs.getTimestamp("APPOINTMENTDATE"),
						rs.getTimestamp("APPOINTCREATED"));

				Statement stm = c.createStatement();
				ResultSet rs1 = stm.executeQuery(
						"SELECT DECLINEDDATE FROM APPOINTMENTDECLINED WHERE APPOINTID="
								+ rs.getInt("ID") + ";");

				while (rs1.next())
				{
					temp.setRuledOutDates(rs1.getTimestamp("DECLINEDDATE"));
				}

				appointDetails.add(temp);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return appointDetails;
	}

	public static ArrayList<AppointmentDetail> getAppointmentsForUser(
			int userID) throws Exception
	{
		appointDetails = new ArrayList<AppointmentDetail>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			String SQLstatement = "SELECT * FROM APPOINTMENTDETAILS APPD INNER JOIN APARTMENTDETAILS APTD ON APPD.APTID = APTD.ID INNER JOIN USERS U ON APPD.USERID = U.ID "
					+ "WHERE U.ID =? AND APTD.UPDATEDONE <= APPD.APPOINTCREATED;";

			PreparedStatement stmt = c.prepareStatement(SQLstatement);
			stmt.setInt(1, userID);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				AppointmentDetail temp = new AppointmentDetail(rs.getInt("ID"),
						new ApartmentDetail(rs.getInt("APTID"),
								rs.getInt("APARTMENTNO"),
								rs.getString("APTTYPE"),
								rs.getString("FACILITIES"),
								rs.getInt("MAXPEOPLE"), rs.getInt("RENT"),
								rs.getInt("DEPOSIT"), rs.getBoolean("VACANT"),
								rs.getTimestamp("UPDATEDONE")),
						new UserDetail(rs.getInt("ID"),
								Roles.getRole(rs.getInt("ROLE")),
								rs.getString("USERNAME"), rs.getString("PASS"),
								rs.getString("EMAIL"), rs.getString("CONTACT"),
								rs.getInt("NOOFPEOPLE"),
								Occupation
										.getOccupation(rs.getInt("OCCUPATION")),
								rs.getString("APTTYPE"),
								rs.getString("PREFERENCES"),
								rs.getTimestamp("NEEDFROM")),
						Status.getStatus(rs.getInt("STATUS")),
						rs.getTimestamp("APPOINTMENTDATE"),
						rs.getTimestamp("APPOINTCREATED"));

				Statement stm = c.createStatement();
				ResultSet rs1 = stm.executeQuery(
						"SELECT DECLINEDDATE FROM APPOINTMENTDECLINED WHERE APPOINTID="
								+ rs.getInt("ID") + ";");

				while (rs1.next())
				{
					temp.setRuledOutDates(rs1.getTimestamp("DECLINEDDATE"));
				}

				appointDetails.add(temp);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return appointDetails;
	}

	public static ArrayList<AppointmentDetail> getAppointmentForApartment(
			int aptID) throws Exception
	{
		appointDetails = new ArrayList<AppointmentDetail>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			String SQLstatement = "SELECT * FROM APPOINTMENTDETAILS APPD INNER JOIN APARTMENTDETAILS APTD ON APPD.APTID = APTD.ID INNER JOIN USERS U ON APPD.USERID = U.ID "
					+ "WHERE APTD.ID =? AND APTD.UPDATEDONE <= APPD.APPOINTCREATED;";

			PreparedStatement stmt = c.prepareStatement(SQLstatement);
			stmt.setInt(1, aptID);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				AppointmentDetail temp = new AppointmentDetail(rs.getInt("ID"),
						new ApartmentDetail(rs.getInt("APTID"),
								rs.getInt("APARTMENTNO"),
								rs.getString("APTTYPE"),
								rs.getString("FACILITIES"),
								rs.getInt("MAXPEOPLE"), rs.getInt("RENT"),
								rs.getInt("DEPOSIT"), rs.getBoolean("VACANT"),
								rs.getTimestamp("UPDATEDONE")),
						new UserDetail(rs.getInt("ID"),
								Roles.getRole(rs.getInt("ROLE")),
								rs.getString("USERNAME"), rs.getString("PASS"),
								rs.getString("EMAIL"), rs.getString("CONTACT"),
								rs.getInt("NOOFPEOPLE"),
								Occupation
										.getOccupation(rs.getInt("OCCUPATION")),
								rs.getString("APTTYPE"),
								rs.getString("PREFERENCES"),
								rs.getTimestamp("NEEDFROM")),
						Status.getStatus(rs.getInt("STATUS")),
						rs.getTimestamp("APPOINTMENTDATE"),
						rs.getTimestamp("APPOINTCREATED"));

				Statement stm = c.createStatement();
				ResultSet rs1 = stm.executeQuery(
						"SELECT DECLINEDDATE FROM APPOINTMENTDECLINED WHERE APPOINTID="
								+ rs.getInt("ID") + ";");

				while (rs1.next())
				{
					temp.setRuledOutDates(rs1.getTimestamp("DECLINEDDATE"));
				}

				appointDetails.add(temp);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return appointDetails;
	}

	public static ArrayList<AppointmentDetail> getAppointmentHistoryForApartment(
			int aptID) throws Exception
	{
		appointDetails = new ArrayList<AppointmentDetail>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			String SQLstatement = "SELECT * FROM APPOINTMENTDETAILS APPD INNER JOIN APARTMENTDETAILS APTD ON APPD.APTID = APTD.ID INNER JOIN USERS U ON APPD.USERID = U.ID "
					+ "WHERE APTD.ID =? AND APTD.UPDATEDONE > APPD.APPOINTCREATED ORDER BY APPD.APPOINTCREATED;";

			PreparedStatement stmt = c.prepareStatement(SQLstatement);
			stmt.setInt(1, aptID);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				AppointmentDetail temp = new AppointmentDetail(rs.getInt("ID"),
						new ApartmentDetail(rs.getInt("APTID"),
								rs.getInt("APARTMENTNO"),
								rs.getString("APTTYPE"),
								rs.getString("FACILITIES"),
								rs.getInt("MAXPEOPLE"), rs.getInt("RENT"),
								rs.getInt("DEPOSIT"), rs.getBoolean("VACANT"),
								rs.getTimestamp("UPDATEDONE")),
						new UserDetail(rs.getInt("ID"),
								Roles.getRole(rs.getInt("ROLE")),
								rs.getString("USERNAME"), rs.getString("PASS"),
								rs.getString("EMAIL"), rs.getString("CONTACT"),
								rs.getInt("NOOFPEOPLE"),
								Occupation
										.getOccupation(rs.getInt("OCCUPATION")),
								rs.getString("APTTYPE"),
								rs.getString("PREFERENCES"),
								rs.getTimestamp("NEEDFROM")),
						Status.getStatus(rs.getInt("STATUS")),
						rs.getTimestamp("APPOINTMENTDATE"),
						rs.getTimestamp("APPOINTCREATED"));

				Statement stm = c.createStatement();
				ResultSet rs1 = stm.executeQuery(
						"SELECT DECLINEDDATE FROM APPOINTMENTDECLINED WHERE APPOINTID="
								+ rs.getInt("ID") + ";");

				while (rs1.next())
				{
					temp.setRuledOutDates(rs1.getTimestamp("DECLINEDDATE"));
				}

				appointDetails.add(temp);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return appointDetails;
	}

	public static ArrayList<RentDetail> getRentDetails() throws Exception
	{
		rentDetails = new ArrayList<RentDetail>();

		try
		{
			c = SQLHelper.getConnection(connObj);
			
			String SQLstatement = "SELECT * FROM RENTDETAILS RD INNER JOIN APARTMENTDETAILS APTD ON APTD.ID = RD.APTID"
					+ " INNER JOIN APPOINTMENTDETAILS APPD ON APPD.ID = RD.APPOINTID INNER JOIN USERS UD ON APPD.USERID = UD.ID"
					+ " WHERE RD.ID= (SELECT MAX(ID) FROM RENTDETAILS WHERE APTID= APTD.ID) AND APTD.VACANT = false ORDER BY APARTMENTNO;";
			
			PreparedStatement stmt = c.prepareStatement(SQLstatement);
			//stmt.setInt(1, aptID);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				ApartmentDetail aptDtl = new ApartmentDetail(rs.getInt("APTID"),
						rs.getInt("APARTMENTNO"), rs.getString("APTTYPE"),
						rs.getString("FACILITIES"), rs.getInt("MAXPEOPLE"),
						rs.getInt("RENT"), rs.getInt("DEPOSIT"),
						rs.getBoolean("VACANT"), rs.getTimestamp("UPDATEDONE"));

				UserDetail userDtl = new UserDetail(rs.getInt("USERID"),
						Roles.getRole(rs.getInt("ROLE")),
						rs.getString("USERNAME"), rs.getString("PASS"),
						rs.getString("EMAIL"), rs.getString("CONTACT"),
						rs.getInt("NOOFPEOPLE"),
						Occupation.getOccupation(rs.getInt("OCCUPATION")),
						rs.getString("APTTYPE"), rs.getString("PREFERENCES"),
						rs.getTimestamp("NEEDFROM"));

				AppointmentDetail appDtl = new AppointmentDetail(
						rs.getInt("APPOINTID"), aptDtl, userDtl,
						Status.getStatus(rs.getInt("STATUS")),
						rs.getTimestamp("APPOINTMENTDATE"),
						rs.getTimestamp("APPOINTCREATED"));

				Statement stm = c.createStatement();
				ResultSet rs1 = stm.executeQuery(
						"SELECT DECLINEDDATE FROM APPOINTMENTDECLINED WHERE APPOINTID="
								+ rs.getInt("APPOINTID") + ";");

				while (rs1.next())
				{
					appDtl.setRuledOutDates(rs1.getTimestamp("DECLINEDDATE"));
				}

				RentDetail rentDtl = new RentDetail(rs.getInt("ID"),appDtl, userDtl, aptDtl,
						rs.getTimestamp("LEASETERMINATION"),
						rs.getString("LEASEHOLDERNAME"),
						rs.getString("IDPROOF"), rs.getInt("RENTRENT"),
						rs.getInt("RENTDEPOSIT"));

				rentDetails.add(rentDtl);
			}

		}
		catch (Exception ex)

		{
			throw ex;
		}
		finally

		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}
		return rentDetails;
	}

	public static ArrayList<RentLog> getRentLog() throws Exception
	{
		rentLog = new ArrayList<RentLog>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			String SQLstatement = "SELECT * FROM RENTLOG RL INNER JOIN RENTDETAILS RDS ON RL.RENTID = RDS.ID INNER JOIN APPOINTMENTDETAILS APPD ON RL.APPOINTID = APPD.ID INNER JOIN APARTMENTDETAILS APTD ON RL.APTID = APTD.ID INNER JOIN USERS U ON RL.USERID = U.ID"
					+ " ORDER BY APTD.APARTMENTNO, RL.RENTMONTH;";
			PreparedStatement stmt = c.prepareStatement(SQLstatement);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				ApartmentDetail aptDtl = new ApartmentDetail(rs.getInt("APTID"),
						rs.getInt("APARTMENTNO"), rs.getString("APTTYPE"),
						rs.getString("FACILITIES"), rs.getInt("MAXPEOPLE"),
						rs.getInt("RENT"), rs.getInt("DEPOSIT"),
						rs.getBoolean("VACANT"), rs.getTimestamp("UPDATEDONE"));

				UserDetail userDtl = new UserDetail(rs.getInt("USERID"),
						Roles.getRole(rs.getInt("ROLE")),
						rs.getString("USERNAME"), rs.getString("PASS"),
						rs.getString("EMAIL"), rs.getString("CONTACT"),
						rs.getInt("NOOFPEOPLE"),
						Occupation.getOccupation(rs.getInt("OCCUPATION")),
						rs.getString("APTTYPE"), rs.getString("PREFERENCES"),
						rs.getTimestamp("NEEDFROM"));

				AppointmentDetail appDtl = new AppointmentDetail(
						rs.getInt("APPOINTID"), aptDtl, userDtl,
						Status.getStatus(rs.getInt("STATUS")),
						rs.getTimestamp("APPOINTMENTDATE"),
						rs.getTimestamp("APPOINTCREATED"));

				Statement stm = c.createStatement();
				ResultSet rs1 = stm.executeQuery(
						"SELECT DECLINEDDATE FROM APPOINTMENTDECLINED WHERE APPOINTID="
								+ rs.getInt("APPOINTID") + ";");

				while (rs1.next())
				{
					appDtl.setRuledOutDates(rs1.getTimestamp("DECLINEDDATE"));
				}

				RentDetail rentDtl = new RentDetail(rs.getInt("ID"),appDtl, userDtl, aptDtl,
						rs.getTimestamp("LEASETERMINATION"),
						rs.getString("LEASEHOLDERNAME"),
						rs.getString("IDPROOF"), rs.getInt("RENTRENT"),
						rs.getInt("RENTDEPOSIT"));

				rentLog.add(new RentLog(rs.getInt(1), userDtl, aptDtl, appDtl, rentDtl,
						rs.getInt("RENTAMOUNT"),
						Months.getMonths(rs.getInt("RENTMONTH")),
						rs.getInt("RENTYEAR"), rs.getDate("PAYDATE")));
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return rentLog;
	}

	public static RentLog getRentLog(int ID) throws Exception
	{
		try
		{
			c = SQLHelper.getConnection(connObj);

			String SQLstatement = "SELECT * FROM RENTLOG RL INNER JOIN RENTDETAILS RDS ON RL.RENTID = RDS.ID INNER JOIN APPOINTMENTDETAILS APPD ON RL.APPOINTID = APPD.ID INNER JOIN APARTMENTDETAILS APTD ON RL.APTID = APTD.ID INNER JOIN USERS U ON RL.USERID = U.ID"
					+ " AND RL.ID=? ORDER BY APTD.APARTMENTNO, RL.RENTMONTH ;";
			PreparedStatement stmt = c.prepareStatement(SQLstatement);

			stmt.setInt(1, ID);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				ApartmentDetail aptDtl = new ApartmentDetail(rs.getInt("APTID"),
						rs.getInt("APARTMENTNO"), rs.getString("APTTYPE"),
						rs.getString("FACILITIES"), rs.getInt("MAXPEOPLE"),
						rs.getInt("RENT"), rs.getInt("DEPOSIT"),
						rs.getBoolean("VACANT"), rs.getTimestamp("UPDATEDONE"));

				UserDetail userDtl = new UserDetail(rs.getInt("USERID"),
						Roles.getRole(rs.getInt("ROLE")),
						rs.getString("USERNAME"), rs.getString("PASS"),
						rs.getString("EMAIL"), rs.getString("CONTACT"),
						rs.getInt("NOOFPEOPLE"),
						Occupation.getOccupation(rs.getInt("OCCUPATION")),
						rs.getString("APTTYPE"), rs.getString("PREFERENCES"),
						rs.getTimestamp("NEEDFROM"));

				AppointmentDetail appDtl = new AppointmentDetail(
						rs.getInt("APPOINTID"), aptDtl, userDtl,
						Status.getStatus(rs.getInt("STATUS")),
						rs.getTimestamp("APPOINTMENTDATE"),
						rs.getTimestamp("APPOINTCREATED"));

				Statement stm = c.createStatement();
				ResultSet rs1 = stm.executeQuery(
						"SELECT DECLINEDDATE FROM APPOINTMENTDECLINED WHERE APPOINTID="
								+ rs.getInt("APPOINTID") + ";");

				while (rs1.next())
				{
					appDtl.setRuledOutDates(rs1.getTimestamp("DECLINEDDATE"));
				}

				RentDetail rentDtl = new RentDetail(rs.getInt("ID"),appDtl, userDtl, aptDtl,
						rs.getTimestamp("LEASETERMINATION"),
						rs.getString("LEASEHOLDERNAME"),
						rs.getString("IDPROOF"), rs.getInt("RENTRENT"),
						rs.getInt("RENTDEPOSIT"));

				rentLo = new RentLog(rs.getInt(1), userDtl, aptDtl, appDtl, rentDtl,
						rs.getInt("RENTAMOUNT"),
						Months.getMonths(rs.getInt("RENTMONTH")),
						rs.getInt("RENTYEAR"), rs.getDate("PAYDATE"));
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return rentLo;
	}
	
	public static int setUser(String username, String password, String email,
			String contact, int people, Occupation occupation,
			String apartmentType, String preferences, java.util.Date needFrom)
					throws Exception
	{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			// Check if User Exist
			boolean UserExist = false;
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT EMAIL FROM USERS");

			while (rs.next())
			{
				if (rs.getString("EMAIL").equals(email))
				{
					UserExist = true;
					break;
				}
			}

			if (!UserExist)
			{
				// Insert User
				String insertTableSQL = "INSERT INTO USERS(ROLE, USERNAME, PASS, EMAIL, CONTACT, NOOFPEOPLE, OCCUPATION, APTTYPE, PREFERENCES, NEEDFROM) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

				PreparedStatement preparedStatement = c
						.prepareStatement(insertTableSQL);
				preparedStatement.setInt(1, 2);
				preparedStatement.setString(2, username);
				preparedStatement.setString(3, password);
				preparedStatement.setString(4, email);
				preparedStatement.setString(5, contact);
				preparedStatement.setInt(6, people);
				preparedStatement.setInt(7,
						Occupation.getValue(occupation.name()));
				preparedStatement.setString(8, apartmentType);
				preparedStatement.setString(9, preferences);
				preparedStatement.setTimestamp(10,
						new java.sql.Timestamp((needFrom).getTime()));

				a = preparedStatement.executeUpdate();
			}
			else
			{
				Exception e = new Exception("User Already Exist.");
				throw e;
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return a;
	}

	public static int setApartment(int aptNo, String type, String facilities,
			int people, int rent, int deposit) throws Exception
	{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			// Check if Apartment Exist
			boolean AptExist = false;
			Statement stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT APARTMENTNO FROM APARTMENTDETAILS;");

			while (rs.next())
			{
				if (rs.getInt("APARTMENTNO") == aptNo)
				{
					AptExist = true;
					break;
				}
			}

			if (!AptExist)
			{
				// Insert User
				String insertTableSQL = "INSERT INTO APARTMENTDETAILS(APARTMENTNO, APTTYPE, FACILITIES, MAXPEOPLE, RENT, DEPOSIT, VACANT, UPDATEDONE) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

				PreparedStatement preparedStatement = c
						.prepareStatement(insertTableSQL);
				preparedStatement.setInt(1, aptNo);
				preparedStatement.setString(2, type);
				preparedStatement.setString(3, facilities);
				preparedStatement.setInt(4, people);
				preparedStatement.setInt(5, rent);
				preparedStatement.setInt(6, deposit);
				preparedStatement.setBoolean(7, true);
				preparedStatement.setTimestamp(8,
						new java.sql.Timestamp(new java.util.Date().getTime()));

				a = preparedStatement.executeUpdate();
			}
			else
			{
				// Apartment Exist
				Exception e = new Exception("Apartment already exist.");
				throw e;
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return a;
	}

	public static int setAppointment(int userID, int aptID) throws Exception
	{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			String insertTableSQL = "INSERT INTO APPOINTMENTDETAILS(APTID, USERID, STATUS, APPOINTCREATED) VALUES(?, ?, ?, ?);";

			PreparedStatement preparedStatement = c
					.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, aptID);
			preparedStatement.setInt(2, userID);
			preparedStatement.setInt(3,
					Status.getValue(Status.Requested.name()));
			preparedStatement.setTimestamp(4,
					new java.sql.Timestamp(new java.util.Date().getTime()));

			preparedStatement.execute();
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}
		return a;
	}

	public static int setRentDetails(int appointmentId,
			java.util.Date leaseTermination, String leaseHolder, String iDProof,
			int rent, int deposit) throws Exception
	{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			// Insert Rent Out Details
			String insertTableSQL = "INSERT INTO RENTDETAILS(APPOINTID, USERID, APTID, LEASETERMINATION, LEASEHOLDERNAME, IDPROOF, RENTRENT, RENTDEPOSIT)"
					+ "SELECT ID, USERID, APTID, ?, ?, ?, ?, ? FROM APPOINTMENTDETAILS WHERE ID=?;";

			PreparedStatement preparedStatement = c
					.prepareStatement(insertTableSQL);

			preparedStatement.setTimestamp(1,
					new java.sql.Timestamp(leaseTermination.getTime()));
			preparedStatement.setString(2, leaseHolder);
			preparedStatement.setString(3, iDProof);
			preparedStatement.setInt(4, rent);
			preparedStatement.setInt(5, deposit);
			preparedStatement.setInt(6, appointmentId);

			a = preparedStatement.executeUpdate();

			String GetKey = ("SELECT APTID FROM APPOINTMENTDETAILS WHERE ID=?");
			PreparedStatement SP1 = c.prepareStatement(GetKey);

			SP1.setInt(1, appointmentId);
			int ApartID = 0;

			ResultSet rs1 = SP1.executeQuery();

			while (rs1.next())
			{
				ApartID = rs1.getInt("APTID");
			}

			String UpdateAppointment1 = "UPDATE APPOINTMENTDETAILS SET STATUS=? WHERE APTID=?;";

			PreparedStatement prepStat1 = c
					.prepareStatement(UpdateAppointment1);

			prepStat1.setInt(1, Status.getValue(Status.Rejected.name()));
			prepStat1.setInt(2, ApartID);

			a += prepStat1.executeUpdate();
			DataAccess.updateAppointmentStatus(Status.Allotted, null,
					appointmentId);
			DataAccess.changeVacancy(false, ApartID);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return a;
	}

	public static int setRentLog(int rentDetailID, int rentAmount, Months month,
			int year) throws Exception
	{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			// Insert Rent Out Details
			String insertTableSQL = "INSERT INTO RENTLOG(RENTID,USERID, APTID, APPOINTID, RENTAMOUNT, RENTMONTH, RENTYEAR, PAYDATE)"
					+ "SELECT ID, USERID, APTID, APPOINTID, ?, ?, ?, ? FROM RENTDETAILS WHERE ID=?;";

			PreparedStatement preparedStatement = c
					.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, rentAmount);
			preparedStatement.setInt(2, Months.getValue(month.name()));
			preparedStatement.setInt(3, year);
			preparedStatement.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
			preparedStatement.setInt(5, rentDetailID);

			a = preparedStatement.executeUpdate();
			
			String SQLstatement = "SELECT MAX(ID) AS ID from rentlog;";

			PreparedStatement stmt = c.prepareStatement(SQLstatement);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				a= rs.getInt("ID");
			}

		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return a;
	}

	public static int changeVacancy(boolean vacantValue, int apartmentID)
			throws Exception
	{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			// Insert User
			String updateTableSQL = "UPDATE APARTMENTDETAILS SET VACANT=?, UPDATEDONE=? WHERE ID=?";

			PreparedStatement preparedStatement = c
					.prepareStatement(updateTableSQL);
			preparedStatement.setBoolean(1, vacantValue);
			preparedStatement.setTimestamp(2,
					new java.sql.Timestamp(new java.util.Date().getTime()));
			preparedStatement.setInt(3, apartmentID);

			a = preparedStatement.executeUpdate();
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}
		return a;
	}

	public static int updateAppointmentStatus(Status status,
			java.util.Date date, int appointmentID) throws Exception
	{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			StringBuilder sbUpdateQuery = new StringBuilder(
					"UPDATE APPOINTMENTDETAILS SET STATUS=? ");

			// If status assigned is Declined.
			if (status.equals(Status.Denied))
			{
				// Change Status to Denied if clicked on link.
				String insertTableSQL = "INSERT INTO APPOINTMENTDECLINED(APPOINTID,DECLINEDDATE) SELECT ID, APPOINTMENTDATE FROM APPOINTMENTDETAILS WHERE ID=?;";

				PreparedStatement prepStat = c.prepareStatement(insertTableSQL);
				prepStat.setInt(1, appointmentID);

				a = prepStat.executeUpdate();
			}

			if (status.equals(Status.Waiting) || status.equals(Status.Denied))
			{
				sbUpdateQuery.append(", APPOINTMENTDATE=? ");
			}

			sbUpdateQuery.append("WHERE ID=?;");

			PreparedStatement preparedStatement = c
					.prepareStatement(sbUpdateQuery.toString());

			// SQL Parameters.
			preparedStatement.setInt(1, Status.getValue(status.name()));

			switch (status)
			{

			case Waiting:
				preparedStatement.setTimestamp(2,
						new java.sql.Timestamp(date.getTime()));
				preparedStatement.setInt(3, appointmentID);
				break;

			case Denied:
				preparedStatement.setTimestamp(2, null);
				preparedStatement.setInt(3, appointmentID);
				break;

			default:
				preparedStatement.setInt(2, appointmentID);
				break;
			}

			a += preparedStatement.executeUpdate();
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			try
			{
				SQLHelper.closeConnection(c);
			}
			catch (Exception e)
			{
				throw e;
			}
		}

		return a;
	}

}
