package Entity;

import java.util.Date;

public class Reservation {
	/*
	 * - ADD RESERVATION ID
	 */

	private String fullName, contactNo;
	private int sizeOfPax, id;
	private Date start, end;
	private static int nextId = 0;
	private Table table;

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

	public boolean isClash(Date start, Date end) {
		return this.start.after(end) || this.end.before(start);
	}

	public Date getStartTime() {
		return start;
	}

	public Date getEndTime() {
		return end;
	}

	public int getSizeOfPax() {
		return sizeOfPax;
	}

	public String getFullName() {
		return fullName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public int getId() {
		return id;
	}

	public Table getTable() {
		return table;
	}
	
	public String toString()
	{
		return id + "|" + table.getId() + "|" fullName + "|" + contactNo + "|" + sizeOfPax + "|" + start.getTime() + "|" + end.getTime();
	}
	
	public void setNextId(int nextId)
	{
		this.nextId = nextId;
	}
}
