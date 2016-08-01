package by.epam.hotel.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import by.epam.hotel.dao.IOrderDAO;
import by.epam.hotel.dao.IRoomDAO;
import by.epam.hotel.dao.impl.OrderDAO;
import by.epam.hotel.dao.impl.RoomDAO;
import by.epam.hotel.entity.Order;
import by.epam.hotel.entity.User;
import by.epam.hotel.exception.DAOException;
import by.epam.hotel.exception.LogicException;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class CreateOrderLogic. All logic associated with creating order.
 */
public class CreateOrderLogic {

	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger(CreateOrderLogic.class);

	/** The Constant PARAM_ERROR_MESSAGE. */
	public static final String PARAM_ERROR_MESSAGE = "errorMessage";

	/** The Constant PARAM_DATE_FORMAT. */
	private static final String PARAM_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Creates the order.
	 * 
	 * @param order
	 *            the order
	 * @param user
	 *            the user
	 * @param idRoom
	 *            the id room
	 * @return the info messenger
	 * @throws TechnicalException
	 *             the logic level exception
	 * @throws LogicException
	 */
	public static void createOrder(Order order, User user, String idRoom,
			String dateIn, String dateOut) throws TechnicalException, LogicException {
		SimpleDateFormat format = new SimpleDateFormat(PARAM_DATE_FORMAT);
		IOrderDAO orderDAO = new OrderDAO();
		IRoomDAO roomDAO = new RoomDAO();
		if (idRoom != null && dateIn != "" && dateOut != "") {
			try {
				order.setDateIn(format.parse(dateIn));
				order.setDateOut(format.parse(dateOut));
			} catch (ParseException e) {
				throw new TechnicalException();
			}
			if (CreateOrderLogic.checkDate(order.getDateIn(),
					order.getDateOut())) {
				order.setUser(user);
				try {
					order.setRoom(roomDAO.findRoomById(Integer.parseInt(idRoom)));
					orderDAO.createOrder(order);
				} catch (DAOException e) {
					throw new TechnicalException();
				}
			} else {
				throw new LogicException(
						ConfigurationManager
								.getInstance()
								.getProperty(
										ConfigurationManager.WRONG_DATE_EXCEPTION_MESSAGE));
			}
		} else {
			throw new LogicException(ConfigurationManager.getInstance()
					.getProperty(
							ConfigurationManager.CREATE_ORDER_PROBLEM_MESSAGE));
		}
	}

	/**
	 * Check date.
	 * 
	 * @param dateIn
	 *            the date in
	 * @param dateOut
	 *            the date out
	 * @return true, if successful
	 * @throws TechnicalException
	 *             the logic level exception
	 */
	private static boolean checkDate(Date dateIn, Date dateOut)
			throws TechnicalException {
		if (dateOut.getTime() - dateIn.getTime() > 0) {
			return true;
		}
		return false;
	}
}
