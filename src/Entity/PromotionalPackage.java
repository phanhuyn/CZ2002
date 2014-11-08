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
