/*
 * Author: Tran Vu Xuan Nhat
 * Matric No: U1323058D
 */

package Entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Data.DataAdapter;
import Data.WriteToTxt;
public class Order implements WriteToTxt{
	/*
	 * mStaffI indicates staff that who created order
	 * mItems is the list of menu items included in order
	 * mPackages is the list of promotional packages included in order
	 * mCustomerID is the ID of customer that do the order
	 * mCustomerName is the name of customer
	 * mTableId indicates the id of the table for the order
	 * mTotalPrice is the total price that need to pay for the order
	 * mTime indicates the time that user make order
	 */
	private int mOrderId;
	private String mStaff;
	private ArrayList<MenuItem> mItems;
	private ArrayList<PromotionalPackage> mPackages;
	private int mCustomerId;
	private String mCustomerName;
	private int mTableId;
	private double mTotalPrice;
	private Date mTime;
	
	public static ArrayList<Order> mOrderList = loadFromDb();
	
	/*
	 * constructor for Class 
	 */
	public Order(String Staff,ArrayList<MenuItem> Items,ArrayList<PromotionalPackage> Packages,int CustomerId, String CustomerName,int TableId){
		mOrderId = mOrderList.size()+1;
		mStaff = Staff;
		mCustomerId = CustomerId;
		mTableId = TableId;
		mCustomerName = CustomerName;
		mTotalPrice = 0;
		mItems = new ArrayList<MenuItem>();
		mPackages = new ArrayList<PromotionalPackage>();
		mTime = Calendar.getInstance().getTime();
		
		for(int i = 0; i < Items.size(); ++i){
			mItems.add(Items.get(i));
		    mTotalPrice+= Items.get(i).getPrice();
		}
		
		for(int i = 0; i < Packages.size();++i){
			mPackages.add(Packages.get(i));
			mTotalPrice+= Packages.get(i).getPrice();
		}
	 
	}
	
	public static ArrayList<Order> loadFromDb() {
		// TODO Auto-generated method stub
		//DataAdapter mDataApdapter = new DataAdapter();
		//return mDataAdapter.loadDataFromDb("Order");
		return new ArrayList<Order>();
	}

	/*
	 * Accessor methods of Class 
	 */
	public int getId() {
		return mOrderId;
	}

	public String getStaff(){
		return mStaff;
	}
	
	public ArrayList<MenuItem> getMenuItemsList(){
		return mItems;
	}
	
	public ArrayList<PromotionalPackage> getPromotionalPackagesList(){
		return mPackages;
	}
	
	public int getCustomerId(){
		return mCustomerId;
	}
	
	public int getTableId(){
		return mTableId;
	}
	
	public String getCustomerName(){
		return mCustomerName;
	}

	public double getTotalPrice() {
		return mTotalPrice;
	}
	
	public Date getTime() {
		return mTime;
	}
	
	/*
	 * Mutator methods for Class
	 */

	public void setStaffInCharge(String staff){
		mStaff = staff;
	}
	
	public void setCustomerId(int customerId){
		mCustomerId = customerId;
	}
	
	public void setCustomerName(String customerName){
		mCustomerName = customerName;
	}
	public void setTableId(int tableId){
		mTableId = tableId;
	}

	public void setTime() {
		mTime = Calendar.getInstance().getTime();
	}
	/*
	 * add methods to add MenuItem or PromotionalPackage to Order
	 */
	public void addMenuItem(MenuItem item){
		mItems.add(item);
	}
		
	public void addPromotionalPackage(PromotionalPackage pPackage){
		mPackages.add(pPackage);
	}
	

	public void reCalculatePrice() {
		// TODO Auto-generated method stub
		mTotalPrice = 0;
		for(int i = 0; i < mItems.size(); ++i){
			mItems.add(mItems.get(i));
		    mTotalPrice+= mItems.get(i).getPrice();
		}
		
		for(int i = 0; i < mPackages.size();++i){
			mPackages.add(mPackages.get(i));
			mTotalPrice+= mPackages.get(i).getPrice();
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}



}