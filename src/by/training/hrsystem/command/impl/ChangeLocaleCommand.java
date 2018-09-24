package by.training.hrsystem.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.PageName;

public class ChangeLocaleCommand implements Command {

	private static final String LOCALE_PARAM = "locale";
	private static final String ENGLISH = "EN";
	private static final String PREV_QUERY = "prev-query";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
			return;
		}
		String lang = request.getParameter(LOCALE_PARAM);
		if (lang.isEmpty()) {
			lang = ENGLISH;
		}
		session.setAttribute(LOCALE_PARAM, lang);
		String prevQuery = (String) session.getAttribute(PREV_QUERY);
		if (prevQuery != null) {
			response.sendRedirect(prevQuery);
		} else {
			request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
		}

	}
}