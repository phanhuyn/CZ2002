package UI;

import java.util.Scanner;

import Controller.MainController;

public class MainUI {

	private MainController mMainController;

	public MainUI(MainController mainController) {
		mMainController = mainController;
	}

	public void displayMainFunction() {
		System.out.println("1. Edit Menu");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice: ");
		int choice;
		choice = sc.nextInt();
		switch (choice) {
		case 1:
			mMainController.menuOption();
		case 2:
			
		}
	}
}
