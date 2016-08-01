package by.epam.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.hotel.command.ICommand;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class NoCommand.
 */
public class NoCommand implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.epam.hotel.command.ICommand#execute(javax.servlet.http.HttpServletRequest
	 * )
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.INDEX_PAGE_PATH);
		return page;
	}

}
