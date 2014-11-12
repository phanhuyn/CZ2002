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

import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;

public class PrintSaleController {
	
	/**
	 * The List of table.
	 */
	
	private ArrayList<Order> listOrder;
	
	/**
	 * The constructor.
	 * @param listOrder getting the list of order.
	 */
	public PrintSaleController(){
		listOrder = Order.mOrderList;
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
			if(date.getDay() == day && date.getMonth() == (month-1) && date.getYear() == (year-1900)){
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
		int TotalPrice= 0;
		
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
				ArrayList<Integer> quantityMenuItems = (order.get(i)).getQuantityMenuItems();
				//quantityMenuItems.get(j)+
				//quantityPackage.get(k) +
				System.out.println("Menu items: ");
				for(int j = 0; j < menuItems.size(); ++j)
				{
					System.out.println((j+1) + ". " + " x "+ (menuItems.get(j)).getName() + "       Price:" + (menuItems.get(j)).getPrice());
				}
				
				ArrayList<PromotionalPackage> packages = (order.get(i)).getPromotionalPackagesList();
				ArrayList<Integer> quantityPackage = (order.get(i)).getQuantityPackages();
				System.out.println("Promotional packages: ");
				for(int k = 0; k < packages.size(); ++k)
				{
					System.out.println((k+1) + ". " +  (packages.get(k)).getName() + "       Price:" + (packages.get(k)).getPrice());
				}
				TotalPrice += (order.get(i)).getTotalPrice();
			}
		}
		System.out.println("Overall revenue for " + d +" " + m +" "+ y + " : " + TotalPrice);  

}
	/**
	 * print the monthly report according to the order from the list available.
	 * @param order the list of the order that match the user input's requirements.
	 * @param m the month requested from the user.
	 * @param y the year of the order to be requested.
	 */
public void RevenueByMonth(ArrayList<Order> order){
	  	double OverallPrice = 0;
	  	double Max = 0;
	  	double Min = 0;
		Calendar DateMax = Calendar.getInstance();
		Calendar DateMin = Calendar.getInstance();
		String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			
		if(order == null){
			System.out.println("No order found on that month!");
			return;
		}
		else{
			for(int i = 0; i < order.size(); ++i)
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
			
			System.out.println("Top sale of the month in " + DateMax.get(Calendar.DATE)+" "+monthNames[ DateMax.get(Calendar.MONTH) ] +" " +DateMax.get(Calendar.YEAR)+ " is: "+	Max);
			System.out.println("Least sale of the month in " + DateMin.get(Calendar.DATE)+" "+monthNames[ DateMin.get(Calendar.MONTH) ] +" " +DateMin.get(Calendar.YEAR)+ " is: " + Min);		
			System.out.println("Overall Total Price: " + OverallPrice); 
		} 
}

