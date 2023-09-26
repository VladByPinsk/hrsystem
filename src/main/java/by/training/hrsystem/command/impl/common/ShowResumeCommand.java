package by.training.hrsystem.command.impl.common;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.command.util.QueryUtil;
import by.training.hrsystem.domain.Education;
import by.training.hrsystem.domain.Resume;
import by.training.hrsystem.domain.ResumeLanguage;
import by.training.hrsystem.domain.Skill;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.WorkPlace;
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.EducationService;
import by.training.hrsystem.service.ResumeLanguageService;
import by.training.hrsystem.service.ResumeService;
import by.training.hrsystem.service.SkillService;
import by.training.hrsystem.service.UserService;
import by.training.hrsystem.service.WorkPlaceService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowResumeCommand implements Command {
  private static final Logger logger = LogManager.getLogger(ShowResumeCommand.class);

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    logger.debug("ShowResumeCommand:execute() start");
    User user = (User) request.getSession().getAttribute(Attribute.USER);
    String idResume = request.getParameter(Attribute.ID_RESUME);
    if (user != null) {
      try {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ResumeService resumeService = serviceFactory.getResumeService();
        UserService userService = serviceFactory.getUserService();
        EducationService educationService = serviceFactory.getEducationService();
        SkillService skillService = serviceFactory.getSkillService();
        ResumeLanguageService resumeLanguageService = serviceFactory.getResumeLanguageService();
        WorkPlaceService workPlaceService = serviceFactory.getWorkPlaceService();

        Resume resume = resumeService.selectResumeById(idResume);
        User applicant = userService.selectUserByIdResume(idResume);
        List<Education> education = educationService.selectEducationbyIdResume(idResume);
        List<Skill> skills = skillService.selectSkillByIdResume(idResume);
        List<ResumeLanguage> resumeLanguage =
            resumeLanguageService.selectLanguageByIdResume(idResume);
        List<WorkPlace> workPlace = workPlaceService.selectWorkPlaceByIdResume(idResume);

        request.setAttribute(Attribute.RESUME, resume);
        request.setAttribute(Attribute.APPLICANT, applicant);
        request.setAttribute(Attribute.EDUCATION_LIST, education);
        request.setAttribute(Attribute.SKILL_LIST, skills);
        request.setAttribute(Attribute.LANGUAGE_LIST, resumeLanguage);
        request.setAttribute(Attribute.WORKPLACE_LIST, workPlace);
        if (user.getRole() == Role.APPLICANT) {
          request.getRequestDispatcher(PageName.APPLICANT_RESUME_PAGE).forward(request, response);
        } else if (user.getRole() == Role.HR) {
          request
              .getRequestDispatcher(PageName.HR_APPLICANT_RESUME_PAGE)
              .forward(request, response);
        }
        QueryUtil.saveHttpQuery(request);
      } catch (ServiceException e) {
        request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
        logger.error("user session is over");
      }
    } else {
      request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
      logger.error("user session is over");
    }
    logger.debug("ShowResumeCommand:execute() end");
  }
}
