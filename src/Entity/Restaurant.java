package Entity;

import java.util.ArrayList;

public class Restaurant {

	private Menu mMenu;
	private ArrayList<Table> mTableList;

	public Restaurant() {
		mMenu = new Menu();
		mTableList = new ArrayList<Table>();
	}

	public Menu getMenu() {
		return mMenu;
	}

	public ArrayList<Table> getTableList() {
		return mTableList;
	}

}
