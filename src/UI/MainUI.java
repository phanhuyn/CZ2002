package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.MainController;

public class MainUI {

	private MainController mMainController;

	public MainUI(MainController mainController) {
		mMainController = mainController;
	}

	public void displayMainFunction() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		while (choice <= 5) {
			printOption();
			choice = MainUI.getInt("Select your option: ");
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
		System.out.println("#      6. Exit without saving   #");
		System.out.println("#      7. Save then Exit        #");
		System.out.println("#################################");
	}
	
	public static int getInt (String question){
		Scanner sc = new Scanner(System.in);
		boolean checkInput = true;
		int choice = 0;
		do {
			try{
				System.out.println(question);
				choice = sc.nextInt();
				checkInput = false;
			}
			catch (InputMismatchException e){
				sc.next();
				System.out.println("Error: Please input an integer!");
			}
		} while (checkInput);
		return choice;
	}
	
	public static double getDouble (String question){
		Scanner sc = new Scanner(System.in);
		double value = 0;
		boolean checkInput = true;
		do{
			try {
				System.out.println(question);
				value = sc.nextDouble();
				checkInput = false;
			}
			catch(InputMismatchException e){
				System.out.print("Please enter a valid number value! ");
				sc.next();
			}
		} while(checkInput);
		return value;
	}
}
