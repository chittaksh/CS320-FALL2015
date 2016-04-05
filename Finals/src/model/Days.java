package model;

public class Days
{
	private int id;
	private String dayName;
	
	public Days(int id, String dayName)
	{
		super();
		this.id = id;
		this.dayName = dayName;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getDayName()
	{
		return dayName;
	}

	public void setDayName(String dayName)
	{
		this.dayName = dayName;
	}
	
}
