package by.training.hrsystem.command.impl.applicant;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.service.EducationService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.education.EducationServiceException;
import by.training.hrsystem.service.exeption.education.WrongDepartmentServiceException;
import by.training.hrsystem.service.exeption.education.WrongEducationServiceException;
import by.training.hrsystem.service.exeption.education.WrongFacultyServiceException;
import by.training.hrsystem.service.exeption.education.WrongGradYearServiceException;
import by.training.hrsystem.service.exeption.education.WrongInstitutionServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddEducationCommand implements Command {

	private static final Logger logger = LogManager.getLogger(AddEducationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("AddEducationCommand.execute() start");

		HttpSession session = request.getSession(false);
		User user = (session == null) ? null : (User) session.getAttribute(Attribute.USER);
		String idResume = request.getParameter(Attribute.ID_RESUME);
		String institution = request.getParameter(Attribute.INSTITUTION);
		String faculty = request.getParameter(Attribute.FACULTY);
		String department = request.getParameter(Attribute.DEPARTMENT);
		String educationField = request.getParameter(Attribute.EDUCATION_FIELD);
		String course = request.getParameter(Attribute.COURSE);
		String gradYear = request.getParameter(Attribute.GRAD_YEAR);
		String postGrad = request.getParameter(Attribute.POSTGRAD);

		if (user != null && user.getRole() == Role.APPLICANT) {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			EducationService educationService = serviceFactory.getEducationService();
			String prevQuery = (String) session.getAttribute(Attribute.PREV_QUERY);
			response.sendRedirect(prevQuery);
			try {
				educationService.addEducation(institution, faculty, department, educationField, course, gradYear,
						postGrad, idResume);
			} catch (WrongInstitutionServiceException e) {
				request.setAttribute(Attribute.ERROR_INSTITUTION, true);
				request.getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE).forward(request, response);
				logger.error("wrong istitution");
			} catch (WrongFacultyServiceException e) {
				request.setAttribute(Attribute.ERROR_FACULTY, true);
				request.getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE).forward(request, response);
				logger.error("wrong faculty");
			} catch (WrongDepartmentServiceException e) {
				request.setAttribute(Attribute.ERROR_DEPARTMENT, true);
				request.getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE).forward(request, response);
				logger.error("wrong department");
			} catch (WrongEducationServiceException e) {
				request.setAttribute(Attribute.ERROR_DEPARTMENT, true);
				request.getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE).forward(request, response);
				logger.error("wrong department");
			} catch (WrongGradYearServiceException e) {
				request.setAttribute(Attribute.ERROR_GRAD_YEAR, true);
				request.getRequestDispatcher(PageName.APPLICANT_EDIT_RESUME_PAGE).forward(request, response);
				logger.error("wrong department");
			} catch (EducationServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
			}
		} else {
			request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
			logger.error("user session is over");
		}

		logger.debug("AddEducationCommand.execute() stop");
	}

}
