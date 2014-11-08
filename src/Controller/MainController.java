package Controller;

import Data.DataAdapter;
import Entity.Restaurant;
import UI.MainUI;
import UI.ReservationUI;

public class MainController {
	
	private Restaurant mRestaurant;
	private MainUI mMainUI;
	
	public MainController (Restaurant restaurant){
		mRestaurant = restaurant;
		mMainUI = new MainUI(this);
	}
	
	public void run(){
		mMainUI.displayMainFunction();
	}
	
	public void menuOption(){
		MenuController menuController = new MenuController(mRestaurant.getMenu());
		menuController.run();
	}
	
	public void orderOption(){
		OrderController orderController = new OrderController();
		//orderController.run();
	}
	
	public void reservationOption(){
		ReservationController reservationController = new ReservationController();
		ReservationUI reservationUI = new ReservationUI(reservationController);
		reservationUI.run();
	}
}
