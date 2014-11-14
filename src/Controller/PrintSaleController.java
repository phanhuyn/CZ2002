/**
  @author Stefan
  @version 1.0
  @since 2014-11-10
 */
/**
  @author Stefan
  @version 1.0
  @since 2014-11-10
 */
package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

import UI.MainUI;
import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;

public class PrintSaleController {
	
	/**
	 * Called when the user choose print sale option.
	 * Ask for the user's choice for the report and call the particular function.
	 */
	
	public void run(){
		int choice = 0;
		while (choice <= 2) {
			printSaleOption();
			Scanner sc = new Scanner(System.in);
			choice = MainUI.getInt("Select your option: ");
			
			switch (choice) {
			case 1:
				DailyReport();
				break;
			case 2:
				MonthlyReport();
				break;
			}
		}
		return;
	}
	
	/**
	 * The List of table.
	 */
	
	private ArrayList<Order> listOrder;
	
	/**
	 * The constructor.
	 * @param listOrder getting the list of order.
	 */
	public PrintSaleController(ArrayList<Order> listOrder){
		this.listOrder = listOrder;
	}
	
	/**
	 * Returns a list of the order that match the date from the user input.
	 * @param day the date from the user input.
	 * @param month the month requested from the user.
	 * @param year the year of the order to be requested.
	 * @return the list of the order that fits the criteria of the date.
	 */
	public ArrayList<Order> findOrderByDate(int day, int month, int year){
		ArrayList<Order> matchDayOrderList = new ArrayList<Order>();
		for(Order order : listOrder){
			Date date = order.getTime();
			if(date.getDate() == day && date.getMonth() == (month-1) && date.getYear() == (year-1900)){
				matchDayOrderList.add(order);
			}
		}

		return matchDayOrderList;
	}
	/**
	 * returns a list of the order from the month and year according to the user input.
	 * @param month the month requested from the user.
	 * @param year the year of the order to be requested.
	 * @return the list of the order that fits the criteria of the month and year.
	 */
	public ArrayList<Order> findOrderByMonth(int month, int year){
		ArrayList<Order> matchDayOrderList = new ArrayList<Order>();
		for(Order order : listOrder){
			Date date = order.getTime();
			if( date.getMonth() == (month-1) && date.getYear() == (year-1900)){
				matchDayOrderList.add(order);
			}
		}
		return matchDayOrderList;
	}
	
	/**
	 * this function is called when the staff want to get the report of particular day.
	 * print the daily report according to the order from the list available.
	 * @param order the list of the order that match the user input's requirements.
	 * @param d the date from the user input.
	 * @param m the month requested from the user.
	 * @param y the year of the order to be requested.
	 */
	
