package Entity;

import java.util.Date;
import java.util.ArrayList;

public class Table {
	private static int nextId = 0;
	private int capacity, id;
	private ArrayList<Reservation> reservation;

	public Table(int capacity) {
		this.id = nextId++;
		this.capacity = capacity;
		reservation = new ArrayList<Reservation>();
	}

	public boolean isAvailable(int sizeOfPax, Date start, Date end) {
		if (capacity >= sizeOfPax) {
			for (Reservation item : reservation)
				if (!item.isClash(start, end))
					return true;

			return false;
		} else
			return false;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean allocate(Reservation item) {
		if (isAvailable(item.getSizeOfPax(), item.getStartTime(),
				item.getEndTime()))
			return reservation.add(item);

		return false;
	}

	public ArrayList<Reservation> getReservation(String fullName,
			String contactNo) {
		ArrayList<Reservation> result = new ArrayList<Reservation>();
		for (Reservation item : reservation)
			if (item.getFullName().compareTo(fullName) == 0
					&& item.getContactNo().compareTo(contactNo) == 0)
				result.add(item);
		return result;
	}

	public boolean removeReservation(Reservation which) {
		return reservation.remove(which);
	}

	public int getId() {
		return id;
	}
	
	public String toString()
	{
		String reservation_str = "";
		for(int i = 0; i < reservation.size(); ++i)
			reservation_str += (i > 0 ? "," : "") + reservation.get(i).getId();
		return id + "|" + capacity + "|" + reservation_str;
	}
	
	public void setNextId(int nextId)
	{
		this.nextId = nextId;
	}
}
