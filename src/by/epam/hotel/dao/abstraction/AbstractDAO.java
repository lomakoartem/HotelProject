package by.epam.hotel.dao.abstraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.epam.hotel.db.pool.ConnectionPool;
import by.epam.hotel.exception.DAOException;

public abstract class AbstractDAO {
	public static final Logger LOG = Logger.getLogger(AbstractDAO.class);
	protected ConnectionPool pool;

	public AbstractDAO() {
		pool = ConnectionPool.getInstance();
	}

	public Connection getConnection() {
		return pool.getConnection();
	}

	public void releaseConnection(Connection connection) {
		if (connection != null) {
			ConnectionPool.getInstance().closeConnection(connection);
		}
	}

	public void closePreparedStatement(PreparedStatement preparedStatement)
			throws DAOException {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DAOException();
			}
		}
	}
}
