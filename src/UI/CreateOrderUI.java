/*
 * Author: Tran Vu Xuan Nhat
 */

package UI;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MainController;
import Controller.MenuController;
import Controller.OrderController;
import Entity.Menu;
import Entity.MenuItem;
import Entity.PromotionalPackage;
import Entity.Restaurant;

public class CreateOrderUI {
	private OrderController mOrderController;
	private final String spacing = "***********************************";
	
	public CreateOrderUI(){
		mOrderController = new OrderController();
	}
	
	public void run(){
		Scanner scan = new Scanner(System.in);
		String mStaffName, mCustomerName;
		int mCustomerId, option = 1, mTableId = 0;
		MenuController mMenuController = new MenuController(new Menu());
		ArrayList<MenuItem> menuItems = mMenuController.getMenuItemList();
		ArrayList<PromotionalPackage> packages = mMenuController.getPackageList();
		ArrayList<MenuItem> orderMenuItemList = new ArrayList<MenuItem>();
		ArrayList<PromotionalPackage> orderPackageList = new ArrayList<PromotionalPackage>();
		while(true){
			/*
			 * Let user input information of new order
			 * option = 1: finish making order,back to main screen
			 * option = 2: start making or re-making order
			 * option = 0: cancel and exit
			 */
			if(option == 0){
				/*
				 * exit and back to main screen
				 */
				System.out.println(spacing);
				scan.close();
				MainUI mainUI = new MainUI(new MainController(new Restaurant()));
				mainUI.displayMainFunction();
			}
			
			System.out.println(spacing);
			if(menuItems ==  null){
				/*
				 * if nothing in the menu, show error and let user exit
				 */
				System.out.println("Nothing in the menu. Some errors may occur. Please enter 0 to exit");
				option = scan.nextInt();
			}
			
			if(option == 1){
				System.out.println("CREATE ORDER FORM: ");
				System.out.print("Please enter staff name: ");
				mStaffName = scan.next();
				System.out.print("Please enter customer name: ");
				mCustomerName = scan.next();
				System.out.print("Please input customer ID: ");
				mCustomerId = scan.nextInt();
				int continueSelect = 1, choice = 0, confirm = 0;
				/*
				 * Loop for user to select menu items
				 */
				while(continueSelect != 0){
					System.out.println("Below is the list of menu items, please enter the number respectively: ");
					int i;
					for(i = 1; i < menuItems.size() + 1; ++i){
						System.out.println(i + ". " + menuItems.get(i-1).getName() + "       Price: " + menuItems.get(i-1).getPrice());
					}
					System.out.println();
					System.out.println("0. Cancel selecting menu items.");
					System.out.print("Your choice: ");
					choice = scan.nextInt();
					/*
					 * if user want to cancel selection
					 */
					if(choice == 0){
						break;
					}
					else{
						i = choice;
					}
					if(i > menuItems.size()){
						System.out.println("No item found with your selection.");
						continueSelect = 1;
						continue;
					}
					/*
					 * ask for confirmation of selecting menu item
					 */
					System.out.print("Enter 1 to confirm, 0 to cancel: ");
					confirm = scan.nextInt();
					/*
					 * if user confirms, add this item to order
					 */
					if(confirm == 1){
						MenuItem mItem = new MenuItem(menuItems.get(i-1).getName(), menuItems.get(i-1).getType(),menuItems.get(i-1).getPrice(),menuItems.get(i-1).getDescription());
						orderMenuItemList.add(mItem);
					}
					/*
					 * ask user to continue selecting menu item or not
					 */
					System.out.print("Enter 1 to continue select other menu items, else enter 0 to cancel: ");
					continueSelect =  scan.nextInt();
				}
				/*
				 * end selecting menu items
				 */
				continueSelect = 1;
				choice = 0;
				confirm = 0;
				/*
				 * Loop for user to select promotional packages
				 */
				while(continueSelect != 0){
					System.out.println("Below is the list of promotional packages, please enter the number respectively: ");
					int i ;
					for(i = 1; i < packages.size() + 1; ++i){
						System.out.println(i + ". " +packages.get(i-1).getName() + "       Price: " + packages.get(i-1).getPrice());
					}
					System.out.println();
					System.out.println("0. Cancel selecting promotional packages.");
					System.out.print("Your choice: ");
					choice = scan.nextInt();
					/*
					 * if user want to cancel selection
					 */
					if(choice == 0){
						break;
					}
					else{
						i = choice;
					}
					if(i > packages.size()){
						System.out.println("No item found with your selection.");
						continueSelect = 1;
						continue;
					}
					/*
					 * ask for confirmation of selecting promotional package
					 */
					System.out.print("Enter 1 to confirm, 0 to cancel: ");
					confirm = scan.nextInt();
					/*
					 * if user confirms, add this package to order
					 */
					if(confirm == 1){
						PromotionalPackage mPackage = new PromotionalPackage(packages.get(i-1).getName(),packages.get(i-1).getPrice(),packages.get(i-1).getDescription(),packages.get(i-1).getItemList());
						orderPackageList.add(mPackage);
					}
					/*
					 * ask user to continue selecting promotional packages or not
					 */
					System.out.print("Enter 1 to continue select other promotional packages, else enter 0 to cancel: ");
					continueSelect =  scan.nextInt();
				}
				/*
				 * end selecting promotional packages
				 */
				
				/*
				 * get table's number
				 */
				System.out.print("Enter table's number: ");
				mTableId = scan.nextInt();
				
				/*
				 * ask user to confirm making order
				 */
				confirm = 0;
				System.out.print("Enter 1 to confirm making order, 0 to cancel,: ");
				confirm = scan.nextInt();
				if(confirm == 1){
					mOrderController.createNewOrder(mStaffName, orderMenuItemList, orderPackageList, mCustomerId, mCustomerName, mTableId);					
				}
				
				/*
				 * ask user to continue or not
				 */
				System.out.println();
				option = -1;
				while(option!= 0 && option != 1){
					System.out.println(spacing);
					System.out.println("Choose the option");
					System.out.println("1. View other order.");
					System.out.println("0. Back to main screen.");
					System.out.print("Your option: ");
					option = scan.nextInt();
				}
			}
		}
	}
}
