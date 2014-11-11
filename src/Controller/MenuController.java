package Controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import UI.MainUI;
import Entity.Menu;
import Entity.MenuItem;
import Entity.PromotionalPackage;

public class MenuController {

	private Menu mMenu;

	public MenuController(Menu menu) {
		mMenu = menu;
	}

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

	// 1. Create new menu item
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

	// 2. Update menu item
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

	// 3. Delete Menu Item
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

	// 4. Create new Promotional Package.
	public void createNewPromotionalPackage() {
		
		//Ask for package's info
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the name of the new promotional package: ");
		String name = sc.nextLine();

		double price = MainUI.getDouble("Input the price of the new promotional package: ");
		
		System.out
				.println("Input the description of the new promotional package: ");
		String description = sc.nextLine();

		int tempMenuItemId;
		mMenu.print(false, true, false);
		ArrayList<MenuItem> tempMenuItemList = new ArrayList<MenuItem>();

		System.out
				.println("Input ONE menu item to add to the package (enter -1 to end): ");
		tempMenuItemId = sc.nextInt();

		while (tempMenuItemId != -1) {

			tempMenuItemList.add(mMenu.getMenuItemById(tempMenuItemId));

			System.out
					.println("Input the list of menu items to add to the package (enter -1 to end): ");
			tempMenuItemId = sc.nextInt();
		}
		mMenu.addPromotionalPackage(new PromotionalPackage(name, price,
				description, tempMenuItemList));
		System.out.println("Promotional package added!");
		// //// ADD EXCEPTION HANDLING!!!!
		// 1. double menu items 2. input type 3. array out of range for item
	}

	// 5. Update Promotional Package.
	public void updatePromotionalPackage() {

		mMenu.print(false, false, true);
		System.out.println("Select the package to edit: ");
		int choice;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		sc.nextLine();

		PromotionalPackage tempOldPackage = mMenu
				.getPromotionalPackageById(choice);

		tempOldPackage.print(true);

		System.out.println("Input the new name of the promotional package: ");
		String name = sc.nextLine();
		// System.out.println(name);

		System.out.println("Input the new price of the promotional package: ");
		double price = sc.nextDouble();
		sc.nextLine();
		// System.out.println(price);

		System.out
				.println("Input the new description of the promotional package: ");
		String description = sc.nextLine();
		// System.out.println(description);

		int tempMenuItemId;
		mMenu.print(false, true, false);
		ArrayList<MenuItem> tempMenuItemList = new ArrayList<MenuItem>();

		System.out
				.println("Enter the new list of menu items to add to the package (enter -1 to end): ");
		tempMenuItemId = sc.nextInt();

		while (tempMenuItemId != -1) {

			tempMenuItemList.add(mMenu.getMenuItemById(tempMenuItemId));

			System.out
					.println("Input ONE menu item to add to the package (enter -1 to end): ");
			tempMenuItemId = sc.nextInt();
		}

		mMenu.replacePromotionalPackageById(choice, new PromotionalPackage(
				name, price, description, tempMenuItemList));
		// ADD EXCEPTION HANDLING
	}

	// 6. Delete Promotional Package.
	public void deletePromotionalPackage() {

		mMenu.print(false, false, true);
		System.out.println("Select the item to delete: ");
		int choice;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		sc.nextLine();

		PromotionalPackage packageToDelete = mMenu
				.getPromotionalPackageById(choice);
		packageToDelete.print(true);
		System.out
				.println("Are you sure you want to delete this item? (Enter 'y' to confirm)");

		String confirm = sc.next();
		// System.out.println(confirm);
		if (confirm.charAt(0) == 'y') {
			mMenu.deletePromotionalPackageById(choice);
			System.out.println("Package deleted.");
		}
	}
	
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

	public ArrayList<MenuItem> getMenuItemList() {
		// TODO Auto-generated method stub
		return mMenu.getListMenuItems();
		// return null;
	}

	public ArrayList<PromotionalPackage> getPackageList() {
		// TODO Auto-generated method stub
		return mMenu.getListPackages();
	}
}
