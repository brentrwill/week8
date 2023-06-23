package projects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import projects.dao.DbConnection;
import projects.entity.Guest;
import projects.entity.GuestListing;
import projects.entity.Listing;
import projects.entity.Reservation;
import projects.entity.Room;

public class ProjectsApp {
	
	/**
	 * Simple definitions of the Bed Options
	 * 
	 * 1 = 1 Queen size bed 
	 * 2 = 2 Queen size beds 
	 * 3 = 1 King size bed 
	 * 4 = 2 King size beds
	 */
	public static void main(String[] args) {
		
		/**
		 * Date helper
		 */
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		/**
		 * Let's create our connection to our Database.
		 */
		Connection conn = DbConnection.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/**
		 * In this exercise we are going to look at a hotel reservation system.
		 * 
		 * 1- We will first create some rooms from our hotel and insert them into our database. 
		 * 2- Read the rooms back from the database. 
		 * 3- Insert the rooms into our Listing table. 
		 * 4- Create a Guest in our database. 
		 * 5- Search the listings to find a room. 
		 * 6- Create a reservation.
		 */
		Random random = new Random();
		System.out.println(
				"1- We will first create some rooms from our hotel and insert them into our database. --------------------------------");
		List<Room> roomsToList = new ArrayList<Room>();

		Room room1 = new Room();
		room1.setRoomNumber(random.nextInt(1000));
		room1.setNumberOfOccupents(4);
		room1.setBedOptions(2);
		room1.setDescription("This room is in the north east corner overlooking the street below.");
		roomsToList.add(room1);

		Room room2 = new Room();
		room2.setRoomNumber(random.nextInt(1000));
		room2.setNumberOfOccupents(4);
		room2.setBedOptions(4);
		room2.setDescription("This room is on the west side overlooking the parking lot.");
		roomsToList.add(room2);

		/**
		 * Now let's insert the Rooms into our Database.
		 */
		try {
			for (int roomIndex = 0; roomIndex < roomsToList.size(); roomIndex++) {
				Room rm = roomsToList.get(roomIndex);
				StringBuilder builder = new StringBuilder();
				builder.append("INSERT INTO room (room_number, number_of_occupents, bed_options, description)");
				builder.append(" VALUES ");
				builder.append("(");
				builder.append(rm.getRoomNumber()).append(",");
				builder.append(rm.getNumberOfOccupents()).append(",");
				builder.append(rm.getBedOptions()).append(",");
				builder.append("'").append(rm.getDescription()).append("'");
				builder.append(")");

				/**
				 * Let's look at the SQL that is printed out.
				 */
				System.out.println(builder.toString());
				stmt.executeUpdate(builder.toString());
			}
		} catch (SQLException e) {
			/**
			 * We will just print out an error if we cannot insert the room.
			 */
			e.printStackTrace();
		}

		System.out.println("2- Read the rooms back from the database. --------------------------------");
		/**
		 * Now let's get back from the Database the rooms I just inserted.
		 */
		List<Room> createdRooms = new ArrayList<Room>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM room");
			while (rs.next()) {
				Room rm = new Room();
				rm.setRoomId(rs.getInt("room_id"));
				rm.setRoomNumber(rs.getInt("room_number"));
				rm.setNumberOfOccupents(rs.getInt("number_of_occupents"));
				rm.setBedOptions(rs.getInt("bed_options"));
				rm.setDescription(rs.getString("description"));

				createdRooms.add(rm);
			}
		} catch (SQLException e) {
			/**
			 * We will just print out an error if we cannot insert the room.
			 */
			e.printStackTrace();
		}

		/**
		 * Let test and see if we have 2 rooms.
		 */
		if (createdRooms != null && createdRooms.size() > 0) {
			System.out.println("We got the correct number of rooms!");
		}

