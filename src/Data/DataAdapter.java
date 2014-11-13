/**
	Read and write data from the .txt files
	@author Nguyen Phan Huy (loadRestaurantResource() for Menu, Reservation and Table),
	 Peter (save() method), Tran Vu Xuan Nhat (loadRestaurantResource() for Staff and Order
	@version 1.0
	@since 2014-11-13
*/

package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import Entity.Menu;
import Entity.MenuItem;
import Entity.Order;
import Entity.PromotionalPackage;
import Entity.Reservation;
import Entity.Restaurant;
import Entity.Staff;
import Entity.Table;

public class DataAdapter {

	private DataAdapter() {
	};

	private static String[] fileName = { "data/menuItem.txt",
			"data/package.txt", "data/table.txt", "data/staff.txt", "data/reservation.txt", "data/order.txt"};

	/**
	* Load all the resources of the restaurant into a restaurant object
	* The resources include: menu (menu items and promotional packages), order, reservation, staff
	* @param restaurant the reference to the restaurant object to be loaded with resources
	* @return
	*/
	public static boolean loadRestaurantResource(Restaurant restaurant) {

		// TODO add reservation loading and table loading

		/*
		 * USE: String.split("\\|", -1) to account for the empty string! More
		 * information at:
		 * http://stackoverflow.com/questions/14602062/java-string
		 * -split-removed-empty-values
		 */
		Menu menu = restaurant.getMenu();

		try {
			// Initialize file reader
			BufferedReader brMenuItem = new BufferedReader(new FileReader(
					fileName[0]));
			BufferedReader brPackage = new BufferedReader(new FileReader(
					fileName[1]));
			BufferedReader brTable = new BufferedReader(new FileReader(
					fileName[2]));
			BufferedReader brStaff = new BufferedReader(new FileReader(
					fileName[3]));
			BufferedReader brReservation = new BufferedReader(new FileReader(
					fileName[4]));
			BufferedReader brOrder = new BufferedReader(new FileReader(
					fileName[5]));
			String tempString;
			String[] tempAttribute;

			// Load menuItem
			tempString = brMenuItem.readLine();
			while (tempString != null) {
				tempAttribute = tempString.split("[|]");
				menu.addMenuItem(new MenuItem(tempAttribute[1],
						tempAttribute[2], Double.parseDouble(tempAttribute[3]),
						tempAttribute[4]));
				tempString = brMenuItem.readLine();
			}
			brMenuItem.close();
			MenuItem.setMenu(menu);
			
			// Load PromotionalPackage
			tempString = brPackage.readLine();
			String[] tempItemId;
			ArrayList<MenuItem> tempPackageMenuItem;
			while (tempString != null) {
				tempAttribute = tempString.split("[|]");
				tempItemId = tempAttribute[4].split("[,]");
				tempPackageMenuItem = new ArrayList<MenuItem>();
				for (int i = 0; i < tempItemId.length; i++) {
					tempPackageMenuItem.add(menu.getMenuItemById(Integer
							.parseInt(tempItemId[i])));

				}
				PromotionalPackage tempPromotionalPackage = new PromotionalPackage(
						tempAttribute[1], Double.parseDouble(tempAttribute[2]),
						tempAttribute[3], tempPackageMenuItem);
				menu.addPromotionalPackage(tempPromotionalPackage);
				tempString = brPackage.readLine();
			}
			brPackage.close();
			PromotionalPackage.setMenu(menu);

			// Load Table
			tempString = brTable.readLine();
			ArrayList<Table> tempTableList = restaurant.getTableList();
			Table tempTable;
			while (tempString != null) {
				tempAttribute = tempString.split("\\|", -1);
				tempTable = new Table(Integer.parseInt(tempAttribute[1]));
				tempTable.setId(Integer.parseInt(tempAttribute[0]));
				tempTableList.add(tempTable);
				tempString = brTable.readLine();
			}
			brTable.close();
			
			// Load Reservation
			tempString = brReservation.readLine();
			Table tempTable2;
			Reservation tempReservation;
			while (tempString != null) {
				tempAttribute = tempString.split("\\|", -1);
				Date startDate = new Date(Long.parseLong(tempAttribute[5]));
				Date endDate = new Date(Long.parseLong(tempAttribute[6]));
				tempTable2 = restaurant.getTableById(Integer.parseInt(tempAttribute[0]));
				tempReservation = new Reservation(tempTable2, tempAttribute[2], tempAttribute[3],
						Integer.parseInt(tempAttribute[4]), startDate, endDate);
				tempReservation.setId(Integer.parseUnsignedInt(tempAttribute[0]));
				tempTable2.allocate(tempReservation);
				tempString = brReservation.readLine();
			}
			brReservation.close();
			
            // Load Staff
			tempString = brStaff.readLine();
			while (tempString != null) {
				tempAttribute = tempString.split("[|]");
				Staff staff = new Staff(Integer.parseInt(tempAttribute[0]), tempAttribute[1]);
				restaurant.addStaff(staff);
				tempString = brStaff.readLine();
			}
			brStaff.close();
			
			//Load order
			tempString = brOrder.readLine();
			int orderId,customerId, tableId;
			String staff, customerName;
			long time;
			ArrayList<Order> orderList = restaurant.getOrderList();
			Order.mOrderList = orderList;
			ArrayList<MenuItem> menuItemList =  new ArrayList<MenuItem>();
			ArrayList<PromotionalPackage> packageList = new ArrayList<PromotionalPackage>();
			ArrayList<Integer> quantityMenuItem = new ArrayList<Integer>();
			ArrayList<Integer> quantityPackage = new ArrayList<Integer>();
			while(tempString!= null){
				tempAttribute = tempString.split("[|]");
				orderId = Integer.parseInt(tempAttribute[0]);
				staff = tempAttribute[1];
				customerName = tempAttribute[2];
				customerId = Integer.parseInt(tempAttribute[3]);
				tableId = Integer.parseInt(tempAttribute[4]);
				time = Long.parseLong(tempAttribute[5]);
				int  itemPos = 6;
				if(tempAttribute[itemPos].compareTo("MenuItem")==0){
					itemPos++;
					while(tempAttribute[itemPos].compareTo("PromotionalPackage") != 0){
						MenuItem item = menu.getMenuItemByName(tempAttribute[itemPos++]);
						if(item != null){
							menuItemList.add(item);
							quantityMenuItem.add(Integer.parseInt(tempAttribute[itemPos++]));
						}
					}
					while(itemPos < tempAttribute.length){
						PromotionalPackage pPackage = menu.getPromotionalPackageByName(tempAttribute[itemPos++]);
						if(pPackage != null){
							packageList.add(pPackage);
							quantityPackage.add(Integer.parseInt(tempAttribute[itemPos++]));
						}
					}
				}
				Date date = new Date();
				date.setTime(time);
				Order order = new Order(staff, menuItemList, quantityMenuItem, packageList, quantityPackage, customerId, customerName,tableId);
				order.setTime(date);
				orderList.add(order);
				tempString = brOrder.readLine();
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error opening the input file: "
					+ e.getMessage());
		}

		System.out.println("Resource Loaded! ");
		return true;
	}

