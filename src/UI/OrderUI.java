package UI;

import java.util.Scanner;

import Controller.MenuController;
import Controller.OrderController;
import Entity.Order;
import Entity.Restaurant;

public class OrderUI {
  private OrderController mOrderController;
  private Restaurant mRestaurant;
  private String customerName;
  private int orderID;
  
  //constructor
  public OrderUI(Restaurant restaurant) {
  	mOrderController = new OrderController();
  	mRestaurant = restaurant;
  }
  



	public void run() {
 		int choice = 0;
			
		Scanner sc = new Scanner(System.in);
 		while (choice <= 5){
 			System.out.println("1. Create Order");
 			System.out.println("2. View Order.");
 			System.out.println("3. Add or Remove item from order.");
 			System.out.println("4. Print Invoice.");
 			System.out.println("5. Quit.");
 			System.out.print("Select your option: ");
 			choice = sc.nextInt();
 			if(choice == 1) {
 					CreateOrderUI  mCreateOrderUI = new CreateOrderUI(mRestaurant);
 					mCreateOrderUI.run();
 					sc.nextLine();
 			}
 			if(choice ==  2){
 					ViewOrderUI mViewOrderUI = new ViewOrderUI();
 					mViewOrderUI.run();
 			}
 			if(choice == 3)	{
 					AddRemoveOrderItemsFromOrder mAddRemoveOrderItemsFromOrder = new AddRemoveOrderItemsFromOrder(mRestaurant.getMenu());
 					mAddRemoveOrderItemsFromOrder.run();
 			}
 			if(choice == 4){
 					printInvoice(); 
 			}
 			if(choice == 5) 
 				break;
 		}

 		sc.close();
 		return;
 	} 
 	
  //Print Invoice
  public void printInvoice(){
  	Scanner sc = new Scanner(System.in);
	
	System.out.print("Enter Customer Name : ");
	customerName = sc.next();

	System.out.print("Enter Order ID : ");
	orderID = sc.nextInt();

	Order order = mOrderController.find(customerName, orderID) ;
	if(order == null)
	{
		System.out.println("No order found!");
	}
	else
	{
		System.out.println("Staff Name                     : " + order.getStaff() );
		System.out.println("Table ID                       : " + order.getTableId() );
		System.out.println("========================================================");
		System.out.println("Menu Items                     : " + order.getMenuItemsList() );
		System.out.println("Promotional Set Packages       : " + order.getPromotionalPackagesList() );
		System.out.println("========================================================");
		System.out.println("Grand Total (inclusive of GST) : " + ( (order.getTotalPrice() ) * 1.07 ) );
		System.out.println("============= Thank you! Please come again! ============");
	}
	sc.close();
	return;
  }
}
