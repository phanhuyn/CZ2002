package Main;

import Controller.MainController;
import Data.DataAdapter;
import Entity.Restaurant;

public class Main {

	public static void main(String[] args) {

		// Initialize
		Restaurant restaurant = new Restaurant();

		DataAdapter.loadRestaurantResource(restaurant);

		MainController mainController = new MainController(restaurant);

		mainController.run();
	}

}
