package by.epam.hotel.dao;

import java.util.ArrayList;

import by.epam.hotel.entity.Order;
import by.epam.hotel.entity.enumeration.OrderStatus;
import by.epam.hotel.exception.DAOException;

/**
 * The Interface IOrderDAO.
 */
public interface IOrderDAO {

	/**
	 * Creates the order.
	 *
	 * @param order the order
	 * @throws DAOException the DAO level exception
	 */
	void createOrder(Order order) throws DAOException;

	/**
	 * Find all orders.
	 *
	 * @return the array list
	 * @throws DAOException the DAO level exception
	 */
	ArrayList<Order> findAllOrders() throws DAOException;

	/**
	 * Find user orders.
	 *
	 * @param idUser the id user
	 * @return the array list
	 * @throws DAOException the DAO level exception
	 */
	ArrayList<Order> findUserOrders(int idUser) throws DAOException;

	/**
	 * Delete order by id.
	 *
	 * @param idOrder the id order
	 * @throws DAOException the DAO level exception
	 */
	void deleteOrderById(int idOrder) throws DAOException;

	/**
	 * Change order status by id.
	 *
	 * @param parseInt the parse int
	 * @param valueOf the value of
	 * @throws DAOException the DAO level exception
	 */
	void changeOrderStatusById(int parseInt, OrderStatus valueOf)
			throws DAOException;

	/**
	 * Find order by id.
	 *
	 * @param id the id
	 * @return the order
	 * @throws DAOException the DAO level exception
	 */
	Order findOrderById(int id) throws DAOException;
}
