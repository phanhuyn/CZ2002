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
import Entity.Restaurant;
import Entity.Table;

public class ReservationController {
	/**
	 * The restaurant.
	 */
	private Restaurant restaurant;
	
	/**
	 * The list of table.
	 */
	private ArrayList<Table> table;
	
	/**
	 * The constructor.
	 * @param restaurant The restaurant.
	 */
	public ReservationController(Restaurant restaurant)
	{
		this.restaurant = restaurant;
		this.table = restaurant.getTableList();
	}
	
	// FOR TESTING ONLY
	// DISABLE THIS PART OF CODE AFTER TESTING
	public ReservationController()
	{
		this.restaurant = null;
		this.table = new ArrayList<Table>();
		table.add(new Table(2));
		table.add(new Table(2));
	}
	
	/**
	 * Returns a table which is able to be reserved during the starting time until the ending time.
	 * If there are multiple tables, the one with the smallest capacity of at least sizeOfPax is choosen.
	 * @param sizeOfPax Number of seats required on the table.
	 * @param start The starting time of booking.
	 * @param end The ending time of booking.
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
	 * @param sizeOfPax Number of seats required.
	 * @param start Starting time of booking.
	 * @param end Ending time of booking.
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
	 * @param which Reference to the reservation.
	 */
	public boolean removeReservation(Reservation which) {
		return which.getTable().removeReservation(which);
	}
}