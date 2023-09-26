package by.training.hrsystem.command.impl;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.service.UserService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.user.PasswordNotEqualsServiceException;
import by.training.hrsystem.service.exeption.user.UserWithThisEmailExistServiceException;
import by.training.hrsystem.service.exeption.user.WrongBirthDateServiceException;
import by.training.hrsystem.service.exeption.user.WrongEmailServiceException;
import by.training.hrsystem.service.exeption.user.WrongNameServiceException;
import by.training.hrsystem.service.exeption.user.WrongPasswordServiceException;
import by.training.hrsystem.service.exeption.user.WrongPhoneServiceException;
import by.training.hrsystem.service.exeption.user.WrongSecondnameServiceException;
import by.training.hrsystem.service.exeption.user.WrongSkypeServiceException;
import by.training.hrsystem.service.exeption.user.WrongSurnameServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserRegistrationCommand implements Command {
  private static final Logger logger = LogManager.getLogger(UserRegistrationCommand.class);

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String email = request.getParameter(Attribute.EMAIL);
    String password = request.getParameter(Attribute.PASSWORD);
    String copyPassword = request.getParameter(Attribute.COPY_PASSWORD);
    String surname = request.getParameter(Attribute.SURNAME);
    String name = request.getParameter(Attribute.NAME);
    String secondName = request.getParameter(Attribute.SECOND_NAME);
    String skype = request.getParameter(Attribute.SKYPE);
    String contactPhone = request.getParameter(Attribute.CONTACT_PHONE);
    String birthDate = request.getParameter(Attribute.BIRHT_DATE);
    String role = request.getParameter(Attribute.ROLE);

    try {
      ServiceFactory serviceFactory = ServiceFactory.getInstance();
      UserService userService = serviceFactory.getUserService();
      userService.registration(
          email,
          password,
          copyPassword,
          surname,
          name,
          secondName,
          skype,
          contactPhone,
          birthDate,
          role);
      User user = userService.login(email, password);
      HttpSession session = request.getSession(true);
      session.setAttribute(Attribute.USER, user);
      response.sendRedirect(PageName.FIRST_PAGE);
    } catch (WrongEmailServiceException e) {
      request.setAttribute(Attribute.ERROR_EMAIL, true);
      request.setAttribute(Attribute.EMAIL, email);
      request.setAttribute(Attribute.SURNAME, surname);
      request.setAttribute(Attribute.NAME, name);
      request.setAttribute(Attribute.SECOND_NAME, secondName);
      request.setAttribute(Attribute.SKYPE, skype);
      request.setAttribute(Attribute.CONTACT_PHONE, contactPhone);
      request.setAttribute(Attribute.BIRHT_DATE, birthDate);
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("Wrong email");
    } catch (UserWithThisEmailExistServiceException e) {
      request.setAttribute(Attribute.EMAIL, email);
      request.setAttribute(Attribute.SURNAME, surname);
      request.setAttribute(Attribute.NAME, name);
      request.setAttribute(Attribute.SECOND_NAME, secondName);
      request.setAttribute(Attribute.SKYPE, skype);
      request.setAttribute(Attribute.CONTACT_PHONE, contactPhone);
      request.setAttribute(Attribute.BIRHT_DATE, birthDate);
      request.setAttribute(Attribute.ERROR_ALREADY_EXIST, true);
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("User with this email is aredy exist");
    } catch (WrongPasswordServiceException e) {
      request.setAttribute(Attribute.EMAIL, email);
      request.setAttribute(Attribute.SURNAME, surname);
      request.setAttribute(Attribute.NAME, name);
      request.setAttribute(Attribute.SECOND_NAME, secondName);
      request.setAttribute(Attribute.SKYPE, skype);
      request.setAttribute(Attribute.CONTACT_PHONE, contactPhone);
      request.setAttribute(Attribute.BIRHT_DATE, birthDate);
      request.setAttribute(Attribute.ERROR_PASSWORD, true);
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("Wrong password");
    } catch (PasswordNotEqualsServiceException e) {
      request.setAttribute(Attribute.EMAIL, email);
      request.setAttribute(Attribute.SURNAME, surname);
      request.setAttribute(Attribute.NAME, name);
      request.setAttribute(Attribute.SECOND_NAME, secondName);
      request.setAttribute(Attribute.SKYPE, skype);
      request.setAttribute(Attribute.CONTACT_PHONE, contactPhone);
      request.setAttribute(Attribute.BIRHT_DATE, birthDate);
      request.setAttribute(Attribute.ERROR_PASSWORD_NOT_EQUALS, true);
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("Passwod must be equals");
    } catch (WrongSurnameServiceException e) {
      request.setAttribute(Attribute.EMAIL, email);
      request.setAttribute(Attribute.SURNAME, surname);
      request.setAttribute(Attribute.NAME, name);
      request.setAttribute(Attribute.SECOND_NAME, secondName);
      request.setAttribute(Attribute.SKYPE, skype);
      request.setAttribute(Attribute.CONTACT_PHONE, contactPhone);
      request.setAttribute(Attribute.BIRHT_DATE, birthDate);
      request.setAttribute(Attribute.ERROR_SURNAME, true);
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("Wrong surname");
    } catch (WrongNameServiceException e) {
      request.setAttribute(Attribute.EMAIL, email);
      request.setAttribute(Attribute.SURNAME, surname);
      request.setAttribute(Attribute.NAME, name);
      request.setAttribute(Attribute.SECOND_NAME, secondName);
      request.setAttribute(Attribute.SKYPE, skype);
      request.setAttribute(Attribute.CONTACT_PHONE, contactPhone);
      request.setAttribute(Attribute.BIRHT_DATE, birthDate);
      request.setAttribute(Attribute.ERROR_NAME, true);
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("wrong name");
    } catch (WrongSecondnameServiceException e) {
      request.setAttribute(Attribute.EMAIL, email);
      request.setAttribute(Attribute.SURNAME, surname);
      request.setAttribute(Attribute.NAME, name);
      request.setAttribute(Attribute.SECOND_NAME, secondName);
      request.setAttribute(Attribute.SKYPE, skype);
      request.setAttribute(Attribute.CONTACT_PHONE, contactPhone);
      request.setAttribute(Attribute.BIRHT_DATE, birthDate);
      request.setAttribute(Attribute.ERROR_SECONDNAME, true);
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("wrong secondname");
    } catch (WrongSkypeServiceException e) {
      request.setAttribute(Attribute.EMAIL, email);
      request.setAttribute(Attribute.SURNAME, surname);
      request.setAttribute(Attribute.NAME, name);
      request.setAttribute(Attribute.SECOND_NAME, secondName);
      request.setAttribute(Attribute.SKYPE, skype);
      request.setAttribute(Attribute.CONTACT_PHONE, contactPhone);
      request.setAttribute(Attribute.BIRHT_DATE, birthDate);
      request.setAttribute(Attribute.ERROR_SKYPE, true);
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("wrong skype");
    } catch (WrongPhoneServiceException e) {
      request.setAttribute(Attribute.EMAIL, email);
      request.setAttribute(Attribute.SURNAME, surname);
      request.setAttribute(Attribute.NAME, name);
      request.setAttribute(Attribute.SECOND_NAME, secondName);
      request.setAttribute(Attribute.SKYPE, skype);
      request.setAttribute(Attribute.CONTACT_PHONE, contactPhone);
      request.setAttribute(Attribute.BIRHT_DATE, birthDate);
      request.setAttribute(Attribute.ERROR_PHONE, true);
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("wrong contcat phone");
    } catch (WrongBirthDateServiceException e) {
      request.setAttribute(Attribute.EMAIL, email);
      request.setAttribute(Attribute.SURNAME, surname);
      request.setAttribute(Attribute.NAME, name);
      request.setAttribute(Attribute.SECOND_NAME, secondName);
      request.setAttribute(Attribute.SKYPE, skype);
      request.setAttribute(Attribute.CONTACT_PHONE, contactPhone);
      request.setAttribute(Attribute.BIRHT_DATE, birthDate);
      request.setAttribute(Attribute.ERROR_DATE, true);
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("wrong date");
    } catch (ServiceException e) {
      request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
      logger.error("Something goes wrong");
    }

    logger.debug("UserRegistationCommand:execute() end");
  }
}
