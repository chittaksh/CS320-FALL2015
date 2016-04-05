package model;

import java.util.Date;

public class UserDetail
{

	// Properties
	private int id;
	private Roles role;
	private String username;
	private String password;
	private String eMail;
	private String contact;
	private int noOfPeople;
	private Occupation occupation;
	private String aptType;
	private String preferences;
	private Date needFrom;

	// Default Constructor
	public UserDetail()
	{
		
	}

	// Custom constructor for Manager
	public UserDetail(int id, Roles role, String username, String password,
			String eMail)
	{
		this.id = id;
		this.role = role;
		this.username = username;
		this.password = password;
		this.eMail = eMail;
	}

	// Custom Constructor for Users.
	public UserDetail(int id, Roles role, String username, String password,
			String email, String contact, int noofpeople, Occupation occupation,
			String apttype, String preferences, Date needfrom)
	{
		this.id = id;
		this.setRole(role);
		this.username = username;
		this.password = password;
		this.eMail = email;
		this.contact = contact;
		this.noOfPeople = noofpeople;
		this.occupation = occupation;
		this.aptType = apttype;
		this.preferences = preferences;
		this.needFrom = needfrom;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Roles getRole()
	{
		return role;
	}

	public void setRole(Roles role)
	{
		this.role = role;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String geteMail()
	{
		return eMail;
	}

	public void seteMail(String eMail)
	{
		this.eMail = eMail;
	}

	public String getContact()
	{
		return contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}

	public int getNoOfPeople()
	{
		return noOfPeople;
	}

	public void setNoOfPeople(int noOfPeople)
	{
		this.noOfPeople = noOfPeople;
	}

	public Occupation getOccupation()
	{
		return occupation;
	}

	public void setOccupation(Occupation occupation)
	{
		this.occupation = occupation;
	}

	

	public String getAptType()
	{
		return aptType;
	}

	public void setAptType(String aptType)
	{
		this.aptType = aptType;
	}

	public String getPreferences()
	{
		return preferences;
	}

	public void setPreferences(String preferences)
	{
		this.preferences = preferences;
	}

	public Date getNeedFrom()
	{
		return needFrom;
	}

	public void setNeedFrom(Date needFrom)
	{
		this.needFrom = needFrom;
	}
}
