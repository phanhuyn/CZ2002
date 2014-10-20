package Entity;

import java.util.ArrayList;

public class Menu {
	
	private ArrayList<MenuItem> mMenuItemList;
	private ArrayList<PromotionalPackage> mPromotionalPackageList;
	private int mLastestMenuItemId;
	private int mLastestPromotionalFoodPackageId;
	
	public Menu(){
		mMenuItemList = new ArrayList<MenuItem>();
		mPromotionalPackageList = new ArrayList<PromotionalPackage>();
	}
	
	public void addMenuItem (MenuItem menuItem){
		mMenuItemList.add(menuItem);
		
	}
	
	public int getLastestMenuItemId (){
		return mLastestMenuItemId;
	}
	
	public void addPromotionalPackage (PromotionalPackage promotionalPackage){
		mPromotionalPackageList.add(promotionalPackage);
	}
	
	public int getLastestPromotionalFoodPackageId (){
		return mLastestPromotionalFoodPackageId;
	}
}
