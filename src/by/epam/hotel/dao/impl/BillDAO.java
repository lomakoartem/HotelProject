package by.epam.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.epam.hotel.dao.IBillDAO;
import by.epam.hotel.dao.IOrderDAO;
import by.epam.hotel.dao.abstraction.AbstractDAO;
import by.epam.hotel.entity.Bill;
import by.epam.hotel.entity.enumeration.BillStatus;
import by.epam.hotel.exception.DAOException;

/**
 * The Class BillDAO.
 */
public class BillDAO extends AbstractDAO implements IBillDAO {

	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger(BillDAO.class);

	/** The Constant PARAM_DATE_FORMAT. */
	public static final String PARAM_DATE_FORMAT = "yyyy-MM-dd";

	/** The Constant PARAM_BILL_ID. */
	public static final String PARAM_BILL_ID = "id_bill";

	/** The Constant PARAM_BILL_STATUS. */
	public static final String PARAM_BILL_STATUS = "status";

	/** The Constant PARAM_BILL_TOTAL_PRICE. */
	public static final String PARAM_BILL_TOTAL_PRICE = "totalPrice";

	/** The Constant PARAM_BILL_DATE. */
	public static final String PARAM_BILL_DATE = "billDate";

	/** The Constant PARAM_BILL_ORDER_ID. */
	public static final String PARAM_BILL_ORDER_ID = "id_order";

	/** The Constant INSERT_BILL. */
	public static final String INSERT_BILL = "INSERT INTO bill"
			+ "(status,totalPrice,billDate,id_order) VALUES(?,?,?,?)";

	/** The Constant FIND_ALL_BILLS. */
	private static final String FIND_ALL_BILLS = "SELECT * FROM bill";

	/** The Constant FIND_USER_BILLS. */
	public static final String FIND_USER_BILLS = "SELECT * FROM `bill` "
			+ "INNER JOIN `order` ON bill.id_order = order.id_order "
			+ "WHERE id_user = ?";

	/** The Constant UPDATE_BILL_STATUS. */
	private static final String UPDATE_BILL_STATUS = "UPDATE bill SET status = ?"
			+ "WHERE id_bill = ?";

	/** The Constant FIND_BILL_BY_ID. */
	private static final String FIND_BILL_BY_ID = "SELECT * FROM bill"
			+ " WHERE id_bill = ?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IBillDAO#findBillById(int)
	 */
	@Override
	public Bill findBillById(int idBill) throws DAOException {

		Bill bill = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_BILL_BY_ID);
			preparedStatement.setInt(1, idBill);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bill = new Bill();
				IOrderDAO orderDAO = new OrderDAO();
				bill.setId(resultSet.getInt(PARAM_BILL_ID));
				bill.setBillDate(resultSet.getDate(PARAM_BILL_DATE));
				bill.setStatus(BillStatus.valueOf(resultSet
						.getString(PARAM_BILL_STATUS)));
				bill.setTotalCost(resultSet.getInt(PARAM_BILL_TOTAL_PRICE));
				bill.setOrder(orderDAO.findOrderById(resultSet
						.getInt(PARAM_BILL_ORDER_ID)));
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return bill;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IBillDAO#createBill(by.epam.hotel.entity.Bill)
	 */
	@Override
	public void createBill(Bill bill) throws DAOException {
		PreparedStatement preparedStatement = null;
		DateFormat format = new SimpleDateFormat(PARAM_DATE_FORMAT);
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(INSERT_BILL);
			preparedStatement.setString(1, bill.getStatus().toString());
			preparedStatement.setInt(2, bill.getTotalCost());
			preparedStatement.setString(3, format.format(bill.getBillDate())
					.toString());
			preparedStatement.setInt(4, bill.getOrder().getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IBillDAO#findAllBills()
	 */
	@Override
	public ArrayList<Bill> findAllBills() throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Bill> billList = new ArrayList<Bill>();
		IOrderDAO orderDAO = new OrderDAO();
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_ALL_BILLS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Bill bill = new Bill();
				bill.setId(resultSet.getInt(PARAM_BILL_ID));
				bill.setStatus(BillStatus.valueOf(resultSet
						.getString(PARAM_BILL_STATUS)));
				bill.setBillDate(resultSet.getDate(PARAM_BILL_DATE));
				bill.setTotalCost(resultSet.getInt(PARAM_BILL_TOTAL_PRICE));
				bill.setOrder(orderDAO.findOrderById(resultSet
						.getInt(PARAM_BILL_ORDER_ID)));
				billList.add(bill);
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return billList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IBillDAO#changeBillStatusById(int,
	 * by.epam.hotel.entity.enumeration.BillStatus)
	 */
	@Override
	public void changeBillStatusById(int idBill, BillStatus status)
			throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_BILL_STATUS);
			preparedStatement.setString(1, status.toString());
			preparedStatement.setInt(2, idBill);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hotel.dao.IBillDAO#findUserBills(int)
	 */
	@Override
	public ArrayList<Bill> findUserBills(int idUser) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Bill> billList = new ArrayList<Bill>();
		IOrderDAO orderDAO = new OrderDAO();
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_USER_BILLS);
			preparedStatement.setInt(1, idUser);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Bill bill = new Bill();
				bill.setId(resultSet.getInt(PARAM_BILL_ID));
				bill.setStatus(BillStatus.valueOf(resultSet
						.getString(PARAM_BILL_STATUS)));
				bill.setBillDate(resultSet.getDate(PARAM_BILL_DATE));
				bill.setTotalCost(resultSet.getInt(PARAM_BILL_TOTAL_PRICE));
				bill.setOrder(orderDAO.findOrderById(resultSet
						.getInt(PARAM_BILL_ORDER_ID)));
				billList.add(bill);
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return billList;
	}
}
