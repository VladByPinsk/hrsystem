package by.training.hrsystem.command.impl.humanresources;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.InterviewService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.interview.InterviewServiceException;
import by.training.hrsystem.service.exeption.interview.WrongDateInterviewServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddInterviewToApplicantCommand implements Command {
	private static final Logger logger = LogManager.getLogger(AddInterviewToApplicantCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("AddInterviewToApplicantCommand:execute() start");
		HttpSession session = request.getSession(false);
		User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);
		String interviewType = request.getParameter(Attribute.TYPE_INTERVIEW);
		String dateInterview = request.getParameter(Attribute.DATE_INTERVIEW);
		String idVerify = request.getParameter(Attribute.ID_VERIFY);
		String prevQuery = (session == null) ? null : (String) session.getAttribute(Attribute.PREV_QUERY);

		if (user != null && user.getRole() == Role.HR) {
			try {
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				InterviewService interviewService = serviceFactory.getInterviewService();
				interviewService.addInterview(interviewType, dateInterview, idVerify);
				response.sendRedirect(prevQuery);
			} catch (WrongDateInterviewServiceException e) {
				request.setAttribute(Attribute.ERROR_DATE_INTERVIEW, true);
				request.getRequestDispatcher(PageName.HR_APPLICANT_INTERVIEW_PAGE).forward(request, response);
				logger.error("wrong dateInterviewt");
			} catch (InterviewServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				logger.error("something goes wrong");
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				logger.error("something goes wrong");
			}
		} else {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
			logger.error("user session is over");
		}
	}
}
