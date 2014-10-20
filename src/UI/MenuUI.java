package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.MenuController;

public class MenuUI {

	private MenuController mMenuController;

	public MenuUI(MenuController menuController) {
		mMenuController = menuController;
	}

	public void displayMenuOption() {
		System.out.println("1. Create new Menu Item.");
		System.out.println("2. Update Menu Item.");
		System.out.println("3. Delete Menu Item.");
		System.out.println("4. Create new Promotional Package.");
		System.out.println("5. Update Promotional Package.");
		System.out.println("6. Delete Promotional Package.");
		System.out.println("Select your option: ");
		int choice;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		switch (choice) {
		case 1:
			createNewMenuItem();
			break;
		case 2:
		}
	}

	private void createNewMenuItem() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input name: ");
		String name = sc.next();
		System.out.println("Input price");
		double price = sc.nextDouble();
		mMenuController.createNewMenuItem(name, price);
	}
}
