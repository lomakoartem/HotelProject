package by.epam.hotel.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * The Class TotalCostTag.
 */
public class TotalCostTag extends TagSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1170111364672775287L;

	/** The Constant PARAM_DATE_FORMAT. */
	public static final String PARAM_DATE_FORMAT = "yyyy-MM-dd";

	/** The Constant PARAM_TOTAL_SUM. */
	public static final String PARAM_TOTAL_SUM = "totalSum";

	/** The difference day. */
	private String differenceDay;

	/** The cost per day. */
	private String costPerDay;

	/**
	 * Sets the difference day.
	 * 
	 * @param differenceDay
	 *            the new difference day
	 */
	public void setDifferenceDay(String differenceDay) {
		this.differenceDay = differenceDay;
	}

	/**
	 * Sets the cost per day.
	 * 
	 * @param costPerDay
	 *            the new cost per day
	 */
	public void setCostPerDay(String costPerDay) {
		this.costPerDay = costPerDay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		try {
			int totalSum = Integer.parseInt(differenceDay)
					* Integer.parseInt(costPerDay);
			JspWriter out = pageContext.getOut();
			pageContext.getRequest().setAttribute(PARAM_TOTAL_SUM, totalSum);
			out.print(totalSum);
		} catch (IOException e) {
			throw new JspException("Error: " + e.getMessage());
		}
		return SKIP_BODY;
	}

}
