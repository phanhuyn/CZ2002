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
	 * The number of seats required by the person.
	 */
	private int sizeOfPax;
	
	/**
	 * The unique ID of this reservation.
	 */
	private int id;
	
	/**
	 * The timeslot.
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
	 * The constructor.
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
	 * Check if a timeslot clash with this reservation.
	 * @param start The starting time.
	 * @param end The ending time.
	 * @return Returns true if both timeslot clash.
	 */
	public boolean isClash(Date start, Date end) {
		return !(this.start.after(end) || this.end.before(start));
	}
	
	/**
	 * Returns the starting time.
	 * @return Returns the starting time.
	 */
	public Date getStartTime() {
		return start;
	}

	/**
	 * Returns the ending time.
	 * @return Returns the ending time.
	 */
	public Date getEndTime() {
		return end;
	}

	/**
	 * Returns the size of pax.
	 * @return Returns the size of pax.
	 */
	public int getSizeOfPax() {
		return sizeOfPax;
	}

	/**
	 * Returns the full name of the customer.
	 * @Returns the full name of the customer.
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Returns the contact number of the customer.
	 * @return Returns the contact number of the customer.
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * Returns the unique ID of that person.
	 * @return Returns the unique ID of that person.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the reference to the reserved table.
	 * @return Returns the reference to the reserved table.
	 */
	public Table getTable() {
		return table;
	}
	
	/**
	 * Returns a string in the format id|table_id|fullName|contactNo|sizeOfPax|start|end.
	 * @return Returns a string in the format id|table_id|fullName|contactNo|sizeOfPax|start|end.
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
	
	/**
	 * IGNORE THIS! It's for unit testing.
	 */
	public static void main(String args[])
	{
		Table table = new Table(10);
		Reservation r1 = new Reservation(table, "Hermanto Pythagoras", "99999999", 7,
		new Date(114, 10, 12, 8, 30, 0), new Date(114, 10, 12, 10, 29, 59));
		Reservation r2 = new Reservation(table, "Zhu Ge Liang", "99999998", 10,
		new Date(114, 10, 12, 10, 30, 0), new Date(114, 10, 12, 12, 29, 59));
		table.allocate(r1);
		table.allocate(r2);
		System.out.println(table);
		System.out.println(r1);
		System.out.println(r2);
	}
}
