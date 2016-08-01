package by.epam.hotel.dao;

import java.util.ArrayList;

import by.epam.hotel.entity.Bill;
import by.epam.hotel.entity.enumeration.BillStatus;
import by.epam.hotel.exception.DAOException;

/**
 * The Interface IBillDAO.
 */
public interface IBillDAO {

	/**
	 * Creates the bill.
	 *
	 * @param bill the bill
	 * @throws DAOException the DAO level exception
	 */
	void createBill(Bill bill) throws DAOException;

	/**
	 * Find all bills.
	 *
	 * @return the array list
	 * @throws DAOException the DAO level exception
	 */
	ArrayList<Bill> findAllBills() throws DAOException;

	/**
	 * Find user bills.
	 *
	 * @param idUser the id user
	 * @return the array list
	 * @throws DAOException the DAO level exception
	 */
	ArrayList<Bill> findUserBills(int idUser) throws DAOException;

	/**
	 * Change bill status by id.
	 *
	 * @param idBill the id bill
	 * @param status the status
	 * @throws DAOException the DAO level exception
	 */
	void changeBillStatusById(int idBill, BillStatus status)
			throws DAOException;

	/**
	 * Find bill by id.
	 *
	 * @param idBill the id bill
	 * @return the bill
	 * @throws DAOException the DAO level exception
	 */
	Bill findBillById(int idBill) throws DAOException;

}
