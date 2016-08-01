package by.epam.hotel.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * The Class TodayTag.
 */
public class TodayTag extends TagSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4307429675149565018L;

	/** The m format. */
	private String mFormat;

	/**
	 * Sets the format.
	 * 
	 * @param format
	 *            the new format
	 */
	public void setFormat(String format) {
		this.mFormat = format;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			Date today = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat(mFormat);
			out.print(dateFormatter.format(today));

		} catch (IOException e) {
			throw new JspException("Error: " + e.getMessage());
		}
		return SKIP_BODY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
