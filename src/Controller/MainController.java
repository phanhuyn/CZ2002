package Controller;

import Data.DataAdapter;
import Entity.Restaurant;
import UI.MainUI;
import UI.OrderUI;
import UI.ReservationUI;

/**
 * @author Zillion Govin
 *
 */
public class MainController {
	
	/**
	 * refer to This Restaurant
	 */
	private Restaurant mRestaurant;
	
	/**
	 *  refer to the MainUI UI
	 */
	private MainUI mMainUI;
	
	/**
	 * Create a new MainController given the restaurant
	 * @param restaurant new Restaurant
	 */
	public MainController (Restaurant restaurant){
		mRestaurant = restaurant;
		mMainUI = new MainUI(this);
	}
	
	/**
	 * run the MainController
	 */
	public void run(){
		mMainUI.displayMainFunction();
	}
	
	/**
	 * run the menuController
	 */
	public void menuOption(){
		MenuController menuController = new MenuController(mRestaurant.getMenu());
		menuController.run();
	}
	
	/**
	 * display the OrderUI
	 */
	public void orderOption(){
		OrderUI mOrderUI = new OrderUI(mRestaurant);
		mOrderUI.run();
	}
	
	/**
	 * run the printsaleController
	 */
	public void printSaleOption(){
		PrintSaleController printsaleController = new PrintSaleController(mRestaurant.getOrderList());
		printsaleController.run();
	}
	
	/**
	 * display the reservationUI
	 */
	public void reservationOption(){
		ReservationController reservationController = new ReservationController(mRestaurant);
		ReservationUI reservationUI = new ReservationUI(reservationController);
		reservationUI.run();
	}
	
	/**
	 * store the new modified data of the restaurant
	 */
	public void save(){
		DataAdapter.save(mRestaurant);
	}
}
