package Controller;

import java.util.ArrayList;
import java.util.Scanner;

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
			System.out.println("1. Create new Menu Item.");
			System.out.println("2. Update Menu Item.");
			System.out.println("3. Delete Menu Item.");
			System.out.println("4. Create new Promotional Package.");
			System.out.println("5. Update Promotional Package.");
			System.out.println("6. Delete Promotional Package.");
			System.out.println("7. Print Menu (detail).");
			System.out.println("8. Print Menu (short).");
			System.out.println("9. Main Menu.");

			System.out.println("Select your option: ");

			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
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
				mMenu.print(false, true, false);
				break;
			}
		}
		return;
	}

	// 4. Create new Promotional Package.
	public void createNewPromotionalPackage() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the name of the new promotional package: ");
		String name = sc.nextLine();
		// System.out.println(name);

		System.out.println("Input the price of the new promotional package: ");
		double price = sc.nextDouble();
		sc.nextLine();
		// System.out.println(price);

		System.out
				.println("Input the description of the new promotional package: ");
		String description = sc.nextLine();
		// System.out.println(description);

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
		System.out.println("Select the package to edit: ");
		mMenu.print(false, false, true);
		int choice;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		sc.nextLine();

		PromotionalPackage tempOldPackage = mMenu
				.getPromotionalPackageById(choice);

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
		System.out.println("Select the item to delete: ");
		mMenu.print(false, true, false);
		int choice;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		sc.nextLine();

		MenuItem itemToDelete = mMenu.getMenuItemById(choice);

		System.out
				.println("Are you sure you want to delete this item? (Enter 'y' to confirm)");
		itemToDelete.print(true);

		String confirm = sc.next();
		System.out.println(confirm);
		if (confirm.charAt(0) == 'y') {
			mMenu.deleteMenuItemById(choice);
			System.out.println("Item deleted.");
		}
	}

	// 3. Delete Menu Item
	public void deleteMenuItem() {
		System.out.println("Select the item to delete: ");
		mMenu.print(false, true, false);
		int choice;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		sc.nextLine();

		MenuItem itemToDelete = mMenu.getMenuItemById(choice);

		System.out
				.println("Are you sure you want to delete this item? (Enter 'y' to confirm)");
		itemToDelete.print(true);

		String confirm = sc.next();
		System.out.println(confirm);
		if (confirm.charAt(0) == 'y') {
			mMenu.deleteMenuItemById(choice);
			System.out.println("Item deleted.");
		}
	}

	// 1. Create new menu item
	public void createNewMenuItem() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the name of the new menu item: ");
		String name = sc.nextLine();
		// System.out.println(name);

		System.out.println("Input the type of the new menu item: ");
		String type = sc.nextLine();
		// System.out.println(type);

		System.out.println("Input the price of the new menu item: ");
		double price = sc.nextDouble();
		sc.nextLine();
		// System.out.println(price);

		System.out.println("Input the description of the new menu item: ");
		String description = sc.nextLine();
		// System.out.println(description);

		mMenu.addMenuItem(new MenuItem(name, type, price, description));
		System.out.println("Menu item added!");

		// //// ADD EXCEPTION HANDLING!!!!
	}

	// 2. Update menu item
	public void updateMenuItem() {
		System.out.println("Select the item to edit: ");
		mMenu.print(false, true, false);
		int choice;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		sc.nextLine();

		MenuItem tempOldMenuItem = mMenu.getMenuItemById(choice);

		System.out.println("Enter the new name: ");
		String name = sc.nextLine();
		System.out.println("Enter the new type: ");
		String type = sc.nextLine();
		System.out.println("Enter the new price: ");
		double price = sc.nextDouble();
		sc.nextLine();
		System.out.println("Enter the new description: ");
		String description = sc.nextLine();

		MenuItem tempMenuItem = new MenuItem(name, type, price, description);
		mMenu.addMenuItem(tempMenuItem);

		mMenu.replaceMenuItemById(choice, tempMenuItem);
		// ADD EXCEPTION HANDLING
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
