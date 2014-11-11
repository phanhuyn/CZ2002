package UI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import Controller.OrderController;
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
		System.out.println("Date(DD/MM/YYYY): " + ( order.getTime() ).getDate() + "/" + ( order.getTime() ).getMonth() + "/" + ( order.getTime() ).getYear());
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
  
  //Revenue by Date
  public void RevenueByDate(){
	  Scanner scan = new Scanner(System.in);
		int d, m, y;
			
		System.out.println("please enter the Date in the following format DD MM YYYY:");
		d = scan.nextInt();
		m = scan.nextInt();
		y = scan.nextInt();
			
		int TotalPrice= 0;
			
		ArrayList<Order> order = mOrderController.findOrderByTime(d, m, y);
		if(order == null){
			System.out.println("No order found on that date!");
			return;
		}
		else
		{
			for(int i = 0 ; i < order.size(); ++i)
			{
				System.out.println("Customer ID:" + order.get(i).getCustomerId());
				ArrayList<MenuItem> menuItems = (order.get(i)).getMenuItemsList();
				ArrayList<PromotionalPackage> packages = (order.get(i)).getPromotionalPackagesList();
					
				System.out.println("Menu items: ");
				for(int j = 0; i < menuItems.size(); ++i)
				{
					System.out.println("- Menu item " + j + " : " + menuItems.get(j).getName() + "       Price:" + (menuItems.get(j).getPrice()));
				}
					
				System.out.println("Promotional packages: ");
				for(int j = 0; i < packages.size(); ++i)
				{
					System.out.println("- PromotionalPackage " + j + " : " + packages.get(j).getName() + "       Price:" + (packages.get(j).getPrice()));
				}
				TotalPrice += (order.get(i)).getTotalPrice();
			}
		}
		System.out.println("Overall revenue for " + d + m + y + " : " + TotalPrice);  
  
  }

  //Revenue by Month
  public void RevenueByMonth(){
	  	double OverallPrice = 0;
	  	double Max = 0;
	  	double Min = 0;
		
		System.out.println("Order Sales: ");
		Scanner scan = new Scanner(System.in);
		int m, y;
			
		System.out.println("please enter the month in the following format MM YYYY:");
		m = scan.nextInt();
		y = scan.nextInt();
			
		Calendar DateMax = Calendar.getInstance();
		Calendar DateMin = Calendar.getInstance();
		
		String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			
		ArrayList<Order> order = mOrderController.findOrderByMonth(m,y);
		if(order == null){
			System.out.println("No order found on that month!");
			return;
		}
		else{
			for(int i = 0; i < order.size(); ++i)
			{
				if(Max < (order.get(i)).getTotalPrice()){
					Max = (order.get(i)).getTotalPrice();
					DateMax.set(Calendar.MONTH, ( (order.get(i)).getTime()).getMonth()+1);
				}
				else if(Min > order.get(i).getTotalPrice()){
					Min = (order.get(i)).getTotalPrice();
					DateMin.set(Calendar.MONTH, ( (order.get(i)).getTime() ).getMonth()+1);
				}
				OverallPrice += order.get(i).getTotalPrice();
			}
		}
			
			System.out.println("Top sale of the month in " + monthNames[ DateMax.get(Calendar.MONTH) ] + "is: "+	Max);
			System.out.println("Least sale of the month in " + monthNames[ DateMin.get(Calendar.MONTH) ] + "is: " + Min);		
			System.out.println("Overall Total Price: " + OverallPrice); 
		} 
}
