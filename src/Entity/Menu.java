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

	public void replaceMenuItemById(int id, MenuItem menuItem) {
		mMenuItemList.set(id - 1, menuItem);
	}

	public void addMenuItem(MenuItem menuItem) {
		mMenuItemList.add(menuItem);

	}

	public void addPromotionalPackage(PromotionalPackage promotionalPackage) {
		mPromotionalPackageList.add(promotionalPackage);
	}

	public void print(boolean detail, boolean printMenuItem,
			boolean printPackage) {

		System.out.println("~~~~~~~~~ MENU ~~~~~~~~~~");
		int i;

		// Print menu items
		if (printMenuItem) {
			i = 1;
			System.out.println("~~~~~~~~~ A la carte ~~~~~~~~~~");
			for (MenuItem item : mMenuItemList) {
				System.out.print(i + ". ");
				item.print(detail);
				i++;
			}
		}

		// Print food package
		if (printPackage) {
			i = 1;
			System.out.println("~~~~~~~~~ Promotional Package ~~~~~~~~~~");
			for (PromotionalPackage promotionalPackage : mPromotionalPackageList) {
				System.out.print(i + ". ");

				i++;
				System.out.println();
			}
		}
		System.out.println("~~~~~~~~~ End of menu ~~~~~~~~~~");
	}
}
