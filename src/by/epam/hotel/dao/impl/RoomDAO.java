package by.epam.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.epam.hotel.dao.IRoomDAO;
import by.epam.hotel.dao.abstraction.AbstractDAO;
import by.epam.hotel.entity.Room;
import by.epam.hotel.entity.enumeration.AppartmentCategory;
import by.epam.hotel.entity.enumeration.RoomStatus;
import by.epam.hotel.exception.DAOException;

public class RoomDAO extends AbstractDAO implements IRoomDAO {

	public static final Logger LOG = Logger.getLogger(RoomDAO.class);

	public static final String PARAM_ID = "id_room";
	public static final String PARAM_ROOM_NUMBER = "roomNumber";
	public static final String PARAM_STATUS_ROOM = "status";
	public static final String PARAM_NUMBER_BEDS = "placeForSleep";
	public static final String PARAM_COST_PER_DAY = "costPerDay";
	public static final String PARAM_CATEGORY = "category";
	public static final String PARAM_DESCRIPTION = "description";

	public static final String FIND_ROOM_LIST = "SELECT * FROM `room` "
			+ "INNER JOIN roominformation ON room.id_info = roominformation.id_info";

	public static final String FIND_ROOM_BY_ID = "SELECT * FROM `room` "
			+ "INNER JOIN roominformation ON room.id_info = roominformation.id_info "
			+ "WHERE id_room=?";

	public static final String UPDATE_ROOM_STATUS = "UPDATE room "
			+ "SET status = ? WHERE id_room = ? ";

	@Override
	public ArrayList<Room> findAllRoom() throws DAOException {

		ArrayList<Room> roomList = new ArrayList<Room>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_ROOM_LIST);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Room room = new Room();
				room.setId(Integer.parseInt(resultSet.getString(PARAM_ID)));
				room.setRoomNumber(Integer.parseInt(resultSet
						.getString(PARAM_ROOM_NUMBER)));
				room.setStatus(RoomStatus.valueOf(resultSet
						.getString(PARAM_STATUS_ROOM)));
				room.setPlaceForSleep(Integer.parseInt(resultSet
						.getString(PARAM_NUMBER_BEDS)));
				room.setCategory(AppartmentCategory.valueOf(resultSet
						.getString(PARAM_CATEGORY)));
				room.setCostPerDay(Integer.parseInt(resultSet
						.getString(PARAM_COST_PER_DAY)));
				room.setDescription(resultSet.getString(PARAM_DESCRIPTION));
				roomList.add(room);
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);

		}
		return roomList;
	}

	@Override
	public Room findRoomById(int idRoom) throws DAOException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Room room = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_ROOM_BY_ID);
			preparedStatement.setInt(1, idRoom);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				room = new Room();
				room.setId(Integer.parseInt(resultSet.getString(PARAM_ID)));
				room.setRoomNumber(Integer.parseInt(resultSet
						.getString(PARAM_ROOM_NUMBER)));
				room.setStatus(RoomStatus.valueOf(resultSet
						.getString(PARAM_STATUS_ROOM)));
				room.setPlaceForSleep(Integer.parseInt(resultSet
						.getString(PARAM_NUMBER_BEDS)));
				room.setCategory(AppartmentCategory.valueOf(resultSet
						.getString(PARAM_CATEGORY)));
				room.setCostPerDay(Integer.parseInt(resultSet
						.getString(PARAM_COST_PER_DAY)));
				room.setDescription(resultSet.getString(PARAM_DESCRIPTION));
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return room;
	}

	@Override
	public void changeRoomStatus(int id, RoomStatus status)
			throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_ROOM_STATUS);
			preparedStatement.setString(1, status.toString());
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}

	}

}
