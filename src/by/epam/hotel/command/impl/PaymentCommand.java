package by.epam.hotel.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.hotel.command.ICommand;
import by.epam.hotel.entity.Bill;
import by.epam.hotel.entity.User;
import by.epam.hotel.exception.LogicException;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.logic.FindBillLogic;
import by.epam.hotel.logic.PayBillLogic;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class PaymentCommand. Command for paid order.
 */
public class PaymentCommand implements ICommand {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(PaymentCommand.class);

	/** The Constant PARAM_CLIENT_BILL. */
	private static final String PARAM_CLIENT_BILL = "clientBill";

	/** The Constant PARAM_BILL_ID. */
	private static final String PARAM_BILL_ID = "id_bill";

	/** The Constant PARAM_USER. */
	private static final String PARAM_USER = "user";

	/** The Constant PARAM_ERROR_MESSAGE. */
	private static final String PARAM_ERROR_MESSAGE = "errorMessage";

	/** The Constant PARAM_ACTION_MESSAGE. */
	private static final String PARAM_ACTION_MESSAGE = "actionMessage";

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

		String idBill = request.getParameter(PARAM_BILL_ID);
		try {
			PayBillLogic.payBill(idBill);
			page = refreshWithChanges(request);
			request.setAttribute(
					PARAM_ACTION_MESSAGE,
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.PAYMENT_SUCCESS_MESSAGE));
		} catch (TechnicalException e) {
			LOG.error("Something goes wrong, redirect to error page.", e);
			request.setAttribute(
					PARAM_ERROR_MESSAGE,
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		} catch (LogicException e) {
			LOG.error("Something goes wrong with payment bill.", e);
			request.setAttribute(PARAM_ERROR_MESSAGE, e);
			page = refreshWithChanges(request);
		}
		return page;

	}

	private String refreshWithChanges(HttpServletRequest request) {
		ArrayList<Bill> clientBill = null;
		User user = (User) request.getSession().getAttribute(PARAM_USER);
		try {
			clientBill = FindBillLogic.findClientBill(user.getId());
			request.setAttribute(PARAM_CLIENT_BILL, clientBill);
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
				ConfigurationManager.BILL_LIST_PATH);
	}
}
