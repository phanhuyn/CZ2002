package Data;

import Entity.MenuItem;
import Entity.Restaurant;

public class DataAdapter {
	public boolean loadRestaurantResource (Restaurant restaurant, String pathToFile){
		// Load Resource From .txt file
		System.out.println("Resource Loaded! ");
		return true;
	}
	
	public boolean writeNewMenuItem (MenuItem menuItem){
		System.out.println("New menu item stored in DS: " + menuItem.toString());
		return true;
	}
	
	
}
