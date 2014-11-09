package Entity;

import java.util.ArrayList;

import Data.WriteToTxt;

public class MenuItem implements WriteToTxt {
	private String mName;
	private String mType;
	private double mPrice;
	private String mDescription;
	private ArrayList<PromotionalPackage> mContainingPackageList;
	private Object mContainingPromotionalPackage;
	
	public MenuItem(){}

	public MenuItem(String name, String type, double price, String description) {
		mName = name;
		mType = type;
		mPrice = price;
		mDescription = description;
		ArrayList<PromotionalPackage> mContainingPackageList = new ArrayList<PromotionalPackage>();
	}
	
	public void print(boolean detail){
		System.out.print(getName());
		if (detail) {
			System.out.print(" (" + getType() + ")" + " : "
					+ getPrice());
			System.out.print(" - " + getDescription());
		}
		System.out.println();
		System.out.println(toString());
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
	public String toString() {
		return (mName + "|" + mType + "|" + mPrice + "|" + mDescription);
	}
	
	public void notifyPromotionalPackage(){
		for (PromotionalPackage promotionalPackage : mContainingPackageList){
			promotionalPackage.update(this);
		}
	}
	
	public void attach (PromotionalPackage promotionalPackage){
		mContainingPackageList.add(promotionalPackage);
	}

	public void detach (PromotionalPackage promotionalPackage){
		mContainingPackageList.remove(promotionalPackage);
	}
}
