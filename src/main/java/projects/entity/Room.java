package projects.entity;

public class Room extends Id {
	private int room_number;
	private int number_of_occupents;
	private int bed_options;
	private String description;

	public int getRoomId() {
		return super.id;
	}
	
	public void setRoomId(int id) {
		super.id = id;
	}
	
	public int getRoomNumber() {
		return room_number;
	}

	public void setRoomNumber(int room_number) {
		this.room_number = room_number;
	}

	public int getNumberOfOccupents() {
		return number_of_occupents;
	}

	public void setNumberOfOccupents(int number_of_occupents) {
		this.number_of_occupents = number_of_occupents;
	}

	public int getBedOptions() {
		return bed_options;
	}

	public void setBedOptions(int bed_options) {
		this.bed_options = bed_options;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}