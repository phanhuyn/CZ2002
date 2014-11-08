package Main;

import Controller.MainController;
import Data.DataAdapter;
import Entity.Restaurant;

public class Main {

	private final static DataAdapter dataAdapter = new DataAdapter();

	public static void main(String[] args) {

		// Initialize
		Restaurant restaurant = new Restaurant();

		dataAdapter.loadRestaurantResource(restaurant);

		MainController mainController = new MainController(restaurant);

		mainController.run();
	}

}
