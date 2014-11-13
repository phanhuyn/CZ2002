package UI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.text.DecimalFormat;

import Controller.OrderController;
import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;
import Entity.Restaurant;
import Entity.Table;

public class OrderUI {
  private OrderController mOrderController;
  private Restaurant mRestaurant;
  private String customerName;
  private int orderID;
  private final String spacing = "######################################";
  
  //constructor
  public OrderUI(Restaurant restaurant) {
  	mOrderController = new OrderController(restaurant);
  	mRestaurant = restaurant;
  	ArrayList<Table> listTable = mRestaurant.getTableList();
  	mOrderController.setListTable(listTable);
  }
  
  public void run() {
 		int choice = 0;
			
		Scanner sc = new Scanner(System.in);
 		while (choice <= 7){
 			System.out.println(spacing);
 			System.out.println("#              ORDER                 #");
 			System.out.println("#    1. Create Order                 #");
 			System.out.println("#    2. View Order                   #");
 			System.out.println("#    3. Add or Remove item from order#");
 			System.out.println("#    4. Print Invoice.               #");
 			System.out.println("#    5. Quit.                        #");
 			System.out.println(spacing);
 			System.out.print("Select your option: ");
 			choice = sc.nextInt();
 			if(choice == 1) {
 					CreateOrderUI  mCreateOrderUI = new CreateOrderUI(mRestaurant);
 					mCreateOrderUI.run();
 					sc.nextLine();
 			}
 			if(choice ==  2){
 					ViewOrderUI mViewOrderUI = new ViewOrderUI(mRestaurant);
 					mViewOrderUI.run();
 			}
 			if(choice == 3)	{
 					AddRemoveOrderItemsFromOrder mAddRemoveOrderItemsFromOrder = new AddRemoveOrderItemsFromOrder(mRestaurant);
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

	int tableID = MainUI.getInt("Enter table ID : ");

	Order order = mOrderController.find(customerName, tableID) ;
	if(order == null)
	{
		System.out.println("No order found!");
	}
	else
	{
		DecimalFormat  df = new DecimalFormat ("#.##");
		ArrayList<MenuItem> item = order.getMenuItemsList();
		ArrayList<Integer> quantityMenuItems = order.getQuantityMenuItems();
		ArrayList<PromotionalPackage> set = order.getPromotionalPackagesList();
		ArrayList<Integer> quantityPackages = order.getQuantityPackages();
		
		System.out.println("================= Loh Yeh Moh Yeh Resto =================");
		System.out.println("Staff Name      : " + order.getStaff() );
		System.out.println("Table ID        : " + order.getTableId() );
		System.out.println("Date(DD/MM/YYYY): " + ( order.getTime() ).getDate() + "/" + ( order.getTime() ).getMonth() + "/" + ( ( order.getTime() ).getYear() + 1900 ));
		System.out.println("---------------------------------------------------------");
		
		if(item.size() > 0){
			System.out.println("Menu Items  : ");
			
			for(int i = 0; i < item.size(); i++ )
			{
				System.out.println("    "  + (quantityMenuItems.get(i)) + "x " + (item.get(i)).getName() + "	" + ( (quantityMenuItems.get(i)) * (item.get(i)).getPrice() ));
			}
		}
		
		if(set.size() > 0){
			System.out.println("---------------------------------------------------------");
			System.out.println("Promotional Set Packages  : ");
			
			for(int i = 0; i < set.size(); i++ )
			{
				System.out.println("    " + (quantityPackages.get(i)) + "x " + (set.get(i)).getName() + "	" + ( (quantityPackages.get(i)) * (set.get(i)).getPrice() ));
			}
		}
		
		System.out.println("---------------------------------------------------------");
		System.out.println("SubTotal               : " + df.format(( order.getTotalPrice() )));
		System.out.println("Taxes                  : " + df.format(( order.getTotalPrice() * 0.07)));
		System.out.println("---------------------------------------------------------	");
		System.out.println("TOTAL                  : " + df.format((order.getTotalPrice() ) * 1.07 ));
		System.out.println("============= Thank you! Please come again! =============");
		
		mOrderController.deallocateTable(order);
	}
	return;
}
}
