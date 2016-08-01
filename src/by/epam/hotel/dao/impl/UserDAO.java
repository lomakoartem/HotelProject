package by.epam.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.epam.hotel.dao.IUserDAO;
import by.epam.hotel.dao.abstraction.AbstractDAO;
import by.epam.hotel.entity.User;
import by.epam.hotel.entity.enumeration.AccessLevel;
import by.epam.hotel.exception.DAOException;

public class UserDAO extends AbstractDAO implements IUserDAO {

	public static final Logger LOG = Logger.getLogger(UserDAO.class);

	public static final String PARAM_ID_USER = "id_user";
	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_NAME = "name";
	public static final String PARAM_FIRST_NAME = "firstName";
	public static final String PARAM_SURNAME = "surname";
	public static final String PARAM_AGE = "age";
	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_ACCESS = "accessLevel";
	public static final String PARAM_ID_PERSON = "id_person";
	public static final String PARAM_ID_REGISTRATION_INFO = "id_info";

	public static final String GET_USER_BY_LOGIN_PASSWORD = "SELECT * FROM `user` "
			+ "INNER JOIN registrationinfo ON user.id_info=registrationinfo.id_info "
			+ "INNER JOIN person ON user.id_person=person.id_person "
			+ "WHERE registrationinfo.login = ? AND registrationinfo.password = ?";

	public static final String GET_USER_BY_ID = "SELECT * FROM `user` "
			+ "INNER JOIN registrationinfo ON user.id_info=registrationinfo.id_info "
			+ "INNER JOIN person ON user.id_person=person.id_person "
			+ "WHERE registrationinfo.login = ?";

	public static final String INSERT_USER = "INSERT INTO `user`"
			+ "(accessLevel,id_person,id_info) VALUES(?,?,?)";

	public static final String INSERT_PERSONAL_INFO = "INSERT INTO `person`"
			+ "(name,firstName,surname,age) VALUES(?,?,?,?)";

	public static final String INSERT_REGISTRATION_INFO = "INSERT INTO `registrationinfo`"
			+ "(login,password,email) VALUES(?,?,?)";

	public static final String GET_PERSONAL_INFO = "SELECT * FROM `person` "
			+ "WHERE name=? AND firstName=? AND surname=? AND age=?";

	public static final String GET_REGISTRATION_INFO = "SELECT * FROM `registrationinfo`"
			+ "WHERE login = ?";

	public static final String GET_ALL_USER = "SELECT * FROM `user` "
			+ "INNER JOIN registrationinfo ON user.id_info=registrationinfo.id_info "
			+ "INNER JOIN person ON user.id_person=person.id_person ";

	public static final String UPDATE_USER_ACCESS_LEVEL = "UPDATE user "
			+ "SET accessLevel = ?" + "WHERE id_user =?";

	public User findUserByLoginPassword(String login, int password)
			throws DAOException {

		User user = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement(GET_USER_BY_LOGIN_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setLong(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(Integer.parseInt(resultSet.getString(PARAM_ID_USER)));
				user.setLogin(resultSet.getString(PARAM_LOGIN));
				user.setPassword(resultSet.getInt(PARAM_PASSWORD));
				user.setName(resultSet.getString(PARAM_NAME));
				user.setFirstName(resultSet.getString(PARAM_FIRST_NAME));
				user.setSurname(resultSet.getString(PARAM_SURNAME));
				user.setAge(Integer.parseInt(resultSet.getString(PARAM_AGE)));
				user.setEmail(resultSet.getString(PARAM_EMAIL));
				user.setAccess(AccessLevel.valueOf(resultSet
						.getString(PARAM_ACCESS)));
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return user;
	}

	@Override
	public User findUserById(int idUser) throws DAOException {

		User user = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
			preparedStatement.setInt(1, idUser);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(PARAM_ID_USER));
				user.setLogin(resultSet.getString(PARAM_LOGIN));
				user.setPassword(resultSet.getInt(PARAM_PASSWORD));
				user.setName(resultSet.getString(PARAM_NAME));
				user.setFirstName(resultSet.getString(PARAM_FIRST_NAME));
				user.setSurname(resultSet.getString(PARAM_SURNAME));
				user.setAge(resultSet.getInt(PARAM_AGE));
				user.setEmail(resultSet.getString(PARAM_EMAIL));
				user.setAccess(AccessLevel.valueOf(resultSet
						.getString(PARAM_ACCESS)));
			}

		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return user;
	}

	@Override
	public void addUser(User user) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(INSERT_USER);
			addRegistrationInfo(user);
			addPersonalInfo(user);
			preparedStatement.setString(1, user.getAccess().toString());
			preparedStatement.setInt(2, getPersonalInfo(user));
			preparedStatement.setInt(3, getRegistrationInfo(user));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}

	private int getPersonalInfo(User user) throws DAOException {
		int personId = -1;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_PERSONAL_INFO);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getSurname());
			preparedStatement.setInt(4, user.getAge());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				personId = resultSet.getInt(PARAM_ID_PERSON);
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return personId;
	}

	private int getRegistrationInfo(User user) throws DAOException {
		int infoId = -1;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement(GET_REGISTRATION_INFO);
			preparedStatement.setString(1, user.getLogin());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				infoId = resultSet.getInt(PARAM_ID_REGISTRATION_INFO);
			}

		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return infoId;
	}

	private void addPersonalInfo(User user) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement(INSERT_PERSONAL_INFO);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getSurname());
			preparedStatement.setInt(4, user.getAge());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}

	}

	private void addRegistrationInfo(User user) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement(INSERT_REGISTRATION_INFO);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setInt(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}

	}

	@Override
	public ArrayList<User> findAllUser() throws DAOException {
		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_ALL_USER);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(PARAM_ID_USER));
				user.setLogin(resultSet.getString(PARAM_LOGIN));
				user.setPassword(resultSet.getInt(PARAM_PASSWORD));
				user.setName(resultSet.getString(PARAM_NAME));
				user.setFirstName(resultSet.getString(PARAM_FIRST_NAME));
				user.setSurname(resultSet.getString(PARAM_SURNAME));
				user.setAge(resultSet.getInt(PARAM_AGE));
				user.setEmail(resultSet.getString(PARAM_EMAIL));
				user.setAccess(AccessLevel.valueOf(resultSet
						.getString(PARAM_ACCESS)));
				userList.add(user);
			}

		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return userList;
	}

	@Override
	public void changeUserAccessById(int id, AccessLevel level)
			throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement(UPDATE_USER_ACCESS_LEVEL);
			preparedStatement.setString(1, level.toString());
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}

	}
}
