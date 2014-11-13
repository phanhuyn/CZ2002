/*
 * Author: Tran Vu Xuan Nhat
 * Matric No: U1323058D
 */
package Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;
import Entity.Reservation;
import Entity.Restaurant;
import Entity.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderController.
 * @author Tran Vu Xuan Nhat
 */
public class OrderController {
	
	/** The list of order. */
	private ArrayList<Order> listOrder;
	
	/** The list of table in restaurant. */
	private ArrayList<Table> listTable;
	
	/**
	 * Instantiates a new order controller.
	 *
	 * @param restaurant the data of restaurant
	 */
	public OrderController(Restaurant restaurant){
		listOrder = restaurant.getOrderList();
		listTable = restaurant.getTableList();
	}

	/*
	 * this method use to find order that match the day user input
	 */
	
	/**
	 * Find the order by customer name and id of the table.
	 *
	 * @param customerName the customer name
	 * @param mTableId the table id
	 * @return Returns the order found
	 */
	public Order find(String customerName, int mTableId) {
		// TODO Auto-generated method stub
		for(Order order: listOrder){
			if(order.getCustomerName().compareTo(customerName) == 0 && order.getTableId()==mTableId){
		        return order;		
			}
		}
		return null;
	}
	
	/**
	 * Gets the list of order.
	 *
	 * @return Returns the list of order
	 */
	public ArrayList<Order> getListOrder(){
		return listOrder;
	}
	
	/**
	 * Creates the new order.
	 *
	 * @param mStaffName the staff name
	 * @param orderMenuItemList the order menu item list
	 * @param quantityMenuItems the quantity menu items
	 * @param orderPackageList the order package list
	 * @param quantityPackage the quantity package
	 * @param mCustomerId the customer id
	 * @param mCustomerName the customer name
	 * @param mTableId the table id
	 * @param isSetDate the boolean value states that date of making order is set automatically
	 * @param date the date input manually
	 * @param hasMembership the boolean value state that customer has membership
	 */
	public void createNewOrder(String mStaffName,
			ArrayList<MenuItem> orderMenuItemList,ArrayList<Integer> quantityMenuItems,
			ArrayList<PromotionalPackage> orderPackageList,ArrayList<Integer> quantityPackage, int mCustomerId,
			String mCustomerName, int mTableId, boolean isSetDate, Date date, int hasMembership) {
		// TODO Auto-generated method stub
		//create the order
		Order order = new Order(mStaffName, orderMenuItemList, quantityMenuItems, orderPackageList, quantityPackage, mCustomerId, mCustomerName, mTableId);
		//check if date is set manual
		if(isSetDate){
			order.setTime(date);
		}
		date = order.getTime();
		//allocate table for the order, time slot will be 1 hour
		Table mTable = null;
		for(Table table: listTable){
			if(table.getId() == mTableId){
				mTable = table;
				break;
			}
		}
		Date end = Calendar.getInstance().getTime();
		end.setHours(end.getHours()+1);
		mTable.allocate(new Reservation(mTable, mCustomerName, mCustomerName, mTableId, date, end));
		/*
		 * discount for membership customer
		 */
		if(hasMembership == 1){
			order.setTotalPrice(order.getTotalPrice()*0.9);
		}
		listOrder.add(order);
		
		showOrder(order);
	}
	
	/**
	 * Sets the list of table.
	 *
	 * @param mTableList the new list of table
	 */
	public void setListTable(ArrayList<Table> mTableList){
		listTable = mTableList;
	}

	/**
	 * Deallocate table after print invoice.
	 *
	 * @param order the order linked to table to be deallocate
	 */
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
	
	/**
	 * Show normal order.
	 *
	 * @param order the order to be showed
	 */
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

	/**
	 * Gets the table list.
	 *
	 * @return Returns the table list
	 */
	public ArrayList<Table> getTableList() {
		// TODO Auto-generated method stub
		return listTable;
	}
}
