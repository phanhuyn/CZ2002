package Entity;

import java.util.ArrayList;

import Data.WriteToTxt;

public class MenuItem implements WriteToTxt {
	private String mName;
	private String mType;
	private double mPrice;
	private String mDescription;
	private static Menu mMenu;
	private ArrayList<PromotionalPackage> mContainingPackageList;
	
	public MenuItem(){}

	public MenuItem(String name, String type, double price, String description) {
		mName = name;
		mType = type;
		mPrice = price;
		mDescription = description;
		mContainingPackageList = new ArrayList<PromotionalPackage>();
	}
	
	public static void setMenu (Menu menu){
		mMenu = menu;
	}
	
	public void print(boolean detail){
		System.out.print(getName());
		if (detail) {
			System.out.print(" (" + getType() + ")" + " : "
					+ getPrice());
			System.out.print(" - " + getDescription());
		}
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
		
		return (mMenu.getMenuItemIndexByReference(this)+"|" + mName + "|" + mType + "|" + mPrice + "|" + mDescription);
	}
	
	public void notifyPromotionalPackage(){
		for (PromotionalPackage promotionalPackage : mContainingPackageList){
			promotionalPackage.update(this);
		}
	}
	
	public void notifyPromotionalPackage(MenuItem menuItem){
		for (PromotionalPackage promotionalPackage : mContainingPackageList){
			promotionalPackage.update(this, menuItem);
		}
	}
	
	public void attach (PromotionalPackage promotionalPackage){
		mContainingPackageList.add(promotionalPackage);
	}

	public void detach (PromotionalPackage promotionalPackage){
		mContainingPackageList.remove(promotionalPackage);
	}
}
