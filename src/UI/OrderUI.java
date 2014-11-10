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
		System.out.println("================= Loh Yeh Moh Yeh Resto =================");
		System.out.println("Staff Name : " + order.getStaff() );
		System.out.println("Table ID   : " + order.getTableId() );
		System.out.println("Date       : " + order.getTime() );
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
  Public void RevenueByDate() {
	Scanner scan = new Scanner();
	int d, m, y;
	
	System.out.println("please enter the Date in the following format DD MM YYYY:");
	d = scan.nextInt();
	m = scan.nextInt();
	y = scan.nextInt() - baseYear;
		
	Date date= new date(d,m,y);
	int TotalPrice= 0;
		
	Order order = mOrderController.find(date);
	if(order == NULL){
		System.out.println("No order found on that date!");
		return -1;
	}
	else{
		for(int i = 0 ; i < order.size(); ++i){
			system.out.println("Customer ID:" + order.get(i).getCustomerId());
			ArrayList<MenuItem> menuItems = order.getMenuItemsList();
			ArrayList<PromotionalPackage> packages = order.getPromotionalPackagesList();
				
			System.out.println("Menu items: ");
			for(int j = 0; i < menuItems.size(); ++i){
				System.out.println("- Menu item " + j + " : " + menuItems.get(j).getName() + "       Price:" + (menuItems.get(j).getPrice());
			}
				
			System.out.println("Promotional packages: ");
			for(int j = 0; i < packages.size(); ++i){
				System.out.println("- PromotionalPackage " + j + " : " + packages.get(j).getName() + "       Price:" + (PromotionalPackage.get(j).getPrice());
			}
			TotalPrice += order.get(i).getTotalPrice();
		}
			
	}
	System.out.println("Overall revenue for "+ date +" : "+ TotalPrice);
  };
		
  Public void RevenueByMonth() {
	int OverallPrice,Max,Min = 0;
	Date dateMin= new Date();
	Date dateMax= new Date();
	System.out.println("Order Sales: ");
	Scanner scan = new Scanner();
	int m, y;
		
	System.out.println("please enter the month in the following format MM YYYY:");
	m = scan.nextInt();
	y = scan.nextInt() - baseYear;
		
	Date date= new Date(m,y);
		
	Order order = mOrderController.find(date);
	if(order == NULL){
		System.out.println("No order found on that month!");
		return -1;

	}
	else{
		for(int i = 0; i < order.size(); ++i){
			if(Max < order.get(i).getTotalPrice()){
				Max= order.get(i).getTotalPrice();
				DateMax= order.get(i).getDate();
			}
			else if{ Min > order.get(i).getTotalPrice()){
				Min= order.get(i).getTotalPrice();
				DateMin= order.get(i).getDate();
			}
			OverallPrice +=order.get(i).getTotalPrice();
		}
	}
	
	System.out.println("Top sale of the month in " + dateMax + "is: "+	Max));
	System.out.println("Least sale of the month in " + dateMin + "is: " + Min));		
	System.out.println("Overall Total Price: " + OverallPrice);
  }
}
