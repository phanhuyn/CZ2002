/*
 * Author: Tran Vu Xuan Nhat
 * Matric No: U1323058D
 */

package Entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Data.DataAdapter;
import Data.WriteToTxt;
// TODO: Auto-generated Javadoc

/**
 * The Class Order a particular order used in application.
 *
 * @author Tran Vu Xuan Nhat
 */
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
	/** The order id. */
	private int mOrderId;
	
	/** The staff. */
	private String mStaff;
	
	/** The menu items. */
	private ArrayList<MenuItem> mItems;
	
	/** The promotional packages. */
	private ArrayList<PromotionalPackage> mPackages;
	
	/** The customer id. */
	private int mCustomerId;
	
	/** The customer name. */
	private String mCustomerName;
	
	/** The table id. */
	private int mTableId;
	
	/** The total price. */
	private double mTotalPrice;
	
	/** The time. */
	private Date mTime;
	
	/** The quantity menu items. */
	ArrayList<Integer> quantityMenuItems;
	
	/** The quantity packages. */
	ArrayList<Integer> quantityPackages;
	
	/*
	 * constructor for Class 
	 */
	/**
	 * Instantiates a new order.
	 *
	 * @param staff The name of the staff
	 * @param items The list of menu items
	 * @param quantityMenuItemList the quantity menu item list
	 * @param packages the packages
	 * @param quantityPackageList the quantity package list
	 * @param CustomerId the customer id
	 * @param CustomerName the customer name
	 * @param TableId the table id
	 */
	public Order(String staff,ArrayList<MenuItem> items,ArrayList<Integer> quantityMenuItemList,ArrayList<PromotionalPackage> packages,ArrayList<Integer> quantityPackageList,int CustomerId, String CustomerName,int TableId){
		
		mStaff = staff;
		mCustomerId = CustomerId;
		mTableId = TableId;
		mCustomerName = CustomerName;
		mTotalPrice = 0;
		mItems = new ArrayList<MenuItem>();
		mPackages = new ArrayList<PromotionalPackage>();
		mTime = Calendar.getInstance().getTime();
		quantityMenuItems =  new ArrayList<Integer>();
		quantityPackages = new ArrayList<Integer>();
		
		int position = -1;
		for(int i = 0; i < items.size(); ++i){
			position = findMenuItem(items.get(i).getName());
			if(position == -1){
				mItems.add(items.get(i));
				quantityMenuItems.add(quantityMenuItemList.get(i));
			}
			else{
				quantityMenuItems.set(position, quantityMenuItems.get(position) + quantityMenuItemList.get(i));
			}
			mTotalPrice+= items.get(i).getPrice()*quantityMenuItemList.get(i);
		}
		
		position = -1;
		for(int i = 0; i < packages.size();++i){
			position = findPackage(packages.get(i).getName());
			if(position == -1){
				mPackages.add(packages.get(i));
				quantityPackages.add(quantityPackageList.get(i));
			}
			else{;
				quantityPackages.set(position, quantityPackages.get(position)+ quantityPackageList.get(i));
			}
			mTotalPrice+= packages.get(i).getPrice()*quantityPackageList.get(i);
		}
		
	}
	
	/**
	 * Find package.
	 *
	 * @param name of package to search for in order
	 * @return Returns the position of the package in the package list of the order. If not found, return -1
	 */
	private int findPackage(String name) {
		// TODO Auto-generated method stub
		for(int i = 0; i<mPackages.size(); ++i){
			if(mPackages.get(i).getName().compareTo(name) == 0){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Find menu item.
	 *
	 * @param name of menu item to search for in order
	 * @return Returns the position of the menu item in the menu item list of the order. If not found, return -1
	 */
	private int findMenuItem(String name) {
		// TODO Auto-generated method stub
		for(int i = 0; i<mItems.size(); ++i){
			if(mItems.get(i).getName().compareTo(name) == 0){
				return i;
			}
		}
		return -1;
	}
	

	/*
	 * Accessor methods of Class 
	 */
	/**
	 * Gets the id of order.
	 *
	 * @return Returns the id of order
	 */
	public int getId() {
		return mOrderId;
	}

	/**
	 * Gets the name of the staff in charge of this order.
	 *
	 * @return Returns the name of the staff in charge of this order.
	 */
	public String getStaff(){
		return mStaff;
	}
	
	/**
	 * Gets the menu items list of the order.
	 *
	 * @return Returns the menu items list of the order
	 */
	public ArrayList<MenuItem> getMenuItemsList(){
		return mItems;
	}
	
	/**
	 * Gets the promotional packages list of the order.
	 *
	 * @return Returns the promotional packages list of the order
	 */
	public ArrayList<PromotionalPackage> getPromotionalPackagesList(){
		return mPackages;
	}
	
	/**
	 * Gets the customer id.
	 *
	 * @return Returns the customer id
	 */
	public int getCustomerId(){
		return mCustomerId;
	}
	
	/**
	 * Gets the table id.
	 *
	 * @return Returns the table id
	 */
	public int getTableId(){
		return mTableId;
	}
	
	/**
	 * Gets the customer name.
	 *
	 * @return Returns the customer name
	 */
	public String getCustomerName(){
		return mCustomerName;
	}

	/**
	 * Gets the total price customer need to paid.
	 *
	 * @return Returns the total price customer need to paid
	 */
	public double getTotalPrice() {
		return mTotalPrice;
	}
	
	/**
	 * Gets the time order is made.
	 *
	 * @return Returns the time order is made
	 */
	public Date getTime() {
		return mTime;
	}
	
	/*
	 * Mutator methods for Class
	 */
	/**
	 * Sets the id of the order.
	 *
	 * @param orderId the new id
	 */
	public void setId(int orderId) {
		// TODO Auto-generated method stub
		mOrderId = orderId;
	}

	
	/**
	 * Sets the staff in charge.
	 *
	 * @param staff the new staff name in charge
	 */
	public void setStaffInCharge(String staff){
		mStaff = staff;
	}
	
	/**
	 * Sets the customer id.
	 *
	 * @param customerId the new customer id
	 */
	public void setCustomerId(int customerId){
		mCustomerId = customerId;
	}
	
	/**
	 * Sets the customer name.
	 *
	 * @param customerName the new customer name
	 */
	public void setCustomerName(String customerName){
		mCustomerName = customerName;
	}
	
	/**
	 * Sets the table id.
	 *
	 * @param tableId the new table id for order
	 */
	public void setTableId(int tableId){
		mTableId = tableId;
	}

	/**
	 * Sets the time order is made.
	 *
	 * @param date the new time order is made
	 */
	public void setTime(Date date) {
		mTime = date;
	}
	
	/**
	 * Sets the total price.
	 *
	 * @param totalPrice the new total price
	 */
	public void setTotalPrice(double totalPrice) {
		// TODO Auto-generated method stub
		mTotalPrice = totalPrice;
	}
	
	/*
	 * add methods to add MenuItem or PromotionalPackage to Order
	 */
	/**
	 * Adds the menu item.
	 *
	 * @param item the item to be added
	 * @param quantity the quantity of the item to be added
	 */
	public void addMenuItem(MenuItem item, int quantity){
		int position = -1;
		for(int i = 0; i < mItems.size(); ++i){
			if(mItems.get(i).getName().compareTo(item.getName()) == 0){
				position = i;
			}
		}
		if(position == -1){
			mItems.add(item);
			quantityMenuItems.add(quantity);
		}
		else{
			quantityMenuItems.set(position, quantityMenuItems.get(position)+quantity);
		}
	}
		
	/**
	 * Adds the promotional package.
	 *
	 * @param pPackage the package to be added
	 * @param quantity the quantity of pPackage to be added
	 */
	public void addPromotionalPackage(PromotionalPackage pPackage, int quantity){
		int position = -1;
		for(int i = 0; i < mPackages.size(); ++i){
			if(mPackages.get(i).getName().compareTo(pPackage.getName()) == 0){
				position = i;
			}
		}
		if(position == -1){
			mPackages.add(pPackage);
			quantityPackages.add(quantity);
		}
		else{
			quantityPackages.set(position,quantityPackages.get(position)+quantity);
		}
	}
	

	/**
	 * Recalculate price after adding or removing item from order.
	 */
	public void reCalculatePrice() {
		// TODO Auto-generated method stub
		mTotalPrice = 0;
		for(int i = 0; i < mItems.size(); ++i){
		    mTotalPrice+= mItems.get(i).getPrice()*quantityMenuItems.get(i);
		}
		
		for(int i = 0; i < mPackages.size();++i){
			mTotalPrice+= mPackages.get(i).getPrice()*quantityPackages.get(i);
		}
	}
	
	/**
	 * Removes the menu item.
	 *
	 * @param itemName the item name
	 * @param quantity the quantity
	 * @return Return true, if remove menu item successful, else return false
	 */
	public boolean removeMenuItem(String itemName, int quantity){
		int position = -1;
		for(int i = 0; i < mItems.size(); ++i){
			if(mItems.get(i).getName().compareTo(itemName) == 0){
				position = i;
				break;
			}
		}
		if(quantity > quantityMenuItems.get(position) || quantity < 1){
			return false;
		}
		if(quantityMenuItems.get(position) <= 1){
			mItems.remove(position);
			quantityMenuItems.remove(position);
		}
		else{
			quantityMenuItems.set(position, quantityMenuItems.get(position)-1);
		}
		return true;
	}
	
	/**
	 * Removes the package.
	 *
	 * @param packageName the package name
	 * @param quantity the quantity
	 * @return Returns true, if remove package successful,else return false
	 */
	public boolean removePackage(String packageName, int quantity){
		int position = -1;
		for(int i = 0; i < mPackages.size(); ++i){
			if(mPackages.get(i).getName().compareTo(packageName) == 0){
				position = i;
				break;
			}
		}
		if(quantity > quantityPackages.get(position) || quantity < 1){
			return false;
		}
		if(quantityPackages.get(position) <= 1){
			mPackages.remove(position);
			quantityPackages.remove(position);
		}
		else{
			quantityPackages.set(position,quantityPackages.get(position)-1);
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String text =  mOrderId + "|" + mStaff + "|" + mCustomerName +"|" + mCustomerId + "|" + mTableId + "|" +mTime.getTime() +"|"+mTotalPrice+"|";
		text += "MenuItem";
		for(int i= 0; i < mItems.size(); ++i){
			text += "|" +mItems.get(i).getName() + "|" + quantityMenuItems.get(i) ;
		}
		text+="|PromotionalPackage";
		for(int i = 0; i < mPackages.size(); ++i){
			text+= "|"+mPackages.get(i).getName() + "|" + quantityPackages.get(i) ;
		}
		return text;
	}

	/**
	 * Gets the quantity menu items.
	 *
	 * @return Returns the quantity menu items
	 */
	public ArrayList<Integer> getQuantityMenuItems() {
		// TODO Auto-generated method stub
		
		return quantityMenuItems;
	}

	/**
	 * Gets the list of quantity of promotional packages.
	 *
	 * @return Returns the list of quantity of promotional packages
	 */
	public ArrayList<Integer> getQuantityPackages() {
		// TODO Auto-generated method stub
		return quantityPackages;
	}

	


}
