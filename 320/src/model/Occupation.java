package model;

public enum Occupation
{
	Student(1), Family(2), Business(3);
	private int Value;

	private Occupation(int value)
	{
		this.Value = value;
	}
	
	public static Occupation getOccupation(int value){
		Occupation temp = null;
		for(Occupation role: Occupation.values()){
			if(role.Value==value){
				temp = role;
				break;
			}
		}
		return temp;
	}
	
	public static int getValue(String value){
		Occupation temp = null;
		for(Occupation role: Occupation.values()){
			if(role.name().equals(value)){
				temp = role;
			break;
			}
		}
		return temp.Value;
	}
}
