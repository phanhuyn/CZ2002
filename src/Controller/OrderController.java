/*
 * Author: Tran Vu Xuan Nhat
 * Matric No: U1323058D
 */
package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;
import UI.OrderUI;

public class OrderController {
	private ArrayList<Order> listOrder;
	private OrderUI mOrderUI;
	
	public OrderController(){
		listOrder = Order.mOrderList;
	}
	
	
	 
	public void run() {
		int choice = 0;
		while (choice <= 5){
			System.out.println("1. Create Order");
			System.out.println("2. View Order.");
			System.out.println("3. Print Invoice.");
			System.out.println("4. Print Sales Revenue Report by Date.");
			System.out.println("5. Print Sales Revenue Report by Month.");
			System.out.println("6. Quit.");
			
			System.out.println("Select your option: ");
			
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			
			switch (choice) {
			case 1: break;
			case 2: break;
			case 3:	mOrderUI.printInvoice();
				break;
			case 4: break;
			case 5: break;
			case 6: break;
			}
		}
		return;
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
	}
	public void saveToDB() {
		// TODO Auto-generated method stub
		
	}

}
