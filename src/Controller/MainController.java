package Controller;

import Data.DataAdapter;
import Entity.Restaurant;
import UI.MainUI;

public class MainController {
	
	private Restaurant mRestaurant;
	private DataAdapter mDataAdapter;
	private MainUI mMainUI;
	
	public MainController (Restaurant restaurant, DataAdapter dataAdapter){
		mRestaurant = restaurant;
		mMainUI = new MainUI(this);
		mDataAdapter = dataAdapter;
	}
	
	public void run(){
		mMainUI.displayMainFunction();
	}
	
	public void menuOption(){
		MenuController menuController = new MenuController(mRestaurant.getMenu(), mDataAdapter);
		menuController.run();
	}
}