		/**
		 * Now let's list our newly add rooms.
		 */
		List<Listing> listings = new ArrayList<Listing>();
		for (int roomIndex = 0; roomIndex < createdRooms.size(); roomIndex++) {
			Room rm = createdRooms.get(roomIndex);
			Listing listing = new Listing();
			listing.setPrice(85.00);
			listing.setRoomId(rm.getRoomId());

			listings.add(listing);
		}

		System.out.println("3- Insert the rooms into our Listing table. --------------------------------");
		/**
		 * Adding the new listings.
		 */
		try {
			for (int listingIndex = 0; listingIndex < listings.size(); listingIndex++) {
				Listing list = listings.get(listingIndex);
				StringBuilder builder = new StringBuilder();
				builder.append("INSERT INTO listing (room_id, price)");
				builder.append(" VALUES ");
				builder.append("(");
				builder.append(list.getRoomId()).append(",");
				builder.append(list.getPrice());
				builder.append(")");

				/**
				 * Let's look at the SQL that is printed out.
				 */
				System.out.println(builder.toString());
				stmt.executeUpdate(builder.toString());
			}
		} catch (SQLException e) {
			/**
			 * We will just print out an error if we cannot insert the room.
			 */
			e.printStackTrace();
		}

		System.out.println("4- Create a Guest. --------------------------------");
		/**
		 * Create a Guest
		 */
		Guest guest = new Guest();
		guest.setFirstName("Susan");
		guest.setLastName("Johnson");
		guest.setEmail("susan_johnson123@gmail.com");
		guest.setAddress1("1234 North 8765 West");
		guest.setCity("New York");
		guest.setZip("84999");
		guest.setPhone("555-979-8002");

		/**
		 * Let's insert our Guest
		 */

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("INSERT INTO guest (first_name, last_name, email, address1, address2, city, zip, phone)");
			builder.append(" VALUES ");
			builder.append("(");
			builder.append("'").append(guest.getFirstName()).append("',");
			builder.append("'").append(guest.getLastName()).append("',");
			builder.append("'").append(guest.getEmail()).append("',");
			builder.append("'").append(guest.getAddress1()).append("',");
			builder.append("'").append(guest.getAddress2()).append("',");
			builder.append("'").append(guest.getCity()).append("',");
			builder.append("'").append(guest.getZip()).append("',");
			builder.append("'").append(guest.getPhone()).append("'");
			builder.append(")");

