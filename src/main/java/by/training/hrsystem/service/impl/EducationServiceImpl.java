package by.training.hrsystem.service.impl;

import by.training.hrsystem.dao.EducationDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.domain.Education;
import by.training.hrsystem.service.EducationService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.education.WrongCourseServiceException;
import by.training.hrsystem.service.exeption.education.WrongDepartmentServiceException;
import by.training.hrsystem.service.exeption.education.WrongEducationServiceException;
import by.training.hrsystem.service.exeption.education.WrongFacultyServiceException;
import by.training.hrsystem.service.exeption.education.WrongGradYearServiceException;
import by.training.hrsystem.service.exeption.education.WrongInstitutionServiceException;
import by.training.hrsystem.service.exeption.education.WrongPostGraduateServiceException;
import by.training.hrsystem.service.parser.Parser;
import by.training.hrsystem.service.parser.exception.ParserException;
import by.training.hrsystem.service.validation.Validation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EducationServiceImpl implements EducationService {
	private static final Logger logger = LogManager.getLogger(EducationServiceImpl.class);

	@Override
	public void addEducation(String institution, String faculty, String department, String education, String course,
			String gradYear, String postgraduate, String idResume)
			throws
            ServiceException {
		logger.debug(
				"EducationServiceImpl.addEducation() : user's data is valid (institution = {}, faculty={}, department = {}, "
						+ "education = {}, course={}, gradYear={}, postGraduate={}, idResume={})",
				institution, faculty, department, education, course, gradYear, postgraduate, idResume);
		if (!Validation.validateSmallestMultyTextField(institution)) {
			throw new WrongInstitutionServiceException("Wrong institution");
		}
		if (!Validation.validateSmallestMultyTextField(faculty)) {
			throw new WrongFacultyServiceException("Wrong faculty");
		}
		if (!Validation.validateSmallestMultyTextField(department)) {
			throw new WrongDepartmentServiceException("Wrong department");
		}
		if (education == null) {
			throw new WrongEducationServiceException("Wrong education");
		}
		if (postgraduate == null) {
			throw new WrongPostGraduateServiceException("Wrong PostGraduate");
		}
		if (!Validation.validateShortDateField(gradYear)) {
			throw new WrongGradYearServiceException("wrong grad year");
		}

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			EducationDAO educationDAO = daoFactory.getEducationDAO();
			Education newEducation = new Education();
			newEducation.setInstitution(institution);
			newEducation.setFaculty(faculty);
			newEducation.setDepartment(department);
			newEducation.setEducation(Parser.fromStringToEducType(education));
			newEducation.setGradYear(Parser.parseStringtoInt(gradYear));
			newEducation.setPostGraduate(Parser.fromStringToPostGradType(postgraduate));
			newEducation.setIdResume(Parser.parseStringtoInt(idResume));
			educationDAO.add(newEducation);
		} catch (DAOException | ParserException e) {
			throw new ServiceException("Service layer: cannot create new education");
		}
	}

	@Override
	public void updateEducation(String institution, String faculty, String department, String education, String course,
			String gradYear, String postgraduate, String idEducation)
			throws
            ServiceException {
		logger.debug(
				"EducationServiceImpl.updateEducation() : user's data is valid (institution = {}, faculty={}, department = {}, "
						+ "education = {}, course={}, gradYear={}, postGraduate={}, idEducation={})",
				institution, faculty, department, education, course, gradYear, postgraduate, idEducation);
		if (!Validation.validateStringField(institution)) {
			throw new WrongInstitutionServiceException("Wrong institution");
		}
		if (!Validation.validateStringField(faculty)) {
			throw new WrongFacultyServiceException("Wrong faculty");
		}
		if (!Validation.validateStringField(department)) {
			throw new WrongDepartmentServiceException("Wrong department");
		}
		if (education == null) {
			throw new WrongEducationServiceException("Wrong education");
		}
		if (postgraduate == null) {
			throw new WrongPostGraduateServiceException("WrongPostGraduate");
		}

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			EducationDAO educationDAO = daoFactory.getEducationDAO();
			Education updateEducation = new Education();
			updateEducation.setInstitution(institution);
			updateEducation.setFaculty(faculty);
			updateEducation.setDepartment(department);
			updateEducation.setEducation(Parser.fromStringToEducType(education));
			updateEducation.setGradYear(Parser.parseStringtoInt(gradYear));
			updateEducation.setPostGraduate(Parser.fromStringToPostGradType(postgraduate));
			updateEducation.setIdEducation(Parser.parseStringtoInt(idEducation));
			educationDAO.update(updateEducation);
		} catch (DAOException | ParserException e) {
			throw new ServiceException("Service layer: cannot update education");
		}
	}

	@Override
	public void deleteEducation(String idEducation) throws ServiceException {
		logger.debug("EducationServiceImpl.deleteEducation() : user's data is valid (idEducation = {})", idEducation);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			EducationDAO educationDAO = daoFactory.getEducationDAO();
			educationDAO.delete(Parser.parseStringtoInt(idEducation));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can not delete education");
		}

	}

	@Override
	public List<Education> selectEducationbyIdResume(String idResume) throws ServiceException {
		logger.debug("EducationServiceImpl.selectEducationbyIdResume() : user's data is valid (idResume = {})",
				idResume);
		List<Education> listEducation = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			EducationDAO educationDAO = daoFactory.getEducationDAO();
			listEducation = educationDAO.getEducationByIdResume(Parser.parseStringtoInt(idResume));
		} catch (DAOException e) {
			throw new ServiceException("Service laye: can not show list of education");
		}
		return listEducation;
	}

}
