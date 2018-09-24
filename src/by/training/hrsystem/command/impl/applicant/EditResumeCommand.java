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
import by.training.hrsystem.service.ResumeService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.resume.WrongResumeNameServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;

public class EditResumeCommand implements Command {

	private static final Logger logger = LogManager.getLogger(EditResumeCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("editResumeCommand.execute() start");

		HttpSession session = request.getSession(false);
		User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);
		String idResume = request.getParameter(Attribute.ID_RESUME);
		String resumeName = request.getParameter(Attribute.RESUME_NAME);
		String resumeMilitary = request.getParameter(Attribute.RESUME_MILITARY);
		String prevQuery = (session == null) ? null : (String) session.getAttribute(Attribute.PREV_QUERY);

		if (user != null && user.getRole() == Role.APPLICANT) {
			try {
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				ResumeService resumeService = serviceFactory.getResumeService();
				resumeService.updateResume(resumeName, resumeMilitary, idResume);
				response.sendRedirect(prevQuery);
			} catch (WrongResumeNameServiceException e) {
				request.setAttribute(Attribute.ERROR_RESUME_NAME, true);
				request.getRequestDispatcher(PageName.APPLICANT_ADD_RESUME_PAGE).forward(request, response);
				logger.error("wrong resume name");
			} catch (ServiceException e) {
				logger.error("somthing goes wrong");
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
			}
		} else {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
			logger.error("user session is over");
		}

		logger.debug("editResumeCommand:execute() stop");
	}
}
