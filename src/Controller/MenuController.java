package Controller;

import Data.DataAdapter;
import Entity.Menu;
import Entity.MenuItem;
import UI.MenuUI;

public class MenuController {

	private Menu mMenu;
	private DataAdapter mDataAdapter;

	public MenuController(Menu menu, DataAdapter dataAdapter) {
		mMenu = menu;
		mDataAdapter = dataAdapter;
	}

	public void run() {
		MenuUI menuUI = new MenuUI(this);
		menuUI.displayMenuOption();
	}

	// 1. Create new menu item
	public void createNewMenuItem(String name, double price) {
		MenuItem menuItem = new MenuItem((mMenu.getLastestMenuItemId() + 1),
				name, price);
		mMenu.addMenuItem(menuItem);
		mDataAdapter.writeNewMenuItem(menuItem);
	}

	// 2. Update menu item
	public void updateNewMenuItem(String name, double price) {
		MenuItem menuItem = new MenuItem((mMenu.getLastestMenuItemId() + 1),
				name, price);
		mMenu.addMenuItem(menuItem);
		mDataAdapter.writeNewMenuItem(menuItem);
	}
	

}
