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
		
		System.out.println("#################################");
		System.out.println("#       RESERVATION OPTION      #");
		System.out.println("#      1. Reserve table(s).     #");
		System.out.println("#      2. Remove a reservation  #");
		System.out.println("#      3. Main menu       .     #");
		System.out.println("#################################");
		System.out.println("Select your option: ");
		int choice = scan.nextInt(); scan.nextLine();
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
			System.out.println("#################################");
			System.out.println("#       RESERVATION OPTION      #");
			System.out.println("#      1. Reserve table(s).     #");
			System.out.println("#      2. Remove a reservation  #");
			System.out.println("#      3. Main menu       .     #");
			System.out.println("#################################");
			System.out.print("Select your option: ");
			choice = scan.nextInt(); scan.nextLine();
		}
	}
	
	/**
	 * This function is called when a customer would like to reserve a table.
	 */
	public void reserveTableOption() {
		System.out.print("\nPlease enter your full name (case sensitive): ");
		String fullName = scan.nextLine();

		System.out.print("Please enter your contact number: ");
		String contactNo = scan.nextLine();

		int d, M, y, h, m, n;
		Table which;
		long duration;
		char choice;
		Date start, end;

		do {
			choice = 'n';
			
			System.out.print("Please provide day, month, and year separated by a space (dd mm yyyy): ");
			do
			{
				d = scan.nextInt();
				M = scan.nextInt() - 1;
				y = scan.nextInt() - baseYear;
				start = new Date(y, M, d);
				Date today = new Date();
				
				if(today.getDate() == d && today.getMonth() == M && today.getYear() == y)
				{
					System.out.println("You may not reserve a table on the same day!");
					return;
				}
				else if(today.after(start))
				{
					System.out.println("Error! Please input the date properly!");
					return;
				}
				else
					break;
			} while(true);

			System.out.print("Please provide hour, and minute separated by a space in 24h format: ");
			h = scan.nextInt();
			m = scan.nextInt();

			System.out.print("Please provide the duration in minutes: ");
			duration = scan.nextLong();

			start.setHours(h); start.setMinutes(m);
			end = new Date(start.getTime() + duration * 60000L - 1L);

			System.out.print("Please input the number of pax: ");
			n = scan.nextInt();

			which = mgr.getTable(n, start, end);

			if (which == null) {
				System.out.print("Sorry, no table is available for your reservation.\nWould you like to reenter your input (y/n): ");
				choice = scan.next().charAt(0);
			}
		} while (choice == 'y' || choice == 'Y');

		if (which != null) {
			if (mgr.allocate(which, fullName, contactNo, n, start, end))
				System.out.println("\nAllocation of table for your timeslot is successful! Table " + which.getId() + " is allocated to you on " + start + " until " + end + "\n");
			else
				System.out.println("\nAllocation of table for your timeslot is unsuccessful!\n");
		} else
			System.out.println("\nReservation is cancelled.\n");
	}
	
	/**
	 * This function is called when a customer would like to remove a reservation.
	 */
	public void removeReservationOption() {
		System.out.print("\nPlease enter your full name (case sensitive): ");
		String fullName = scan.nextLine();

		System.out.print("Please enter your contact number: ");
		String contactNo = scan.nextLine();

		System.out.println("\nSearching. . .");
		ArrayList<Reservation> result = mgr.getReservation(fullName, contactNo);

		if(result.isEmpty())
			System.out.println("You does not have any reservation for now.");
		else
		{
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
	
	/**
	 * This is for unit testing
	 */
	public static void main(String[] args)
	{
		ReservationUI ui = new ReservationUI(new ReservationController());
		
		ui.run();
	}
}
