package projects.entity;

public class Listing extends Id {
	private int room_id;
	private double price;

	public int getListingId() {
		return super.id;
	}
	
	public void setListingId(int id) {
		super.id = id;
	}
	
	public int getRoomId() {
		return room_id;
	}

	public void setRoomId(int room_id) {
		this.room_id = room_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
