/**
	Represent the menu in the restaurant
	Includes menu item list and promotional package list
	@author Nguyen Phan Huy
	@version 1.0
	@since 2014-11-13
*/

package Entity;

import java.util.ArrayList;


public class Menu {

	/**
	 * The list of menu items 
	 */
	private ArrayList<MenuItem> mMenuItemList;
	
	/**
	 * The list of promotional package
	 */
	private ArrayList<PromotionalPackage> mPromotionalPackageList;

	/**
	 * Initiate a menu by create 2 empty list of menu items and promotional packages
	 */
	public Menu() {
		mMenuItemList = new ArrayList<MenuItem>();
		mPromotionalPackageList = new ArrayList<PromotionalPackage>();
	}

	// MenuItem methods
	/**
	 * Get a menu item in the menu item list by id
	 * @param id the id of the item (as in the ArrayList)
	 * @return the reference to the menu item
	 */
	public MenuItem getMenuItemById(int id) {
		return mMenuItemList.get(id - 1);
	}

	/**
	 * Replace a menu item (specified by the id) with another menu item object
	 * @param id the id of the item to be replaced (as in the ArrayList)
	 * @param menuItem the reference to the replacing menu item
	 */
	public void replaceMenuItemById(int id, MenuItem menuItem) {
		// notify the packages that include this item
		(mMenuItemList.get(id -1)).notifyPromotionalPackage(menuItem);
		mMenuItemList.set(id - 1, menuItem);
	}

	/**
	 * Delete a menu item in the list
	 * @param id the id of the item to be replace (as in the ArrayList)
	 */
	public void deleteMenuItemById(int id) {
		// notify the packages that include this item
		(mMenuItemList.get(id -1)).notifyPromotionalPackage();
		mMenuItemList.remove(id - 1);
	}

	/**
	 * Add a menu item to the menu item list. The new menu item will be placed at the end of the list
	 * @param menuItem the reference to the new menu item to be added
	 */
	public void addMenuItem(MenuItem menuItem) {
		mMenuItemList.add(menuItem);
	}
	
	/**
	 * Get the id (as in the ArrayList) of a menu item using the reference to that item
	 * @param menuItem the reference to that menu item
	 * @return the id of that menu item (index number in the list of menu item)
	 */
	public int getMenuItemIndexByReference(MenuItem menuItem){
		return mMenuItemList.indexOf(menuItem) + 1;
	}

	// Promotional Package methods

	/**
	 * Get a promotional package in the package list using its id
	 * @param id the id of the promotional package (as in the ArrayList)
	 * @return the reference to that promotional packge
	 */
	public PromotionalPackage getPromotionalPackageById(int id) {
		return mPromotionalPackageList.get(id - 1);
	}

	/**
	 * Replace a promotional package in the list with another promotional package
	 * @param id the id of the promotional package to be replace
	 * @param promotionalPackage the reference to the replacing promotional package object
	 */
	public void replacePromotionalPackageById(int id, PromotionalPackage promotionalPackage) {
		// Detach the replaced promotional package from the menu items
		for (MenuItem menuItem : getPromotionalPackageById(id).getItemList()){
			menuItem.detach(promotionalPackage);
		}
		mPromotionalPackageList.set(id - 1, promotionalPackage);
		// Attach the new promotional package to the menu items
		for (MenuItem menuItem : promotionalPackage.getItemList()){
			menuItem.attach(promotionalPackage);
		}
	}

	/**
	 * Delete a promotional package by its id
	 * @param id the id of the promotional package to be deleted
	 */
	public void deletePromotionalPackageById(int id) {
		// detach the deleted promotional package from the menu items
		for (MenuItem menuItem : getPromotionalPackageById(id).getItemList()){
			menuItem.attach(getPromotionalPackageById(id));
		}
		mPromotionalPackageList.remove(id - 1);
	}

	/**
	 * add a promotional package to the list
	 * @param promotionalPackage the reference to the promotional package to be added
	 */
	public void addPromotionalPackage(PromotionalPackage promotionalPackage) {
		mPromotionalPackageList.add(promotionalPackage);
		// attach the new promotional package to menu items
		for (MenuItem menuItem : promotionalPackage.getItemList()){
			menuItem.attach(promotionalPackage);
		}
	}
	
	/**
	 * Get a promotional package id using its reference
	 * @param promotionalPackage the reference to the promotional package
	 * @return the id of the promotional package (as in the ArrayList)
	 */
	public int getPromotionalPackageIndexByReference(PromotionalPackage promotionalPackage){
		return mPromotionalPackageList.indexOf(promotionalPackage) + 1;
	}

	/**
	 * Print the menu
	 * @param detail the boolean parameter deciding whether to print a detail menu or a short one
	 * @param printMenuItem the boolean parameter deciding whether to print the menu item list
	 * @param printPackage the boolean parameter deciding whether to print the promotional package list
	 */
	public void print(boolean detail, boolean printMenuItem,
			boolean printPackage) {

		System.out.println("################## MENU ##################");
		int i;

		// Print menu items
		if (printMenuItem) {
			i = 1;
			System.out.println("~~~~~~~~~ A la carte ~~~~~~~~~~");
			for (MenuItem item : mMenuItemList) {
				System.out.print(i + ". ");
				item.print(detail);
				System.out.println();
				i++;
			}
		}

		// Print food package
		if (printPackage) {
			i = 1;
			System.out.println("~~~~~~~~~ Promotional Package ~~~~~~~~~~");
			for (PromotionalPackage promotionalPackage : mPromotionalPackageList) {
				System.out.print(i + ". ");
				promotionalPackage.print(detail);
				i++;
				System.out.println();
			}
		}
		System.out.println("################## END ##################");
	}

	/**
	 * Get the reference to the list of menu item
	 * @return the reference to the list of menu item
	 */
	public ArrayList<MenuItem> getListMenuItems() {
		// TODO Auto-generated method stub
		return mMenuItemList;
		// return null;
	}
	
	/**
	 * Get the reference to the list of promotional package
	 * @return the reference to the list of promotional package
	 */
	public ArrayList<PromotionalPackage> getListPackages() {
		// TODO Auto-generated method stub
		return mPromotionalPackageList;
	}

	public MenuItem getMenuItemByName(String name) {
		// TODO Auto-generated method stub
		for(MenuItem item: mMenuItemList){
			if(item.getName().compareTo(name) == 0){
				return item;
			}
		}
		return null;
	}

	public PromotionalPackage getPromotionalPackageByName(String name) {
		// TODO Auto-generated method stub
		for(PromotionalPackage mPackage: mPromotionalPackageList){
			if(mPackage.getName().compareTo(name) == 0){
				return mPackage;
			}
		}
		return null;
	}
}
