/*
 * Author: Tran Vu Xuan Nhat
 * Matric No: U1323058D
 */
package Controller;

import java.util.ArrayList;
import java.util.Date;

import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;

public class OrderController {
	private ArrayList<Order> listOrder;
	public OrderController(){
		listOrder = Order.mOrderList;
	}
	
	/*
	 * this method use to find order that match the day user input
	 */
	public ArrayList<Order> findOrderByTime(int day, int month, int year){
		ArrayList<Order> matchDayOrderList = new ArrayList<Order>();
		for(Order order : listOrder){
			Date date = order.getTime();
			if(date.getDay() == day && date.getMonth() == (month-1) && date.getYear() == (year-1900)){
				matchDayOrderList.add(order);
			}
		}
		return matchDayOrderList;
	}
	
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
	
	public Order find(String customerName, int mId) {
		// TODO Auto-generated method stub
		for(Order order: listOrder){
			if(order.getCustomerName().compareTo(customerName) == 0 && order.getId()==mId){
		        return order;		
			}
		}
		return null;
	}
	
	
	public ArrayList<Order> getListOrder(){
		return listOrder;
	}
	public void createNewOrder(String mStaffName,
			ArrayList<MenuItem> orderMenuItemList,
			ArrayList<PromotionalPackage> orderPackageList, int mCustomerId,
			String mCustomerName, int mTableId, boolean isSetDate, Date date) {
		// TODO Auto-generated method stub
		Order order = new Order(mStaffName, orderMenuItemList, orderPackageList, mCustomerId, mCustomerName, mTableId);
		if(isSetDate){
			order.setTime(date);
		}
		listOrder.add(order);
		System.out.println("Order is made, below is the information: ");
		System.out.println("Order ID: " + order.getId());
		System.out.println("Staff created order: " + order.getStaff());
		System.out.println("Date: " + order.getTime());
		System.out.println("Customer: "+ order.getCustomerName() + "       ID:"+order.getCustomerId());
		System.out.println("Table: " + order.getTableId());
		System.out.println("Order list: ");
		System.out.println("Menu items: ");
		ArrayList<MenuItem> menuItems = order.getMenuItemsList();
		for(int i = 0; i < menuItems.size(); ++i){
			System.out.println("- Menu item " + (i+1) + " : " + menuItems.get(i).getName() + "       Price:" + menuItems.get(i).getPrice());
		}
		System.out.println("Promotional packages: ");
		ArrayList<PromotionalPackage> packages = order.getPromotionalPackagesList();
		for(int i = 0; i < packages.size(); ++i){
			System.out.println("- PromotionalPackage " + (i+1) + " : " + packages.get(i).getName() + "       Price:" + menuItems.get(i).getPrice());
		}
		System.out.println("Total Price: " + order.getTotalPrice());
	}

}
