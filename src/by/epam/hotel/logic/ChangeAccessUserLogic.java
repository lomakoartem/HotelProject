package by.epam.hotel.logic;

import by.epam.hotel.dao.IUserDAO;
import by.epam.hotel.dao.impl.UserDAO;
import by.epam.hotel.entity.enumeration.AccessLevel;
import by.epam.hotel.exception.DAOException;
import by.epam.hotel.exception.LogicException;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class ChangeAccessUserLogic.All logic associated with changing user
 * access level.
 */
public class ChangeAccessUserLogic {

	/** The Constant PARAM_EMPTY_STATUS. */
	private static final String PARAM_EMPTY_STATUS = "empty";

	/**
	 * Change access level.
	 * 
	 * @param idUser
	 *            the id user
	 * @param accessLevel
	 *            the access level
	 * @return the info messenger
	 * @throws TechnicalException
	 *             the logic level exception
	 * @throws LogicException
	 */
	public static void changeAccessLevel(String idUser, String accessLevel)
			throws TechnicalException, LogicException {

		IUserDAO userDAO = new UserDAO();

		if (idUser != null) {
			if (PARAM_EMPTY_STATUS.equals(accessLevel)) {
				throw new LogicException(
						ConfigurationManager
								.getInstance()
								.getProperty(
										ConfigurationManager.CHOOSE_ACTION_ERROR_MESSAGE));
			}
			try {
				userDAO.changeUserAccessById(Integer.parseInt(idUser),
						AccessLevel.valueOf(accessLevel));
			} catch (NumberFormatException e) {
				throw new TechnicalException();
			} catch (DAOException e) {
				throw new TechnicalException();
			}
		} else {
			throw new LogicException(
					ConfigurationManager
							.getInstance()
							.getProperty(
									ConfigurationManager.CHANGE_ACCESS_LEVEL_EXCEPTION_MESSAGE));
		}
	}
}
