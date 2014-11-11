package UI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import Controller.OrderController;
import Controller.PrintSaleController;
import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;
import Entity.Restaurant;

public class OrderUI {
  private OrderController mOrderController;
  private PrintSaleController mPrintSaleController;
  private Restaurant mRestaurant;
  private String customerName;
  private int orderID;
  
  //constructor
  public OrderUI(Restaurant restaurant) {
  	mOrderController = new OrderController();
  	mPrintSaleController = new PrintSaleController();
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
 			System.out.println("5.Print Daily Report");
 			System.out.println("6.Print Monthly Report");
 			System.out.println("7. Quit.");
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
 			if(choice == 5){
					DailyReport(); 
			}
 			if(choice == 6){
					MonthlyReport(); 
			}
 			if(choice == 7) 
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
		System.out.println("================= Loh Yeh Moh Yeh Resto =================");
		System.out.println("Staff Name      : " + order.getStaff() );
		System.out.println("Table ID        : " + order.getTableId() );
		System.out.println("Date(DD/MM/YYYY): " + ( order.getTime() ).get(Calendar.DATE) + "/" + ( order.getTime() ).get(Calendar.MONTH) + "/" + ( order.getTime() ).get(Calendar.YEAR));
		System.out.println("---------------------------------------------------------");
		System.out.println("Menu Items                     : " + order.getMenuItemsList() );
		System.out.println("Promotional Set Packages       : " + order.getPromotionalPackagesList() );
		System.out.println("---------------------------------------------------------");
		System.out.println("SubTotal					   : " + ( order.getTotalPrice() ) );
		System.out.println("Taxes                          : " + ( order.getTotalPrice() * 0.07));
		System.out.println("---------------------------------------------------------	");
		System.out.println("TOTAL                          : " + (order.getTotalPrice() ) * 1.07 );
		System.out.println("============= Thank you! Please come again! =============");
	}
	sc.close();
	return;
  }
  public void DailyReport(){
	  Scanner scan = new Scanner(System.in);
		int d, m, y;
			
		System.out.println("please enter the Date in the following format DD MM YYYY:");
		d = scan.nextInt();
		m = scan.nextInt();
		y = scan.nextInt();
		ArrayList<Order> order = mPrintSaleController.findOrderByDate(d, m, y);
		mPrintSaleController.RevenueByDate(order,d,m,y);
		
  }
  public void MonthlyReport(){
	  System.out.println("Order Sales: ");
		Scanner scan = new Scanner(System.in);
		int m, y;
			
		System.out.println("please enter the month in the following format MM YYYY:");
		m = scan.nextInt();
		y = scan.nextInt();
		ArrayList<Order> order = mPrintSaleController.findOrderByMonth(m,y);
		mPrintSaleController.RevenueByMonth(order);
		
  }
}
  
  //Revenue by Date
  
