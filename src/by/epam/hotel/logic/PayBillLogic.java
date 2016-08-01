package by.epam.hotel.logic;

import by.epam.hotel.dao.IBillDAO;
import by.epam.hotel.dao.impl.BillDAO;
import by.epam.hotel.entity.Bill;
import by.epam.hotel.entity.enumeration.BillStatus;
import by.epam.hotel.exception.DAOException;
import by.epam.hotel.exception.LogicException;
import by.epam.hotel.exception.TechnicalException;
import by.epam.hotel.manager.ConfigurationManager;

/**
 * The Class PayBillLogic. All logic associated with paid order.
 */
public class PayBillLogic {

	/**
	 * Pay bill.
	 * 
	 * @param idBill
	 *            the id bill
	 * @return the info messenger
	 * @throws TechnicalException
	 *             the logic level exception
	 * @throws LogicException
	 */
	public static void payBill(String idBill) throws TechnicalException,
			LogicException {
		IBillDAO billDAO = new BillDAO();
		if (idBill != null) {
			try {
				Bill bill = billDAO.findBillById(Integer.parseInt(idBill));
				if (bill.getStatus().equals(BillStatus.PAID)) {
					throw new LogicException(
							ConfigurationManager
									.getInstance()
									.getProperty(
											ConfigurationManager.BILL_ALREADY_PAID_MESSAGE));
				}
				billDAO.changeBillStatusById(Integer.parseInt(idBill),
						BillStatus.PAID);
			} catch (DAOException e) {
				throw new TechnicalException();
			}
		} else {
			throw new LogicException(ConfigurationManager.getInstance()
					.getProperty(ConfigurationManager.BILL_PAID_ERROR_MESSAGE));
		}
	}
}
