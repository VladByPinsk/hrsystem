package by.training.hrsystem.command.impl.applicant;

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
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.EducationService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;

public class DeleteEducationCommand implements Command {

  private static final Logger logger = LogManager.getLogger(DeleteEducationCommand.class);

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    logger.debug("DeltetEducationCommand.execute() start");
    HttpSession session = request.getSession(false);
    User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);
    String prevQuery =
        (session == null) ? null : (String) session.getAttribute(Attribute.PREV_QUERY);
    String idEducation = request.getParameter(Attribute.ID_EDUCATION);

    if (user != null && user.getRole() == Role.APPLICANT) {
      try {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EducationService educationService = serviceFactory.getEducationService();
        educationService.deleteEducation(idEducation);
        response.sendRedirect(prevQuery);
      } catch (ServiceException e) {
        request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
        logger.error("something goes wrong");
      }
    } else {
      request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
      logger.error("user session is over");
    }

    logger.debug("DeleteEducationCommand.execute() stop");
  }
}
