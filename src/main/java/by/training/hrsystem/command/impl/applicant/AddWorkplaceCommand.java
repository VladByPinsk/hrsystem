package by.training.hrsystem.command.impl.applicant;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.WorkPlaceService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongCompanyNameServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateBeginServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateEndServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongPositionServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddWorkplaceCommand implements Command {

  private static final Logger logger = LogManager.getLogger(AddWorkplaceCommand.class);

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    logger.debug("AddWorkPlaceCommand.execute() start");

    HttpSession session = request.getSession(false);
    User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);

    String idResume = request.getParameter(Attribute.ID_RESUME);
    String name = request.getParameter(Attribute.WORKPLACE_NAME);
    String position = request.getParameter(Attribute.WORKPLACE_POSITION);
    String dateBegin = request.getParameter(Attribute.WORKPLACE_DATE_BEGIN);
    String dateEnd = request.getParameter(Attribute.WORKPLACE_DATE_END);
    String prevQuery =
        (session == null) ? null : (String) session.getAttribute(Attribute.PREV_QUERY);

    if (user != null && user.getRole() == Role.APPLICANT) {
      try {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        WorkPlaceService workPlaceService = serviceFactory.getWorkPlaceService();
        workPlaceService.addWorkplace(name, position, dateBegin, dateEnd, idResume);
        response.sendRedirect(prevQuery);
      } catch (WrongCompanyNameServiceException e) {
        request.setAttribute(Attribute.ERROR_COMPANY_NAME, true);
        request
            .getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE)
            .forward(request, response);
        logger.error("wrong skill name");
      } catch (WrongPositionServiceException e) {
        request.setAttribute(Attribute.ERROR_POSITION, true);
        request
            .getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE)
            .forward(request, response);
        logger.error("wrong skill name");
      } catch (WrongDateBeginServiceException e) {
        request.setAttribute(Attribute.ERROR_DATE_BEGIN, true);
        request
            .getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE)
            .forward(request, response);
        logger.error("wrong skill name");
      } catch (WrongDateEndServiceException e) {
        request.setAttribute(Attribute.ERROR_DATE_END, true);
        request
            .getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE)
            .forward(request, response);
        logger.error("wrong skill name");
      } catch (WrongDateServiceException e) {
        request.setAttribute(Attribute.ERROR_DATE_HIGHER, true);
        request
            .getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE)
            .forward(request, response);
        logger.error("wrong skill name");
      } catch (ServiceException e) {
        request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
        logger.error("something goes wrong");
      }

    } else {
      request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
      logger.error("user session is over");
    }
    logger.debug("AddWorkplaceCommand.execute() stop");
  }
}
