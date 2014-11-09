package Entity;

import Data.WriteToTxt;

public class MenuItem implements WriteToTxt {
	private String mName;
	private String mType;
	private double mPrice;
	private String mDescription;
	
	public MenuItem(){}

	public MenuItem(String name, String type, double price, String description) {
		mName = name;
		mType = type;
		mPrice = price;
		mDescription = description;
	}
	
	public void print(boolean detail){
		System.out.print(getName());
		if (detail) {
			System.out.print(" (" + getType() + ")" + " : "
					+ getPrice());
			System.out.print(" - " + getDescription());
		}
		System.out.println();
	}
	
	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public double getPrice() {
		return mPrice;
	}

	public void setPrice(double price) {
		mPrice = price;
	}
	
	public String getType(){
		return mType;
	}
	
	public String getDescription(){
		return mDescription;
	}

	@Override
	public String toText() {
		// TODO Auto-generated method stub
		return null;
	}

}
