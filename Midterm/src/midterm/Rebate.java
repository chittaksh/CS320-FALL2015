package midterm;

import java.util.Date;

public class Rebate
{
	private int id;
	private User user;
	private String name;
	private int amount;
	private Date received;
	
	public Rebate(int id, User user, String name, int amount)
	{
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.amount = amount;
		this.received = null;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public Date getReceived()
	{
		return received;
	}

	public void setReceived(Date received)
	{
		this.received = received;
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
