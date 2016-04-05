package model;

public enum Status
{
	Requested(1), Waiting(2), Accepted(3), Denied(4), Rejected(5),  Allotted(6);
	private int Value;

	private Status(int value)
	{
		this.Value = value;
	}
	
	public static Status getStatus(int value){
		Status temp = null;
		for(Status role: Status.values()){
			if(role.Value==value){
				temp = role;
				break;
			}
		}
		return temp;
	}
	
	public static int getValue(String value){
		Status temp = null;
		for(Status role: Status.values()){
			if(role.name().equals(value)){
				temp = role;
			break;
			}
		}
		return temp.Value;
	}
}
