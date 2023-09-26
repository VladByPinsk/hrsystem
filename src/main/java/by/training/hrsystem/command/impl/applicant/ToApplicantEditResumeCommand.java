package by.training.hrsystem.command.impl.applicant;

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
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToApplicantEditResumeCommand implements Command {
	private static final Logger logger = LogManager.getLogger(ToApplicantEditResumeCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("ToApplicantEditResumeCommand.execute() start");
		HttpSession session = request.getSession(false);
		User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);

		String idResume = request.getParameter(Attribute.ID_RESUME);
		if (user != null && user.getRole() == Role.APPLICANT) {
			try {
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				ResumeService resumeService = serviceFactory.getResumeService();
				UserService userService = serviceFactory.getUserService();
				EducationService educationService = serviceFactory.getEducationService();
				ResumeLanguageService resumeLanguageService = serviceFactory.getResumeLanguageService();
				SkillService skillService = serviceFactory.getSkillService();
				WorkPlaceService workPlaceService = serviceFactory.getWorkPlaceService();

				Resume resume = resumeService.selectResumeById(idResume);
				User applicant = userService.selectUserByIdVacancy(idResume);
				List<Education> listEducation = educationService.selectEducationbyIdResume(idResume);
				List<ResumeLanguage> listResumeLanguage = resumeLanguageService.selectLanguageByIdResume(idResume);
				List<Skill> listSkill = skillService.selectSkillByIdResume(idResume);
				List<WorkPlace> listWorkPlace = workPlaceService.selectWorkPlaceByIdResume(idResume);

				request.setAttribute(Attribute.RESUME, resume);
				request.setAttribute(Attribute.APPLICANT, applicant);
				request.setAttribute(Attribute.EDUCATION_LIST, listEducation);
				request.setAttribute(Attribute.LANGUAGE_LIST, listResumeLanguage);
				request.setAttribute(Attribute.SKILL_LIST, listSkill);
				request.setAttribute(Attribute.WORKPLACE_LIST, listWorkPlace);

				request.getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE).forward(request, response);

			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				logger.error("something goes wrong");
			}
		} else {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
			logger.error("user session is over");
		}
		QueryUtil.saveHttpQuery(request);
		logger.debug("ToApplicantEditResumeCommand.execute() end");

	}

}
