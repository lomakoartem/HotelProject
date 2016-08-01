package by.epam.hotel.manager;

import java.util.ResourceBundle;

/**
 * The Class ConfigurationManager.
 */
public class ConfigurationManager {

	/** The instance. */
	private static ConfigurationManager instance;

	/** The resource bundle. */
	private ResourceBundle resourceBundle;

	/** The Constant BUNDLE. */
	private static final String BUNDLE = "resources.config";

	/** The Constant DB_URL. */
	public static final String DB_URL = "db.url";

	/** The Constant DB_USER. */
	public static final String DB_USER = "db.user";

	/** The Constant DB_PASSWORD. */
	public static final String DB_PASSWORD = "db.password";

	/** The Constant DB_MAXCONN. */
	public static final String DB_MAXCONN = "db.maxConnections";

	/** The Constant ERROR_PAGE_PATH. */
	public static final String ERROR_PAGE_PATH = "error.page.path";

	/** The Constant LOGIN_PAGE_PATH. */
	public static final String LOGIN_PAGE_PATH = "login.page.path";

	/** The Constant CLIENT_PAGE_PATH. */
	public static final String CLIENT_PAGE_PATH = "client.page.path";

	/** The Constant INDEX_PAGE_PATH. */
	public static final String INDEX_PAGE_PATH = "index.page.path";

	/** The Constant ADMIN_PAGE_PATH. */
	public static final String ADMIN_PAGE_PATH = "admin.page.path";

	/** The Constant CREATE_ORDER_PATH. */
	public static final String CREATE_ORDER_PATH = "create.order.page.path";

	/** The Constant CLIENT_ORDER_LIST_PATH. */
	public static final String CLIENT_ORDER_LIST_PATH = "client.order.list.page.path";

	/** The Constant USER_ADMINISTRATION_PATH. */
	public static final String USER_ADMINISTRATION_PATH = "user.administration.path";

	/** The Constant ORDER_LIST_PATH. */
	public static final String ORDER_LIST_PATH = "order.list.path";

	/** The Constant LOG4J_PATH. */
	public static final String LOG4J_PATH = "log4j.path";

	/** The Constant BILL_LIST_PATH. */
	public static final String BILL_LIST_PATH = "bill.list.path";

	/** The Constant REGISTRATION_PAGE_PATH. */
	public static final String REGISTRATION_PAGE_PATH = "registration.page.path";

	/** The Constant LOGIN_ERROR_MESSAGE. */
	public static final String LOGIN_ERROR_MESSAGE = "login.error.message";

	/** The Constant SERVLET_EXCEPTION_ERROR_MESSAGE. */
	public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "servler.exception.error.message";

	/** The Constant IO_EXCEPTION_ERROR_MESSAGE. */
	public static final String IO_EXCEPTION_ERROR_MESSAGE = "io.exception.error.message";

	/** The Constant UNKNOWN_COMMAND_ERROR_MESSAGE. */
	public static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "unknown.command.error";

	/** The Constant SQL_EXCEPTION_ERROR_MESSAGE. */
	public static final String SQL_EXCEPTION_ERROR_MESSAGE = "sql.exception.error.message";

	/** The Constant DOES_NOT_HAVE_FREE_ROOM_MESSAGE. */
	public static final String DOES_NOT_HAVE_FREE_ROOM_MESSAGE = "does.not.have.free.rooms";

	/** The Constant CREATE_ORDER_PROBLEM_MESSAGE. */
	public static final String CREATE_ORDER_PROBLEM_MESSAGE = "create.order.problem";

	/** The Constant DOES_NOT_HAVE_CLIENT_ORDER_MESSAGE. */
	public static final String DOES_NOT_HAVE_CLIENT_ORDER_MESSAGE = "does.not.have.free.order";

	/** The Constant BANNED_MESSAGE. */
	public static final String BANNED_MESSAGE = "account.was.banned";

	/** The Constant REGISTRATION_WAS_SUCCESSFUL_MESSAGE. */
	public static final String REGISTRATION_WAS_SUCCESSFUL_MESSAGE = "registration.was.successful";

	/** The Constant REGISTRATION_WAS_INTERRUPT_MESSAGE. */
	public static final String REGISTRATION_WAS_INTERRUPT_MESSAGE = "registration.was.interrupt";

	/** The Constant DELETE_ORDER_PROBLEM_MESSAGE. */
	public static final String DELETE_ORDER_PROBLEM_MESSAGE = "delete.order.problem";

	/** The Constant LOGIC_EXCEPTION_ERROR_MESSAGE. */
	public static final String LOGIC_EXCEPTION_ERROR_MESSAGE = "logic.exception.error.message";

	/** The Constant CHANGE_ACCESS_LEVEL_EXCEPTION_MESSAGE. */
	public static final String CHANGE_ACCESS_LEVEL_EXCEPTION_MESSAGE = "change.access.exception.message";

