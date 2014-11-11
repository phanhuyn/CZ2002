package Entity;

import java.util.ArrayList;

public class Restaurant {

	private Menu mMenu;
	private ArrayList<Table> mTableList;
	private ArrayList<Staff> mStaffList;
	private ArrayList<Reservation> mReservationList;

	public Restaurant() {
		mMenu = new Menu();
		mTableList = new ArrayList<Table>();
		mStaffList = new ArrayList<Staff>();
		mReservationList = new ArrayList<Reservation>();
	}

	public Menu getMenu() {
		return mMenu;
	}

	public ArrayList<Table> getTableList() {
		return mTableList;
	}

	public void setTableList(ArrayList<Table> tableList) {
		mTableList = tableList;
	}

	public ArrayList<Staff> getStaffList() {
		return mStaffList;
	}

	public void setStaffList(ArrayList<Staff> staffList) {
		mStaffList = staffList;
	}
	
	public ArrayList<Reservation> getReservationList() {
		return mReservationList;
	}
	
	public Table getTableById (int id){
		return mTableList.get(id);
	}
	
	public void addStaff(Staff staff){
		mStaffList.add(staff);
	}

}
