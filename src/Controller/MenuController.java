package Controller;

import java.util.Scanner;

import Entity.Menu;
import Entity.MenuItem;

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

	// 1. Create new menu item
	public void createNewMenuItem() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the name of the new menu item: ");
		String name = sc.nextLine();
		System.out.println(name);

		System.out.println("Input the type of the new menu item: ");
		String type = sc.nextLine();
		System.out.println(type);

		System.out.println("Input the price of the new menu item: ");
		double price = sc.nextDouble();
		sc.nextLine();
		System.out.println(price);

		System.out.println("Input the description of the new menu item: ");
		String description = sc.nextLine();
		System.out.println(description);

		mMenu.addMenuItem(new MenuItem(name, type, price, description));
		System.out.println("Menu item added!");

		////// ADD EXCEPTION HANDLING!!!!
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

}
