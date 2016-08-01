package by.epam.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;

import by.epam.hotel.entity.enumeration.BillStatus;

/**
 * The Class Bill.
 */
public class Bill implements Serializable, Cloneable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7353342132759647115L;
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger(Bill.class);
	
	/** The id. */
	private int id;
	
	/** The status. */
	private BillStatus status;
	
	/** The order. */
	private Order order;
	
	/** The total cost. */
	private int totalCost;
	
	/** The bill date. */
	private Date billDate;

	/**
	 * Instantiates a new bill.
	 */
	public Bill() {
		LOG.info("Create 'Bill' object.");
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public BillStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(BillStatus status) {
		this.status = status;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Gets the total cost.
	 *
	 * @return the total cost
	 */
	public int getTotalCost() {
		return totalCost;
	}

	/**
	 * Sets the total cost.
	 *
	 * @param totalCost the new total cost
	 */
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * Gets the bill date.
	 *
	 * @return the bill date
	 */
	public Date getBillDate() {
		return billDate;
	}

	/**
	 * Sets the bill date.
	 *
	 * @param billDate the new bill date
	 */
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Bill bill = (Bill) super.clone();
		bill.order = (Order) order.clone();
		return bill;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + totalCost;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill bill = (Bill) obj;
		if (billDate == null) {
			if (bill.billDate != null)
				return false;
		} else if (!billDate.equals(bill.billDate))
			return false;
		if (id != bill.id)
			return false;
		if (order == null) {
			if (bill.order != null)
				return false;
		} else if (!order.equals(bill.order))
			return false;
		if (status != bill.status)
			return false;
		if (totalCost != bill.totalCost)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(getClass().getSimpleName())
				.append(": id:").append(id).append(", bill status:")
				.append(status).append(", for payment:").append(totalCost)
				.append(", date of payment:").append(billDate)
				.append(", order:").append(order).toString();
	}
}
