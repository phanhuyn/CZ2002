package UI;

import java.util.Scanner;

import Controller.MainController;

public class MainUI {

	private MainController mMainController;

	public MainUI(MainController mainController) {
		mMainController = mainController;
	}

	public void displayMainFunction() {
		printOption();
		Scanner sc = new Scanner(System.in);
		int choice;
		choice = sc.nextInt();
		while (choice <= 5) {
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
				mMainController.orderOption();
				break;
			case 5:
				mMainController.save();
				break;
			case 7:
				mMainController.save();
				break;
			}
			printOption();
			choice = sc.nextInt();
		}
		System.out.println("Programm terminating...");
	}
	
	public void printOption (){
		System.out.println("#################################");
		System.out.println("#         MAIN SCREEN           #");
		System.out.println("#      1. Menu Option           #");
		System.out.println("#      2. Reservation Option    #");
		System.out.println("#      3. Order Option          #");
		System.out.println("#      4. Sale Report Option    #");
		System.out.println("#      5. Save data             #");
		System.out.println("#      6. Exit without save     #");
		System.out.println("#      7. Save then Exit        #");
		System.out.println("#################################");
		System.out.println("Enter your choice: ");
	}
}
