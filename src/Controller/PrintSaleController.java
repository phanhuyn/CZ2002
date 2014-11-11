package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;

public class PrintSaleController {
	private ArrayList<Order> listOrder;
	public PrintSaleController(){
		listOrder = Order.mOrderList;
	}
	
	/*
	 * this method use to find order that match the day user input
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
	/*
	 * this method use to find order that match the month user input
	 */
	public ArrayList<Order> findOrderByMonth(int month, int year){
		ArrayList<Order> matchDayOrderList = new ArrayList<Order>();
		for(Order order : listOrder){
			Date date = order.getTime();
			if( date.getMonth() == month && date.getYear() == year){
				matchDayOrderList.add(order);
			}
		}
		return matchDayOrderList;
	}

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
				}
				else if(Min > order.get(i).getTotalPrice()){
					Min = (order.get(i)).getTotalPrice();
					DateMin.set(Calendar.MONTH, order.get(i).getTime().getMonth()+1);
				}
				OverallPrice += order.get(i).getTotalPrice();
			}
		}
			
			System.out.println("Top sale of the month in " + monthNames[ DateMax.get(Calendar.MONTH) ] + "is: "+	Max);
			System.out.println("Least sale of the month in " + monthNames[ DateMin.get(Calendar.MONTH) ] + "is: " + Min);		
			System.out.println("Overall Total Price: " + OverallPrice); 
		} 
}
