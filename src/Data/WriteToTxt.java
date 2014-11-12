package Data;

/**
 * The interface declaring a class to be able to convert its information into a string 
 * under a particular format used in this application
 * @author Nguyen Phan Huy
 * @version 1.0
 * @since 2014-11-13
 */
public interface WriteToTxt {
	/**
	 *  Convert the contain of the menu item to a string to be used to store in txt file
	 * @return a String containing the information of the object
	 */
	public String toString();
}
