package by.training.hrsystem.service;

import java.util.List;

import by.training.hrsystem.domain.Vacancy;
import by.training.hrsystem.service.exeption.ServiceException;

public interface VacancyService {

  void addVacancy(
      String vacancyName,
      String slary,
      String currency,
      String description,
      String duties,
      String conditions,
      String employmentType,
      String hrEmail)
      throws ServiceException;

  void updateVacancy(
      String vacancyName,
      String slary,
      String currency,
      String description,
      String duties,
      String conditions,
      String employmentType,
      String idVacancy)
      throws ServiceException;

  void deleteVacancy(String idVacancy) throws ServiceException;

  void addTranslVacancy(
      String idVacancy,
      String lang,
      String vacancyName,
      String description,
      String duties,
      String conditions)
      throws ServiceException;

  void updateTranslVacancy(
      String vacancyName,
      String description,
      String duties,
      String conditions,
      String idVacancy,
      String lang)
      throws ServiceException;

  List<Vacancy> selectAllVacancy(String lang) throws ServiceException;

  List<Vacancy> selectAllActiveVacancy(String lang, int first, int perPage) throws ServiceException;

  int countAllActiveVacancy() throws ServiceException;

  Vacancy selectVacancyById(String idVacancy, String lang) throws ServiceException;

  Vacancy selectNormalVacancyById(String idVacancy) throws ServiceException;

  Vacancy selectTranslVacancyById(String idVacancy) throws ServiceException;

  List<Vacancy> selectVacancyByHrEmail(String hrEmail, String lang, int first, int perPage)
      throws ServiceException;

  List<Vacancy> selectVacancyLike(String vacancyName) throws ServiceException;

  int countVacancyByHrEmail(String hrEmail) throws ServiceException;

  void activateVacancy(String idVacancy) throws ServiceException;

  void deactivateVacancy(String idVacancy) throws ServiceException;

  void hotVacancy(String idVacancy) throws ServiceException;

<<<<<<< Updated upstream
	public boolean translExist(String idVacancy) throws ServiceException;

	List<Vacancy> selectHotVacancy(String lang) throws ServiceException;
=======
  boolean translExist(String idVacancy) throws ServiceException;
>>>>>>> Stashed changes

  List<Vacancy> selectHotVacancy(String lang) throws ServiceException;
}
