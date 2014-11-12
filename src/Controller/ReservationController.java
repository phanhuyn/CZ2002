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
	 * @return The table that fits the requirements.
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
	 * @return Returns true if successful.
	 */
	public boolean allocate(Table which, String fullName, String contactNo,
			int sizeOfPax, Date start, Date end) {
		Reservation reservation = new Reservation(which, fullName, contactNo,
				sizeOfPax, start, end);
		return which.allocate(reservation);
	}
	
	/**
	 * Returns the customer's existing reservations on all tables.
	 * @param fullName The full name of the customer (case-sensitive).
	 * @param contactNo The contact number of the customer (case-sensitve).
	 * @return List of reservations of that customer.
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
	 * @return Returns true if successful.
	 */
	public boolean removeReservation(Reservation which) {
		return which.getTable().removeReservation(which);
	}
	
	/**
	 * Clears all expired Reservations
	 */
	public void cleanUp()
	{
		Date now = new Date();
		for(Table table: this.table)
			table.cleanUp(now);
	}
	
	/**
	 * THIS IS UNIT TESTING! DON'T BOTHER!
	 */
	public static void main(String[] args)
	{
		ReservationController mgr = new ReservationController();
		
		mgr.table.get(0).allocate(new Reservation(mgr.table.get(0), "P", "9", 2,
		new Date(114, 10, 10, 8, 30), new Date(114, 10, 10, 10, 29)));
		
		mgr.table.get(1).allocate(new Reservation(mgr.table.get(0), "P", "9", 2,
		new Date(114, 10, 10, 8, 30), new Date(114, 10, 10, 10, 29)));
		
		System.out.println(mgr.table.get(0));
		System.out.println(mgr.table.get(1));
		
		mgr.cleanUp();
		
		System.out.println(mgr.table.get(0));
		System.out.println(mgr.table.get(1));
	}
}