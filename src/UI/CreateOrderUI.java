

package UI;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import Controller.MainController;
import Controller.MenuController;
import Controller.OrderController;
import Entity.Menu;
import Entity.MenuItem;
import Entity.PromotionalPackage;
import Entity.Restaurant;
import Entity.Staff;
import Entity.Table;

public class CreateOrderUI {
	private OrderController mOrderController;
	private MenuController mMenuController;
	private ArrayList<Staff> mStaffList;
	private ArrayList<Table> mTableList;
	private final String spacing = "***********************************";
	
	public CreateOrderUI(Restaurant restaurant){
		mOrderController = new OrderController(restaurant);
		mMenuController = new MenuController(restaurant.getMenu());
		mStaffList = restaurant.getStaffList();
		mTableList = mOrderController.getTableList();
	}
	
	public void run(){
		Scanner scan = new Scanner(System.in);
		int option = 1;
		
		ArrayList<MenuItem> menuItems = mMenuController.getMenuItemList();
		ArrayList<PromotionalPackage> packages = mMenuController.getPackageList();
		
		while(true){
			String mStaffName = null, mCustomerName;
			int mCustomerId, mTableId = 0,quantity = 1;
			ArrayList<MenuItem> orderMenuItemList = new ArrayList<MenuItem>();
			ArrayList<PromotionalPackage> orderPackageList = new ArrayList<PromotionalPackage>();
			ArrayList<Integer> quantityMenuItem = new ArrayList<Integer>();
			ArrayList<Integer> quantityPackage = new ArrayList<Integer>();
			 /*
			  *  Let user input information of new order
			 */
			/*
			 * option = 1: create another order
			 * option = 0: cancel and exit
			 */
			if(option == 0){
				/*
				 * exit and back to main screen
				 */
				System.out.println(spacing);
				break;
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
				/*
				* get table's number
				*/
				boolean noTableAvailable = true;
				int numOfPeople = 0;
				Date currentTime = Calendar.getInstance().getTime();
				Date nextTime =  Calendar.getInstance().getTime();
				nextTime.setHours(currentTime.getHours()+1);
				for(Table table: mTableList){
					if(table.isAvailable(numOfPeople, currentTime, nextTime)){
						if(noTableAvailable)
							noTableAvailable = false;
					}
				}
				if(noTableAvailable){
					System.out.println("No table free at this moment");
					break;
				}
				boolean noTableEnoughPax = true;
				numOfPeople = MainUI.getInt("Please input number of people: ");
				noTableEnoughPax = true;
				for(Table table : mTableList){
					if(table.getCapacity() >= numOfPeople){
						noTableEnoughPax = false;
						break;
					}
				}
				if(noTableEnoughPax){
					System.out.println("No table can serve such many people at that time!\n");
					break;
				}
					
				while(true){
					System.out.println("Below is the list of free table, choose by enter tablee's number: ");
					for(Table table : mTableList){
						if(table.isAvailable(numOfPeople, currentTime, nextTime)){
								System.out.println("Table number: " + table.getId() + ", #pax: " + table.getCapacity());
						}
					}
					mTableId = MainUI.getInt("Enter table's number: ");
					if(0<= mTableId && mTableId <= mTableList.size() && mTableList.get(mTableId).isAvailable(numOfPeople, currentTime, nextTime)){
						break;
					}
				}
				while(true){
					System.out.println("List of staff avaialable for service: ");
					for(Staff staff: mStaffList){
						System.out.println("- Staff name: " + staff.getName() + ", id: " + staff.getId() );
					}
					int staffId = -1;
					while(staffId  < 0 || staffId >1){
						staffId = MainUI.getInt("Please enter the ID of the staff: ");
					}
					mStaffName = mStaffList.get(staffId).getName();
					if(mStaffName!= null){
						break;
					}
				}
				
				System.out.print("Please enter customer name: ");
				mCustomerName = scan.next();
				mCustomerId = MainUI.getInt("Please input customer ID: ");
				int continueSelect = 1, choice = 0, confirm = 0;
				/*
				 * Loop for user to select menu items
				 */
				while(continueSelect != 0){
					if(menuItems.size() == 0){
						System.out.println("No item in menu!");
						break;
					}
					System.out.println("Below is the list of menu items, please enter the number respectively: ");
					int i;
					for(i = 1; i < menuItems.size() + 1; ++i){
						System.out.println(i + ". " + menuItems.get(i-1).getName() + "       Price: " + menuItems.get(i-1).getPrice());
					}
					System.out.println();
					System.out.println("0. Cancel selecting menu items.");
					choice = MainUI.getInt("Your choice: ");
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
					 * ask user input the quantity of menu item
					 */
					quantity = MainUI.getInt("Input the quantity of this item you want");
					if(!(0< quantity && quantity <= 99)){
						System.out.println("Invalid quantity!!");
						continue;
					}
					/*
					 * ask for confirmation of selecting menu item
					 */
					confirm = MainUI.getInt("Enter 1 to confirm, 0 to cancel: ");
					/*
					 * if user confirms, add this item to order
					 */
					if(confirm == 1){
						MenuItem mItem = new MenuItem(menuItems.get(i-1).getName(), menuItems.get(i-1).getType(),menuItems.get(i-1).getPrice(),menuItems.get(i-1).getDescription());
						orderMenuItemList.add(mItem);
						quantityMenuItem.add(quantity);
					}
					/*
					 * ask user to continue selecting menu item or not
					 */
					continueSelect =  MainUI.getInt("Enter 1 to continue select other menu items, else enter 0 to cancel: ");
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
					quantity = 1;
					if(packages.size() == 0){
						System.out.println("No promotional packages in the menu");
						break;
					}
					System.out.println("Below is the list of promotional packages, please enter the number respectively: ");
					int i ;
					for(i = 1; i < packages.size() + 1; ++i){
						System.out.println(i + ". " +packages.get(i-1).getName() + "       Price: " + packages.get(i-1).getPrice());
					}
					System.out.println();
					System.out.println("0. Cancel selecting promotional packages.");
					choice = MainUI.getInt("Your choice: ");
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
					 * ask user input the quantity of package
					 */
					quantity = MainUI.getInt("Input the quantity of this package you want");
					if(!(0< quantity && quantity <= 99)){
						System.out.println("Invalid quantity!!");
						continue;
					}
					/*
					 * ask for confirmation of selecting promotional package
					 */
					confirm = MainUI.getInt("Enter 1 to confirm, 0 to cancel: ");
					/*
					 * if user confirms, add this package to order
					 */
					if(confirm == 1){
						PromotionalPackage mPackage = new PromotionalPackage(packages.get(i-1).getName(),packages.get(i-1).getPrice(),packages.get(i-1).getDescription(),packages.get(i-1).getItemList());
						orderPackageList.add(mPackage);
						quantityPackage.add(quantity);
					}
					/*
					 * ask user to continue selecting promotional packages or not
					 */
					continueSelect =  MainUI.getInt("Enter 1 to continue select other promotional packages, else enter 0 to cancel: ");
				}
				/*
				 * end selecting promotional packages
				 */
				
				
				
				int hasMembership = -1;
				while(hasMembership != 0 && hasMembership != 1){
					hasMembership = MainUI.getInt("Enter 1 if customer has membership, else enter 0: ");
				}
				/*
				 * ask user to confirm making order
				 */
				confirm = 0;
				confirm = MainUI.getInt("Enter 1 to confirm making order, 0 to cancel,: ");
				if(confirm == 1){
					/*
					 * ask user want to set Date for order
					 */
					boolean isSetDate = false;
					Date date = Calendar.getInstance().getTime();
					confirm = MainUI.getInt("Enter 1 to set date created order, 0 to use current day by system: ");
					System.out.println(spacing);
					if(confirm==1){
						isSetDate = true;
						System.out.println("Enter day created: ");
						date.setDate(scan.nextInt());
						System.out.println("Enter month created: ");
						date.setMonth(scan.nextInt()-1);
						System.out.println("Enter year created: ");
						date.setYear(scan.nextInt()-1900);
					}
					mOrderController.createNewOrder(mStaffName, orderMenuItemList,quantityMenuItem, orderPackageList, quantityPackage,mCustomerId, mCustomerName, mTableId, isSetDate, date, hasMembership);	
				}
				
				/*
				 * ask user to continue or not
				 */
				System.out.println();
				option = -1;
				while(option!= 0 && option != 1){
					System.out.println(spacing);
					System.out.println("Choose the option");
					System.out.println("1. Create other order.");
					System.out.println("0. Back to main screen.");
					option = MainUI.getInt("Your option: ");
				}
			}
		}
	}
}
