package Entity;

import java.util.ArrayList;

public class Menu {

	private ArrayList<MenuItem> mMenuItemList;
	private ArrayList<PromotionalPackage> mPromotionalPackageList;

	public Menu() {
		mMenuItemList = new ArrayList<MenuItem>();
		mPromotionalPackageList = new ArrayList<PromotionalPackage>();
	}

	// MenuItem methods

	public MenuItem getMenuItemById(int id) {
		return mMenuItemList.get(id - 1);
	}

	public void replaceMenuItemById(int id, MenuItem menuItem) {
		mMenuItemList.set(id - 1, menuItem);
	}

	public void deleteMenuItemById(int id) {
		mMenuItemList.remove(id - 1);
	}

	public void addMenuItem(MenuItem menuItem) {
		mMenuItemList.add(menuItem);

	}

	// Promotional Package methods

	public PromotionalPackage getPromotionalPackageById(int id) {
		return mPromotionalPackageList.get(id - 1);
	}

	public void replacePromotionalPackageById(int id, PromotionalPackage promotionalPackage) {
		mPromotionalPackageList.set(id - 1, promotionalPackage);
	}

	public void deletePromotionalPackageById(int id) {
		mPromotionalPackageList.remove(id - 1);
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
				promotionalPackage.print(detail);
				i++;
				System.out.println();
			}
		}
		System.out.println("~~~~~~~~~ End of menu ~~~~~~~~~~");
	}

	public ArrayList<MenuItem> getListMenuItems() {
		// TODO Auto-generated method stub
		return mMenuItemList;
		// return null;
	}

	public ArrayList<PromotionalPackage> getListPackages() {
		// TODO Auto-generated method stub
		return mPromotionalPackageList;
	}

}
