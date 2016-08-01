package by.epam.hotel.db.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class ConnectionPool.
 */
public final class ConnectionPool {

	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger(ConnectionPool.class);

	/** The instance. */
	private static ConnectionPool instance;

	/** The pool. */
	private static BlockingQueue<Connection> pool;

	/** The lock. */
	private static ReentrantLock lock = new ReentrantLock();

	/** The config. */
	private static ConfigurationManager config = ConfigurationManager
			.getInstance();

	/** The flag. */
	private boolean flag = true;

	/**
	 * Gets the single instance of ConnectionPool.
	 * 
	 * @return single instance of ConnectionPool
	 */
	public static ConnectionPool getInstance() {
		if (instance == null) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new ConnectionPool();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	/**
	 * Instantiates a new connection pool.
	 */
	private ConnectionPool() {
		init();
	}

	/**
	 * Inits connection pool.
	 */
	private static void init() {
		LOG.info("Trying to create pool of connections...");
		String url = config.getProperty(ConfigurationManager.DB_URL);
		String user = config.getProperty(ConfigurationManager.DB_USER);
		String password = config.getProperty(ConfigurationManager.DB_PASSWORD);
		int size = Integer.parseInt(config
				.getProperty(ConfigurationManager.DB_MAXCONN));
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			pool = new ArrayBlockingQueue<Connection>(size);
			for (int i = 0; i < size; i++) {
				Connection connection = DriverManager.getConnection(url, user,
						password);
				pool.offer(connection);
			}
			LOG.info("Connection pool succesfully initialized.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the connection.
	 * 
	 * @return the connection
	 */
	public Connection getConnection() {
		Connection connection = null;
		if (flag) {
			try {
				connection = pool.take();
			} catch (InterruptedException e) {
				LOG.warn(
						"Free connection waiting interrupted. Returned `null` connection.",
						e);
			}
		}
		return connection;
	}

	/**
	 * Close connection.
	 * 
	 * @param connection
	 *            the connection
	 */
	public void closeConnection(Connection connection) {
		try {
			if (!connection.isClosed()) {
				if (!pool.offer(connection)) {
					LOG.warn("Connection not added. Possible `leakage` of connections.");
				}
			} else {
				LOG.warn("Connection already closed. Possible `leakage` of connections.");
			}
		} catch (SQLException e) {
			LOG.warn(
					"SQLException at conection isClosed () checking. Connection not added.",
					e);
		}
	}

	/**
	 * Release all connections.
	 */
	public void releaseConnections() {
		flag = false;
		Connection connection = null;
		int realSize = Integer.parseInt(config
				.getProperty(ConfigurationManager.DB_MAXCONN));
		while (realSize > 0) {
			try {
				connection = pool.take();
			} catch (InterruptedException e) {
				LOG.warn(
						"Waiting connection, interrupt exception. Return null connection.",
						e);
			}
			if (connection != null) {
				try {
					if (!connection.isClosed()) {
						connection.close();
					}
				} catch (SQLException e) {
					LOG.warn("Problem with connection closing", e);
				}
				realSize--;
			}
		}
		LOG.info("Pool succesfully cleared.");
	}
}
