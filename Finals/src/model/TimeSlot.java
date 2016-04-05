package model;

public class TimeSlot
{
	private int id;
	private String timeSlot;
	
	public TimeSlot(int id, String timeSlot)
	{
		super();
		this.id = id;
		this.timeSlot = timeSlot;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTimeSlot()
	{
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot)
	{
		this.timeSlot = timeSlot;
	}
}
