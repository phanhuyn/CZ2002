/**
  @author Stefan
  @version 1.0
  @since 2014-11-09
 */
package Entity;

public class Staff {
	
	/**
	 * The Id of the Staff.
	 */
	private int mId;
	
	/**
	 * The Name of the Staff.
	 */
	private String mName;
	
	/**
	 * The constructor.
	 * @param id The Staff's id for identification purpose.
	 * @param name The full name of the customer.
	 */
	public Staff(int id,String name){
		mId = id;
		mName = name;
	}
	
	/**
	 * This function will return the ID of the staff when it is called.
	 * @return Returns the Id of the staff.
	 */
	public int getId() {
		return mId;
	}
	/**
	 * This function will change the Id of the staff.
	 */
	public void setmId(int id) {
		mId = id;
	}

	/**
	 * This function will return the name of the staff when it is called.
	 * @return Returns the name of the staff.
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * This function will change the name of the staff.
	 */
	public void setName(String name) {
		mName = name;
	}
	
	
	
}
