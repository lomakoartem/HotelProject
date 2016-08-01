package by.epam.hotel.exception;

/**
 * The Class DAOLevelException.
 */
public class DAOException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4840982228821374383L;

	/**
	 * Instantiates a new DAO level exception.
	 */
	public DAOException() {
		super();
	}

	/**
	 * Instantiates a new DAO level exception.
	 * 
	 * @param message
	 *            the message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new DAO level exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new DAO level exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
}
