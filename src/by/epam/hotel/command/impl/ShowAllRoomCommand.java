package by.epam.hotel.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.hotel.command.ICommand;
import by.epam.hotel.entity.Room;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.logic.FindRoomLogic;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class ShowAllRoomCommand. Command for forward to room administration
 * page.
 */
public class ShowAllRoomCommand implements ICommand {

	/** The Constant LOG. */
	private static final Logger LOG = Logger
			.getLogger(ShowAllRoomCommand.class);

	/** The Constant PARAM_LIST_ROOM. */
	private static final String PARAM_LIST_ROOM = "roomList";

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
		ArrayList<Room> listRoom = null;
		try {
			listRoom = FindRoomLogic.findAllRoom();
			request.setAttribute(PARAM_LIST_ROOM, listRoom);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ROOM_ADMINISTRATION_PATH);
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
