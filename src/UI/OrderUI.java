package UI;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MainController;
import Controller.MenuController;
import Controller.OrderController;
import Entity.Order;

public class OrderUI {
  private OrderController mOrderController;
  
  private String customerName;
  private int orderID;
  
  //constructor
  public OrderUI(OrderController orderCont) {
  	mOrderController = orderCont;
  }
  
  //Print Invoice
  public void printInvoice(){
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