	/**
	* Save all the resources from the restaurant objects to the .txt files
	* The resources include: menu (menu items and promotional packages), order, reservation, staff
	* @param restaurant the reference to the restaurant object that contains the information to be saved
	* @return
	*/
	public static boolean save (Restaurant restaurant)
	{
		PrintWriter writer = null, writer2 = null;
		
		/*
			Table and reservation
		*/
		try
		{
			ArrayList<Table> tables = restaurant.getTableList();
			writer  = new PrintWriter("data/table.txt");
			writer2 = new PrintWriter("data/reservation.txt");
			for(Table table: tables)
			{
				writer.println(table);
				
				int counter = 0;
				for(Reservation item: table.getReservation())
					System.out.println((counter++ > 0 ? "," : "") + item.getId());
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found exception: " + e.getMessage());
			return false;
		}
		catch(IOException e)
		{
			System.out.println("I/O exception: " + e.getMessage());
			return false;
		}
		catch(Exception e)
		{
			System.out.println("Other exception: " + e.getMessage());
			return false;
		}
		finally
		{
			try
			{
				writer.close();
				writer2.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception caught while JAVA trying to close PrintWriter!");
				return false;
			}
		}
		
		/*
			Menu Item
		*/
		try
		{
			ArrayList<MenuItem> menuItemList = restaurant.getMenu().getListMenuItems();
			writer = new PrintWriter("data/menuItem.txt");
			for(MenuItem item: menuItemList)
				writer.println(item);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found exception: " + e.getMessage());
			return false;
		}
		catch(IOException e)
		{
			System.out.println("I/O exception: " + e.getMessage());
			return false;
		}
		catch(Exception e)
		{
			System.out.println("Other exception: " + e.getMessage());
			return false;
		}
		finally
		{
			try
			{
				writer.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception caught while JAVA trying to close PrintWriter!");
				return false;
			}
		}
		
		/*
			Promotional Package
		*/
		try
		{
			ArrayList<PromotionalPackage> packageList = restaurant.getMenu().getListPackages();
			writer = new PrintWriter("data/package.txt");
			for(PromotionalPackage packageItem: packageList)
				writer.println(packageItem);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found exception: " + e.getMessage());
			return false;
		}
		catch(IOException e)
		{
			System.out.println("I/O exception: " + e.getMessage());
			return false;
		}
		catch(Exception e)
		{
			System.out.println("Other exception: " + e.getMessage());
			return false;
		}
		finally
		{
			try
			{
				writer.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception caught while JAVA trying to close PrintWriter!");
				return false;
			}
		}
		
		/*
			Staff
		*/
		/*
		try
		{
			ArrayList<Staff> staffs = restaurant.getStaffList();
			writer = new PrintWriter("data/staff.txt");
			for(Staff staff: staffs)
				writer.println(staff);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found exception: " + e.getMessage());
			return false;
		}
		catch(IOException e)
		{
			System.out.println("I/O exception: " + e.getMessage());
			return false;
		}
		catch(Exception e)
		{
			System.out.println("Other exception: " + e.getMessage());
			return false;
		}
		finally
		{
			try
			{
				writer.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception caught while JAVA trying to close PrintWriter!");
				return false;
			}
		}
		*/
		return true;
	}
}
