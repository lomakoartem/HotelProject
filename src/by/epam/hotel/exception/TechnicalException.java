package by.epam.hotel.exception;

/**
 * The Class LogicLevelException.
 */
public class TechnicalException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7434161787705766077L;

	/**
	 * Instantiates a new logic level exception.
	 */
	public TechnicalException() {
		super();
	}

	/**
	 * Instantiates a new logic level exception.
	 * 
	 * @param message
	 *            the message
	 */
	public TechnicalException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new logic level exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new logic level exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public TechnicalException(Throwable cause) {
		super(cause);
	}
}
