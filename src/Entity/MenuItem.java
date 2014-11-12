/**
 	Represent a menu item in the menu of the restaurant
	@author Nguyen Phan Huy
	@version 1.0
	@since 2014-11-13
 */

package Entity;

import java.util.ArrayList;

import Data.WriteToTxt;

public class MenuItem implements WriteToTxt {
	/**
	 * The name of the menu item
	 */
	private String mName;
	
	/**
	 * The type of the menu item
	 */
	private String mType;
	
	/**
	 * The price of the menu item
	 */
	private double mPrice;
	
	/**
	 * The description of the menu item
	 */
	private String mDescription;
	
	/**
	 * The menu containing the menu item
	 */
	private static Menu mMenu;
	
	/**
	 * The list of Promotional package containing this menu item
	 */
	private ArrayList<PromotionalPackage> mContainingPackageList;

	/**
	 * Create a new menu item with name, type, price and description
	 * @param name the name of this menu item
	 * @param type the type of this menu item
	 * @param price the price of this menu item
	 * @param description the description of this menu item
	 */
	public MenuItem(String name, String type, double price, String description) {
		mName = name;
		mType = type;
		mPrice = price;
		mDescription = description;
		mContainingPackageList = new ArrayList<PromotionalPackage>();
	}
	
	/**
	 * Set the menu containing this menu item
	 * @param menu the menu containing this menu item
	 */
	public static void setMenu (Menu menu){
		mMenu = menu;
	}
	
	/**
	 * Print the detail of this menu item
	 * @param detail whether to print all the detail of this menu item
	 * detail == true: print name, price, type, description
	 * detail == false: print only name
	 */
	public void print(boolean detail){
		System.out.print(getName());
		if (detail) {
			System.out.print(" (" + getType() + ")" + " : "
					+ getPrice());
			System.out.print(" - " + getDescription());
		}
	}
	
	/**
	 * Get the name of the menu item
	 * @return the name of the menu item
	 */
	public String getName() {
		return mName;
	}

	/**
	 * Get the price of the menu item
	 * @return the price of the menu item
	 */
	public double getPrice() {
		return mPrice;
	}
	
	/**
	 * Get the type of the menu item
	 * @return the type of the menu item
	 */
	public String getType(){
		return mType;
	}
	
	/**
	 * Get the description of the menu item
	 * @return the description of the menu item
	 */
	public String getDescription(){
		return mDescription;
	}

	/**
	 * Format of the string: id|name|type|price|description
	 * @return a String containing the information of this menu item
	 */
	@Override
	public String toString() {
		return (mMenu.getMenuItemIndexByReference(this)+"|" + mName + "|" + mType + "|" + mPrice + "|" + mDescription);
	}
	
	/**
	 * Notify all the promotional package containing this menu item
	 * To be used when the menu item is deleted
	 */
	public void notifyPromotionalPackage(){
		for (PromotionalPackage promotionalPackage : mContainingPackageList){
			promotionalPackage.update(this);
		}
	}
	
	/**
	 * Notify all the promotional package containing this menu item
	 * To be used when the menu item is replace with another menu item
	 * @param menuItem the replacing menu item
	 */
	public void notifyPromotionalPackage(MenuItem menuItem){
		for (PromotionalPackage promotionalPackage : mContainingPackageList){
			promotionalPackage.update(this, menuItem);
		}
	}
	
	/**
	 * Attach a promotional package to this menu item
	 * The attached promotional package will receive the updates from this menu item
	 * @param promotionalPackage the reference to the promotional package to be attached
	 */
	public void attach (PromotionalPackage promotionalPackage){
		mContainingPackageList.add(promotionalPackage);
	}

	/**
	 * Detach a promotional package from this menu item
	 * The detached promotional package will no longer receive updates from this menu item
	 * @param promotionalPackage the reference to the promotional package to be detached
	 */
	public void detach (PromotionalPackage promotionalPackage){
		mContainingPackageList.remove(promotionalPackage);
	}
}
