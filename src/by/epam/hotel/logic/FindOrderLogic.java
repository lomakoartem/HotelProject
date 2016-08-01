package by.epam.hotel.logic;

import java.util.ArrayList;

import by.epam.hotel.dao.IOrderDAO;
import by.epam.hotel.dao.impl.OrderDAO;
import by.epam.hotel.entity.Order;
import by.epam.hotel.exception.DAOException;
import by.epam.hotel.exception.TechnicalException;

/**
 * The Class FindOrderLogic. All logic associated with finding order.
 */
public class FindOrderLogic {

	/**
	 * Gets the client order.
	 * 
	 * @param idUser
	 *            the id user
	 * @return the client order
	 * @throws TechnicalException
	 *             the logic level exception
	 */
	public static ArrayList<Order> findClientOrder(int idUser)
			throws TechnicalException {
		IOrderDAO orderDAO = new OrderDAO();
		ArrayList<Order> orderList = new ArrayList<Order>();
		try {
			for (Order order : orderDAO.findUserOrders(idUser)) {
				orderList.add(order);
			}
		} catch (DAOException e) {
			throw new TechnicalException();
		}
		return orderList;
	}

	/**
	 * Gets the all order.
	 * 
	 * @return the all order
	 * @throws TechnicalException
	 *             the logic level exception
	 */
	public static ArrayList<Order> findAllOrder() throws TechnicalException {
		IOrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findAllOrders();
		} catch (DAOException e) {
			throw new TechnicalException();
		}
	}
}