	/** The Constant CHANGE_STATUS_ORDER_EXCEPTION_MESSAGE. */
	public static final String CHANGE_STATUS_ORDER_EXCEPTION_MESSAGE = "change.status.order.exception.message";

	/** The Constant DELETE_CONFIRMED_ORDER_MESSAGE. */
	public static final String DELETE_CONFIRMED_ORDER_MESSAGE = "delete.confirmed.order.message";

	/** The Constant DOES_NOT_HAVE_BILL_MESSAGE. */
	public static final String DOES_NOT_HAVE_BILL_MESSAGE = "does.not.have.bill.message";

	/** The Constant PAYMENT_SUCCESS_MESSAGE. */
	public static final String PAYMENT_SUCCESS_MESSAGE = "payment.success.mesage";

	/** The Constant FIRST_NAME_IS_NOT_VALID. */
	public static final String FIRST_NAME_IS_NOT_VALID = "first.name.is.not.valid";

	/** The Constant SURNAME_IS_NOT_VALID. */
	public static final String SURNAME_IS_NOT_VALID = "surname.is.not.valid";

	/** The Constant LOGIN_IS_NOT_VALID. */
	public static final String LOGIN_IS_NOT_VALID = "login.is.not.valid";

	/** The Constant AGE_IS_NOT_VALID. */
	public static final String AGE_IS_NOT_VALID = "age.is.not.valid";

	/** The Constant EMAIL_IS_NOT_VALID. */
	public static final String EMAIL_IS_NOT_VALID = "email.is.not.valid";

	/** The Constant NAME_IS_NOT_VALID. */
	public static final String NAME_IS_NOT_VALID = "name.is.not.valid";

	/** The Constant PASSWORD_IS_NOT_VALID. */
	public static final String PASSWORD_IS_NOT_VALID = "password.is.not.valid";

	/** The Constant ROOM_ADMINISTRATION_PATH. */
	public static final String ROOM_ADMINISTRATION_PATH = "room.administration.path";

	/** The Constant DOES_NOT_HAVE_ACCESS_LEVEL_MESSAGE. */
	public static final String DOES_NOT_HAVE_ACCESS_LEVEL_MESSAGE = "does.not.have.access.level";

	/** The Constant USER_NOT_FOUND_MESSAGE. */
	public static final String USER_NOT_FOUND_MESSAGE = "user.not.found";

	/** The Constant WRONG_DATE_EXCEPTION_MESSAGE. */
	public static final String WRONG_DATE_EXCEPTION_MESSAGE = "wrong.date.exception.message";

	/** The Constant BILL_ALREADY_PAID_MESSAGE. */
	public static final String BILL_ALREADY_PAID_MESSAGE = "bill.already.paid.message";

	/** The Constant CHOOSE_ACTION_ERROR_MESSAGE. */
	public static final String CHOOSE_ACTION_ERROR_MESSAGE = "choose.action.error.message";

	/** The Constant CHANGE_STATUS_SUCCESS_MESSAGE. */
	public static final String CHANGE_STATUS_SUCCESS_MESSAGE = "change.status.success.message";

	/** The Constant CREATE_ORDER_SUCCESS_MESSAGE. */
	public static final String CREATE_ORDER_SUCCESS_MESSAGE = "create.order.success.message";

	/** The Constant CHANGE_ROOM_STATUS_ERROR_MESSAGE. */
	public static final String CHANGE_ROOM_STATUS_ERROR_MESSAGE = "change.room.status.error.message";

	/** The Constant DELETE_ORDER_SUCCESS_MESSAGE. */
	public static final String DELETE_ORDER_SUCCESS_MESSAGE = "delete.order.success.message";

	/** The Constant BILL_PAID_ERROR_MESSAGE. */
	public static final String BILL_PAID_ERROR_MESSAGE = "bill.paid.error.message";

	/**
	 * Gets the single instance of ConfigurationManager.
	 * 
	 * @return single instance of ConfigurationManager
	 */
	public static ConfigurationManager getInstance() {
		if (instance == null) {
			instance = new ConfigurationManager();
			instance.setResourceBundle(ResourceBundle.getBundle(BUNDLE));
		}
		return instance;
	}

	/**
	 * Gets the resource bundle.
	 * 
	 * @return the resource bundle
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	/**
	 * Sets the resource bundle.
	 * 
	 * @param resourceBundle
	 *            the new resource bundle
	 */
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	/**
	 * Gets the property.
	 * 
	 * @param property
	 *            the property
	 * @return the property
	 */
	public String getProperty(String property) {
		if (resourceBundle == null) {
			instance.setResourceBundle(ResourceBundle.getBundle(BUNDLE));
		}
		return (String) resourceBundle.getObject(property);
	}
}
