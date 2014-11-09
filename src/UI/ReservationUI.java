/**
  @author Peter
  @version 1.0
  @since 2014-11-09
 */

package UI;

import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;

import Controller.ReservationController;
import Entity.Reservation;
import Entity.Table;

public class ReservationUI {
	/**
	 * Reservation controller class.
	 */
	private ReservationController mgr;
	
	/**
	 * A scanner for input operation.
	 */
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * Base year of Date class.
	 */
	private static int baseYear = 1900;
	
	/**
	 * Constructor.
	 * @param mgr Reservation controller class.
	 */
	public ReservationUI(ReservationController mgr) {
		this.mgr = mgr;
	}

	/**
	 * This function is called when reservation option is selected.
	 */
	public void run() {
		System.out.println("1. Reserve table(s)");
		System.out.println("2. Remove a reservation");
		System.out.println("3. Return to main menu");
		
		System.out.print("Select your option: ");
		int choice = scan.nextInt();
		while (1 <= choice && choice <= 2) {
			switch (choice) {
				case 1:
					reserveTableOption();
					break;
				case 2:
					removeReservationOption();
					break;
				default:
					break;
			}
		}
	}
	
	/**
	 * This function is called when a customer would like to reserve a table.
	 */
	public void reserveTableOption() {
		System.out.print("\nPlease enter your full name (case sensitive): ");
		String fullName = scan.next();

		System.out.print("Please enter your contact number: ");
		String contactNo = scan.next();

		int d, M, y, h, m, n;
		Table which;
		long duration;
		char choice = 'n';
		Date start, end;

		do {
			System.out.print("Please provide day, month, and year separated by a space: ");
			d = scan.nextInt();
			M = scan.nextInt();
			y = scan.nextInt() - baseYear;

			System.out.print("Please provide hour, and minute separated by a space in 24h format: ");
			h = scan.nextInt();
			m = scan.nextInt();

			System.out.print("Please provide the duration in minutes: ");
			duration = scan.nextLong();

			start = new Date(y, M, d, h, m);
			end = new Date(start.getTime() + duration * 60000L - 1L);

			System.out.print("Please input the number of pax: ");
			n = scan.nextInt();

			which = mgr.getTable(n, start, end);

			if (which == null) {
				System.out.print("Sorry, no table is available for your reservation.\nWould you like to reenter your input: ");
				choice = scan.next().charAt(0);
			}
		} while (choice == 'y' || choice == 'Y');

		if (which != null) {
			if (mgr.allocate(which, fullName, contactNo, n, start, end))
				System.out.println("Allocation of reservation is successful!");
			else
				System.out.println("Allocation of reservation is unsuccessful!");
		} else
			System.out.println("Reservation is cancelled.");
	}
	
	/**
	 * This function is called when a customer would like to remove a reservation.
	 */
	public void removeReservationOption() {
		System.out.print("\nPlease enter your full name (case sensitive): ");
		String fullName = scan.next();

		System.out.print("Please enter your contact number: ");
		String contactNo = scan.next();

		System.out.println("\nSearching. . .");
		ArrayList<Reservation> result = mgr.getReservation(fullName, contactNo);

		for (Reservation item : result) {
			System.out.println("Reservation id: " + item.getId());
			System.out.println("Table id      : " + item.getTable().getId());
			System.out.println("Number of pax : " + item.getSizeOfPax());
			System.out.println("Starting time : " + item.getStartTime());
			System.out.println("Ending time   : " + item.getEndTime());
		}

		System.out.println("\nSelect a reservation by its id to remove it: ");
		int choice = scan.nextInt();

		for (Reservation item : result)
			if (item.getId() == choice)
				if (mgr.removeReservation(item)) {
					System.out.println("Reservation id " + item.getId()
							+ " has been successfully removed.");
					return;
				}
		System.out.println("Reservation id " + choice + " is not found!");
	}
}
