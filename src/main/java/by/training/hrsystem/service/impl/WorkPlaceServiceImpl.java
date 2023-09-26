package by.training.hrsystem.service.impl;

import by.training.hrsystem.dao.WorkPlaceDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.domain.WorkPlace;
import by.training.hrsystem.service.WorkPlaceService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.workplace.ListWorkPlaceIsEmptyServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongCompanyNameServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateBeginServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateEndServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongPositionServiceException;
import by.training.hrsystem.service.parser.Parser;
import by.training.hrsystem.service.parser.exception.ParserException;
import by.training.hrsystem.service.validation.Validation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorkPlaceServiceImpl implements WorkPlaceService {
	private static final Logger logger = LogManager.getLogger(WorkPlaceServiceImpl.class);

	@Override
	public void addWorkplace(String companyName, String position, String dateBegin, String dateEnd, String idResume)
			throws
            ServiceException {
		logger.debug(
				"WorkPlaceServiceImpl.addWorkPlace() : user's data is valid (companyName = {}, position={}, dateBigin = {}, "
						+ " dateEnd = {}, idResume={})",
				companyName, position, dateBegin, dateEnd, idResume);

		if (!Validation.validateStringField(companyName)) {
			throw new WrongCompanyNameServiceException("Wrong companyName");
		}
		if (!Validation.validateStringField(position)) {
			throw new WrongPositionServiceException("Wrong position");
		}
		if (!Validation.validateFullDateField(dateBegin)) {
			throw new WrongDateBeginServiceException("Wrong dateBegin");
		}
		if (!Validation.validateFullDateField(dateEnd)) {
			throw new WrongDateEndServiceException("Wrong dateEnd");
		}
		try {
			if (!Validation.validateDate(Parser.parseToFullDate(dateBegin), Parser.parseToFullDate(dateEnd))) {
				throw new WrongDateServiceException("dateBegin must be < then dateEnd");
			}
		} catch (ParserException e1) {
			throw new ServiceException("Service layer: can not parse");
		}

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			WorkPlaceDAO workPlaceDAO = daoFactory.getWorkPlaceDAO();
			WorkPlace workPlace = new WorkPlace();
			workPlace.setCompanyName(companyName);
			workPlace.setPosition(position);
			workPlace.setDateBegin(Parser.parseToFullDate(dateBegin));
			workPlace.setDateEnd(Parser.parseToFullDate(dateEnd));
			workPlace.setIdResume(Parser.parseStringtoInt(idResume));

			workPlaceDAO.add(workPlace);

		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a new worplace", e);
		} catch (ParserException e) {
			throw new ServiceException("Service layer: can not parse");
		}
	}

	@Override
	public void updateWorkplace(String companyName, String position, String dateBegin, String dateEnd,
			String idWorkPlace) throws
            ServiceException {
		logger.debug(
				"WorkPlaceServiceImpl.updateWorkPlace() : user's data is valid (companyName = {}, position={}, dateBigin = {}, "
						+ " dateEnd = {}, idWorkPlace={})",
				companyName, position, dateBegin, dateEnd, idWorkPlace);

		if (!Validation.validateStringField(companyName)) {
			throw new WrongCompanyNameServiceException("Wrong companyName");
		}
		if (!Validation.validateStringField(position)) {
			throw new WrongPositionServiceException("Wrong position");
		}
		if (!Validation.validateFullDateField(dateBegin)) {
			throw new WrongDateBeginServiceException("Wrong dateBegin");
		}
		if (!Validation.validateFullDateField(dateEnd)) {
			throw new WrongDateEndServiceException("Wrong dateEnd");
		}
		try {
			if (!Validation.validateDate(Parser.parseToFullDate(dateBegin), Parser.parseToFullDate(dateEnd))) {
				throw new WrongDateServiceException("dateBegin must be < then dateEnd");
			}
		} catch (ParserException e1) {
			throw new ServiceException("Service layer: can not parse");
		}

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			WorkPlaceDAO workPlaceDAO = daoFactory.getWorkPlaceDAO();
			WorkPlace workPlace = new WorkPlace();
			workPlace.setCompanyName(companyName);
			workPlace.setPosition(position);
			workPlace.setDateBegin(Parser.parseToFullDate(dateBegin));
			workPlace.setDateEnd(Parser.parseToFullDate(dateEnd));
			workPlace.setIdWorkPlace(Parser.parseStringtoInt(idWorkPlace));

			workPlaceDAO.update(workPlace);

		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a new worplace", e);
		} catch (ParserException e) {
			throw new ServiceException("Service layer: can not parse");
		}

	}

	@Override
	public void deleteWorkplace(String idWorPlace) throws ServiceException {
		logger.debug("WorkPlaceServiceImpl: deleteWorkPlace() : user's data is valid (idWorkPlace={})", idWorPlace);

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			WorkPlaceDAO workPlaceDAO = daoFactory.getWorkPlaceDAO();
			workPlaceDAO.delete(Parser.parseStringtoInt(idWorPlace));

		} catch (DAOException e) {
			throw new ServiceException("Service layer: can not delete workPlace");
		}

	}

	@Override
	public List<WorkPlace> selectWorkPlaceByIdResume(String idResume)
			throws ServiceException {
		logger.debug("WorkPlaceServiceImpl.selectWorkPlaceByIdResume() : user's data is valid (idResume={})", idResume);

		List<WorkPlace> listWorkPlace = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			WorkPlaceDAO workPlaceDAO = daoFactory.getWorkPlaceDAO();

			listWorkPlace = workPlaceDAO.getWorkPlaceByIdResume(Parser.parseStringtoInt(idResume));

		} catch (DAOException e) {
			throw new ServiceException("Service laye: can not show list of workPlace");
		}
		return listWorkPlace;
	}

}
