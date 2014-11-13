package Controller;

import Data.DataAdapter;
import Entity.Restaurant;
import UI.MainUI;
import UI.OrderUI;
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
		OrderUI mOrderUI = new OrderUI(mRestaurant);
		mOrderUI.run();
	}
	
	public void printSaleOption(){
		PrintSaleController printsaleController = new PrintSaleController(mRestaurant.getOrderList());
		printsaleController.run();
	}
	
	public void reservationOption(){
		ReservationController reservationController = new ReservationController(mRestaurant);
		ReservationUI reservationUI = new ReservationUI(reservationController);
		reservationUI.run();
	}
	
	public void save(){
		DataAdapter.save(mRestaurant);
	}
}
