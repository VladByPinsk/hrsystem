package by.training.hrsystem.service.impl;

import by.training.hrsystem.dao.VacancyDao;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.domain.Vacancy;
import by.training.hrsystem.service.VacancyService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.vacancy.WrongConditionsServiceException;
import by.training.hrsystem.service.exeption.vacancy.WrongDescriptionServiceException;
import by.training.hrsystem.service.exeption.vacancy.WrongDutyServiceException;
import by.training.hrsystem.service.exeption.vacancy.WrongSalaryServiceException;
import by.training.hrsystem.service.exeption.vacancy.WrongVacancyNameServiceException;
import by.training.hrsystem.service.parser.Parser;
import by.training.hrsystem.service.parser.exception.ParserException;
import by.training.hrsystem.service.validation.Validation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VacancyServiceImpl implements VacancyService {
  private static final Logger logger = LogManager.getLogger(VacancyServiceImpl.class);

  @Override
  public void addVacancy(
      String vacancyName,
      String salary,
      String currency,
      String description,
      String duties,
      String conditions,
      String employmentType,
      String hrEmail)
      throws ServiceException {
    logger.debug(
        "VacancyServiceImpl.addVacancy() : user's data is valid (vacancyName = {}, salary={}, currency = {}, "
            + "description = {}, conditions={}, employmantType={}, hrEmail={})",
        vacancyName,
        salary,
        currency,
        description,
        conditions,
        employmentType,
        hrEmail);
    if (!Validation.validateMultyTextField(vacancyName)) {
      throw new WrongVacancyNameServiceException("wrong vacancy name");
    }
    if (!Validation.validateSalaryField(salary)) {
      throw new WrongSalaryServiceException("wrong salary");
    }
    if (!Validation.validateTextField(description)) {
      throw new WrongDescriptionServiceException("wrong description");
    }
    if (!Validation.validateTextField(duties)) {
      throw new WrongDutyServiceException("wrong duty");
    }
    if (!Validation.validateTextField(conditions)) {
      throw new WrongConditionsServiceException("wrong conditions");
    }

    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();

      Vacancy vacancy = new Vacancy();
      vacancy.setName(vacancyName);
      vacancy.setSalary(Parser.parseStringtoInt(salary));
      vacancy.setCurrency(Parser.fromStringToCurrencyType(currency));
      vacancy.setDescription(description);
      vacancy.setDuty(duties);
      vacancy.setCondition(conditions);
      vacancy.setEmploymentType(Parser.fromStringToEmplType(employmentType));
      vacancy.setHrEmail(hrEmail);
      vacancyDAO.add(vacancy);

    } catch (DAOException e) {
      throw new ServiceException("Service layer: cannot make a new registrarion", e);
    } catch (ParserException e) {
      throw new ServiceException("Service layer: can not parse date");
    }
  }

  @Override
  public void updateVacancy(
      String vacancyName,
      String salary,
      String currency,
      String description,
      String duties,
      String conditions,
      String employmentType,
      String idVacancy)
      throws ServiceException {
    logger.debug(
        "VacancyServiceImpl.addVacancy() : user's data is valid (vacancyName = {}, salary={}, currency = {}, "
            + "description = {}, conditions={}, employmantType={}, idVacancy={})",
        vacancyName,
        salary,
        currency,
        description,
        conditions,
        employmentType,
        idVacancy);
    if (!Validation.validateMultyTextField(vacancyName)) {
      throw new WrongVacancyNameServiceException("wrong vacancy name");
    }
    if (!Validation.validateSalaryField(salary)) {
      throw new WrongSalaryServiceException("wrong salary");
    }
    if (!Validation.validateTextField(description)) {
      throw new WrongDescriptionServiceException("wrong description");
    }
    if (!Validation.validateTextField(duties)) {
      throw new WrongDutyServiceException("wrong duty");
    }
    if (!Validation.validateTextField(conditions)) {
      throw new WrongConditionsServiceException("wrong conditions");
    }

    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();

      Vacancy vacancy = new Vacancy();
      vacancy.setName(vacancyName);
      vacancy.setSalary(Parser.parseStringtoInt(salary));
      vacancy.setCurrency(Parser.fromStringToCurrencyType(currency));
      vacancy.setDescription(description);
      vacancy.setDuty(duties);
      vacancy.setCondition(conditions);
      vacancy.setEmploymentType(Parser.fromStringToEmplType(employmentType));
      vacancy.setIdVacancy(Parser.parseStringtoInt(idVacancy));
      vacancyDAO.update(vacancy);

    } catch (DAOException e) {
      throw new ServiceException("Service layer: cannot make a new registrarion", e);
    } catch (ParserException e) {
      throw new ServiceException("Service layer: can not parse date");
    }
  }

  @Override
  public void deleteVacancy(String idVacancy) throws ServiceException {
    logger.debug("VacancyServiceImpl: deleteVacancy() : idVacancy = {}", idVacancy);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      vacancyDAO.delete(Parser.parseStringtoInt(idVacancy));
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not delete vacancy");
    }
  }

  @Override
  public List<Vacancy> selectAllVacancy(String lang) throws ServiceException {
    logger.debug("VacancyServiceImpl.selectAllVacancy() : user's data is valid (lang={}", lang);
    List<Vacancy> listVacancy = null;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();

      listVacancy = vacancyDAO.selectAllVacancy(lang);

    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not show list of vacancies");
    }
    return listVacancy;
  }

  @Override
  public List<Vacancy> selectAllActiveVacancy(String lang, int first, int perPage)
      throws ServiceException {
    logger.debug(
        "VacancyServiceImpl.selectAllActiveVacancy() : user's data is valid (lang={}, first={}, perPage={})",
        lang,
        first,
        perPage);
    List<Vacancy> listVacancy;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      listVacancy = vacancyDAO.selectAllActiveVacancy(lang, first, perPage);
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not show list of vacancies");
    }
    return listVacancy;
  }

  @Override
  public int countAllActiveVacancy() throws ServiceException {
    int countActiveVacancy = 0;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      countActiveVacancy = vacancyDAO.selectCountActiveVacancy();
    } catch (DAOException e) {

      throw new ServiceException("Service layer: cant show count of vacancy");
    }
    logger.debug("VacancyServiceImpl.countAllActiveVacancy() : count={}", countActiveVacancy);
    return countActiveVacancy;
  }

  @Override
  public List<Vacancy> selectVacancyByHrEmail(String hrEmail, String lang, int first, int perPage)
      throws ServiceException {
    logger.debug(
        "VacancyServiceImpl.selectVacancyByHrEmail() : hrEmail={} lang={}, first={}, perPage={}",
        hrEmail,
        lang,
        first,
        perPage);
    List<Vacancy> listVacancy;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      listVacancy = vacancyDAO.selectVacancyByHrEmail(hrEmail, lang, first, perPage);
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not show list of vacancies");
    }
    return listVacancy;
  }

  @Override
  public int countVacancyByHrEmail(String hrEmail) throws ServiceException {
    int countVacancy = 0;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      countVacancy = vacancyDAO.selectCountVacancyByHrEmail(hrEmail);
    } catch (DAOException e) {

      throw new ServiceException("Service layer: cant show count of vacancy");
    }
    logger.debug("VacancyServiceImpl.countVacancyByHrEmail() : count={}", countVacancy);
    return countVacancy;
  }

  @Override
  public void activateVacancy(String idVacancy) throws ServiceException {
    logger.debug("VacancyServiceImpl.activateVacancy() : idVacancy = {}", idVacancy);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      vacancyDAO.activateVacancy(Parser.parseStringtoInt(idVacancy));
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not activate vacancy");
    }
  }

  @Override
  public void deactivateVacancy(String idVacancy) throws ServiceException {
    logger.debug("VacancyServiceImpl.deactivateVacancy() : idVacancy = {}", idVacancy);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      vacancyDAO.deactivateVacancy(Parser.parseStringtoInt(idVacancy));
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not deactivate vacancy");
    }
  }

  @Override
  public void hotVacancy(String idVacancy) throws ServiceException {
    logger.debug("VacancyServiceImpl.hotVacancy() : idVacancy = {}", idVacancy);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      vacancyDAO.hotVacancy(Parser.parseStringtoInt(idVacancy));
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not deactivate vacancy");
    }
  }

  @Override
  public List<Vacancy> selectHotVacancy(String lang) throws ServiceException {
    logger.debug("VacancyServiceImpl.selectHotVacancy() : lang={}", lang);
    List<Vacancy> listVacancy;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      listVacancy = vacancyDAO.selectAllHotVacancy(lang);
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not show list of vacancies");
    }
    return listVacancy;
  }

  @Override
  public List<Vacancy> selectVacancyLike(String vacancyName) throws ServiceException {
    logger.debug("VacancyServiceImpl.selectVacancyLike() : vacancyName={}", vacancyName);
    List<Vacancy> listVacancy;
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      listVacancy = vacancyDAO.selectVacancyLike(vacancyName);
    } catch (DAOException e) {
      throw new ServiceException("Service layer: can not show list of vacancy");
    }
    return listVacancy;
  }

  @Override
  public void addTranslVacancy(
      String idVacancy,
      String lang,
      String vacancyName,
      String description,
      String duties,
      String conditions)
      throws ServiceException {
    logger.debug(
        "VacancyServiceImpl.addTranslVacancy : user's data is valid (Idvacancy={}, lang = {}, vacancyName = {},  "
            + "description = {}, duties={}, conditions={}, employmantType={}, hrEmail={})",
        idVacancy,
        lang,
        vacancyName,
        description,
        duties,
        conditions);
    if (!Validation.validateMultyTextField(vacancyName)) {
      throw new WrongVacancyNameServiceException("wrong vacancy name");
    }
    if (!Validation.validateTextField(description)) {
      throw new WrongDescriptionServiceException("wrong description");
    }
    if (!Validation.validateTextField(duties)) {
      throw new WrongDutyServiceException("wrong duty");
    }
    if (!Validation.validateTextField(conditions)) {
      throw new WrongConditionsServiceException("wrong conditions");
    }
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();

      Vacancy vacancy = new Vacancy();
      vacancy.setIdVacancy(Parser.parseStringtoInt(idVacancy));
      vacancy.setName(vacancyName);
      vacancy.setDescription(description);
      vacancy.setDuty(duties);
      vacancy.setCondition(conditions);
      vacancyDAO.addTranslateVacancy(vacancy, lang);

    } catch (DAOException e) {
      throw new ServiceException("Service layer: cannot add translation of vacancy", e);
    }
  }

  @Override
  public Vacancy selectVacancyById(String idVacancy, String lang) throws ServiceException {
    logger.debug(
        "VacancyServiceImpl.selectVacancyById() : idVacancy = {}, lang={}", idVacancy, lang);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      Vacancy vacancy = vacancyDAO.selectVacancyById(Parser.parseStringtoInt(idVacancy), lang);
      return vacancy;
    } catch (DAOException e) {
      throw new ServiceException("Service layer: cannot make a selectVacancyById operation", e);
    }
  }

  @Override
  public Vacancy selectNormalVacancyById(String idVacancy) throws ServiceException {
    logger.debug("VacancyServiceImpl.selectNormalVacancyById : idVacancy = {}", idVacancy);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      Vacancy vacancy = vacancyDAO.selectNormalVacancyById(Parser.parseStringtoInt(idVacancy));
      return vacancy;
    } catch (DAOException e) {
      throw new ServiceException(
          "Service layer: cannot make a selectNormalVacancyById operation", e);
    }
  }

  @Override
  public Vacancy selectTranslVacancyById(String idVacancy) throws ServiceException {
    logger.debug("VacancyServiceImpl.selectVacancyById() : idVacancy = {}", idVacancy);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      Vacancy vacancy = vacancyDAO.selectTranslVacancyById(Parser.parseStringtoInt(idVacancy));
      return vacancy;
    } catch (DAOException e) {
      throw new ServiceException(
          "Service layer: cannot make a selectTranslVacancyById operation", e);
    }
  }

  @Override
  public void updateTranslVacancy(
      String vacancyName,
      String description,
      String duties,
      String conditions,
      String idVacancy,
      String lang)
      throws ServiceException {
    logger.debug(
        "VacancyServiceImpl.updateTranslVacancy : user's data is valid (Idvacancy={}, lang = {}, vacancyName = {},  "
            + "description = {}, duties={}, conditions={}, employmantType={}, hrEmail={})",
        idVacancy,
        lang,
        vacancyName,
        description,
        duties,
        conditions);
    if (!Validation.validateMultyTextField(vacancyName)) {
      throw new WrongVacancyNameServiceException("wrong vacancy name");
    }
    if (!Validation.validateTextField(description)) {
      throw new WrongDescriptionServiceException("wrong description");
    }
    if (!Validation.validateTextField(duties)) {
      throw new WrongDutyServiceException("wrong duty");
    }
    if (!Validation.validateTextField(conditions)) {
      throw new WrongConditionsServiceException("wrong conditions");
    }
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();

      Vacancy vacancy = new Vacancy();
      vacancy.setName(vacancyName);
      vacancy.setDescription(description);
      vacancy.setDuty(duties);
      vacancy.setCondition(conditions);
      vacancy.setIdVacancy(Parser.parseStringtoInt(idVacancy));
      vacancyDAO.updateTranslateVacancy(vacancy, lang);

    } catch (DAOException e) {
      throw new ServiceException("Service layer: cannot update translation  of vacancy", e);
    }
  }

  @Override
  public boolean translExist(String idVacancy) throws ServiceException {
    logger.debug("VacancyServiceImpl.translExist() : idVacancy = {}", idVacancy);
    try {
      DAOFactory daoFactory = DAOFactory.getInstance();
      VacancyDao vacancyDAO = daoFactory.getVacancyDAO();
      return vacancyDAO.translExist(Parser.parseStringtoInt(idVacancy));
    } catch (DAOException e) {
      throw new ServiceException("Service layer: cannot make a selectUserByEmail operation", e);
    }
  }
}
