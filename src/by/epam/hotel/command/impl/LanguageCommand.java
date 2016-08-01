package by.epam.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.hotel.command.ICommand;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class LanguageCommand. Command for change language.
 */
public class LanguageCommand implements ICommand {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(LanguageCommand.class);

	/** The Constant PARAM_LANG. */
	private static final String PARAM_LANG = "lang";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.epam.hotel.command.ICommand#execute(javax.servlet.http.HttpServletRequest
	 * )
	 */
	@Override
	public String execute(HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		String lang = (String) request.getParameter(PARAM_LANG);
		LOG.info("Add to session language parametr.");
		session.setAttribute(PARAM_LANG, lang);
		return ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.INDEX_PAGE_PATH);

	}
}
