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
import Entity.Table;

/**
 * @author Zillion Govin
 *
 */
public class OrderUI {
	
	  /**
	 * refer to the OrderController
	 */
	private OrderController mOrderController;
	
	  /**
	 * refer to the Restaurant
	 */
	private Restaurant mRestaurant;
	  
	/**
	 * Name of the Customer
	 */
	private String customerName;
	
	/**
	 * Identification for table
	 */
	private int TableID;
	
	/**
	 * for constructing border
	 */
	private final String spacing = "######################################";
  
  
	/**
	 * Creates OrderUI with the given restaurant
	 * @param restaurant Restaurant class reference
	 */
	public OrderUI(Restaurant restaurant) {
	  	mOrderController = new OrderController();
	  	mRestaurant = restaurant;
	  	ArrayList<Table> listTable = mRestaurant.getTableList();
	  	mOrderController.setListTable(listTable);
	  }
  
	/**
	 * run the order UI
	 * prompt user to with options to create, view, edit order
	 * allow user to print invoice of a specific table
	 */
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
	 	
  
	/**
	 * Ask for customer name and table ID
	 * Find the related order
	 * Print the details of the order (total price, items, staff name, date)
	 */
	public void printInvoice(){
	  	Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Customer Name : ");
		customerName = sc.next();
	
		TableID = MainUI.getInt("Enter Table ID : ");
	
		Order order = mOrderController.find(customerName, TableID) ;
		if(order == null)
		{
			System.out.println("No order found!");
		}
		else
		{
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
					System.out.printf("    %dx %-30s $%3.2f\n"  , (quantityMenuItems.get(i)) , (item.get(i)).getName() , ( (quantityMenuItems.get(i)) * (item.get(i)).getPrice() ));
				}
			}
			
			if(set.size() > 0){
				System.out.println("---------------------------------------------------------");
				System.out.println("Promotional Set Packages  : ");
				
				for(int i = 0; i < set.size(); i++ )
				{
					System.out.printf("    %dx %-30s $%3.2f\n" , (quantityPackages.get(i)) , (set.get(i)).getName() , ( (quantityPackages.get(i)) * (set.get(i)).getPrice() ));
				}
			}
			
			System.out.println("---------------------------------------------------------");
			System.out.printf("SubTotal    :                         $%.2f\n" ,( order.getTotalPrice() ));
			System.out.printf("Taxes       :                         $%.2f\n" ,( order.getTotalPrice() * 0.07));
			System.out.println("---------------------------------------------------------	");
			System.out.printf("TOTAL       :                         $%.2f\n" , (order.getTotalPrice() ) * 1.07 );
			System.out.println("\n============= Thank you! Please come again! =============\n");
			
			mOrderController.deallocateTable(order);
		}
		return;
	}
}
