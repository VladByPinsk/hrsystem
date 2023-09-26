package by.training.hrsystem.command.impl.common;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.command.util.QueryUtil;
import by.training.hrsystem.domain.Resume;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.Vacancy;
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.ResumeService;
import by.training.hrsystem.service.UserService;
import by.training.hrsystem.service.VacancyService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowVacancyCommand implements Command {
	private static final Logger logger = LogManager.getLogger(ShowVacancyCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("ShowVacancyCommand:execute() start");

		String idVacancy = request.getParameter(Attribute.ID_VACANCY);
		String lang = (String) request.getSession().getAttribute(Attribute.LOCALE);
		User user = (User) request.getSession().getAttribute(Attribute.USER);

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			VacancyService vacancyService = serviceFactory.getVacancyService();
			UserService userService = serviceFactory.getUserService();
			ResumeService resumeService = serviceFactory.getResumeService();
			User applicantEmail = (User) request.getSession().getAttribute(Attribute.USER);

			Vacancy vacnacy = vacancyService.selectVacancyById(idVacancy, lang);
			User hr = userService.selectUserByIdVacancy(idVacancy);
			if (user != null && user.getRole() == Role.APPLICANT) {
				List<Resume> leftResume = resumeService.selectLeftResume(idVacancy, applicantEmail.getEmail());
				List<Resume> resumeList = resumeService.selectResumeForVacancy(applicantEmail.getEmail());
				request.setAttribute(Attribute.LIST_RESUME_BY_EMAIL, resumeList);
				request.setAttribute(Attribute.LIST_LEFT_RESUME, leftResume);

			}
			request.setAttribute(Attribute.VACANCY, vacnacy);
			request.setAttribute(Attribute.HR, hr);
			request.getRequestDispatcher(PageName.VACANCY_PAGE).forward(request, response);
			QueryUtil.saveHttpQuery(request);
		} catch (ServiceException e) {
			request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
			logger.error("user session is over");
		}
		logger.debug("ShowVacancyCommand:execute() end");

	}

}
