package by.epam.hotel.logic;

import java.util.ArrayList;

import by.epam.hotel.dao.IBillDAO;
import by.epam.hotel.dao.impl.BillDAO;
import by.epam.hotel.entity.Bill;
import by.epam.hotel.exception.DAOException;
import by.epam.hotel.exception.TechnicalException;

/**
 * The Class FindBillLogic. All logic associated with finding bill.
 */
public class FindBillLogic {

	/**
	 * Gets the client bill.
	 * 
	 * @param idUser
	 *            the id user
	 * @return the client bill
	 * @throws TechnicalException
	 *             the logic level exception
	 */
	public static ArrayList<Bill> findClientBill(int idUser)
			throws TechnicalException {
		IBillDAO billDAO = new BillDAO();
		ArrayList<Bill> billList = new ArrayList<Bill>();
		try {
			for (Bill bill : billDAO.findUserBills(idUser)) {
				billList.add(bill);
			}
		} catch (DAOException e) {
			throw new TechnicalException();
		}
		return billList;
	}

}
