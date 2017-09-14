package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Statement;

import helper.SQLHelper;
import helper.connectionObject;

public class DataLink
{
	private static ArrayList<Days> days;
	private static ArrayList<TimeSlot> timeSlots;
	private static ArrayList<Meeting> meetings;

	// Connection Object
	static connectionObject connObj = new connectionObject(
			 "jdbc:mysql://localhost/cs320stu115", "cs320", "demosite");
	static Connection c = null;

	public static ArrayList<Days> getDays() throws Exception
	{
		days = new ArrayList<Days>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			Statement stmt = (Statement) c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM DAYS;");

			while (rs.next())
			{
				Days day = new Days(rs.getInt("ID"), rs.getString("DAYNAME"));
				days.add(day);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SQLHelper.closeConnection(c);
		}
		return days;
	}

	public static ArrayList<TimeSlot> getTimeSlots() throws Exception
	{
		timeSlots = new ArrayList<TimeSlot>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			Statement stmt = (Statement) c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM TIMESLOT;");

			while (rs.next())
			{
				TimeSlot time = new TimeSlot(rs.getInt("ID"),
						rs.getString("TIMEVALUE"));
				timeSlots.add(time);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SQLHelper.closeConnection(c);
		}
		return timeSlots;
	}

	public static ArrayList<Meeting> getMeetings() throws Exception
	{
		meetings = new ArrayList<Meeting>();

		try
		{
			c = SQLHelper.getConnection(connObj);

			Statement stmt = (Statement) c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM MEETINGS;");

			while (rs.next())
			{
				Meeting meet = new Meeting(rs.getInt("ID"), rs.getInt("DAYID"),
						rs.getInt("TIMESLOTID"), rs.getString("NOTES"));
				meetings.add(meet);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SQLHelper.closeConnection(c);
		}
		return meetings;
	}

	public static int insertTimeSlot(String time) throws Exception
	{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			String strSQl = "INSERT INTO TIMESLOT(TIMEVALUE) VALUES(?);";

			PreparedStatement stmt = c.prepareStatement(strSQl);

			stmt.setString(1, time);

			a = stmt.executeUpdate();

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SQLHelper.closeConnection(c);
		}

		return a;
	}

	public static int insertMeeting(int dayID, int timeID, String note)
			throws Exception
	{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			String strSQl = "INSERT INTO MEETINGS(DAYID, TIMESLOTID, NOTES) VALUES(?,?,?);";

			PreparedStatement stmt = c.prepareStatement(strSQl);

			stmt.setInt(1, dayID);
			stmt.setInt(2, timeID);
			stmt.setString(3, note);

			a = stmt.executeUpdate();

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SQLHelper.closeConnection(c);
		}

		return a;
	}

	public static int updateMeeting(int id, int dayID, int timeID, String note)
			throws Exception
	{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			String strSQl = "UPDATE MEETINGS SET DAYID=?, TIMESLOTID=?, NOTES=? WHERE ID=?;";

			PreparedStatement stmt = c.prepareStatement(strSQl);

			stmt.setInt(1, dayID);
			stmt.setInt(2, timeID);
			stmt.setString(3, note);
			stmt.setInt(4, id);

			a = stmt.executeUpdate();

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SQLHelper.closeConnection(c);
		}

		return a;
	}

	public static int deleteMeeting(int id) throws Exception{
		int a = 0;

		try
		{
			c = SQLHelper.getConnection(connObj);

			String strSQl = "DELETE FROM MEETINGS WHERE ID=?";

			PreparedStatement stmt = c.prepareStatement(strSQl);

			stmt.setInt(1, id);

			a = stmt.executeUpdate();

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SQLHelper.closeConnection(c);
		}

		return a;
	}
}
