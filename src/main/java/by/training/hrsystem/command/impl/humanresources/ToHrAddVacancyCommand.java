package by.training.hrsystem.command.impl.humanresources;

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

public class ToHrAddVacancyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (session == null) ? null :(User) session.getAttribute(Attribute.USER);
		if (user != null && user.getRole() == Role.HR) {
			request.getRequestDispatcher(PageName.HR_ADD_VACANCY_PAGE).forward(request, response);
			QueryUtil.saveHttpQuery(request);
		} else {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
		}
	}
}
