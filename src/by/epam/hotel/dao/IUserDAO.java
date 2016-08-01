package by.epam.hotel.dao;

import java.util.ArrayList;

import by.epam.hotel.entity.User;
import by.epam.hotel.entity.enumeration.AccessLevel;
import by.epam.hotel.exception.DAOException;

/**
 * The Interface IUserDAO.
 */
public interface IUserDAO {

	/**
	 * Find user by login password.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the user
	 * @throws DAOException the DAO level exception
	 */
	User findUserByLoginPassword(String login, int password)
			throws DAOException;

	/**
	 * Find user by id.
	 *
	 * @param id the id
	 * @return the user
	 * @throws DAOException the DAO level exception
	 */
	User findUserById(int id) throws DAOException;

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @throws DAOException the DAO level exception
	 */
	void addUser(User user) throws DAOException;

	/**
	 * Find all user.
	 *
	 * @return the array list
	 * @throws DAOException the DAO level exception
	 */
	ArrayList<User> findAllUser() throws DAOException;

	/**
	 * Change user access by id.
	 *
	 * @param parameter the parameter
	 * @param level the level
	 * @throws DAOException the DAO level exception
	 */
	void changeUserAccessById(int parameter, AccessLevel level)
			throws DAOException;

}
