/*
 * Author: Tran Vu Xuan Nhat
 * Matric No: U1323058D
 */

package UI;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MenuController;
import Controller.OrderController;
import Entity.Menu;
import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;

public class AddRemoveOrderItemsFromOrder {
	private OrderController mOrderController;
	private MenuController mMenuController;
	private final String spacing = "***********************************";
	
	public AddRemoveOrderItemsFromOrder(Menu menu){
		mOrderController = new OrderController();
		mMenuController = new MenuController(menu);
	}
	
	public void run(){
		Scanner scan = new Scanner(System.in);
		int option = 1;
		while(true){
			if(option == 0){
				/*
				 * exit and back to main screen
				 */
				break;
			}
			if(option == 1){
				/*
				 * let user select add or remove item from order.
				 */
				int choice = -1;
				while(choice!= 1 && choice != 2 && choice != 0){
					System.out.println(spacing);
					System.out.println("Choose the task you want by enter number respectively: ");
					System.out.println("1. Add item to order.");
					System.out.println("2. Remove item from order");
					System.out.print("Your option: ");
					choice = scan.nextInt();
					if(choice == 1){
						/*
						 * Add item to order
						 */
						int mId;
						String mCustomerName;
						System.out.print("Enter order's ID: ");
						mId = scan.nextInt();
						System.out.print("Enter customer's name: ");
						mCustomerName = scan.next();
						Order order = mOrderController.find(mCustomerName,mId);
						
						if(order == null){
							/*
							 * No order matches customer 
							 */
							System.out.println("No order found!");
						}
						else
						{
							/*
							 * start adding item to order
							 */
							int continueSelect = 1, choice2 = 0, confirm = 0;
							ArrayList<MenuItem> menuItems = mMenuController.getMenuItemList();
							ArrayList<PromotionalPackage> packages = mMenuController.getPackageList();
							ArrayList<MenuItem> orderMenuItemList = order.getMenuItemsList();
							ArrayList<PromotionalPackage> orderPackageList = order.getPromotionalPackagesList();
							/*
							 * Loop for user to adding menu items
							 */
							while(continueSelect != 0){
								System.out.println("Below is the list of menu items, please enter the number of item you want to add respectively: ");
								int i ;
								for(i = 1; i < menuItems.size() + 1; ++i){
									System.out.println(i + ". " + menuItems.get(i-1).getName() + "       Price: " + menuItems.get(i-1).getPrice());
								}
								System.out.println();
								System.out.println("0. Cancel adding menu item to order.");
								System.out.print("Your choice: ");
								choice2 = scan.nextInt();
								/*
								 * if user want to cancel adding
								 */
								if(choice2 == 0){
									break;
								}
								else{
									i = choice2;
								}
								if(i > orderMenuItemList.size()){
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
									order.reCalculatePrice();
									mOrderController.saveToDB();
								}
								/*
								 * ask user to continue adding menu item or not
								 */
								System.out.print("Enter 1 to continue select other menu items, else enter 0 to cancel: ");
								continueSelect =  scan.nextInt();
							}
							/*
							 * end adding menu items
							 */
							continueSelect = 1;
							choice2 = 0;
							confirm = 0;
							/*
							 * Loop for user to adding promotional packages
							 */
							while(continueSelect != 0){
								System.out.println("Below is the list of promotional packages, please enter the number of item you want toadd respectively: ");
								int i ;
								for(i = 1; i < packages.size() + 1; ++i){
									System.out.println(i + ". " +packages.get(i-1).getName() + "       Price: " + packages.get(i-1).getPrice());
								}
								System.out.println();
								System.out.println("0. Cancel selecting promotional packages.");
								System.out.print("Your choice: ");
								choice2 = scan.nextInt();
								/*
								 * if user want to cancel selection
								 */
								if(choice2 == 0){
									break;
								}
								else{
									i = choice2;
								}
								if(i > orderPackageList.size()){
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
									order.reCalculatePrice();
									mOrderController.saveToDB();
								}
								/*
								 * ask user to continue adding promotional packages or not
								 */
								System.out.print("Enter 1 to continue select other promotional packages, else enter 0 to cancel: ");
								continueSelect =  scan.nextInt();
							}
							/*
							 * end adding promotional packages
							 */
							/*
							 * end adding item to order
							 */
							System.out.println("End adding item to order");
							System.out.println(spacing);
						}
					}
					else if(choice == 2){
						/*
						 * Remove item from order
						 */
	
						int mId;
						String mCustomerName;
						System.out.print("Enter order's ID: ");
						mId = scan.nextInt();
						System.out.print("Enter customer's name: ");
						mCustomerName = scan.next();
						Order order = mOrderController.find(mCustomerName,mId);
						
						if(order == null){
							/*
							 * No order matches customer 
							 */
							System.out.println("No order found!");
						}
						else
						{
							/*
							 * start removing item from order
							 */
							int continueSelect = 1, choice2 = 0, confirm = 0;
							ArrayList<MenuItem> orderMenuItemList = order.getMenuItemsList();
							ArrayList<PromotionalPackage> orderPackageList = order.getPromotionalPackagesList();
							
							/*
							 * Loop for user to removing menu items
							 */
							while(continueSelect != 0){
								if(orderMenuItemList.size() == 0){
									System.out.println("Menu item list is empty!");
									break;
								}
								System.out.println("Below is the list of menu items in order, please enter the number of item you want to remove respectively: ");
								int i ;
								for(i = 1; i < orderMenuItemList.size() + 1; ++i){
									System.out.println(i + ". " + orderMenuItemList.get(i-1).getName() + "       Price: " + orderMenuItemList.get(i-1).getPrice());
								}
								System.out.println();
								System.out.println("0. Cancel removing menu item to order.");
								System.out.print("Your choice: ");
								choice2 = scan.nextInt();
								/*
								 * if user want to cancel removing
								 */
								if(choice2 == 0){
									break;
								}
								else{
									i = choice2;
								}
								if(i > orderMenuItemList.size()){
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
								 * if user confirms, remove this item from order
								 */
								if(confirm == 1){
									orderMenuItemList.remove(i-1);
									order.reCalculatePrice();
									mOrderController.saveToDB();
								}
								/*
								 * ask user to continue removing menu item or not
								 */
								System.out.print("Enter 1 to continue removing other menu items, else enter 0 to cancel: ");
								continueSelect =  scan.nextInt();
							}
							/*
							 * end removing menu items
							 */
							continueSelect = 1;
							choice2 = 0;
							confirm = 0;
							
							/*
							 * Loop for user to removing promotional packages
							 */
							while(continueSelect != 0){
								if(orderPackageList.size() == 0){
									System.out.println("Promotional package list is empty!");
									break;
								}
								System.out.println("Below is the list of promotional packages in order, please enter the number respectively: ");
								int i ;
								for(i = 1; i < orderPackageList.size() + 1; ++i){
									System.out.println(i + ". " +orderPackageList.get(i-1).getName() + "       Price: " + orderPackageList.get(i-1).getPrice());
								}
								System.out.println();
								System.out.println("0. Cancel selecting promotional packages.");
								System.out.print("Your choice: ");
								choice2 = scan.nextInt();
								/*
								 * if user want to cancel selection
								 */
								if(choice2 == 0){
									break;
								}
								else{
									i = choice2;
								}
								if(i > orderPackageList.size()){
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
								 * if user confirms, remove this package from order
								 */
								if(confirm == 1){
									orderPackageList.remove(i-1);
									order.reCalculatePrice();
									mOrderController.saveToDB();
								}
								/*
								 * ask user to continue removing promotional packages or not
								 */
								System.out.print("Enter 1 to continue removing other promotional packages, else enter 0 to cancel: ");
								continueSelect =  scan.nextInt();
							}
							/*
							 * end removing promotional packages
							 */
							/*
							 * end removing item from order
							 */
							System.out.println("Endvremoving item from order");
							System.out.println(spacing);
						}
					}
					
					System.out.println();
					option = -1;
					while(option!= 0 && option != 1){
						System.out.println(spacing);
						System.out.println("Choose the option: ");
						System.out.println("1. Continue adding or removing item.");
						System.out.println("0. Back to main screen.");
						System.out.print("Your option: ");
						option = scan.nextInt();
					}
				}
			}
		}
	}
}
