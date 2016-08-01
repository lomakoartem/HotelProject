package by.epam.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.epam.hotel.dao.IOrderDAO;
import by.epam.hotel.dao.IRoomDAO;
import by.epam.hotel.dao.IUserDAO;
import by.epam.hotel.dao.abstraction.AbstractDAO;
import by.epam.hotel.entity.Order;
import by.epam.hotel.entity.enumeration.OrderStatus;
import by.epam.hotel.exception.DAOException;

/**
 * The Class OrderDAO.
 */
public class OrderDAO extends AbstractDAO implements IOrderDAO {

	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger(OrderDAO.class);

	/** The Constant PARAM_DATE_FORMAT. */
	public static final String PARAM_DATE_FORMAT = "yyyy-MM-dd";

	/** The Constant PARAM_ID_ORDER. */
	public static final String PARAM_ID_ORDER = "id_order";

	/** The Constant PARAM_DESCRIPTION. */
	public static final String PARAM_DESCRIPTION = "description";

	/** The Constant PARAM_STATUS_ORDER. */
	public static final String PARAM_STATUS_ORDER = "status";

	/** The Constant PARAM_DATE_CREATING_ORDER. */
	public static final String PARAM_DATE_CREATING_ORDER = "creatingDate";

	/** The Constant PARAM_DATE_OF_ARRIVAL. */
	public static final String PARAM_DATE_OF_ARRIVAL = "dateIn";

	/** The Constant PARAM_DATE_OF_DEPARTURE. */
	public static final String PARAM_DATE_OF_DEPARTURE = "dateOut";

	/** The Constant PARAM_ID_USER. */
	public static final String PARAM_ID_USER = "id_user";

	/** The Constant PARAM_ID_ROOM. */
	public static final String PARAM_ID_ROOM = "id_room";

	/** The Constant INSERT_ORDER. */
	public static final String INSERT_ORDER = "INSERT INTO `order`"
			+ "(status,creatingDate,description,id_room,dateIn,dateOut,id_user)"
			+ "VALUES(?,?,?,?,?,?,?)";

	/** The Constant GET_ALL_ORDERS. */
	public static final String GET_ALL_ORDERS = "SELECT * FROM `order`";

	/** The Constant GET_USER_ORDERS. */
	public static final String GET_USER_ORDERS = "SELECT * FROM `order`"
			+ "WHERE id_user = ?";

	/** The Constant DELETE_ORDER_BY_ID. */
	public static final String DELETE_ORDER_BY_ID = "DELETE FROM `order` "
			+ "WHERE id_order = ?";

	/** The Constant UPDATE_ORDER_STATUS. */
	public static final String UPDATE_ORDER_STATUS = "UPDATE `order` "
			+ "SET status = ?" + "WHERE id_order =?";

	/** The Constant GET_ORDER_BY_ID. */
	public static final String GET_ORDER_BY_ID = "SELECT * FROM `order`"
			+ "WHERE id_order = ?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IOrderDAO#createOrder(by.epam.hotel.entity.Order)
	 */
	@Override
	public void createOrder(Order order) throws DAOException {
		PreparedStatement preparedStatement = null;
		DateFormat format = new SimpleDateFormat(PARAM_DATE_FORMAT);
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(INSERT_ORDER);
			preparedStatement.setString(1, order.getStatus().toString());
			preparedStatement.setString(2, format.format(order.getDateOrder())
					.toString());
			preparedStatement.setString(3, order.getDescription());
			preparedStatement.setInt(4, order.getRoom().getId());
			preparedStatement.setString(5, format.format(order.getDateIn())
					.toString());
			preparedStatement.setString(6, format.format(order.getDateOut())
					.toString());
			preparedStatement.setInt(7, order.getUser().getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IOrderDAO#findAllOrders()
	 */
	@Override
	public ArrayList<Order> findAllOrders() throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		IUserDAO userDAO = new UserDAO();
		IRoomDAO roomDAO = new RoomDAO();
		ArrayList<Order> orderList = new ArrayList<Order>();
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt(PARAM_ID_ORDER));
				order.setDescription(resultSet.getString(PARAM_DESCRIPTION));
				order.setDateOrder(resultSet.getDate(PARAM_DATE_CREATING_ORDER));
				order.setStatus(OrderStatus.valueOf(resultSet
						.getString(PARAM_STATUS_ORDER)));
				order.setUser(userDAO.findUserById(resultSet
						.getInt(PARAM_ID_USER)));
				order.setDateIn(resultSet.getDate(PARAM_DATE_OF_ARRIVAL));
				order.setDateOut(resultSet.getDate(PARAM_DATE_OF_DEPARTURE));
				order.setRoom(roomDAO.findRoomById(resultSet
						.getInt(PARAM_ID_ROOM)));
				orderList.add(order);
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return orderList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IOrderDAO#deleteOrderById(int)
	 */
	@Override
	public void deleteOrderById(int idOrder) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID);
			preparedStatement.setInt(1, idOrder);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IOrderDAO#changeOrderStatusById(int,
	 * by.epam.hotel.entity.enumeration.OrderStatus)
	 */
	@Override
	public void changeOrderStatusById(int id, OrderStatus status)
			throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement(UPDATE_ORDER_STATUS);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IOrderDAO#findOrderById(int)
	 */
	@Override
	public Order findOrderById(int id) throws DAOException {
		Order order = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				IRoomDAO roomDAO = new RoomDAO();
				IUserDAO userDAO = new UserDAO();
				order.setId(resultSet.getInt(PARAM_ID_ORDER));
				order.setDateOrder(resultSet.getDate(PARAM_DATE_CREATING_ORDER));
				order.setDateIn(resultSet.getDate(PARAM_DATE_OF_ARRIVAL));
				order.setDateOut(resultSet.getDate(PARAM_DATE_OF_DEPARTURE));
				order.setStatus(OrderStatus.valueOf(resultSet
						.getString(PARAM_STATUS_ORDER)));
				order.setDescription(resultSet.getString(PARAM_DESCRIPTION));
				order.setUser(userDAO.findUserById(resultSet
						.getInt(PARAM_ID_USER)));
				order.setRoom(roomDAO.findRoomById(resultSet
						.getInt(PARAM_ID_ROOM)));
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return order;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IOrderDAO#findUserOrders(int)
	 */
	@Override
	public ArrayList<Order> findUserOrders(int idUser) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		IUserDAO userDAO = new UserDAO();
		IRoomDAO roomDAO = new RoomDAO();
		ArrayList<Order> orderList = new ArrayList<Order>();
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_USER_ORDERS);
			preparedStatement.setInt(1, idUser);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt(PARAM_ID_ORDER));
				order.setDescription(resultSet.getString(PARAM_DESCRIPTION));
				order.setDateOrder(resultSet.getDate(PARAM_DATE_CREATING_ORDER));
				order.setStatus(OrderStatus.valueOf(resultSet
						.getString(PARAM_STATUS_ORDER)));
				order.setUser(userDAO.findUserById(resultSet
						.getInt(PARAM_ID_USER)));
				order.setDateIn(resultSet.getDate(PARAM_DATE_OF_ARRIVAL));
				order.setDateOut(resultSet.getDate(PARAM_DATE_OF_DEPARTURE));
				order.setRoom(roomDAO.findRoomById(resultSet
						.getInt(PARAM_ID_ROOM)));
				orderList.add(order);
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return orderList;
	}

}
