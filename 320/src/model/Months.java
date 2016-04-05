package model;

public enum Months
{
	Jan(1), Feb(2), Mar(3), Apr(4), May(5), Jun(6), July(7), Aug(8), Sept(9), Oct(10), Nov(11), Dec(12);
	private int Value;

	private Months(int value)
	{
		this.Value = value;
	}
	
	public static Months getMonths(int value){
		Months temp = null;
		for(Months month: Months.values()){
			if(month.Value==value){
				temp = month;
				break;
			}
		}
		return temp;
	}
	
	public static int getValue(String value){
		Months temp = null;
		for(Months month: Months.values()){
			if(month.name().equals(value)){
				temp = month;
			break;
			}
		}
		return temp.Value;
	}
}
