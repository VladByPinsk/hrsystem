package by.training.hrsystem.command.impl.applicant;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.command.util.QueryUtil;
import by.training.hrsystem.domain.Resume;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.ResumeService;
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

public class ToApplicantListResumeCommand implements Command {

	private static final Logger logger = LogManager.getLogger(ToApplicantListResumeCommand.class);
	private static final int PAGE_NUMBER = 1;
	private static final int RESUME_PER_PAGE = 4;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("ToApplicantListResumeCommand.execute() - start");

		HttpSession session = request.getSession(false);
		User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);

		if (user != null && user.getRole() == Role.APPLICANT) {
			int pageNumber = PAGE_NUMBER;
			if (request.getParameter(Attribute.PAGE) != null) {
				pageNumber = Integer.parseInt(request.getParameter(Attribute.PAGE));
			}
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ResumeService resumeService = serviceFactory.getResumeService();
			User applicantEmail = (User) request.getSession().getAttribute(Attribute.USER);
			try {
				List<Resume> resumeList = resumeService.selectResumeByEmail(applicantEmail.getEmail(),
						(pageNumber - 1) * RESUME_PER_PAGE, RESUME_PER_PAGE);
				request.setAttribute(Attribute.LIST_RESUME_BY_EMAIL, resumeList);

				int resumeAmount = resumeService.countVacancyByEmail(applicantEmail.getEmail());
				int pageAmount = (int) Math.ceil(resumeAmount * 1.0 / RESUME_PER_PAGE);
				request.setAttribute(Attribute.COUNT_ALL_RESUME, resumeAmount);
				request.setAttribute(Attribute.PAGE_AMONT, pageAmount);
				request.setAttribute(Attribute.PAGE, pageNumber);
				request.getRequestDispatcher(PageName.APPLICANT_LIST_RESUME_PAGE).forward(request, response);
				QueryUtil.saveHttpQuery(request);
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				logger.error("something goes wrong");
			}
		}

		logger.debug("ToApplicantListResumeCommand.execute() - end");

	}
}
