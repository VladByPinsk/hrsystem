package by.training.hrsystem.command.impl.humanresources;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.command.util.QueryUtil;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.Vacancy;
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.VacancyService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;

public class ToHrListVacancyCommand implements Command {

	private static final Logger logger = LogManager.getLogger(ToHrListVacancyCommand.class);
	private static final int PAGE_NUMBER = 1;
	private static final int VACANCY_PER_PAGE = 9;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.debug("ToHrListVacancyCommand.execute()  start");

		HttpSession session = request.getSession(false);
		User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);
		if (user != null && user.getRole() == Role.HR) {

			int pageNumber = PAGE_NUMBER;
			if (request.getParameter(Attribute.PAGE) != null) {
				pageNumber = Integer.parseInt(request.getParameter(Attribute.PAGE));
			}
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			VacancyService vacancyService = serviceFactory.getVacancyService();
			String lang = (session == null) ? null : (String) request.getSession().getAttribute(Attribute.LOCALE);

			try {
				List<Vacancy> vacancyList = vacancyService.selectVacancyByHrEmail(user.getEmail(), lang,
						(pageNumber - 1) * VACANCY_PER_PAGE, VACANCY_PER_PAGE);
				request.setAttribute(Attribute.VACANCIES, vacancyList);
				int vacancyAmount = vacancyService.countVacancyByHrEmail(user.getEmail());
				int pageAmount = (int) Math.ceil(vacancyAmount * 1.0 / VACANCY_PER_PAGE);
				request.setAttribute(Attribute.COUNT_ALL_ACTIVE_VACANCY, vacancyAmount);
				request.setAttribute(Attribute.PAGE_AMONT, pageAmount);
				request.setAttribute(Attribute.PAGE, pageNumber);
				request.getRequestDispatcher(PageName.HR_LIST_VACANCY_PAGE).forward(request, response);
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				logger.error("something goes wrong");
			}
		} else {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
			logger.error("user session is over");
		}
		QueryUtil.saveHttpQuery(request);
		logger.debug("ToHrListVacancyCommand.execute()  end");
	}
}
