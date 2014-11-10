/**
  @author Peter
  @version 1.0
  @since 2014-11-09
 */

package Entity;

import java.util.Date;
import java.util.ArrayList;

public class Table {
	/**
	 * Next unique identifier which is available for the next table.
	 */
	private static int nextId = 0;
	
	/**
	 * The capacity of a table.
	 */
	private int capacity;
	
	/**
	 * The unique id of a table.
	 */
	private int id;
	
	/**
	 * List of reservation on this particular table.
	 */
	private ArrayList<Reservation> reservation;
	
	/**
	 * 
	 */
	
	/**
	 * Constructor.
	 * @param capacity The capacity of the new table.
	 */
	public Table(int capacity) {
		this.id = nextId++;
		this.capacity = capacity;
		reservation = new ArrayList<Reservation>();
	}
	
	/**
	 * Check if this particular table is available for that time slot and for that size of pax.
	 * @param sizeOfPax The minimum capacity required.
	 * @param start Starting time of the reservation (inclusive).
	 * @param end Ending time of the reservation (exclusive).
	 */
	public boolean isAvailable(int sizeOfPax, Date start, Date end) {
		if (capacity >= sizeOfPax) {
			for (Reservation item : reservation)
				if (item.isClash(start, end))
					return false;

			return true;
		} else
			return false;
	}
	
	/**
	 * Get the capacity of this table.
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Allocate this table for that reservation's timeslot.
	 * @param item The reservation details.
	 */
	public boolean allocate(Reservation item) {
		if (isAvailable(item.getSizeOfPax(), item.getStartTime(),
				item.getEndTime()))
			return reservation.add(item);

		return false;
	}
	
	/**
	 * Get the list of reservations of this particular table.
	 */
	public ArrayList<Reservation> getReservation()
	{
		return reservation;
	}
	
	/**
	 * Get the list of reservations of that particular person in this table.
	 * @param fullName The full name of the person.
	 * @param contactNo The contact number of that person.
	 */
	public ArrayList<Reservation> getReservation(String fullName,
			String contactNo) {
		ArrayList<Reservation> result = new ArrayList<Reservation>();
		for (Reservation item : reservation)
			if (item.getFullName().compareTo(fullName) == 0
					&& item.getContactNo().compareTo(contactNo) == 0)
				result.add(item);
		return result;
	}
	
	/**
	 * Remove the reservation from this table.
	 * @param which The reference to that reservation.
	 */
	public boolean removeReservation(Reservation which) {
		return reservation.remove(which);
	}
	
	/**
	 * Get the unique ID of this table.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns a string in the format id|capacity|list_of_reservation_id.
	 */
	public String toString()
	{
		String reservation_str = "";
		for(int i = 0; i < reservation.size(); ++i)
			reservation_str += (i > 0 ? "," : "") + reservation.get(i).getId();
		return id + "|" + capacity + "|" + reservation_str;
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
