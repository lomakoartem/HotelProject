package by.epam.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.hotel.command.ICommand;
import by.epam.hotel.entity.User;
import by.epam.hotel.entity.enumeration.AccessLevel;
import by.epam.hotel.exception.LogicException;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.logic.RegistrationLogic;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class RegistrationCommand. Command for registration.
 */
public class RegistrationCommand implements ICommand {

	/** The Constant LOG. */
	private static final Logger LOG = Logger
			.getLogger(RegistrationCommand.class);

	/** The Constant PARAM_USER_NAME. */
	private static final String PARAM_USER_NAME = "name";

	/** The Constant PARAM_USER_PASSWORD. */
	private static final String PARAM_USER_PASSWORD = "password";

	/** The Constant PARAM_USER_LOGIN. */
	private static final String PARAM_USER_LOGIN = "login";

	/** The Constant PARAM_USER_SURNAME. */
	private static final String PARAM_USER_SURNAME = "surname";

	/** The Constant PARAM_USER_FIRST_NAME. */
	private static final String PARAM_USER_FIRST_NAME = "firstname";

	/** The Constant PARAM_USER_AGE. */
	private static final String PARAM_USER_AGE = "age";

	/** The Constant PARAM_USER_EMAIL. */
	private static final String PARAM_USER_EMAIL = "email";

	/** The Constant PARAM_USER_PASSWORD_AGAIN. */
	private static final String PARAM_USER_PASSWORD_AGAIN = "passwordagain";

	/** The Constant PARAM_ACTION_MESSAGE. */
	private static final String PARAM_ACTION_MESSAGE = "actionMessage";

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
		User user = new User();
		String password = request.getParameter(PARAM_USER_PASSWORD);
		String passwordAgain = request.getParameter(PARAM_USER_PASSWORD_AGAIN);
		user.setName(request.getParameter(PARAM_USER_NAME));
		user.setLogin(request.getParameter(PARAM_USER_LOGIN));
		user.setPassword(password.hashCode());
		user.setFirstName(request.getParameter(PARAM_USER_FIRST_NAME));
		user.setSurname(request.getParameter(PARAM_USER_SURNAME));
		user.setEmail(request.getParameter(PARAM_USER_EMAIL));
		user.setAge(Integer.parseInt(request.getParameter(PARAM_USER_AGE)));
		user.setAccess(AccessLevel.CLIENT);

		try {
			RegistrationLogic.registration(user, password, passwordAgain);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.INDEX_PAGE_PATH);
			request.setAttribute(
					PARAM_ACTION_MESSAGE,
					ConfigurationManager
							.getInstance()
							.getProperty(
									ConfigurationManager.REGISTRATION_WAS_SUCCESSFUL_MESSAGE));

		} catch (TechnicalException e) {
			LOG.error("Something goes wrong, redirect to error page.", e);
			request.setAttribute(
					PARAM_ERROR_MESSAGE,
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		} catch (LogicException e) {
			LOG.error("Something goes wrong with registration.", e);
			request.setAttribute(PARAM_ERROR_MESSAGE, e);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
		}
		return page;
	}
}
