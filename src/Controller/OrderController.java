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
import Entity.Reservation;
import Entity.Table;

public class OrderController {
	private ArrayList<Order> listOrder;
	private ArrayList<Table> listTable;
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
			ArrayList<MenuItem> orderMenuItemList,ArrayList<Integer> quantityMenuItems,
			ArrayList<PromotionalPackage> orderPackageList,ArrayList<Integer> quantityPackage, int mCustomerId,
			String mCustomerName, int mTableId, boolean isSetDate, Date date) {
		// TODO Auto-generated method stub
		//create the order
		Order order = new Order(mStaffName, orderMenuItemList, quantityMenuItems, orderPackageList, quantityPackage, mCustomerId, mCustomerName, mTableId);
		//check if date is set manual
		if(isSetDate){
			order.setTime(date);
		}
		//allocate table for the order, time slot will be 1 hour
		Table mTable = null;
		for(Table table: listTable){
			if(table.getId() == mTableId){
				mTable = table;
				break;
			}
		}
		Date end = new Date();
		end.setDate(date.getDate());
		end.setMonth(date.getMonth());
		end.setYear(date.getYear());
		end.setHours(date.getHours() + 1);
		mTable.allocate(new Reservation(mTable, mCustomerName, mCustomerName, mTableId, date, end));
			
		listOrder.add(order);
		
		showOrder(order);
	}
	
	public void setListTable(ArrayList<Table> mTableList){
		listTable = mTableList;
	}

	public void deallocateTable(Order order) {
		// TODO Auto-generated method stub
		Table mTable = null;
		for(Table table: listTable){
			if(table.getId() == order.getTableId()){
				mTable = table;
				break;
			}
		}
		Reservation which = null;
		for(Reservation reservation : mTable.getReservation(order.getCustomerName(),order.getCustomerName())){
			if(reservation.getStartTime() == order.getTime()){
				which = reservation;
				break;
			}
		}
		mTable.removeReservation(which);
		
	}
	
	public void showOrder(Order order){
		System.out.println("Order is made, below is the information: ");
		System.out.println("Order ID: " + order.getId());
		System.out.println("Staff created order: " + order.getStaff());
		System.out.println("Date: " + order.getTime());
		System.out.println("Customer: "+ order.getCustomerName() + "       ID:"+order.getCustomerId());
		System.out.println("Table: " + order.getTableId());
		System.out.println("Order list: ");
		ArrayList<MenuItem> menuItems = order.getMenuItemsList();
		ArrayList<Integer> quantityMenuItems = order.getQuantityMenuItems();
		ArrayList<PromotionalPackage> packages = order.getPromotionalPackagesList();
		ArrayList<Integer> quantityPackage = order.getQuantityPackages();
		if(menuItems.size()>0){
			System.out.println("Menu items: ");
		}
		
		for(int i = 0; i < menuItems.size(); ++i){
			System.out.println((i+1) + ". " + quantityMenuItems.get(i)+" x "+ menuItems.get(i).getName() + "       Price:" + menuItems.get(i).getPrice());
		}
		if(packages.size() > 0){
			System.out.println("Promotional packages: ");
		}
		for(int i = 0; i < packages.size(); ++i){
			System.out.println((i+1) + ". " + quantityPackage.get(i) + " x " + packages.get(i).getName() + "       Price:" + packages.get(i).getPrice());
		}
		System.out.println("Total Price: " + order.getTotalPrice());
	}
}
