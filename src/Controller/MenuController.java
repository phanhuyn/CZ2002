package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Data.DataAdapter;
import Entity.Menu;
import Entity.MenuItem;
import Entity.PromotionalPackage;
import Main.Main;

public class MenuController {

	private Menu mMenu;

	public MenuController(Menu menu) {
		mMenu = menu;
	}

	public void run() {
		int choice = 0;
		while (choice <= 8){
			System.out.println("1. Create new Menu Item.");
			System.out.println("2. Update Menu Item.");
			System.out.println("3. Delete Menu Item.");
			System.out.println("4. Create new Promotional Package.");
			System.out.println("5. Update Promotional Package.");
			System.out.println("6. Delete Promotional Package.");
			System.out.println("7. Print Menu.");
			
			System.out.println("Select your option: ");
			
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				createNewMenuItem();
				break;
			case 7:
				mMenu.print();
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
		double price = sc.nextDouble(); sc.nextLine();
		System.out.println(price);
		
		System.out.println("Input the description of the new menu item: ");
		String description = sc.nextLine();
		System.out.println(description);
		
		mMenu.addMenuItem(new MenuItem (name, type, price, description));
		System.out.println("Menu item added!");
	}

	// 2. Update menu item
	public void updateMenuItem(String name, double price) {
		//MenuItem menuItem = new MenuItem((mMenu.getLastestMenuItemId() + 1), name, price);
		//mMenu.addMenuItem(menuItem);
	}
	
	public ArrayList<MenuItem> getMenuItemList() {
		// TODO Auto-generated method stub
		return mMenu.getListMenuItems();
		//return null;
	}

	public ArrayList<PromotionalPackage> getPackageList() {
		// TODO Auto-generated method stub
		return mMenu.getListPackages();
	}

}
