package model;

import java.sql.Date;

public class RentLog
{
	private int id;
	private UserDetail userDetail;
	private ApartmentDetail apartmentDetail;
	private AppointmentDetail appointDetail;
	private RentDetail rentDetail;
	
	private int rentPaid;
	private Months rentMonth;
	private int rentYear;
	private Date payDate;
	
	public RentLog(int id, UserDetail userDetail, ApartmentDetail apartmentDetail,
			AppointmentDetail appointDetail, RentDetail rentDetail,
			int rentPaid, Months rentMonth, int rentYear, Date payDate)
	{
		super();
		this.setId(id);
		this.userDetail = userDetail;
		this.apartmentDetail = apartmentDetail;
		this.appointDetail = appointDetail;
		this.rentDetail = rentDetail;
		this.rentPaid = rentPaid;
		this.rentMonth = rentMonth;
		this.rentYear = rentYear;
		this.payDate = payDate;
	}

	public UserDetail getUserDetail()
	{
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail)
	{
		this.userDetail = userDetail;
	}

	public ApartmentDetail getApartmentDetail()
	{
		return apartmentDetail;
	}

	public void setApartmentDetail(ApartmentDetail apartmentDetail)
	{
		this.apartmentDetail = apartmentDetail;
	}

	public AppointmentDetail getAppointDetail()
	{
		return appointDetail;
	}

	public void setAppointDetail(AppointmentDetail appointDetail)
	{
		this.appointDetail = appointDetail;
	}

	public RentDetail getRentDetail()
	{
		return rentDetail;
	}

	public void setRentDetail(RentDetail rentDetail)
	{
		this.rentDetail = rentDetail;
	}

	public int getRentPaid()
	{
		return rentPaid;
	}

	public void setRentPaid(int rentPaid)
	{
		this.rentPaid = rentPaid;
	}

	public Months getRentMonth()
	{
		return rentMonth;
	}

	public void setRentMonth(Months rentMonth)
	{
		this.rentMonth = rentMonth;
	}

	public int getRentYear()
	{
		return rentYear;
	}

	public void setRentYear(int year)
	{
		this.rentYear = year;
	}

	public Date getPayDate()
	{
		return payDate;
	}

	public void setPayDate(Date payDate)
	{
		this.payDate = payDate;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
