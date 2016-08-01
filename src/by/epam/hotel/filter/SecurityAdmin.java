package by.epam.hotel.filter;

import by.epam.hotel.entity.User;
import by.epam.hotel.entity.enumeration.AccessLevel;
import by.epam.hotel.manager.ConfigurationManager;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The Class SecurityAdmin.
 */
public class SecurityAdmin implements Filter {

	/** The Constant PARAM_USER. */
	public static final String PARAM_USER = "user";

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
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User) session.getAttribute(PARAM_USER);
		if (user != null) {
			AccessLevel userAccess = user.getAccess();
			if (!AccessLevel.ADMIN.equals(userAccess)) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(ConfigurationManager
								.getInstance().getProperty(
										ConfigurationManager.INDEX_PAGE_PATH));
				request.setAttribute(
						"errorMessage",
						ConfigurationManager
								.getInstance()
								.getProperty(
										ConfigurationManager.DOES_NOT_HAVE_ACCESS_LEVEL_MESSAGE));
				dispatcher.forward(request, response);
			}
			chain.doFilter(request, response);
		} else {
			request.setAttribute(
					"errorMessage",
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.USER_NOT_FOUND_MESSAGE));
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(ConfigurationManager.getInstance()
							.getProperty(ConfigurationManager.INDEX_PAGE_PATH));
			dispatcher.forward(request, response);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
