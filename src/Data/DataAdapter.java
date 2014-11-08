package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entity.Menu;
import Entity.MenuItem;
import Entity.PromotionalPackage;
import Entity.Restaurant;
import Entity.Staff;
import Entity.Table;

public class DataAdapter {
	public boolean loadRestaurantResource(Restaurant restaurant) {
		
		Menu menu = restaurant.getMenu();
		ArrayList<Table> tableList = new ArrayList<Table>();
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		
		try {
			// Initiate file reader
			BufferedReader brMenuItem = new BufferedReader(new FileReader(
					"data/menuItem.txt"));
			BufferedReader brPackage = new BufferedReader(new FileReader(
					"data/package.txt"));
			BufferedReader brTable = new BufferedReader(new FileReader(
					"data/table.txt"));
			BufferedReader brStaff = new BufferedReader(new FileReader(
					"data/staff.txt"));

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
				menu.addPromotionalPackage(new PromotionalPackage(
						tempAttribute[1], Double.parseDouble(tempAttribute[2]),
						tempAttribute[3], tempPackageMenuItem));
				tempString = brPackage.readLine();
			}
			brPackage.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error opening the input file: "
					+ e.getMessage());
		}

		System.out.println("Resource Loaded! ");
		return true;
	}

	public boolean save() {
		FileWriter fwStreamShort;
		try {
			fwStreamShort = new FileWriter("a");
			BufferedWriter bwStreamShort = new BufferedWriter(fwStreamShort);
			PrintWriter pwStreamShort = new PrintWriter(bwStreamShort, false);

			FileWriter fwStreamLong = new FileWriter("a");
			BufferedWriter bwStreamLong = new BufferedWriter(fwStreamLong);
			PrintWriter pwStreamLong = new PrintWriter(bwStreamLong, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
