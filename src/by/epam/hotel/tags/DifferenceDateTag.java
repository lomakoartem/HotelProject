package by.epam.hotel.tags;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * The Class DifferenceDateTag.
 */
public class DifferenceDateTag extends TagSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1170111364672775287L;

	/** The Constant PARAM_DATE_FORMAT. */
	public static final String PARAM_DATE_FORMAT = "yyyy-MM-dd";

	/** The Constant PARAM_DIFFERENCE_DAY. */
	public static final String PARAM_DIFFERENCE_DAY = "diffDay";

	/** The date out. */
	private String dateOut;

	/** The date in. */
	private String dateIn;

	/**
	 * Sets the date out.
	 * 
	 * @param dateOut
	 *            the new date out
	 */
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}

	/**
	 * Sets the date in.
	 * 
	 * @param dateIn
	 *            the new date in
	 */
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		try {
			long difference;
			difference = (getTime(dateOut) - getTime(dateIn))
					/ (1000 * 60 * 60 * 24);

			pageContext.getRequest().setAttribute(PARAM_DIFFERENCE_DAY,
					difference);
		} catch (ParseException e) {
			throw new JspException("Error: " + e.getMessage());
		}
		return SKIP_BODY;
	}

	/**
	 * Gets the time.
	 * 
	 * @param date
	 *            the date
	 * @return the time
	 * @throws ParseException
	 *             the parse exception
	 */
	private long getTime(String date) throws ParseException {
		DateFormat formater = new SimpleDateFormat(PARAM_DATE_FORMAT);
		long dateTime = formater.parse(date).getTime();
		return dateTime;
	}
}
