package by.epam.hotel.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.hotel.command.ICommand;
import by.epam.hotel.entity.User;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.logic.FindUserLogic;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class UserAdministrationCommand. Command for forward to user
 * administration page.
 */
public class UserAdministrationCommand implements ICommand {

	/** The Constant LOG. */
	private static final Logger LOG = Logger
			.getLogger(UserAdministrationCommand.class);

	/** The Constant PARAM_USER_LIST. */
	private static final String PARAM_USER_LIST = "userList";

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
		try {
			ArrayList<User> userList = FindUserLogic.findAllUser();
			request.setAttribute(PARAM_USER_LIST, userList);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.USER_ADMINISTRATION_PATH);
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
