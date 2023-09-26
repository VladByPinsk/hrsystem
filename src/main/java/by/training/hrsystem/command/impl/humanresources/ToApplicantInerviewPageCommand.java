package by.training.hrsystem.command.impl.humanresources;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.command.util.QueryUtil;
import by.training.hrsystem.domain.Interview;
import by.training.hrsystem.domain.InterviewMark;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.Verify;
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.InterviewMarkService;
import by.training.hrsystem.service.InterviewService;
import by.training.hrsystem.service.VerifyService;
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

public class ToApplicantInerviewPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger(ToApplicantInerviewPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("ToApplicantInerviewPageCommand .execute() start");
		HttpSession session = request.getSession(false);
		User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);
		String idVerify = request.getParameter(Attribute.ID_VERIFY);
		if (user != null && user.getRole() == Role.HR) {
			try {
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				VerifyService verifyService = serviceFactory.gerVerifyService();
				InterviewService interviewService = serviceFactory.getInterviewService();
				InterviewMarkService interviewMarkService = serviceFactory.getInterviewMarkService();
				Verify verify = verifyService.selectVerifyById(idVerify);
				List<Interview> listInterview = interviewService.selectInterviewByVerify(idVerify);
				List<InterviewMark> listMarkTechical = interviewMarkService.selectMarkOfTechicalInterview(idVerify);
				List<InterviewMark> listMarkPreliminary = interviewMarkService
						.selectMarkOfPreliminaryInterview(idVerify);
				request.setAttribute(Attribute.LIST_INTERVIEW, listInterview);
				request.setAttribute(Attribute.VERIFY, verify);
				request.setAttribute(Attribute.MARK_TECHICAL, listMarkTechical);
				request.setAttribute(Attribute.MARK_PRELIMINARY, listMarkPreliminary);
				request.getRequestDispatcher(PageName.HR_APPLICANT_INTERVIEW_PAGE).forward(request, response);
				QueryUtil.saveHttpQuery(request);
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				logger.error("something goes wrong!");
			}
		} else {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
			logger.error("user session is over");
		}
		logger.debug("ToApplicantInerviewPageCommand .execute() stop");
	}

}
