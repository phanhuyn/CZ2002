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

// TODO: Auto-generated Javadoc
/**
 * The Class ViewOrderUI display the old order
 * @author Tran Vu Xuan Nhat 
 */
public class ViewOrderUI {
	
	/** The order controller. */
	private OrderController mOrderController;
	
	/** The constant spacing. */
	private final String spacing = "***********************************";
	
	/**
	 * Instantiates a new ViewOrderUI.
	 *
	 * @param restaurant the data of restaurant
	 */
	public ViewOrderUI(Restaurant restaurant){
		mOrderController = new OrderController(restaurant);
	}
	
	/**
	 * Run the user interface
	 */
	public void run(){
		Scanner scan = new Scanner(System.in);
		int option = 1;
		while(true){
			if(option == 0){
				/*
				 * Back to Order UI
				 */
				break;
			}
			if(mOrderController.getListOrder().size() == 0){
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
				int mTableId = 0;
				System.out.print("Please input customer's name: ");
				mCustomerName = scan.next();
				System.out.print("Please input table's ID: ");
				mTableId = scan.nextInt();
				Order order = mOrderController.find(mCustomerName,mTableId);
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
					mOrderController.showOrder(order);
				}
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
