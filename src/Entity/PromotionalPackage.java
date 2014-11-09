package Entity;

import java.util.ArrayList;

import Data.WriteToTxt;

public class PromotionalPackage implements WriteToTxt {

	private String mName;
	private double mPrice;
	private String mDescription;
	private ArrayList<MenuItem> mItemList;

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

	@Override
	public String toText() {
		// TODO Auto-generated method stub
		return null;
	}

}
