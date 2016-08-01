package by.epam.hotel.logic;

import by.epam.hotel.dao.IOrderDAO;
import by.epam.hotel.dao.impl.OrderDAO;
import by.epam.hotel.entity.enumeration.OrderStatus;
import by.epam.hotel.exception.DAOException;
import by.epam.hotel.exception.LogicException;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class ChangeStatusOrderLogic. All logic associated with changing order
 * status.
 */
public class ChangeStatusOrderLogic {

	/** The Constant PARAM_ERROR_MESSAGE. */
	public static final String PARAM_ERROR_MESSAGE = "errorMessage";

	/** The Constant PARAM_STATUS_EMPTY. */
	public static final String PARAM_STATUS_EMPTY = "empty";

	/**
	 * Change status order.
	 * 
	 * @param idOrder
	 *            the id order
	 * @param statusOrder
	 *            the status order
	 * @return the info messenger
	 * @throws TechnicalException
	 *             the logic level exception
	 * @throws LogicException
	 */

	public static void changeStatusOrder(String idOrder, String statusOrder)
			throws TechnicalException, LogicException {
		IOrderDAO orderDAO = new OrderDAO();
		if (idOrder != null) {
			if (PARAM_STATUS_EMPTY.equals(statusOrder)) {
				throw new LogicException(
						ConfigurationManager
								.getInstance()
								.getProperty(
										ConfigurationManager.CHOOSE_ACTION_ERROR_MESSAGE));
			}
			try {
				orderDAO.changeOrderStatusById(Integer.parseInt(idOrder),
						OrderStatus.valueOf(statusOrder));
				if (OrderStatus.valueOf(statusOrder).equals(
						OrderStatus.CONFIRMED)) {
					CreateBillLogic.createBill(idOrder);
				}
			} catch (DAOException e) {
				throw new TechnicalException();
			}
		} else {
			throw new LogicException(
					ConfigurationManager
							.getInstance()
							.getProperty(
									ConfigurationManager.CHANGE_STATUS_ORDER_EXCEPTION_MESSAGE));
		}
	}
}
