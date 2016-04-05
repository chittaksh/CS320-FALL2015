package midterm;

public class User
{
	private int id;
	private String name;
	
	public User(int id, String name)
	{
		super();
		this.setId(id);
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	
}
