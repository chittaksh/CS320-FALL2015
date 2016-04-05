package model;

import java.util.Date;

public class RentDetail
{
	private int id;
	
	private AppointmentDetail appointDetail;
	private UserDetail userDetail;
	private ApartmentDetail apartmentDetail;
	
	private Date leaseTermimnation;
	private String leaseHolderName;
	private String idProof;
	private int rent;
	private int deposite;
	
	public RentDetail(int id,AppointmentDetail appointDetail, UserDetail userDetail,
			ApartmentDetail apartmentDetail, Date leaseTermimnation,
			String leaseHolderName, String idProof, int rent, int deposite)
	{
		super();
		this.id = id;
		this.appointDetail = appointDetail;
		this.userDetail = userDetail;
		this.apartmentDetail = apartmentDetail;
		this.leaseTermimnation = leaseTermimnation;
		this.leaseHolderName = leaseHolderName;
		this.idProof = idProof;
		this.rent = rent;
		this.deposite = deposite;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public AppointmentDetail getAppointDetail()
	{
		return appointDetail;
	}

	public void setAppointDetail(AppointmentDetail appointDetail)
	{
		this.appointDetail = appointDetail;
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

	public Date getLeaseTermimnation()
	{
		return leaseTermimnation;
	}
	
	public void setLeaseTermimnation(Date leaseTermimnation)
	{
		this.leaseTermimnation = leaseTermimnation;
	}
	
	public String getLeaseHolderName()
	{
		return leaseHolderName;
	}
	
	public void setLeaseHolderName(String leaseHolderName)
	{
		this.leaseHolderName = leaseHolderName;
	}
	
	public String getIdProof()
	{
		return idProof;
	}
	
	public void setIdProof(String idProof)
	{
		this.idProof = idProof;
	}
	public int getRent()
	{
		return rent;
	}
	
	public void setRent(int rent)
	{
		this.rent = rent;
	}
	
	public int getDeposite()
	{
		return deposite;
	}
	
	public void setDeposite(int deposite)
	{
		this.deposite = deposite;
	}

}
