package by.training.hrsystem.controller;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.PageName;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller extends HttpServlet {

	public final static String COMMAND = "command";
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(Controller.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String commandName = request.getParameter(COMMAND);
			Command command = CommandHelper.getInstance().getCommand(commandName);
			command.execute(request, response);
		} catch (RuntimeException e) {
			logger.error("runtime");
			request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
		}

	}

}
