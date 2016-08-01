package by.epam.hotel.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The Interface ICommand.
 */
public interface ICommand {

	/**
	 * Execute.
	 * 
	 * @param request
	 *            the request
	 * @return page(string)
	 */
	String execute(HttpServletRequest request);

}
