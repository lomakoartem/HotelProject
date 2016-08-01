package by.epam.hotel.logic;

import java.util.ArrayList;

import by.epam.hotel.dao.IRoomDAO;
import by.epam.hotel.dao.impl.RoomDAO;
import by.epam.hotel.entity.Room;
import by.epam.hotel.entity.enumeration.RoomStatus;
import by.epam.hotel.exception.DAOException;
import by.epam.hotel.exception.TechnicalException;

/**
 * The Class RoomLogic. All logic associated with finding room.
 */
public class FindRoomLogic {

	/**
	 * Gets the free room.
	 * 
	 * @return the free room
	 * @throws TechnicalException
	 *             the logic level exception
	 */
	public static ArrayList<Room> findFreeRoom() throws TechnicalException {
		IRoomDAO roomDAO = new RoomDAO();
		ArrayList<Room> freeRoom = new ArrayList<Room>();
		try {
			for (Room room : roomDAO.findAllRoom()) {
				if (room.getStatus().equals(RoomStatus.FREE))
					freeRoom.add(room);
			}
		} catch (DAOException e) {
			throw new TechnicalException();
		}
		return freeRoom;
	}

	/**
	 * Gets the all room.
	 * 
	 * @return the all room
	 * @throws TechnicalException
	 *             the logic level exception
	 */
	public static ArrayList<Room> findAllRoom() throws TechnicalException {
		IRoomDAO roomDAO = new RoomDAO();
		ArrayList<Room> listRoom = new ArrayList<Room>();
		try {
			listRoom = roomDAO.findAllRoom();
		} catch (DAOException e) {
			throw new TechnicalException();
		}
		return listRoom;
	}
}
