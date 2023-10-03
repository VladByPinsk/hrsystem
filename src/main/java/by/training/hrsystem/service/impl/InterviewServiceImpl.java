package by.training.hrsystem.service.impl;

import by.training.hrsystem.dao.InterviewDao;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.domain.Interview;
import by.training.hrsystem.domain.Verify;
import by.training.hrsystem.service.InterviewService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.interview.WrongDateInterviewServiceException;
import by.training.hrsystem.service.parser.Parser;
import by.training.hrsystem.service.parser.exception.ParserException;
import by.training.hrsystem.service.validation.Validation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InterviewServiceImpl implements InterviewService {
	private static final Logger logger = LogManager.getLogger(InterviewServiceImpl.class);

	@Override
	public void addInterview(String interivewType, String dateInterview, String idVerify)
			throws ServiceException {
		logger.debug(
				"InterviewServiceImpl.addInterview() : interview's data is valid (interivewType = {}, dateInterview = {}, "
						+ "idVerify = {})",
				interivewType, dateInterview, idVerify);

		if (!Validation.validateDateTime(dateInterview)) {
			throw new WrongDateInterviewServiceException("wrong dateInterview");
		}

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			InterviewDao interviewDAO = daoFactory.getInterviewDAO();
			Interview interview = new Interview();
			interview.setInterviewType(Parser.fromStringToInterviewType(interivewType));
			interview.setDateBegin(Parser.parseToDateTime(dateInterview));
			Verify verify = new Verify();
			verify.setIdVerify(Parser.parseStringtoInt(idVerify));
			interview.setVerify(verify);
			interviewDAO.add(interview);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot add new interview", e);
		} catch (ParserException e) {
			throw new ServiceException("Service layer: can not parse date");
		}

	}

	@Override
	public List<Interview> selectInterviewByVerify(String idVerify) throws ServiceException {
		logger.debug("InterviewServiceImpl.selectInterviewByVerify() : idVerify={}", idVerify);
		List<Interview> listInterview = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			InterviewDao interviewDAO = daoFactory.getInterviewDAO();
			listInterview = interviewDAO.selectInterviewByIdVerify(Parser.parseStringtoInt(idVerify));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can not show list of interview");
		}
		return listInterview;
	}

	@Override
	public void deleteInterview(String idInterview) throws ServiceException {
		logger.debug("InterviewServiceImpl.deleteInterview : idInterview={}", idInterview);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			InterviewDao interviewDAO = daoFactory.getInterviewDAO();
			interviewDAO.delete(Parser.parseStringtoInt(idInterview));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can not delete interview");
		}

	}

}
