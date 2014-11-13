package Entity;

import java.util.ArrayList;

/**
 * @author Zillion Govin
 *
 */
public class Restaurant {

	
	/**
	 * refer to Menu entity
	 */
	private Menu mMenu;
	
	/**
	 * refer to Table entity
	 */
	private ArrayList<Table> mTableList;
	
	/**
	 * refer to Staff entity
	 */
	private ArrayList<Staff> mStaffList;
	
	/**
	 * refer to Order entity
	 */
	private ArrayList<Order> mOrderList;
	
	/**
	 * create a new Restaurant
	 * include new Menu, Table, Staff, and Order
	 */
	public Restaurant() {
		mMenu = new Menu();
		mTableList = new ArrayList<Table>();
		mStaffList = new ArrayList<Staff>();
		mOrderList = new ArrayList<Order>();
	}

	/**
	 * @return this Restaurant's Menu
	 */
	public Menu getMenu() {
		return mMenu;
	}

	/**
	 * @return this Restaurant's TableList
	 */
	public ArrayList<Table> getTableList() {
		return mTableList;
	}

	/**
	 * modify This Restaurant's TableList
	 * @param tableList This Restaurant's new TableList
	 */
	public void setTableList(ArrayList<Table> tableList) {
		mTableList = tableList;
	}

	/**
	 * @return This Restaurant's Staff
	 */
	public ArrayList<Staff> getStaffList() {
		return mStaffList;
	}

	/**
	 * edit This Restaurant's StaffList
	 * @param staffList This Restaurant's new StaffList
	 */
	public void setStaffList(ArrayList<Staff> staffList) {
		mStaffList = staffList;
	}
	
	/**
	 * @param id tableID that needs to be searched
	 * @return This Restaurant's specific table
	 */
	public Table getTableById (int id){
		return mTableList.get(id);
	}
	
	/**
	 * add new staff to This Retaurant's StaffList
	 * @param staff This Restaurant's new Staff
	 */
	public void addStaff(Staff staff){
		mStaffList.add(staff);
	}
	
	/**
	 * @return This Restaurant's list of Order
	 */
	public ArrayList<Order> getOrderList() {
		return mOrderList;
	}

	/**
	 * store the new OrderList
	 * @param orderList This Restaurant's new OrderList
	 */
	public void setOrderList(ArrayList<Order> orderList) {
		mOrderList = orderList;
	}
}
