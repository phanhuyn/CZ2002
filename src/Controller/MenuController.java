/**
	The controller of the functions related to menu
	This controller handling the updating of the menu, including add/remove/update menu item and promotional package
	@author Nguyen Phan Huy
	@version 1.0
	@since 2014-11-13
*/

package Controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import UI.MainUI;
import Entity.Menu;
import Entity.MenuItem;
import Entity.PromotionalPackage;

public class MenuController {

	/**
	 * The menu which the controller will handle
	 */
	private Menu mMenu;

	/**
	 * Create the controller with the reference to the menu it controls
	 * @param menu the menu of the restaurant
	 */
	public MenuController(Menu menu) {
		mMenu = menu;
	}

	/**
	 * Called when the user choose menu option
	 * Ask for the user's choice of menu options and call the function's based on the choice
	 */
	public void run() {
		int choice = 0;
		while (choice <= 8) {
			printMenuOption();
			Scanner sc = new Scanner(System.in);
			choice = MainUI.getInt("Select your option: ");
			
			switch (choice) {
			case 1:
				createNewMenuItem();
				break;
			case 2:
				updateMenuItem();
				break;
			case 3:
				deleteMenuItem();
				break;
			case 4:
				createNewPromotionalPackage();
				break;
			case 5:
				updatePromotionalPackage();
				break;
			case 6:
				deletePromotionalPackage();
				break;
			case 7:
				mMenu.print(true, true, true);
				break;
			case 8:
				mMenu.print(false, true, true);
				break;
			}
		}
		return;
	}

	/**
	 * Create a new menu item in the menu
	 */
	public void createNewMenuItem() {
		
		// Ask for new item info
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the name of the new menu item: (Enter -1 to go back)");
		String name = sc.nextLine();
		try {
			if (Integer.parseInt(name) == -1){
				return;
			}
		}
		catch (Exception e){}
		
		System.out.println("Input the type of the new menu item: ");
		String type = sc.nextLine();

		double price = MainUI.getDouble("Input the price of the new menu item: ");

		System.out.println("Input the description of the new menu item: ");
		String description = sc.nextLine();

		//Add the new item
		mMenu.addMenuItem(new MenuItem(name, type, price, description));
		System.out.println("Menu item added!");
	}

	/**
	 * Update a menu item in the menu
	 */
	public void updateMenuItem() {
		mMenu.print(false, true, false);
		
		// Select item to edit
		int choice= -2;
		Scanner sc = new Scanner(System.in);
		choice = MainUI.getInt("Select the item to edit: (Enter -1 to go back)");
		while (choice < -1 || choice > mMenu.getListMenuItems().size() || choice == 0 ){
			System.out.println("Value out of range! Please try again!");
			choice = MainUI.getInt("Select the item to edit: (Enter -1 to go back)");
		}
		if (choice == -1 ){
			return;
		}
		MenuItem tempOldMenuItem = mMenu.getMenuItemById(choice);
		
		// Print the selected item and ask for new info
		tempOldMenuItem.print(true);
		System.out.println();
		System.out.println("Enter the new name: ");
		String name = sc.nextLine();
		System.out.println("Enter the new type: ");
		String type = sc.nextLine();
		double price = MainUI.getDouble("Enter the new price: ");
		System.out.println("Enter the new description: ");
		String description = sc.nextLine();

		MenuItem tempMenuItem = new MenuItem(name, type, price, description);

		// Replace the item
		mMenu.replaceMenuItemById(choice, tempMenuItem);
		System.out.println("Item edited.");
	}

	/**
	 * Delete a menu item in the menu
	 */
	public void deleteMenuItem() {

		mMenu.print(false, true, false);
		
		//Select item to delete
		int choice= -2;
		Scanner sc = new Scanner(System.in);
		choice = MainUI.getInt("Select the item to delete: (Enter -1 to go back)");
		while (choice < -1 || choice > mMenu.getListMenuItems().size() || choice == 0 ){
			System.out.println("Value out of range! Please try again!");
			choice = MainUI.getInt("Select the item to delete: (Enter -1 to go back)");
		}
		if (choice == -1 ){
			return;
		}

		MenuItem itemToDelete = mMenu.getMenuItemById(choice);

		//Confirm and delete
		System.out
				.println("Are you sure you want to delete this item? (Enter 'y' to confirm)");
		itemToDelete.print(true);
		System.out.println();
		
		String confirm = sc.next();
		if (confirm.charAt(0) == 'y') {
			mMenu.deleteMenuItemById(choice);
			System.out.println("Item deleted.");
		}
	}

