package by.training.hrsystem.service.impl;

import by.training.hrsystem.dao.VerifyDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.domain.Resume;
import by.training.hrsystem.domain.Vacancy;
import by.training.hrsystem.domain.Verify;
import by.training.hrsystem.service.VerifyService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.parser.Parser;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VerifyServiceImpl implements VerifyService {

	private static final Logger logger = LogManager.getLogger(VerifyServiceImpl.class);

	@Override
	public void addResumeToVacancy(String idVacancy, String idResume) throws ServiceException {
		logger.debug("VerifyServiceImpl.addResumeToVacancy() : idVacancy = {}, idResume={}", idVacancy, idResume);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			VerifyDAO verifyDAO = daoFactory.getVerifyDAO();
			Verify verify = new Verify();
			Vacancy vacancy = new Vacancy();
			Resume resume = new Resume();
			resume.setIdResume(Parser.parseStringtoInt(idResume));
			vacancy.setIdVacancy(Parser.parseStringtoInt(idVacancy));
			verify.setResume(resume);
			verify.setVacancy(vacancy);
			verifyDAO.addResumeToVacancy(verify);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can not add resume to vacancy");
		}

	}

	@Override
	public List<Verify> verifyList(String idVacancy) throws ServiceException {
		logger.debug("VerifyServiceImpl.verifyList() : idVacancy = {}", idVacancy);
		List<Verify> listVerify = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			VerifyDAO verifyDAO = daoFactory.getVerifyDAO();
			listVerify = verifyDAO.verifyList(Parser.parseStringtoInt(idVacancy));
		} catch (DAOException e) {
			throw new ServiceException("Service laye: can not show list of verfiy");
		}
		return listVerify;
	}

	@Override
	public List<Verify> passVerifyList(String idVacancy) throws ServiceException {
		logger.debug("VerifyServiceImpl.passVerifyList() : idVacancy = {}", idVacancy);
		List<Verify> listVerify = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			VerifyDAO verifyDAO = daoFactory.getVerifyDAO();
			listVerify = verifyDAO.passVerifyList(Parser.parseStringtoInt(idVacancy));
		} catch (DAOException e) {
			throw new ServiceException("Service laye: can not show list of pass verify");
		}
		return listVerify;
	}

	@Override
	public Verify selectVerifyById(String idVerify) throws ServiceException {
		logger.debug("VerifyServiceImpl.selectVerifyById() : idVerify = {}", idVerify);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			VerifyDAO verifyDAO = daoFactory.getVerifyDAO();
			Verify verify = verifyDAO.getVerifyById(Parser.parseStringtoInt(idVerify));
			return verify;
		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a selectVerifyById operation", e);
		}
	}

	@Override
	public void verifyResumePass(String idVerify) throws ServiceException {
		logger.debug("VerifyServiceImpl.verifyResumePass : idVerify = {}", idVerify);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			VerifyDAO verifyDAO = daoFactory.getVerifyDAO();
			verifyDAO.verifyResumePass(Parser.parseStringtoInt(idVerify));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a verifyResumePass operation", e);
		}

	}

	@Override
	public void verifyResumeNotPass(String idVerify) throws ServiceException {
		logger.debug("VerifyServiceImpl.verifyResumeNotPass : idVerify = {}", idVerify);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			VerifyDAO verifyDAO = daoFactory.getVerifyDAO();
			verifyDAO.verifyResumeNotPass(Parser.parseStringtoInt(idVerify));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a verifyResumeNotPass operation", e);
		}

	}
}
