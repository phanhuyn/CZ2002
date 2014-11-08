package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entity.Reservation;
import Entity.Table;

public class ReservationController {
	/*
	 * - FINISH getReservation() METHOD
	 */

	// private Restaurant restaurant;
	private ArrayList<Table> table;

	/*
	 * public ReservationController(Restaurant restaurant) { this.restaurant =
	 * restaurant; this.table = restaurant.getTableList(); }
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

	public boolean allocate(Table which, String fullName, String contactNo,
			int sizeOfPax, Date start, Date end) {
		Reservation reservation = new Reservation(which, fullName, contactNo,
				sizeOfPax, start, end);
		return which.allocate(reservation);
	}

	public ArrayList<Reservation> getReservation(String fullName,
			String contactNo) {
		ArrayList<Reservation> result = new ArrayList<Reservation>();
		for (Table item : table)
			result.addAll(item.getReservation(fullName, contactNo));
		return result;
	}

	public boolean removeReservation(Reservation which) {
		return which.getTable().removeReservation(which);
	}
}