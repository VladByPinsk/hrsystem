package by.training.hrsystem.command.impl.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.service.UserService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.user.PasswordNotEqualsServiceException;
import by.training.hrsystem.service.exeption.user.WrongBirthDateServiceException;
import by.training.hrsystem.service.exeption.user.WrongNameServiceException;
import by.training.hrsystem.service.exeption.user.WrongPasswordServiceException;
import by.training.hrsystem.service.exeption.user.WrongPhoneServiceException;
import by.training.hrsystem.service.exeption.user.WrongSecondnameServiceException;
import by.training.hrsystem.service.exeption.user.WrongSkypeServiceException;
import by.training.hrsystem.service.exeption.user.WrongSurnameServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;

public class EditProfileCommand implements Command {
	private static final Logger logger = LogManager.getLogger(EditProfileCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.debug("ApplicantEditProfileCommand:execute() start");
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute(Attribute.USER);
		String surname = request.getParameter(Attribute.SURNAME);
		String name = request.getParameter(Attribute.NAME);
		String secondName = request.getParameter(Attribute.SECOND_NAME);
		String skype = request.getParameter(Attribute.SKYPE);
		String contactPhone = request.getParameter(Attribute.CONTACT_PHONE);
		String birthDate = request.getParameter(Attribute.BIRHT_DATE);
		String prevQuery = (String) session.getAttribute(Attribute.PREV_QUERY);
		if (user != null) {
			try {
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				UserService userService = serviceFactory.getUserService();
				userService.updateProfile(surname, name, secondName, skype, contactPhone, birthDate, user.getEmail());
				response.sendRedirect(prevQuery);
			} catch (WrongPasswordServiceException e) {
				request.setAttribute(Attribute.ERROR_PASSWORD, true);
				response.sendRedirect(prevQuery);
				logger.error("Wrong password");
			} catch (PasswordNotEqualsServiceException e) {
				request.setAttribute(Attribute.ERROR_PASSWORD_NOT_EQUALS, true);
				response.sendRedirect(prevQuery);
				logger.error("Passwod must be equals");
			} catch (WrongSurnameServiceException e) {
				request.setAttribute(Attribute.ERROR_SURNAME, true);
				response.sendRedirect(prevQuery);
				logger.error("Wrong surname");
			} catch (WrongNameServiceException e) {
				request.setAttribute(Attribute.ERROR_NAME, true);
				response.sendRedirect(prevQuery);
				logger.error("wrong name");
			} catch (WrongSecondnameServiceException e) {
				request.setAttribute(Attribute.ERROR_SECONDNAME, true);
				response.sendRedirect(prevQuery);
				logger.error("wrong secondname");
			} catch (WrongSkypeServiceException e) {
				request.setAttribute(Attribute.ERROR_SKYPE, true);
				response.sendRedirect(prevQuery);
				logger.error("wrong skype");
			} catch (WrongPhoneServiceException e) {
				request.setAttribute(Attribute.ERROR_PHONE, true);
				response.sendRedirect(prevQuery);
				logger.error("wrong contcat phone");
			} catch (WrongBirthDateServiceException e) {
				request.setAttribute(Attribute.ERROR_DATE, true);
				response.sendRedirect(prevQuery);
				logger.error("wrong date");
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				logger.error("something goes wrong");
			}
		} else {
			request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
			logger.error("user session is over");
		}
		logger.debug("EditProfileCommand.execute() end");
	}

}
