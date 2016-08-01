package by.epam.hotel.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.hotel.command.ICommand;
import by.epam.hotel.entity.Order;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.logic.FindOrderLogic;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class OrderAdministrationCommand.
 */
public class OrderAdministrationCommand implements ICommand {

	/** The Constant LOG. */
	private static final Logger LOG = Logger
			.getLogger(OrderAdministrationCommand.class);

	/** The Constant PARAM_ORDER_LIST. */
	private static final String PARAM_ORDER_LIST = "orderList";

	/** The Constant PARAM_ERROR_MESSAGE. */
	private static final String PARAM_ERROR_MESSAGE = "errorMessage";

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
		ArrayList<Order> orderList = null;
		try {
			orderList = FindOrderLogic.findAllOrder();
			request.setAttribute(PARAM_ORDER_LIST, orderList);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ORDER_LIST_PATH);
		} catch (TechnicalException e) {
			LOG.error("Something goes wrong, redirect to error page.", e);
			request.setAttribute(
					PARAM_ERROR_MESSAGE,
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		}
		return page;
	}

}
