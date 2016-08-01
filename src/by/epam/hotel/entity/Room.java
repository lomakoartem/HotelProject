package by.epam.hotel.entity;

import java.io.Serializable;

import org.apache.log4j.Logger;

import by.epam.hotel.entity.enumeration.AppartmentCategory;
import by.epam.hotel.entity.enumeration.RoomStatus;

/**
 * The Class Room.
 */
public class Room implements Cloneable, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8690246284286509733L;
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger(Room.class);
	
	/** The id. */
	private int id;
	
	/** The room number. */
	private int roomNumber;
	
	/** The place for sleep. */
	private int placeForSleep;
	
	/** The category. */
	private AppartmentCategory category;
	
	/** The cost per day. */
	private int costPerDay;
	
	/** The status. */
	private RoomStatus status;
	
	/** The description. */
	private String description;

	/**
	 * Instantiates a new room.
	 */
	public Room() {
		LOG.info("Create 'Room' object.");
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the room number.
	 *
	 * @return the room number
	 */
	public int getRoomNumber() {
		return this.roomNumber;
	}

	/**
	 * Sets the room number.
	 *
	 * @param roomNumber the new room number
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * Gets the place for sleep.
	 *
	 * @return the place for sleep
	 */
	public int getPlaceForSleep() {
		return placeForSleep;
	}

	/**
	 * Sets the place for sleep.
	 *
	 * @param placeForSleep the new place for sleep
	 */
	public void setPlaceForSleep(int placeForSleep) {
		this.placeForSleep = placeForSleep;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public AppartmentCategory getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(AppartmentCategory category) {
		this.category = category;
	}

	/**
	 * Gets the cost per day.
	 *
	 * @return the cost per day
	 */
	public int getCostPerDay() {
		return costPerDay;
	}

	/**
	 * Sets the cost per day.
	 *
	 * @param costPerDay the new cost per day
	 */
	public void setCostPerDay(int costPerDay) {
		this.costPerDay = costPerDay;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public RoomStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + costPerDay;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + placeForSleep;
		result = prime * result + roomNumber;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (category != other.category)
			return false;
		if (costPerDay != other.costPerDay)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (placeForSleep != other.placeForSleep)
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(getClass().getSimpleName())
				.append(": id: ").append(id).append(", roomNumber: ")
				.append(roomNumber).append(", placeForSleep: ")
				.append(placeForSleep).append(", costPerDay: ")
				.append(costPerDay).append(", category: ").append(category)
				.append(", description: ").append(", status: ").append(status)
				.append(".").toString();
	}
}
