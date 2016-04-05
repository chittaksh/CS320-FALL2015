package model;

public class Meeting
{
	private int id;
	private int dayID;
	private int timeID;
	private String notes;
	
	public Meeting(int id, int dayID, int timeID, String notes)
	{
		super();
		this.id = id;
		this.dayID = dayID;
		this.timeID = timeID;
		this.notes = notes;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getDayID()
	{
		return dayID;
	}

	public void setDayID(int dayID)
	{
		this.dayID = dayID;
	}

	public int getTimeID()
	{
		return timeID;
	}

	public void setTimeID(int timeID)
	{
		this.timeID = timeID;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}
}
