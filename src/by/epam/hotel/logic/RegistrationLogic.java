package by.epam.hotel.logic;

import java.util.regex.Pattern;

import by.epam.hotel.dao.IUserDAO;
import by.epam.hotel.dao.impl.UserDAO;
import by.epam.hotel.entity.User;
import by.epam.hotel.exception.DAOException;
import by.epam.hotel.exception.LogicException;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class RegistrationLogic. All logic associated with registration new user.
 */
public class RegistrationLogic {

	/** The Constant PARAM_EMAIL_VALIDATION. */
	private static final String PARAM_EMAIL_VALIDATION = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/** The Constant PARAM_PASSWORD_VALIDATION. */
	private static final String PARAM_PASSWORD_VALIDATION = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";

	/** The Constant PARAM_NAME_VALIDATION. */
	private static final String PARAM_NAME_VALIDATION = "^[À-ÿ¨¸a-zA-Z]{1,20}$";

	/** The Constant PARAM_AGE_VALIDATION. */
	private static final String PARAM_AGE_VALIDATION = "\\d{1,3}";

	/** The Constant PARAM_LOGIN_VALIDATION. */
	private static final String PARAM_LOGIN_VALIDATION = "^[À-ÿ¨¸a-zA-Z]{1,20}$";

	/**
	 * Registration.
	 * 
	 * @param user
	 *            the user
	 * @param password
	 *            the password
	 * @param passwordAgain
	 *            the password again
	 * @return the info messenger
	 * @throws TechnicalException
	 *             the logic level exception
	 * @throws LogicException
	 */
	public static void registration(User user, String password,
			String passwordAgain) throws TechnicalException, LogicException {
		IUserDAO userDAO = new UserDAO();
		try {
			validation(user, password, passwordAgain);
			System.out.println(userDAO.findUserByLoginPassword(user.getLogin(),
					user.getPassword()));
			if (userDAO.findUserByLoginPassword(user.getLogin(),
					user.getPassword()) == null) {
				userDAO.addUser(user);
			} else {
				throw new LogicException(
						ConfigurationManager
								.getInstance()
								.getProperty(
										ConfigurationManager.REGISTRATION_WAS_INTERRUPT_MESSAGE));
			}
		} catch (NumberFormatException e) {
			throw new TechnicalException();
		} catch (DAOException e) {
			throw new TechnicalException();
		}
	}

	/**
	 * Validation.
	 * 
	 * @param user
	 *            the user
	 * @param password
	 *            the password
	 * @param passwordAgain
	 *            the password again
	 * @return the info messenger
	 * @throws LogicException 
	 */
	private static void validation(User user, String password,
			String passwordAgain) throws LogicException {
		validationField(PARAM_LOGIN_VALIDATION,
				ConfigurationManager.LOGIN_IS_NOT_VALID, user.getLogin());
		validationPassword(password, passwordAgain);
		validationField(PARAM_EMAIL_VALIDATION,
				ConfigurationManager.EMAIL_IS_NOT_VALID, user.getEmail());
		validationField(PARAM_NAME_VALIDATION,
				ConfigurationManager.NAME_IS_NOT_VALID, user.getName());
		validationField(PARAM_NAME_VALIDATION,
				ConfigurationManager.FIRST_NAME_IS_NOT_VALID,
				user.getFirstName());
		validationField(PARAM_NAME_VALIDATION,
				ConfigurationManager.SURNAME_IS_NOT_VALID, user.getSurname());
		validationField(PARAM_AGE_VALIDATION,
				ConfigurationManager.AGE_IS_NOT_VALID,
				String.valueOf(user.getAge()));
	}

	/**
	 * Validation password.
	 * 
	 * @param password
	 *            the password
	 * @param passwordAgain
	 *            the password again
	 * @return the info messenger
	 * @throws LogicException 
	 */
	private static void validationPassword(String password, String passwordAgain) throws LogicException {
		if (!password.equals(passwordAgain)
				&& Pattern.matches(PARAM_PASSWORD_VALIDATION, password)) {
			throw new LogicException(ConfigurationManager.getInstance()
					.getProperty(ConfigurationManager.PASSWORD_IS_NOT_VALID));
		}
	}

	/**
	 * Validation field.
	 * 
	 * @param pattern
	 *            the pattern
	 * @param field
	 *            the field
	 * @return the info messenger
	 * @throws LogicException
	 */
	private static void validationField(String pattern, String errorMessage,
			String field) throws LogicException {
		if (!Pattern.matches(pattern, field)) {
			throw new LogicException(ConfigurationManager.getInstance()
					.getProperty(errorMessage));
		}
	}
}
