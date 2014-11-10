package UI;

import java.util.Scanner;

import Controller.MainController;

public class MainUI {

	private MainController mMainController;

	public MainUI(MainController mainController) {
		mMainController = mainController;
	}

	public void displayMainFunction() {
		System.out.println("1. Menu Option");
		System.out.println("2. Reservation Option");
		System.out.println("3. Order Option");
		System.out.println("4. Sale Report Option");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice: ");
		int choice;
		choice = sc.nextInt();
		while (choice <= 4) {

			switch (choice) {
			case 1:
				mMainController.menuOption();
				break;
			case 2:
				mMainController.reservationOption();
				break;
			case 3:
				mMainController.orderOption();
				break;
			case 4:
				// mMainController.orderOption();
				break;
			case 5:
				//save
				break;
			case 6:
				System.out.println("Programm terminating...");
			}
			System.out.println("1. Menu Option");
			System.out.println("2. Reservation Option");
			System.out.println("3. Order Option");
			System.out.println("4. Sale Report Option");
			System.out.println("5. Save data");
			System.out.println("6. Exit");
			System.out.println("Enter your choice: ");

			choice = sc.nextInt();
		}
	}
}