	/**
	 * Create a new promotional package in the menu
	 */
	public void createNewPromotionalPackage() {
		
		//Ask for package's info
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the name of the new promotional package: (Enter -1 to go back)");
		String name = sc.nextLine();
		try {
			if (Integer.parseInt(name) == -1){
				return;
			}
		}
		catch (Exception e){}

		double price = MainUI.getDouble("Input the price of the new promotional package: ");
		
		System.out
				.println("Input the description of the new promotional package: ");
		String description = sc.nextLine();

		// Ask for the list of menu items to add to the package
		mMenu.print(false, true, false);
		ArrayList<MenuItem> tempMenuItemList = new ArrayList<MenuItem>();
		
		int tempMenuItemId = 0;
		while (tempMenuItemId != -1) {
			tempMenuItemId = MainUI.getInt("Input the menu items to add to the package, ONE at a time (enter -1 to end): ");
			while (tempMenuItemId == 0 || tempMenuItemId < -1 || tempMenuItemId > mMenu.getListMenuItems().size()){
				System.out.println("Value out of range, please try again!");
				tempMenuItemId = MainUI.getInt("Input ONE menu item to add to the package (enter -1 to end): ");
			}
			if (tempMenuItemId!= -1) tempMenuItemList.add(mMenu.getMenuItemById(tempMenuItemId));
		}
		
		mMenu.addPromotionalPackage(new PromotionalPackage(name, price,
				description, tempMenuItemList));

		System.out.println("Promotional package added!");
	}

	/**
	 * Update a promotional package in the menu
	 */
	public void updatePromotionalPackage() {

		mMenu.print(false, false, true);
		Scanner sc = new Scanner(System.in);

		// Select the package to update
		int choice = MainUI.getInt("Select the package to edit: (Enter -1 to go back)");
		
		while (choice < -1 || choice > mMenu.getListPackages().size() || choice == 0 ){
			System.out.println("Value out of range! Please try again!");
			choice = MainUI.getInt("Select the package to edit: (Enter -1 to go back)");
		}
		if (choice == -1 ){
			return;
		}

		PromotionalPackage tempOldPackage = mMenu
				.getPromotionalPackageById(choice);

		tempOldPackage.print(true);

		// Ask for new info of the package
		System.out.println("Input the new name of the promotional package: ");
		String name = sc.nextLine();

		double price = MainUI.getDouble("Input the new price of the promotional package: ");

		System.out
				.println("Input the new description of the promotional package: ");
		String description = sc.nextLine();

		// Ask for the list of menu items to add to the package
		mMenu.print(false, true, false);
		ArrayList<MenuItem> tempMenuItemList = new ArrayList<MenuItem>();
		
		int tempMenuItemId = 0;
		while (tempMenuItemId != -1) {
			tempMenuItemId = MainUI.getInt("Input the menu items to add to the package, ONE at a time (enter -1 to end): ");
			while (tempMenuItemId == 0 || tempMenuItemId < -1 || tempMenuItemId > mMenu.getListMenuItems().size()){
				System.out.println("Value out of range, please try again!");
				tempMenuItemId = MainUI.getInt("Input ONE menu item to add to the package (enter -1 to end): ");
			}
			if (tempMenuItemId!= -1) tempMenuItemList.add(mMenu.getMenuItemById(tempMenuItemId));
		}
		
		mMenu.replacePromotionalPackageById(choice, new PromotionalPackage(
				name, price, description, tempMenuItemList));
	}

	/**
	 * Delete a promotional package in the menu
	 */
	public void deletePromotionalPackage() {

		mMenu.print(false, false, true);
		// Select the package to delete
		
		int choice = MainUI.getInt("Select the package to delete: (Enter -1 to go back)");
		
		while (choice < -1 || choice > mMenu.getListPackages().size() || choice == 0 ){
			System.out.println("Value out of range! Please try again!");
			choice = MainUI.getInt("Select the package to delete: (Enter -1 to go back)");
		}
		if (choice == -1 ){
			return;
		}

		PromotionalPackage packageToDelete = mMenu.getPromotionalPackageById(choice);
		packageToDelete.print(true);
		System.out.println("Are you sure you want to delete this item? (Enter 'y' to confirm)");

		Scanner sc = new Scanner (System.in);
		String confirm = sc.next();
		if (confirm.charAt(0) == 'y') {
			mMenu.deletePromotionalPackageById(choice);
			System.out.println("Package deleted.");
		}
	}
	
	
	/**
	 * Print all the available menu options
	 */
	public void printMenuOption() {
		System.out.println("#################################");
		System.out.println("#           MENU OPTION         #");
		System.out.println("#      1. Create Menu Item.     #");
		System.out.println("#      2. Update Menu Item.     #");
		System.out.println("#      3. Delete Menu Item.     #");
		System.out.println("# 4. Create Promotional Package.#");
		System.out.println("# 5. Update Promotional Package.#");
		System.out.println("# 6. Delete Promotional Package.#");
		System.out.println("#      7. Print Menu (detail).  #");
		System.out.println("#      8. Print Menu (short).   #");
		System.out.println("#      9. Main Menu.            #");
		System.out.println("#################################");
	}

	/**
	 * Get the list of all menu items
	 * @return the list of all menu items in the menu
	 */
	public ArrayList<MenuItem> getMenuItemList() {
		// TODO Auto-generated method stub
		return mMenu.getListMenuItems();
		// return null;
	}

	/**
	 * Get the list of all promotional packages
	 * @return the list of all promotional packages
	 */
	public ArrayList<PromotionalPackage> getPackageList() {
		// TODO Auto-generated method stub
		return mMenu.getListPackages();
	}
}
