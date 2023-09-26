package by.training.hrsystem.command.impl.applicant;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.command.util.QueryUtil;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.role.Role;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToApplicantAddResumeCommand implements Command {
	private static final Logger logger = LogManager.getLogger(ToApplicantAddResumeCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("ToApplicantAddResumeCommand.execute() start");
		HttpSession session = request.getSession(false);
		User user = (session == null) ? null :(User) session.getAttribute(Attribute.USER);
		if (user != null && user.getRole() == Role.APPLICANT) {
			request.getRequestDispatcher(PageName.APPLICANT_ADD_RESUME_PAGE).forward(request, response);
			QueryUtil.saveHttpQuery(request);
		} else {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
			logger.error("user session is over");
		}
		logger.debug("ToApplicantAddResumeCommand.execute() end");
	}

}
