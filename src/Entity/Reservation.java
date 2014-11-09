/**
  @author Peter
  @version 1.0
  @since 2014-11-09
 */

package Entity;

import java.util.Date;

public class Reservation {
	/**
	 * The full name of the person who created this reservation.
	 */
	private String fullName;
	
	/**
	 * The contact number of the person who created this reservation.
	 */
	private String contactNo;
	
	/**
	 * The number of seats required.
	 */
	private int sizeOfPax;
	
	/**
	 * The unique ID of this reservation.
	 */
	private int id;
	
	/**
	 * The timeslot [start, end).
	 */
	private Date start, end;
	
	/**
	 * The next available unique ID.
	 */
	private static int nextId = 0;
	
	/**
	 * The reference to the reserved table.
	 */
	private Table table;

	/**
	 * Constructor.
	 * @param table The reference to the booked table.
	 * @param fullName The full name of the customer.
	 * @param contactNo The contact number of the customer.
	 * @param sizeOfPax The number of seats required.
	 * @param start The starting time of reservation (inclusive).
	 * @param end The ending time of reservation (exclusive).
	 */
	public Reservation(Table table, String fullName, String contactNo,
			int sizeOfPax, Date start, Date end) {
		this.id = nextId++;
		this.table = table;
		this.fullName = fullName;
		this.contactNo = contactNo;
		this.sizeOfPax = sizeOfPax;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Check if the timeslot [start, end) clash with this reservation.
	 * @param start The starting time (inclusive).
	 * @param end The ending time (exclusive).
	 */
	public boolean isClash(Date start, Date end) {
		return this.start.after(end) || this.end.before(start);
	}
	
	/**
	 * Gets starting time.
	 */
	public Date getStartTime() {
		return start;
	}

	/**
	 * Gets ending time.
	 */
	public Date getEndTime() {
		return end;
	}

	/**
	 * Gets the size of pax.
	 */
	public int getSizeOfPax() {
		return sizeOfPax;
	}

	/**
	 * Gets the full name of the customer.
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Gets the contact number of the customer.
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * Gets the unique ID of that person.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the reference to the reserved table.
	 */
	public Table getTable() {
		return table;
	}
	
	/**
	 * Returns a string in the format id|table_id|fullName|contactNo|sizeOfPax|start|end.
	 */
	public String toString()
	{
		return id + "|" + table.getId() + "|" +fullName + "|" + contactNo + "|" + sizeOfPax + "|" + start.getTime() + "|" + end.getTime();
	}
	
	/**
	 * Set the next available unique ID.
	 * @param nextId The next available unique ID.
	 */
	public void setNextId(int nextId)
	{
		this.nextId = nextId;
	}
}
