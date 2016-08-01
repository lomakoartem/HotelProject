package by.epam.hotel.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class LogicException.
 */
public class LogicException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7434161787705766077L;

	/**
	 * Instantiates a new logic exception.
	 */
	public LogicException() {
		super();
	}

	/**
	 * Instantiates a new logic exception.
	 * 
	 * @param message
	 *            the message
	 */
	public LogicException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new logic exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new logic exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public LogicException(Throwable cause) {
		super(cause);
	}
}
