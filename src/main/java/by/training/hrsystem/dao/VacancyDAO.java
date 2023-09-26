package by.training.hrsystem.dao;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.Vacancy;
import java.util.List;

/**
 * Interface {@code VacancyDAO} extends {@link AbstractDAO} and declare method that appropriate just
 * for {@link by.training.hrsystem.domain.Vacancy Vacancy} objects.
 *
 * @author Vladislav
 * @see AbstractDAO
 * @see by.training.hrsystem.domain.Vacancy Vacancy
 */
public interface VacancyDAO extends AbstractDAO<Vacancy> {
  /**
   * Method {@code addTranslateVacancy} allow to create translation of object vacancy.
   *
   * @param vacancy object that will be created.
   * @param lang language of translation.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while creating translation.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  void addTranslateVacancy(Vacancy vacancy, String lang) throws DAOException;

  /**
   * Method {@code updateTranslateVacancy} allow to update translation of object vacancy.
   *
   * @param vacancy object that will be update.
   * @param lang language of translation.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while updating translation.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  void updateTranslateVacancy(Vacancy vacancy, String lang) throws DAOException;

  /**
   * Method {@code selectCountVacancy} allow to find count of all vacancies.
   *
   * @return count of all vacancies.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding count of vacancies.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  int selectCountVacancy() throws DAOException;

  /**
   * Method {@code selectCountActiveVacancy} allow to find count of all active vacancies.
   *
   * @return count of all active vacancies.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding count of all active vacancies.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  int selectCountActiveVacancy() throws DAOException;

  /**
   * Method {@code selectVacancyById} allow to find vacancy object by id depending on the selected
   * language.
   *
   * @param idVacancy key of entity,it will use to find vacancy object from database.
   * @param lang the language in which the vacancy is displayed.
   * @return vacancy object depending on the selected language.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding vacancy object by id.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  Vacancy selectVacancyById(int idVacancy, String lang) throws DAOException;

  /**
   * Method {@code selectTranslVacancyById} allow to find translation of vacancy object by id.
   *
   * @param idVacancy key of entity,it will use to find translation of vacancy object from database.
   * @return translation of vacancy object.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding translation of vacancy object by id.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  Vacancy selectTranslVacancyById(int idVacancy) throws DAOException;

  /**
   * Method {@code selectNormalVacancyById} allow to find vacancy object by id.
   *
   * @param idVacancy key of entity,it will use to find vacancy object from database.
   * @return vacancy object.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding vacancy object by id.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  Vacancy selectNormalVacancyById(int idVacancy) throws DAOException;

  /**
   * Method {@code translVacancyExist} lets you know if there is a translation of the vacancy.
   *
   * @param idVacancy key of entity,it will use to find translation of vacancy object from database.
   * @return true if translation exist and false if not.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding vacancy object by id.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  boolean translExist(int idVacancy) throws DAOException;

  /**
   * Method {@code selectAllVacancy} allow to find list of all vacancies objects depending on the
   * selected language.
   *
   * @param lang the language in which the list of all vacancies objects are displayed.
   * @return list of all vacancies objects depending on the selected language.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding list of all vacancies objects.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  List<Vacancy> selectAllVacancy(String lang) throws DAOException;

  /**
   * Method {@code selectAllActiveVacancy} allow to find limit list of active vacancies objects
   * depending on the selected language.
   *
   * @param lang the language in which the list of all vacancies objects are displayed.
   * @param pageNum start field.
   * @param amountPerPage how many fields I need.
   * @return limit list of active vacancies objects depending on the selected language..
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding limit list of vacancies.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  List<Vacancy> selectAllActiveVacancy(String lang, int pageNum, int amountPerPage)
      throws DAOException;

  /**
   * Method {@code selectVacancyLike} allow to find list of vacancies objects by name.
   *
   * @param name name of vacancy.
   * @return list of vacancies objects with given name.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding list of vacancies objects.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  List<Vacancy> selectVacancyLike(String name) throws DAOException;

  /**
   * Method {@code selectVacancyByHrEmail} allow to find limit list of vacancies by human resource
   * email.
   *
   * @param hrEmail human resource email.
   * @param pageNum start field.
   * @param amountPerPage how many fields I need.
   * @return limit list of vacancies by human resource email.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding limit list of resume.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  List<Vacancy> selectVacancyByHrEmail(String hrEmail, String lang, int pageNum, int amountPerPage)
      throws DAOException;

  /**
   * Method {@code selectCountVacancyByHrEmail} allow to find count of all vacancies belonging to
   * given human resource.
   *
   * @param hrEmail human resource email.
   * @return count of all vacancies belonging to given human resource.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding count of all vacancies belonging to given human resource.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  int selectCountVacancyByHrEmail(String hrEmail) throws DAOException;

  /**
   * Method {@code activateVacancy} allow to set status of vacancy as "active" and add the date of
   * activation.
   *
   * @param idVacancy key of entity,it will use to find vacancy object from database.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding vacancy object.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  void activateVacancy(int idVacancy) throws DAOException;

  /**
   * Method {@code deactivateVacancy} allow to set status of vacancy as "not hot" and "not active".
   *
   * @param idVacancy key of entity,it will use to find vacancy object from database.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding vacancy object.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  void deactivateVacancy(int idVacancy) throws DAOException;

  /**
   * Method {@code hotVacancy} allow to set status of vacancy as "hot".
   *
   * @param idVacancy key of entity,it will use to find vacancy object from database.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding vacancy object.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  void hotVacancy(int idVacancy) throws DAOException;

  /**
   * Method {@code selectAllHotVacancy} allow to find all hot vacancies objects depending on the
   * selected language.
   *
   * @param lang the language in which list of hot vacancies objects are displayed.
   * @return list of hot vacancies objects depending on the selected language.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding list of hot vacancies objects.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Vacancy
   */
  List<Vacancy> selectAllHotVacancy(String lang) throws DAOException;
}
