/*
 * Author: Tran Vu Xuan Nhat
 * Matric No: U1323058D
 */
package UI;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MainController;
import Controller.OrderController;
import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;
import Entity.Restaurant;

public class ViewOrderUI {
	private OrderController mOrderController;
	private final String spacing = "***********************************";
	public ViewOrderUI(){
		mOrderController = new OrderController();
	}
	
	public void run(){
		Scanner scan = new Scanner(System.in);
		int option = 1;
		while(true){
			if(mOrderController.getListOrder() == null){
				/*
				 * if no order exist, informs user 
				 */
				System.out.println("No order available for view");
				System.out.println("Enter 0 to back to main screen.");
			}
			else if(option == 1){
				/*
				 * let user input name of customer to find order respectively
				 */
				String mCustomerName;
				int mId = 0;
				System.out.print("Please input customer's name: ");
				mCustomerName = scan.next();
				System.out.print("Please input order's ID: ");
				mId = scan.nextInt();
				Order order = mOrderController.find(mCustomerName,mId);
				if(order == null){
					/*
					 * No order matches customer 
					 */
					System.out.println("No order found!");
				}
				else{
					/*display order information on the screen by following information:
					 * Order ID
					 * Staff serves the order
					 * Customer name, ID
					 * Table
					 * Order list, include Menu items and promotional packages
					 */
					System.out.println(spacing);
					System.out.println("Order ID: " + order.getId());
					System.out.println("Staff created order: " + order.getStaff());
					System.out.println("Date: " + order.getTime());
					System.out.println("Customer: "+ order.getCustomerName() + "       ID:"+order.getCustomerId());
					System.out.println("Table: " + order.getTableId());
					System.out.println("Order list: ");
					System.out.println("Menu items: ");
					ArrayList<MenuItem> menuItems = order.getMenuItemsList();
					for(int i = 0; i < menuItems.size(); ++i){
						System.out.println("- Menu item " + (i+1) + " : " + menuItems.get(i).getName() + "       Price:" + menuItems.get(i).getPrice());
					}
					System.out.println("Promotional packages: ");
					ArrayList<PromotionalPackage> packages = order.getPromotionalPackagesList();
					for(int i = 0; i < packages.size(); ++i){
						System.out.println("- PromotionalPackage " + (i+1) + " : " + packages.get(i).getName() + "       Price:" + menuItems.get(i).getPrice());
					}
					System.out.println("Total Price: " + order.getTotalPrice());
				}
			}
			else if(option == 0){
				/*
				 * Back to Order UI
				 */
				break;
			}
			
			/*
			 * Ask user to continue view other order or not
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
