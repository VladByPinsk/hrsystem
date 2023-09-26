package by.training.hrsystem.command.impl.humanresources;

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
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToHrEditVacancyCommand implements Command {

	private static final Logger logger = LogManager.getLogger(ToHrEditVacancyCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("ToEditVacancyCommand.execute() start");
		HttpSession session = request.getSession(false);
		User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);
		String idVacancy = request.getParameter(Attribute.ID_VACANCY);
		if (user != null && user.getRole() == Role.HR) {
			try {
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				VacancyService vacancyService = serviceFactory.getVacancyService();
				Vacancy vacnacy = vacancyService.selectNormalVacancyById(idVacancy);
				request.setAttribute(Attribute.VACANCY, vacnacy);
				request.getRequestDispatcher(PageName.HR_EDIT_VACANCY_PAGE).forward(request, response);
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				logger.error("something goes wrong");
			}
		} else {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
			logger.error("user session is over");
		}
		QueryUtil.saveHttpQuery(request);
		logger.debug("ToEditVacancyCommand.execute() end");

	}
}
