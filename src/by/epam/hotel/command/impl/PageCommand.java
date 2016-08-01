package by.epam.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.hotel.command.ICommand;
import by.epam.hotel.entity.User;
import by.epam.hotel.entity.enumeration.AccessLevel;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class PageCommand.
 */
public class PageCommand implements ICommand {

	/** The Constant PARAM_PAGE. */
	private static final String PARAM_PAGE = "page";

	/** The Constant PARAM_LOGIN_PAGE. */
	private static final String PARAM_LOGIN_PAGE = "login";

	/** The Constant PARAM_REGISTRATION_PAGE. */
	private static final String PARAM_REGISTRATION_PAGE = "registration";

	/** The Constant PARAM_CLIENT_PAGE. */
	private static final String PARAM_CLIENT_PAGE = "clientpage";

	/** The Constant PARAM_ADMIN_PAGE. */
	private static final String PARAM_ADMIN_PAGE = "adminpage";

	/** The Constant PARAM_CABINET_PAGE. */
	private static final String PARAM_CABINET_PAGE = "cabinet";

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
		page = checkPage(request);
		return page;
	}

	/**
	 * Check page.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	private String checkPage(HttpServletRequest request) {
		String pageParam = request.getParameter(PARAM_PAGE);
		switch (pageParam) {
		case PARAM_LOGIN_PAGE:
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.LOGIN_PAGE_PATH);
		case PARAM_REGISTRATION_PAGE:
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.REGISTRATION_PAGE_PATH);
		case PARAM_CLIENT_PAGE:
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.CLIENT_PAGE_PATH);
		case PARAM_ADMIN_PAGE:
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
		case PARAM_CABINET_PAGE:
			return checkRole(request);
		default:
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.INDEX_PAGE_PATH);
		}
	}

	/**
	 * Check role.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	private String checkRole(HttpServletRequest request) {
		AccessLevel check = ((User) request.getSession().getAttribute("user"))
				.getAccess();
		if (check != null) {
			switch (check) {
			case ADMIN:
				return ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.ADMIN_PAGE_PATH);
			case CLIENT:
				return ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.CLIENT_PAGE_PATH);
			case GUEST:
				request.setAttribute(
						"errorMessage",
						ConfigurationManager.getInstance().getProperty(
								ConfigurationManager.BANNED_MESSAGE));
				return ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.INDEX_PAGE_PATH);
			}
		}
		return ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.ERROR_PAGE_PATH);
	}
}
