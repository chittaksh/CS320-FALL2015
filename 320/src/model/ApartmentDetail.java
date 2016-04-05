package model;

import java.util.Date;

public class ApartmentDetail
{
	private int apartmentId;
	private int apartmentNo;
	private String aptType;
	private String facilities;
	private int maxPeople;
	private int rent;
	private int deposit;
	private boolean vacant;
	private Date updateDone;

	public ApartmentDetail(int apartmentId, int apartmentNo, String aptType,
			String facilities, int maxPeople, int rent, int deposit,
			boolean vacant,Date updateDone)
	{
		this.apartmentId = apartmentId;
		this.apartmentNo = apartmentNo;
		this.aptType = aptType;
		this.facilities = facilities;
		this.maxPeople = maxPeople;
		this.rent = rent;
		this.deposit = deposit;
		this.vacant = vacant;
		this.updateDone = updateDone;
	}

	public int getApartmentId()
	{
		return apartmentId;
	}

	public void setApartmentId(int apartmentId)
	{
		this.apartmentId = apartmentId;
	}

	public int getApartmentNo()
	{
		return this.apartmentNo;
	}

	public void setApartmentNo(int apartmentNo)
	{
		this.apartmentNo = apartmentNo;
	}

	public String getAptType()
	{
		return aptType;
	}

	public void setAptType(String aptType)
	{
		this.aptType = aptType;
	}

	public String getFacilities()
	{
		return this.facilities;
	}

	public void setFacilities(String facilities)
	{
		this.facilities = facilities;
	}

	public int getMaxPeople()
	{
		return this.maxPeople;
	}

	public void setMaxPeople(int maxPeople)
	{
		this.maxPeople = maxPeople;
	}

	public int getRent()
	{
		return this.rent;
	}

	public void setRent(int rent)
	{
		this.rent = rent;
	}

	public int getDeposit()
	{
		return this.deposit;
	}

	public void setDeposit(int deposit)
	{
		this.deposit = deposit;
	}

	public boolean isVacant()
	{
		return this.vacant;
	}

	public void setVacant(boolean vacant)
	{
		this.vacant = vacant;
	}

	public Date getUpdateDone()
	{
		return updateDone;
	}

	public void setUpdateDone(Date updateDone)
	{
		this.updateDone = updateDone;
	}
}
