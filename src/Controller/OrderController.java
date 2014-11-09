/*
 * Author: Tran Vu Xuan Nhat
 * Matric No: U1323058D
 */
package Controller;

import java.util.ArrayList;

import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;

public class OrderController {
	private ArrayList<Order> listOrder;
	public OrderController(){
		listOrder = Order.mOrderList;
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
			String mCustomerName, int mTableId) {
		// TODO Auto-generated method stub
		Order order = new Order(mStaffName, orderMenuItemList, orderPackageList, mCustomerId, mCustomerName, mTableId);
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
	public void saveToDB() {
		// TODO Auto-generated method stub
		
	}

}
