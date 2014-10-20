package Main;

import Controller.MainController;
import Data.DataAdapter;
import Entity.Restaurant;

public class Main {

	private final static String PATH_TO_FILE = "";

	public static void main(String[] args) {

		// Initialize
		DataAdapter dataAdapter = new DataAdapter();
		Restaurant restaurant = new Restaurant();

		dataAdapter.loadRestaurantResource(restaurant, PATH_TO_FILE);

		MainController mainController = new MainController(restaurant,
				dataAdapter);

		mainController.run();
	}

	public static String getPathToFile() {
		return PATH_TO_FILE;
	}

}
