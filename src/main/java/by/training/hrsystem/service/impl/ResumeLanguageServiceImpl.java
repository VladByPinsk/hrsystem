package by.training.hrsystem.service.impl;

import by.training.hrsystem.dao.ResumeLanguageDao;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.domain.ResumeLanguage;
import by.training.hrsystem.service.ResumeLanguageService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.languagelevel.LanguageLevelServiceException;
import by.training.hrsystem.service.exeption.languagelevel.LanguageNameServiceException;
import by.training.hrsystem.service.parser.Parser;
import by.training.hrsystem.service.parser.exception.ParserException;
import by.training.hrsystem.service.validation.Validation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResumeLanguageServiceImpl implements ResumeLanguageService {
  private static final Logger logger = LogManager.getLogger(ResumeLanguageServiceImpl.class);

  @Override
  public void addLanguage(String name, String skillLevel, String idResume) throws ServiceException {
    logger.debug(
        "ResumeLanguageImpl.addLanguage() : user's data is valid (name = {}, skillLevel={}, idResume={})",
        name,
        skillLevel,
        idResume);

    if (!Validation.validateStringField(name)) {
      throw new LanguageNameServiceException("wrong language");
    }
    if (skillLevel == null) {
      throw new LanguageLevelServiceException("wrong level");
    }

    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeLanguageDao resumeLanguageDao = daoFactory.getResumeLanguageDAO();

      ResumeLanguage language = new ResumeLanguage();
      language.setName(name);
      language.setRaiting(Parser.fromStringToLanguageLevel(skillLevel));
      language.setIdResume(Parser.parseStringtoInt(idResume));

      resumeLanguageDao.add(language);

    } catch (DAOException | ParserException e) {
      throw new ServiceException("Service layer: cannot make a new resumeLanguage", e);
    }
  }

  @Override
  public void updateLanguage(String name, String skillLevel, String idLanguage)
      throws ServiceException {
    logger.debug(
        "ResumeLanguageImpl.updateLanguage() : user's data is valid (name = {}, skillLevel={}, idLanguage={})",
        name,
        skillLevel,
        idLanguage);
    if (!Validation.validateStringField(name)) {
      throw new LanguageNameServiceException("wrong language");
    }
    if (skillLevel == null) {
      throw new LanguageLevelServiceException("wrong level");
    }

    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeLanguageDao resumeLanguageDao = daoFactory.getResumeLanguageDAO();

      ResumeLanguage language = new ResumeLanguage();
      language.setName(name);
      language.setRaiting(Parser.fromStringToLanguageLevel(skillLevel));
      language.setIdLanguage(Parser.parseStringtoInt(idLanguage));

      resumeLanguageDao.update(language);

    } catch (DAOException | ParserException e) {
      throw new ServiceException("Service layer: cannot update ResumeLanguage", e);
    }
  }

  @Override
  public void deleteLanguage(String idLanguage) throws ServiceException {
    logger.debug("ResumeLanguageImpl.deleteLanguage() : idLanguage={}", idLanguage);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeLanguageDao resumeLanguageDao = daoFactory.getResumeLanguageDAO();
      resumeLanguageDao.delete(Parser.parseStringtoInt(idLanguage));
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not delete resumeLanguage");
    }
  }

  @Override
  public List<ResumeLanguage> selectLanguageByIdResume(String idResume) throws ServiceException {
    logger.debug("ResumeLanguageImpl.selectLanguageByIdResume() : idResume={}", idResume);
    List<ResumeLanguage> listResumeLanguage = null;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      ResumeLanguageDao resumeLanguageDAO = daoFactory.getResumeLanguageDAO();

      listResumeLanguage =
          resumeLanguageDAO.getResumeLangByIdResume(Parser.parseStringtoInt(idResume));

    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not show list of resume languages");
    }
    return listResumeLanguage;
  }
}