			/**
			 * Let's look at the SQL that is printed out.
			 */
			System.out.println(builder.toString());
			stmt.executeUpdate(builder.toString());
		} catch (SQLException e) {
			/**
			 * We will just print out an error if we cannot insert the room.
			 */
			e.printStackTrace();
		}

		/**
		 * Let lookup up the Guest we just created.
		 */
		Guest newGuest = null;
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM guest WHERE email = '" + guest.getEmail() + "'");
			while (rs.next()) {
				newGuest = new Guest();
				newGuest.setGuestId(rs.getInt("guest_id"));
				newGuest.setFirstName(rs.getString("first_name"));
				newGuest.setLastName(rs.getString("last_name"));
				newGuest.setEmail(rs.getString("email"));
				newGuest.setAddress1(rs.getString("address1"));
				newGuest.setAddress2(rs.getString("address2"));
				newGuest.setCity(rs.getString("city"));
				newGuest.setPhone(rs.getString("phone"));
				newGuest.setZip(rs.getString("zip"));
			}
		} catch (SQLException e) {
			/**
			 * We will just print out an error if we cannot insert the room.
			 */
			e.printStackTrace();
		}
		if (newGuest != null) {
			System.out.println("Found our guest with email address : " + newGuest.getEmail());
		}

		System.out.println("5- Search the listings to find a room. --------------------------------");
		/**
		 * Let search the listings.
		 */
		List<GuestListing> guestListing = new ArrayList<GuestListing>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * from listing l JOIN room r WHERE r.room_id = l.room_id");
			while (rs.next()) {
				Listing list = new Listing();
				list.setListingId(rs.getInt("listing_id"));
				list.setPrice(rs.getDouble("price"));

				Room room = new Room();
				room.setRoomId(rs.getInt("l.room_id"));
				room.setNumberOfOccupents(rs.getInt("number_of_occupents"));
				room.setRoomNumber(rs.getInt("room_number"));
				room.setBedOptions(rs.getInt("bed_options"));
				room.setDescription(rs.getString("description"));

				GuestListing guestList = new GuestListing();
				guestList.setListing(list);
				guestList.setRoom(room);

				guestListing.add(guestList);
			}
		} catch (SQLException e) {
			/**
			 * We will just print out an error if we cannot insert the room.
			 */
			e.printStackTrace();
		}
		if (guestListing != null && guestListing.size() > 0) {
			System.out.println("We found some rooms to look at!");
		}

		/**
		 * Let's print out the listings and a nice readable
		 */
		System.out.println("------------------------------------------------------------------");
		System.out.println(" Room Number | Room Occupency | Price | Beds | Description ");
		for (int listingIndex = 0; listingIndex < guestListing.size(); listingIndex++) {
			GuestListing theList = guestListing.get(listingIndex);
			String bedTypes = "";
			if (theList.getRoom().getBedOptions() == 2) {
				bedTypes = "2 Queen Beds";
			} else if (theList.getRoom().getBedOptions() == 4) {
				bedTypes = "2 Kings Beds";
			}
			System.out.println(theList.getRoom().getRoomNumber() + " , " + theList.getRoom().getNumberOfOccupents()
					+ " , " + theList.getListing().getPrice() + " , " + bedTypes + " , "
					+ theList.getRoom().getDescription());
		}
		System.out.println("------------------------------------------------------------------");

		
		System.out.println("6- Create a reservation. --------------------------------");
		
		/**
		 * We grab the first one in the list for this demo.
		 */
		try {
			GuestListing theList = guestListing.get(0);
			Reservation reservation = new Reservation();
			reservation.setGuestId(newGuest.getGuestId());
			reservation.setRoomId(theList.getRoom().getRoomId());
			reservation.setPrice(theList.getListing().getPrice());
			reservation.setStartDate(new java.sql.Date(dateFormat.parse("2023/07/18 00:00:00").getTime()));
			reservation.setEndDate(new java.sql.Date(dateFormat.parse("2023/07/21 00:00:00").getTime()));
			
			StringBuilder builder = new StringBuilder();
			builder.append("INSERT INTO reservation (guest_id, room_id, start_date, end_date, price)");
			builder.append(" VALUES ");
			builder.append("(");
			builder.append(reservation.getGuesId()).append(",");
			builder.append(reservation.getRoomId()).append(",");
			builder.append("'").append(reservation.getStartDate()).append("',");
			builder.append("'").append(reservation.getEndDate()).append("',");
			builder.append(reservation.getPrice());
			builder.append(")");

			/**
			 * Let's look at the SQL that is printed out.
			 */
			System.out.println(builder.toString());
			stmt.executeUpdate(builder.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * Now let's read back our reservation.
		 */
		Reservation reservation = null;
		try {
			ResultSet rs = stmt.executeQuery("SELECT * from reservation r JOIN guest g WHERE r.guest_id = g.guest_id AND r.guest_id = " + newGuest.getGuestId());
			while (rs.next()) {
				reservation = new Reservation();
				reservation.setRervationId(rs.getInt("reservation_id"));
				reservation.setGuestId(rs.getInt("guest_id"));
				reservation.setRoomId(rs.getInt("room_id"));
				reservation.setStartDate(rs.getDate("start_date"));
				reservation.setEndDate(rs.getDate("end_date"));
				reservation.setPrice(rs.getDouble("price"));
			}
		} catch (SQLException e) {
			/**
			 * We will just print out an error if we cannot insert the room.
			 */
			e.printStackTrace();
		}
		if(reservation != null) {
			System.out.println("Found our reservation!");
			System.out.println("Reservaton Id : " + reservation.getRervationId());
		}
	}
}
