package by.epam.hotel.logic;

import java.util.Date;

import by.epam.hotel.dao.IBillDAO;
import by.epam.hotel.dao.IOrderDAO;
import by.epam.hotel.dao.IRoomDAO;
import by.epam.hotel.dao.impl.BillDAO;
import by.epam.hotel.dao.impl.OrderDAO;
import by.epam.hotel.dao.impl.RoomDAO;
import by.epam.hotel.entity.Bill;
import by.epam.hotel.entity.Order;
import by.epam.hotel.entity.enumeration.BillStatus;
import by.epam.hotel.entity.enumeration.RoomStatus;
import by.epam.hotel.exception.DAOException;
import by.epam.hotel.exception.TechnicalException;

/**
 * The Class CreateBillLogic. All logic associated with creating bill.
 */
public class CreateBillLogic {

	/**
	 * Creates the bill.
	 * 
	 * @param idOrder
	 *            the id order
	 * @throws TechnicalException
	 *             the logic level exception
	 */
	public static void createBill(String idOrder) throws TechnicalException {
		try {
			IBillDAO billDAO = new BillDAO();
			IOrderDAO orderDAO = new OrderDAO();
			Bill bill = new Bill();
			Order order = orderDAO.findOrderById(Integer.parseInt(idOrder));
			bill.setBillDate(new Date());
			bill.setOrder(order);
			bill.setStatus(BillStatus.UNPAID);
			bill.setTotalCost(calculateTotalSum(order));
			setBusyRoom(order.getRoom().getId());
			billDAO.createBill(bill);
		} catch (DAOException e) {
			throw new TechnicalException();
		}
	}

	/**
	 * Sets the busy room.
	 * 
	 * @param id
	 *            the new busy room
	 * @throws DAOException
	 *             the DAO level exception
	 */
	private static void setBusyRoom(int id) throws DAOException {
		IRoomDAO roomDAO = new RoomDAO();
		roomDAO.changeRoomStatus(id, RoomStatus.BUSY);
	}

	/**
	 * Calculate total sum.
	 * 
	 * @param order
	 *            the order
	 * @return the int
	 */
	private static int calculateTotalSum(Order order) {
		int sumOfDay = (int) ((order.getDateOut().getTime() - order.getDateIn()
				.getTime()) / (24 * 60 * 60 * 1000));
		int totalSum = sumOfDay * order.getRoom().getCostPerDay();
		return totalSum;
	}
}