	public void RevenueByDate(ArrayList<Order> order,int d, int m, int y){
		double TotalPrice= 0;
		if(order == null || order.size() == 0){
			System.out.println("No order found on that date!");
			return;
		}
		else
		{
			System.out.println("===================== Daily Report =========================");
			for(int i = 0 ; i < order.size(); ++i)
			{
				System.out.println("============================================================");
				System.out.println("Customer ID				:" + order.get(i).getCustomerId());
				ArrayList<MenuItem> menuItems = (order.get(i)).getMenuItemsList();
				ArrayList<Integer> quantityMenuItems = (order.get(i)).getQuantityMenuItems();
				System.out.println("Menu items				: ");
				for(int j = 0; j < menuItems.size(); ++j)
				{
					System.out.printf((j+1) + ". " + quantityMenuItems.get(j)+" x "+ menuItems.get(j).getName() + "	: $%.2f\n" + menuItems.get(j).getPrice());
				}
				
				ArrayList<PromotionalPackage> packages = (order.get(i)).getPromotionalPackagesList();
				ArrayList<Integer> quantityPackage = (order.get(i)).getQuantityPackages();
				System.out.println("Promotional packages	: ");
				for(int k = 0; k < packages.size(); ++k)
				{
					System.out.printf((k+1) + ". " + quantityPackage.get(k) + " x " + packages.get(k).getName() + "       Price	: " + packages.get(k).getPrice());
				}
				TotalPrice += (order.get(i)).getTotalPrice();
				System.out.println("------------------------------------------------------------");
				System.out.printf("Total Price				:  $%.2f\n" +order.get(i).getTotalPrice());
			}
		}
		System.out.println("------------------------------------------------------------");
		System.out.printf("Overall revenue for " + d +" " + m +" "+ y + "		:  $%.2f\n" + TotalPrice);  
		System.out.println("============================================================");
}
	/**
	 * print the monthly report according to the order from the list available.
	 * @param order the list of the order that match the user input's requirements.
	 */
	public void RevenueByMonth(ArrayList<Order> order){
	  	double OverallPrice = order.get(0).getTotalPrice();
	  	double Max = order.get(0).getTotalPrice();
	  	double Min = order.get(0).getTotalPrice();
		Calendar DateMax = Calendar.getInstance();
		Calendar DateMin = Calendar.getInstance();
		DateMax.set(Calendar.MONTH, order.get(0).getTime().getMonth()+1);
		DateMax.set(Calendar.DATE, order.get(0).getTime().getDate());
		DateMax.set(Calendar.YEAR, order.get(0).getTime().getYear()+1900);
		DateMin.set(Calendar.MONTH, order.get(0).getTime().getMonth()+1);
		DateMin.set(Calendar.DATE, order.get(0).getTime().getDate());
		DateMin.set(Calendar.YEAR, order.get(0).getTime().getYear()+1900);
		
		String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			
		if(order == null|| order.size() == 0){
			System.out.println("No order found on that month!");
			return;
		}
		else{
			for(int i = 1; i < order.size(); ++i)
			{
				if(Max < (order.get(i)).getTotalPrice()){
					Max = (order.get(i)).getTotalPrice();
					DateMax.set(Calendar.MONTH, order.get(i).getTime().getMonth()+1);
					DateMax.set(Calendar.DATE, order.get(i).getTime().getDate());
					DateMax.set(Calendar.YEAR, order.get(i).getTime().getYear()+1900);
				}
				else if(Min > order.get(i).getTotalPrice()){
					Min = (order.get(i)).getTotalPrice();
					DateMin.set(Calendar.MONTH, order.get(i).getTime().getMonth()+1);
					DateMin.set(Calendar.DATE, order.get(i).getTime().getDate());
					DateMin.set(Calendar.YEAR, order.get(i).getTime().getYear()+1900);
				}
				OverallPrice += (order.get(i)).getTotalPrice();
			}
		}
		System.out.println("===================== Monthly Report =======================");
			System.out.println("Top sale of the month in " + DateMax.get(Calendar.DATE)+" "+monthNames[ DateMax.get(Calendar.MONTH) ] +" " +DateMax.get(Calendar.YEAR)+ " is: $"+	Max);
			System.out.println("Least sale of the month in " + DateMin.get(Calendar.DATE)+" "+monthNames[ DateMin.get(Calendar.MONTH) ] +" " +DateMin.get(Calendar.YEAR)+ " is: $" + Min);		
			System.out.println("------------------------------------------------------------");
			System.out.printf("Overall Revenue:  $%.2f\n" + OverallPrice); 
			System.out.println("============================================================");
		} 
/**
 * Print the list of main functionality
 */
	public void printSaleOption (){
		System.out.println("#################################");
		System.out.println("#         Print Sale Option     #");
		System.out.println("#      1. Daily Report          #");
		System.out.println("#      2. Monthly Report        #");
		System.out.println("#      3. Return to Main Menu   #");
		System.out.println("#################################");
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
	ArrayList<Order> order = findOrderByDate(d, m, y);
	RevenueByDate(order,d,m,y);
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
	ArrayList<Order> order = findOrderByMonth(m,y);
	RevenueByMonth(order);
	return;
  }
}

