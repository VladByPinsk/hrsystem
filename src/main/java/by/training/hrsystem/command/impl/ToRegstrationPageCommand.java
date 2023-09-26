package by.training.hrsystem.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.command.util.QueryUtil;

public class ToRegstrationPageCommand implements Command {

<<<<<<< Updated upstream
	private static final Logger logger = LogManager.getLogger(ToRegstrationPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("ToRegistrarionPageCommand.execute()-start");
		request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
		QueryUtil.saveHttpQuery(request);
		logger.debug("ToRegistrarionPageCommand.execute()-end");
	}
=======
  private static final Logger logger = LogManager.getLogger(ToRegstrationPageCommand.class);
>>>>>>> Stashed changes

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    logger.debug("ToRegistrarionPageCommand.execute()-start");
    request.getRequestDispatcher(PageName.REGISTRATION_PAGE).forward(request, response);
    QueryUtil.saveHttpQuery(request);
    logger.debug("ToRegistrarionPageCommand.execute()-end");
  }
}
