package projects.entity;

import java.sql.Date;

public class Reservation extends Id {
	private int guest_id;
	private int room_id;
	private Date start_date;
	private Date end_date;
	private double price;

	public int getRervationId() {
		return super.id;
	}
	
	public void setRervationId(int id) {
		super.id = id;
	}
	
	public int getGuesId() {
		return guest_id;
	}

	public void setGuestId(int guest_id) {
		this.guest_id = guest_id;
	}

	public int getRoomId() {
		return room_id;
	}

	public void setRoomId(int room_id) {
		this.room_id = room_id;
	}

	public Date getStartDate() {
		return start_date;
	}

	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEndDate() {
		return end_date;
	}

	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}