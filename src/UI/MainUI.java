/**
	The main UI of the application, responsible for switching between main functionality based on user's choice
	Provide the methods to take in user's input
	@author Nguyen Phan Huy
	@version 1.0
	@since 2014-11-13
*/

package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.MainController;

public class MainUI {

	/**
	 * The main controller of the application
	 */
	private MainController mMainController;

	/**
	 * Create the MainUI
	 * @param mainController the reference to the main controller
	 */
	public MainUI(MainController mainController) {
		mMainController = mainController;
	}

	/**
	 * Prompt for user's next choice of main functionality
	 * Call the main controller's function's based on the choice
	 */
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
	
	/**
	 * Print the list of main functionality
	 */
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
	
	/**
	 * Ask for an integer input from the user
	 * The method includes exception handling in case the user keys in an invalid value
	 * @param question the information about the integer that the user needs to key in
	 * @return the integer input from the user 
	 */
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
	
	/**
	 * Ask for an double input from the user
	 * The method includes exception handling in case the user keys in an invalid value
	 * @param question the information about the double that the user needs to key in
	 * @return the double input from the user 
	 */	
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
