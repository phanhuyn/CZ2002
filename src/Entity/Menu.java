package Entity;

import java.util.ArrayList;

public class Menu {

	private ArrayList<MenuItem> mMenuItemList;
	private ArrayList<PromotionalPackage> mPromotionalPackageList;

	public Menu() {
		mMenuItemList = new ArrayList<MenuItem>();
		mPromotionalPackageList = new ArrayList<PromotionalPackage>();
	}

	public MenuItem getMenuItemById(int id) {
		return mMenuItemList.get(id - 1);
	}

	public void addMenuItem(MenuItem menuItem) {
		mMenuItemList.add(menuItem);

	}

	public void addPromotionalPackage(PromotionalPackage promotionalPackage) {
		mPromotionalPackageList.add(promotionalPackage);
	}

	public void print() {
		int i = 1;
		System.out.println("~~~~~~~~~ MENU ~~~~~~~~~~");
		System.out.println("~~~~~~~~~ A la carte ~~~~~~~~~~");
		for (MenuItem item : mMenuItemList) {
			System.out.print(i + ". " + item.getName() + " (" + item.getType()
					+ ")" + " : " + item.getPrice());
			System.out.print(" - " + item.getDescription());
			System.out.println();
			i++;
		}
		i =1;
		System.out.println("~~~~~~~~~ Promotional Package ~~~~~~~~~~");
		for (PromotionalPackage promotionalPackage : mPromotionalPackageList) {
			System.out.println(i + ". " + promotionalPackage.getName() + " : "
					+ promotionalPackage.getPrice());
			System.out.print("Items included: ");
			double price = 0;
			for (MenuItem item : promotionalPackage.getItemList()){
				System.out.print(item.getName() + ", ");
				price += item.getPrice();
			}
			System.out.println("Save " + String.format( "%.2f", (price - promotionalPackage.getPrice()) ) +  " dollar!");
			i++;
		}
		System.out.println("~~~~~~~~~ End of menu ~~~~~~~~~~");
	}

	public ArrayList<MenuItem> getListMenuItems() {
		// TODO Auto-generated method stub
		return mMenuItemList;
		//return null;
	}

	public ArrayList<PromotionalPackage> getListPackages() {
		// TODO Auto-generated method stub
		return mPromotionalPackageList;
	}

}
