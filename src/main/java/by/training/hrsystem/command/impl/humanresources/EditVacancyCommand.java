package by.training.hrsystem.command.impl.humanresources;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.VacancyService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.vacancy.WrongConditionsServiceException;
import by.training.hrsystem.service.exeption.vacancy.WrongDescriptionServiceException;
import by.training.hrsystem.service.exeption.vacancy.WrongSalaryServiceException;
import by.training.hrsystem.service.exeption.vacancy.WrongVacancyNameServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditVacancyCommand implements Command {

  private static final Logger logger = LogManager.getLogger(EditVacancyCommand.class);

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    logger.debug("EditVacancyCommand.execute() start");

    HttpSession session = request.getSession(false);
    User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);
    String vacancyName = request.getParameter(Attribute.VACANCY_NAME);
    String salary = request.getParameter(Attribute.SALARY);
    String currency = request.getParameter(Attribute.CURRENCY);
    String description = request.getParameter(Attribute.DESCRIPTION);
    String duties = request.getParameter(Attribute.DUTIES);
    String conditions = request.getParameter(Attribute.CONDITIONS);
    String employmentType = request.getParameter(Attribute.EMPLOYMENT_TYPE);
    String idVacancy = request.getParameter(Attribute.ID_VACANCY);

    if (user != null && user.getRole() == Role.HR) {
      try {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        VacancyService vacancyService = serviceFactory.getVacancyService();
        vacancyService.updateVacancy(
            vacancyName,
            salary,
            currency,
            description,
            duties,
            conditions,
            employmentType,
            idVacancy);
        request.setAttribute(Attribute.ADD_VACANCY_SUCCESS, true);
        request.getRequestDispatcher(PageName.HR_TO_LIST_VACANCY_PAGE).forward(request, response);
      } catch (WrongVacancyNameServiceException e) {
        request.setAttribute(Attribute.ERROR_VACANCY_NAME, true);
        request.getRequestDispatcher(PageName.HR_ADD_VACANCY_PAGE).forward(request, response);
        logger.error("wrong vacancy name");
      } catch (WrongSalaryServiceException e) {
        request.setAttribute(Attribute.ERROR_SALARY, true);
        request.getRequestDispatcher(PageName.HR_ADD_VACANCY_PAGE).forward(request, response);
        logger.error("wrong salary");
      } catch (WrongDescriptionServiceException e) {
        request.setAttribute(Attribute.ERROR_DESCRIPTION, true);
        request.getRequestDispatcher(PageName.HR_ADD_VACANCY_PAGE).forward(request, response);
        logger.error("wrong description");
      } catch (WrongConditionsServiceException e) {
        request.setAttribute(Attribute.ERROR_CONDITIONS, true);
        request.getRequestDispatcher(PageName.HR_ADD_VACANCY_PAGE).forward(request, response);
        logger.error("wrong condotions");
      } catch (ServiceException e) {
        request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
        logger.error("something goes wrong!");
      }
    } else {
      request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
      logger.error("user session is over");
    }
    logger.debug("EditVacancyCommand.execute() end");
  }
}
