package UI;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MainController;
import Controller.MenuController;
import Controller.OrderController;
import Entity.Order;

public class OrderUI {
  
  private CreateOrderUI mCreateOrder;
  private ViewOrderUI mViewOrder;
  private OrderController mOrderController;
  
  private String customerName;
  private int orderID;
  
  public OrderUI(OrderController orderCont) {
  	mOrderController = orderCont;
  }
  
  public void run() {
		int choice = 0;
		while (choice <= 5){
			System.out.println("1. Create Order");
			System.out.println("2. View Order.");
			System.out.println("3. Print Invoice.");
			System.out.println("4. Print Sales Revenue Report by Date.");
			System.out.println("5. Print Sales Revenue Report by Month.");
			System.out.println("6. Quit.");
			
			System.out.println("Select your option: ");
			
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			
			switch (choice) {
			case 1: break;
			case 2: break;
			case 3:	printInvoice();
				break;
			case 4: break;
			case 5: break;
			case 6: break;
			}
		}
		return;
	}
	
	//1. Print Invoice
	public void printInvoice()
	{
	  Scanner sc = new Scanner(System.in);
	  
		System.out.print("Enter Customer Name : ");
		customerName = sc.next();
		
		System.out.print("Enter Order ID : ");
		orderID = sc.nextInt();
		
		Order order = new( morderController.find(customerName, orderID) );
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
		return;
	}
}
