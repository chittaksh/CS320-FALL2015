package model;

import java.util.ArrayList;
import java.util.Date;

public class AppointmentDetail
{
	private int appointmentID;
	private ApartmentDetail apartmentDetail;
	private UserDetail userDetail;
	private Status appointmentStatus;
	private Date appointmentDate;
	private Date appointCreated;
	private ArrayList<Date> ruledOutDates = new ArrayList<Date>();

	public AppointmentDetail(int appointmentID, ApartmentDetail apartmentDetail,
			UserDetail userDetail, Status appointmentStatus,
			Date appointmentDate, Date appointCreated)
	{
		super();
		this.appointmentID = appointmentID;
		this.apartmentDetail = apartmentDetail;
		this.userDetail = userDetail;
		this.appointmentStatus = appointmentStatus;
		this.appointmentDate = appointmentDate;
		this.appointCreated = appointCreated;
	}

	public int getAppointmentID()
	{
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID)
	{
		this.appointmentID = appointmentID;
	}

	public ApartmentDetail getApartmentDetail()
	{
		return apartmentDetail;
	}

	public void setApartmentDetail(ApartmentDetail apartmentDetail)
	{
		this.apartmentDetail = apartmentDetail;
	}

	public UserDetail getUserDetail()
	{
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail)
	{
		this.userDetail = userDetail;
	}

	public Status getAppointmentStatus()
	{
		return appointmentStatus;
	}

	public void setAppointmentStatus(Status appointmentStatus)
	{
		this.appointmentStatus = appointmentStatus;
	}

	public Date getAppointmentDate()
	{
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate)
	{
		this.appointmentDate = appointmentDate;
	}

	public ArrayList<Date> getRuledOutDates()
	{
		return ruledOutDates;
	}

	public void setRuledOutDates(Date ruledOutDates)
	{
		this.ruledOutDates.add(ruledOutDates);
	}

	public Date getAppointCreated()
	{
		return appointCreated;
	}

	public void setAppointCreated(Date appointCreated)
	{
		this.appointCreated = appointCreated;
	}
}
