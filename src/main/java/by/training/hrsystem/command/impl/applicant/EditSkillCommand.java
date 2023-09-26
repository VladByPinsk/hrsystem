package by.training.hrsystem.command.impl.applicant;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.SkillService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.skill.WrongRaitingServiceException;
import by.training.hrsystem.service.exeption.skill.WrongSkillNameServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditSkillCommand implements Command {

  private static final Logger logger = LogManager.getLogger(EditSkillCommand.class);

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    logger.debug("EditSkillCommand.execute() start");

    HttpSession session = request.getSession(false);
    User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);
    String idSkill = request.getParameter(Attribute.ID_SKILL);
    String skillName = request.getParameter(Attribute.SKILL_NAME);
    String skillLevel = request.getParameter(Attribute.SKILL_LEVEL);
    String prevQuery =
        (session == null) ? null : (String) session.getAttribute(Attribute.PREV_QUERY);

    if (user != null && user.getRole() == Role.APPLICANT) {
      try {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SkillService skillService = serviceFactory.getSkillService();
        skillService.updateSkill(skillName, skillLevel, idSkill);
        response.sendRedirect(prevQuery);
      } catch (WrongSkillNameServiceException e) {
        request.setAttribute(Attribute.ERROR_SKILL_NAME, true);
        request
            .getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE)
            .forward(request, response);
        logger.error("wrong skill name");
      } catch (WrongRaitingServiceException e) {
        request.setAttribute(Attribute.ERROR_RAITING, true);
        request
            .getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE)
            .forward(request, response);
        logger.error("wrong raiting");
      } catch (ServiceException e) {
        request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
        logger.error("something goes wrong");
      }
    } else {
      request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
      logger.error("user session is over");
    }

    logger.debug("EditSkillCommand.execute() stop");
  }
}
