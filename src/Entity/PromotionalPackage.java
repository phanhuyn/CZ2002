/**
 	Represent a promotional package in the menu of the restaurant
	@author Nguyen Phan Huy
	@version 1.0
	@since 2014-11-13
 */

package Entity;

import java.util.ArrayList;

import Data.WriteToTxt;

public class PromotionalPackage implements WriteToTxt {

	/**
	 * The name of the promotional package
	 */
	private String mName;
	
	/**
	 * The price of the promotional package
	 */
	private double mPrice;
	
	/**
	 * The description of the promotional package
	 */
	private String mDescription;
	
	/**
	 * The list of menu items that the promotional package contains
	 */
	private ArrayList<MenuItem> mItemList;
	
	/**
	 * The reference to the menu containing this promotional package
	 */
	private static Menu mMenu;

	/**
	 * Create a promotional package with name, price, description and a list of menu item
	 * @param name the name of the promotional package
	 * @param price the price of the promotional package
	 * @param description the description of the promotional package
	 * @param itemList the list of the menu item in the promotional package
	 */
	public PromotionalPackage(String name, double price, String description,
			ArrayList<MenuItem> itemList) {
		mName = name;
		mPrice = price;
		mDescription = description;
		mItemList = itemList;
	}

	/**
	 * Print the detail of the promotional package
	 * @param detail whether to print all the detail of the promotional package
	 * detail == true: print name, price, price, description and all the menu item it contains
	 * detail == false: print the name of the promotional package only
	 */
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

	/**
	 * Receive the update from a menu item which the promotional package contains
	 * This method is called when a menu item (which the promotional package contains) is deleted
	 * to remove that menu item from its containing list
	 * @param menuItem the reference to the menu item sending the notify
	 */
	public void update(MenuItem menuItem){
		mItemList.remove(menuItem);
		double price = 0;
		for (MenuItem item : mItemList) {
			price += item.getPrice();
		}
		if (price < mPrice){
			mPrice = price;
		}
	}
	
	/**
	 * Receive the update from a menu item which the promotional contains
	 * This method is called when a menu item (which the promotional package contains) is replaced with another menu item
	 * To replace the old menu item with the new one
	 * @param menuItem the menu item to be replaced
	 * @param newItem the new menu item
	 */
	public void update(MenuItem menuItem, MenuItem newItem){
		mItemList.remove(menuItem);
		mItemList.add(newItem);
		double price = 0;
		for (MenuItem item : mItemList) {
			price += item.getPrice();
		}
		if (price < mPrice){
			mPrice = price;
		}
	}
	
	/**
	 * Get the name of the promotional package
	 * @return the name of the promotional package
	 */
	public String getName() {
		return mName;
	}

	/**
	 * Get the price of the promotional package
	 * @return the price of the promotional package
	 */
	public double getPrice() {
		return mPrice;
	}

	/**
	 * Get the description of the promotional package
	 * @return the description of the promotional package
	 */
	public String getDescription() {
		return mDescription;
	}

	/**
	 * Get the list of menu items that the promotional package contains
	 * @return the list of the menu items that the promotional package contains
	 */
	public ArrayList<MenuItem> getItemList() {
		return mItemList;
	}
	
	/**
	 * Set the menu containing this promotional package
	 * @param menu the menu containing this promotional package
	 */
	public static void setMenu(Menu menu){
		mMenu = menu;
	}

	/**
	 * Format of the string: id|name|price|description|<list of menu items>
	 * Format of the list of menu items: idOfItem1, idOfItem2, idOfITem3
	 * @return a String containing the information of this promotional package
	 */
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
