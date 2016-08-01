package by.epam.hotel.command.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.hotel.command.ICommand;
import by.epam.hotel.entity.Order;
import by.epam.hotel.entity.Room;
import by.epam.hotel.entity.User;
import by.epam.hotel.entity.enumeration.OrderStatus;
import by.epam.hotel.exception.LogicException;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.logic.CreateOrderLogic;
import by.epam.hotel.logic.FindRoomLogic;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class CreateOrderCommand. Command for creating order.
 */
public class CreateOrderCommand implements ICommand {

	/** The Constant LOG. */
	private static final Logger LOG = Logger
			.getLogger(CreateOrderCommand.class);

	/** The Constant PARAM_FREE_ROOM. */
	private static final String PARAM_FREE_ROOM = "freeRoom";

	/** The Constant PARAM_ERROR_MESSAGE. */
	private static final String PARAM_ERROR_MESSAGE = "errorMessage";

	/** The Constant PARAM_ACTION_MESSAGE. */
	public static final String PARAM_ACTION_MESSAGE = "actionMessage";

	/** The Constant PARAM_DATE_OF_ARRIVAL. */
	private static final String PARAM_DATE_OF_ARRIVAL = "dateIn";

	/** The Constant PARAM_DATE_OF_DEPARTURE. */
	private static final String PARAM_DATE_OF_DEPARTURE = "dateOut";

	/** The Constant PARAM_ROOM_ID. */
	private static final String PARAM_ROOM_ID = "id_room";

	/** The Constant PARAM_DESCRIPTION. */
	private static final String PARAM_DESCRIPTION = "description";

	/** The Constant PARAM_USER. */
	private static final String PARAM_USER = "user";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.epam.hotel.command.ICommand#execute(javax.servlet.http.HttpServletRequest
	 * )
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;

		Order order = new Order();
		User user = (User) request.getSession().getAttribute(PARAM_USER);
		order.setDateOrder(new Date());
		order.setDescription(request.getParameter(PARAM_DESCRIPTION));
		order.setStatus(OrderStatus.PENDING);
		String idRoom = request.getParameter(PARAM_ROOM_ID);
		String dateIn = request.getParameter(PARAM_DATE_OF_ARRIVAL);
		String dateOut = request.getParameter(PARAM_DATE_OF_DEPARTURE);
		try {
			CreateOrderLogic.createOrder(order, user, idRoom, dateIn, dateOut);
			ArrayList<Room> freeRoom = FindRoomLogic.findFreeRoom();
			request.setAttribute(PARAM_FREE_ROOM, freeRoom);
			page = refreshWithChanges(request);
			request.setAttribute(
					PARAM_ACTION_MESSAGE,
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.CREATE_ORDER_SUCCESS_MESSAGE));
		} catch (TechnicalException e) {
			LOG.error("Something goes wrong, redirect to error page.", e);
			request.setAttribute(
					PARAM_ERROR_MESSAGE,
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		} catch (LogicException e) {
			LOG.error("Something goes wrong with creating order.", e);
			request.setAttribute(PARAM_ERROR_MESSAGE, e);
			page = refreshWithChanges(request);
		}
		return page;
	}

	private String refreshWithChanges(HttpServletRequest request) {
		ArrayList<Room> freeRoom;
		try {
			freeRoom = FindRoomLogic.findFreeRoom();
			request.setAttribute(PARAM_FREE_ROOM, freeRoom);
		} catch (TechnicalException e) {
			LOG.error("Something goes wrong, redirect to error page.", e);
			request.setAttribute(
					PARAM_ERROR_MESSAGE,
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		}
		return ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.CREATE_ORDER_PATH);
	}
}
