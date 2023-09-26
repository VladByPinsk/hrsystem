package by.training.hrsystem.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.PageName;

public class UserLogOutCommand implements Command {

  private static final Logger LOGGER = LogManager.getLogger(UserLogOutCommand.class);

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LOGGER.debug("UserLogOutCommand: execute() - start");

    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
      request.getRequestDispatcher(PageName.FIRST_PAGE).forward(request, response);
    }
    LOGGER.debug("UserLogOutCommand:: execute() - end");
  }
}
