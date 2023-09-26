package by.training.hrsystem.service.impl;

import by.training.hrsystem.dao.ResumeDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.domain.Resume;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.service.ResumeService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.resume.WrongResumeNameServiceException;
import by.training.hrsystem.service.parser.Parser;
import by.training.hrsystem.service.parser.exception.ParserException;
import by.training.hrsystem.service.validation.Validation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResumeServiceImpl implements ResumeService {
  private static final Logger logger = LogManager.getLogger(ResumeServiceImpl.class);

  @Override
  public void addResume(String name, String military, String email) throws ServiceException {

    logger.debug(
        "ResumeServiceImpl.addResume() : user's data is valid (name = {}, military={}, email={})",
        name,
        military,
        email);

    if (!Validation.validateMultyTextField(name)) {
      throw new WrongResumeNameServiceException("wrong resume name");
    }

    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeDAO resumeDAO = daoFactory.getResumeDAO();
      Resume resume = new Resume();
      resume.setName(name);
      resume.setMilitatyType(Parser.fromStringToMilitaryType(military));
      User applicant = new User();
      applicant.setEmail(email);
      resume.setApplicant(applicant);

      resumeDAO.add(resume);

    } catch (DAOException | ParserException e) {
      throw new ServiceException("Service layer: cannot make a new resume", e);
    }
  }

  @Override
  public void updateResume(String name, String military, String idResume) throws ServiceException {

    logger.debug(
        "ResumeServiceImpl.updateResume() : user's data is valid (name = {}, military={}, idResume={})",
        name,
        military,
        idResume);

    if (!Validation.validateStringField(name)) {
      throw new WrongResumeNameServiceException("wrong resume name");
    }

    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeDAO resumeDAO = daoFactory.getResumeDAO();
      Resume resume = new Resume();

      resume.setName(name);
      resume.setMilitatyType(Parser.fromStringToMilitaryType(military));
      resume.setIdResume(Parser.parseStringtoInt(idResume));
      resumeDAO.update(resume);

    } catch (DAOException | ParserException e) {
      throw new ServiceException("Service layer: cannot update resume", e);
    }
  }

  @Override
  public void deleteResume(String idResume) throws ServiceException {
    logger.debug("ResumeServiceImpl.deleteResume() : idResume = {}", idResume);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeDAO resumeDAO = daoFactory.getResumeDAO();
      resumeDAO.delete(Parser.parseStringtoInt(idResume));
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not delete resume");
    }
  }

  @Override
  public List<Resume> selectResumeByEmail(String email, int first, int perPage)
      throws ServiceException {
    logger.debug(
        "ResumeServiceImpl.selectResumeByEmail() : email = {}, first={}, perpage={})",
        email,
        first,
        perPage);
    List<Resume> listResume = null;

    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeDAO resumeDAO = daoFactory.getResumeDAO();
      listResume = resumeDAO.selectResumeByApplicant(email, first, perPage);
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not select resume by email");
    }
    return listResume;
  }

  @Override
  public int countAllResume() throws ServiceException {
    int countResume;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeDAO resumeDAO = daoFactory.getResumeDAO();
      countResume = resumeDAO.selectCountResume();
    } catch (DAOException e) {
      throw new ServiceException("Service layer: cant show count resume");
    }
    logger.debug("ResumeServiceImpl.countAllResume() : count={}", countResume);
    return countResume;
  }

  @Override
  public int countVacancyByEmail(String hrEmail) throws ServiceException {
    int countResume;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeDAO resumeDAO = daoFactory.getResumeDAO();
      countResume = resumeDAO.selectCountResumeByEmail(hrEmail);
    } catch (DAOException e) {

      throw new ServiceException("Service layer: cant show count of vacancy");
    }
    logger.debug("ResumeServiceImpl.countResumeByEmail() : count={}", countResume);
    return countResume;
  }

  @Override
  public Resume selectResumeById(String idResume) throws ServiceException {
    logger.debug("ResumeServiceImpl.selectResumeById() : idResume = {}", idResume);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeDAO resumeDAO = daoFactory.getResumeDAO();
      Resume resume = resumeDAO.selectResumeById(Parser.parseStringtoInt(idResume));
      return resume;
    } catch (DAOException e) {
      throw new ServiceException("Service layer: cannot make a selectUserByEmail operation", e);
    }
  }

  @Override
  public List<Resume> selectResumeForVacancy(String applicantEmail) throws ServiceException {
    logger.debug("ResumeServiceImpl.selectResumeForVacancy() : email = {}", applicantEmail);
    List<Resume> listResume = null;

    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeDAO resumeDAO = daoFactory.getResumeDAO();
      listResume = resumeDAO.selectResumeForVacancy(applicantEmail);
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not select Resume for vacancy");
    }

    return listResume;
  }

  @Override
  public List<Resume> selectListResumeByVacancy(String idVacancy) throws ServiceException {
    logger.debug("ResumeServiceImpl.selectListResumeByVacancy() : idVacancy={}", idVacancy);
    List<Resume> listResume = null;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeDAO resumeDAO = daoFactory.getResumeDAO();
      listResume = resumeDAO.selectListResumeByVacancy(Parser.parseStringtoInt(idVacancy));
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not select list resume by id vacacncy");
    }

    return listResume;
  }

  @Override
  public List<Resume> selectLeftResume(String idVacancy, String applicantEmail)
      throws ServiceException {
    logger.debug(
        "ResumeServiceImpl.selectLeftResume() : idVacancy={}, applicantEmail={}",
        idVacancy,
        applicantEmail);
    List<Resume> listResume = null;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeDAO resumeDAO = daoFactory.getResumeDAO();
      listResume = resumeDAO.selectLeftResume(Parser.parseStringtoInt(idVacancy), applicantEmail);
    } catch (DAOException e) {
      throw new ServiceException("Service layer: cannot find left resume");
    }
    return listResume;
  }
}
