package by.training.hrsystem.command.impl;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.service.UserService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.user.PasswordNotEqualsServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLoginCommand implements Command {

	private static final Logger logger = LogManager.getLogger(UserLoginCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
			return;
		}
		String prevQuery = (String) request.getSession(false).getAttribute(Attribute.PREV_QUERY);
		logger.debug("UserLoginCommand:execute() start");
		String email = request.getParameter(Attribute.EMAIL);
		String password = request.getParameter(Attribute.PASSWORD);

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			userService.userExist(email, password);
			User user = userService.login(email, password);
			session.setAttribute(Attribute.USER, user);
			response.sendRedirect(prevQuery);
		} catch (PasswordNotEqualsServiceException e) {
			session.setAttribute(Attribute.ERROR_EMAIL_OR_PASS, true);
			response.sendRedirect(prevQuery);
		} catch (ServiceException e) {
			request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
		}
		logger.debug("UserLoginCommand:execute() end");
	}
}