package UI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.text.DecimalFormat;

import Controller.OrderController;
import Controller.PrintSaleController;
import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;
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
		DecimalFormat  df = new DecimalFormat ("#.##");
		ArrayList<MenuItem> item = order.getMenuItemsList();
		ArrayList<PromotionalPackage> set = order.getPromotionalPackagesList();
		
		System.out.println("================= Loh Yeh Moh Yeh Resto =================");
		System.out.println("Staff Name      : " + order.getStaff() );
		System.out.println("Table ID        : " + order.getTableId() );
		System.out.println("Date(DD/MM/YYYY): " + ( order.getTime() ).getDate() + "/" + ( order.getTime() ).getMonth() + "/" + ( ( order.getTime() ).getYear() + 1900 ));
		System.out.println("---------------------------------------------------------");
		
		if(item.size() > 0){
			System.out.println("Menu Items  : ");
			
				for(int i = 0; i < item.size(); i++ )
				{
					System.out.println("    " + (item.get(i)).getName() + "	" + (item.get(i)).getPrice() );
				}
		}
		
		if(set.size() > 0){
			System.out.println("---------------------------------------------------------");
			System.out.println("Promotional Set Packages  : ");
			
				for(int i = 0; i < item.size(); i++ )
				{
					System.out.println("    " + (set.get(i)).getName() + "	" + (set.get(i)).getPrice() );
				}
		}
		
		System.out.println("---------------------------------------------------------");
		System.out.println("SubTotal               : " + df.format(( order.getTotalPrice() )));
		System.out.println("Taxes                  : " + df.format(( order.getTotalPrice() * 0.07)));
		System.out.println("---------------------------------------------------------	");
		System.out.println("TOTAL                  : " + df.format((order.getTotalPrice() ) * 1.07 ));
		System.out.println("============= Thank you! Please come again! =============");
	}
	sc.close();
	return;
}
  
	/**
 	* this function is called when the staff want to get the report of particular day.
 	*/
  public void DailyReport(){
	Scanner scan = new Scanner(System.in);
	int d, m, y;
			
	System.out.println("please enter the Date in the following format DD MM YYYY:");
	d = scan.nextInt();
	m = scan.nextInt();
	y = scan.nextInt();
	PrintSaleController mPrintSaleController = new PrintSaleController();
	ArrayList<Order> order = mPrintSaleController.findOrderByDate(d, m, y);
	mPrintSaleController.RevenueByDate(order,d,m,y);
	scan.close();
	return;
		
  }
	/**
	 * this function is called when the staff want to get the report of particular month.
	 */
  public void MonthlyReport(){
	System.out.println("Order Sales: ");
	Scanner scan = new Scanner(System.in);
	int m, y;
			
	System.out.println("please enter the month in the following format MM YYYY:");
	m = scan.nextInt();
	y = scan.nextInt();
	PrintSaleController mPrintSaleController = new PrintSaleController();
	ArrayList<Order> order = mPrintSaleController.findOrderByMonth(m,y);
	mPrintSaleController.RevenueByMonth(order);
	scan.close();
	return;
  }
}
