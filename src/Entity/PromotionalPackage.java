package Entity;

import java.util.ArrayList;

import Data.WriteToTxt;

public class PromotionalPackage implements WriteToTxt {

	private String mName;
	private double mPrice;
	private String mDescription;
	private ArrayList<MenuItem> mItemList;
	private static Menu mMenu;

	public PromotionalPackage(String name, double price, String description,
			ArrayList<MenuItem> itemList) {
		mName = name;
		mPrice = price;
		mDescription = description;
		mItemList = itemList;
	}

	public void print(boolean detail) {
		System.out.print(getName());
		if (detail) {
			System.out.println(" : " + getPrice());
			System.out.print("Items included: ");
			double price = 0;
			for (MenuItem item : mItemList) {
				System.out.print(item.getName() + ", ");
				price += item.getPrice();
			}
			System.out.println("Save "
					+ String.format("%.2f", (price - getPrice())) + " dollar!");
		}
	}

	public void update(MenuItem menuItem){
		mItemList.remove(menuItem);
		double price = 0;
		for (MenuItem item : mItemList) {
			//System.out.print(item.getName() + ", ");
			price += item.getPrice();
		}
		if (price < mPrice){
			mPrice = price;
		}
	}
	
	public void update(MenuItem menuItem, MenuItem newItem){
		mItemList.remove(menuItem);
		mItemList.add(newItem);
		double price = 0;
		for (MenuItem item : mItemList) {
			System.out.print(item.getName() + ", ");
			price += item.getPrice();
		}
		if (price < mPrice){
			mPrice = price;
		}
	}
	
	public String getName() {
		return mName;
	}

	public double getPrice() {
		return mPrice;
	}

	public String getDescription() {
		return mDescription;
	}

	public ArrayList<MenuItem> getItemList() {
		return mItemList;
	}
	
	public static void setMenu(Menu menu){
		mMenu = menu;
	}

	@Override
	public String toString() {
		String itemList;
		itemList = "";
		for (int i = 0; i < mItemList.size(); i++){
			itemList += mMenu.getMenuItemIndexByReference(mItemList.get(i));
			if (i < (mItemList.size() -1)){
				itemList += ",";
			}
		}
		return (mMenu.getPromotionalPackageIndexByReference(this) + "|" + mName + "|" + mPrice + "|" + mDescription + "|" + itemList);
	}

}
