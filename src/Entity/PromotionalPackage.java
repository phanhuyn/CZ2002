package Entity;

import java.util.ArrayList;

import Data.WriteToTxt;

public class PromotionalPackage implements WriteToTxt {

	private String mName;
	private double mPrice;
	private String mDescription;
	private ArrayList<MenuItem> mItemList;
	private Menu mMenu;

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
			for (MenuItem item : getItemList()) {
				System.out.print(item.getName() + ", ");
				price += item.getPrice();
			}
			System.out.print("Save "
					+ String.format("%.2f", (price - getPrice())) + " dollar!");
		}
		System.out.println(toString());
	}

	public void update(MenuItem menuItem){
		mItemList.remove(menuItem);
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
	
	public void setMenu(Menu menu){
		mMenu = menu;
	}

	@Override
	public String toString() {
		String itemList;
		itemList = "";
		for (int i = 0; i < mItemList.size(); i++){
			if (i < mItemList.size()){
				itemList += ",";
			}
		}
		return (mName + "|" + mPrice + "|" + mDescription + "|" + itemList);
	}

}
