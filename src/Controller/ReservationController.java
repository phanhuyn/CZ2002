/**
  @author Peter
  @version 1.0
  @since 2014-11-09
 */

package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entity.Reservation;
import Entity.Table;

public class ReservationController {
	/**
	 * Restaurant.
	 */
	private Restaurant restaurant;
	
	/**
	 * List of table.
	 */
	private ArrayList<Table> table;
	
	/**
	 * Constructor.
	 * @param restaurant This restaurant.
	 */
	public ReservationController(Restaurant restaurant)
	{
		this.restaurant = restaurant;
		this.table = restaurant.getTableList();
	}
	
	/**
	 * Returns a table which is able to be reserved during start time until end time.
	 * If there are multiple tables, the one with smallest capacity is returned.
	 * @param sizeOfPax Number of seats required on the table.
	 * @param start Starting time of booking (inclusive).
	 * @param end Ending time of booking (exclusive).
	 */
	public Table getTable(int sizeOfPax, Date start, Date end) {
		Table which = null;

		for (Table item : table) {
			if (item.isAvailable(sizeOfPax, start, end)) {
				if (which == null)
					which = item;
				else if (which.getCapacity() > item.getCapacity())
					which = item;
			}
		}

		return which;
	}
	
	/**
	 * Allocate that table to the customer.
	 * @param which Reference to the table.
	 * @param fullName Full name of the customer (case-sensitive).
	 * @param contactNo Contact number of the customer (case-sensitive).
	 * @param sizeOfPax The number of seats required.
	 * @param start Starting time of booking (inclusive).
	 * @param end Ending time of booking (exclusive).
	 */
	public boolean allocate(Table which, String fullName, String contactNo,
			int sizeOfPax, Date start, Date end) {
		Reservation reservation = new Reservation(which, fullName, contactNo,
				sizeOfPax, start, end);
		return which.allocate(reservation);
	}
	
	/**
	 * Get the customer's existing reservations.
	 * @param fullName The full name of the customer (case-sensitive).
	 * @param contactNo The contact number of the customer (case-sensitve).
	 */
	public ArrayList<Reservation> getReservation(String fullName,
			String contactNo) {
		ArrayList<Reservation> result = new ArrayList<Reservation>();
		for (Table item : table)
			result.addAll(item.getReservation(fullName, contactNo));
		return result;
	}
	
	/**
	 * Remove a reservation.
	 * @param which Reference to the table.
	 */
	public boolean removeReservation(Reservation which) {
		return which.getTable().removeReservation(which);
	}
}