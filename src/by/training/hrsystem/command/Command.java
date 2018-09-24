package by.training.hrsystem.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface {@code Command} is implemented by classes that encapsulate the
 * algorithm command on the HTTP-request processing. Team - an intermediate
 * layer between the controller (getting the client's request) and service
 * (application logic). This command is used to process the request parameters
 * and transfer their service in the correct data format. As a result of its
 * implementation, the team must set the appropriate attributes in the request
 * context / session for the purpose of informing the client about the result of
 * its request (whether the request was successful or an error occurs as a
 * result ended). Also the team on the basis of the parameters / attributes
 * request / session, and on the basis of the results of the service makes the
 * decision on what type of page you want to redirect a request or a command to
 * transfer control for further processing.
 * 
 * @author Vladislav
 *
 */
public interface Command {
	/**
	 * Method {@code execute} describes the algorithm of a specific command for
	 * the HTTP-request processing. The method accesses the HTTP-request query
	 * parameters through the parameter which is HttpServletRequest object
	 * class. Through this same setting method accesses a client session.
	 * 
	 * @param request
	 *            http-request client
	 * @param response
	 *            http-response server
	 * @throws ServletException
	 * @throws IOException
	 */
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
