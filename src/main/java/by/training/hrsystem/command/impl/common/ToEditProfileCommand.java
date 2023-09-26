package by.training.hrsystem.command.impl.common;

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

public class ToEditProfileCommand implements Command {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    User user = (User) request.getSession().getAttribute(Attribute.USER);
    if (user != null) {
      if (user.getRole() == Role.APPLICANT) {
        request
            .getRequestDispatcher(PageName.APPLICANT_EDIT_PROFILE_PAGE)
            .forward(request, response);
      } else if (user.getRole() == Role.HR) {
        request.getRequestDispatcher(PageName.HR_EDIT_PROFILE_PAGE).forward(request, response);
      }
      QueryUtil.saveHttpQuery(request);
    } else {
      request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
    }
  }
}
