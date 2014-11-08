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
	/*
	 * Constructor
	 */
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
	}
	public void saveToDB() {
		// TODO Auto-generated method stub
		
	}

}
