package by.training.hrsystem.command.impl.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.command.util.QueryUtil;
import by.training.hrsystem.domain.Vacancy;
import by.training.hrsystem.service.ResumeService;
import by.training.hrsystem.service.UserService;
import by.training.hrsystem.service.VacancyService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;

public class SearchVacancyByNameCommand implements Command {

	private static final Logger logger = LogManager.getLogger(SearchVacancyByNameCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("SearchVacancyByNameCommand.execute()  start");

		String vacancyName = request.getParameter(Attribute.VACANCY_NAME);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		VacancyService vacancyService = serviceFactory.getVacancyService();
		UserService userService = serviceFactory.getUserService();
		ResumeService resumeService = serviceFactory.getResumeService();

		try {
			List<Vacancy> vacancyList = vacancyService.selectVacancyLike(vacancyName);
			request.setAttribute(Attribute.VACANCIES, vacancyList);
			int vacancyAmount = vacancyService.countAllActiveVacancy();
			request.setAttribute(Attribute.COUNT_ALL_ACTIVE_VACANCY, vacancyAmount);
			int countApplicants = userService.countAllApplicants();
			request.setAttribute(Attribute.COUNT_ALL_APPLICANTS, countApplicants);
			int countResume = resumeService.countAllResume();
			request.setAttribute(Attribute.COUNT_ALL_RESUME, countResume);
			request.getRequestDispatcher(PageName.SEARCH_PAGE).forward(request, response);

		} catch (ServiceException e) {
			request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
			logger.error("user session is over");
		}

		QueryUtil.saveHttpQuery(request);
		logger.debug("SearchVacancyByNameCommand.execute()  end");

	}
}
