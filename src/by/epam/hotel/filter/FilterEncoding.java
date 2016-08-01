package by.epam.hotel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * The Class FilterEncoding.
 */
public class FilterEncoding implements Filter {

	/** The Constant FILTERABLE_CONTENT_TYPE. */
	private static final String FILTERABLE_CONTENT_TYPE = "application/x-www-form-urlencoded";

	/** The Constant ENCODING_DEFAULT. */
	private static final String ENCODING_DEFAULT = "UTF-8";

	/** The Constant ENCODING_INIT_PARAM_NAME. */
	private static final String ENCODING_INIT_PARAM_NAME = "encoding";

	/** The encoding. */
	private String encoding;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		String contentType = request.getContentType();
		if (contentType != null
				&& contentType.startsWith(FILTERABLE_CONTENT_TYPE)) {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
		if (encoding == null) {
			encoding = ENCODING_DEFAULT;
		}
	}
}
