package by.training.hrsystem.dao;

import by.training.hrsystem.dao.entity.VacancyEntity;
import java.util.List;

/**
 * The {@code VacancyDAO} interface extends {@link CommonDao} and declares methods tailored
 * specifically for operations involving {@link VacancyEntity} objects.
 *
 * @author Uladzislau Hapeyenka
 */
public interface VacancyDao extends CommonDao<VacancyEntity> {

  /**
   * Method {@code selectCountVacancy} counts all the vacancies present in the system.
   *
   * @return the total count of all vacancies.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  int selectCountVacancy();

  /**
   * Method {@code selectCountActiveVacancy} counts all the active vacancies present in the system.
   *
   * @return the total count of active vacancies.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  int selectCountActiveVacancy();

  /**
   * Method {@code selectNormalVacancyById} retrieves a specific vacancy based on its ID.
   *
   * @param idVacancy the unique identifier of the vacancy.
   * @return the corresponding vacancy object or null if not found.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  VacancyEntity selectVacancyById(String idVacancy);

  /**
   * Method {@code selectAllActiveVacancy} retrieves a limited list of active vacancies.
   *
   * @param amount the maximum number of vacancies to return.
   * @return a list of active vacancies up to the specified amount.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  List<VacancyEntity> selectAllActiveVacancy(int amount);

  /**
   * Method {@code selectVacancyLike} searches for vacancies by their name.
   *
   * @param name the name or partial name of the vacancy.
   * @return a list of vacancies that match the given name.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  List<VacancyEntity> selectVacancyLike(String name);

  /**
   * Method {@code selectVacancyByHrEmail} retrieves vacancies associated with a specific human
   * resource email.
   *
   * @param hrEmail the email of the human resource.
   * @param amount the maximum number of vacancies to return.
   * @return a list of vacancies associated with the given HR email.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  List<VacancyEntity> selectVacancyByHrEmail(String hrEmail, int amount);

  /**
   * Method {@code selectCountVacancyByHrEmail} counts the number of vacancies associated with a
   * specific human resource email.
   *
   * @param idHr the email of the human resource.
   * @return the number of vacancies linked with the HR email.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  int selectCountVacancyByHrId(String idHr);

  /**
   * Method {@code activateVacancy} activates a specified vacancy and updates its activation date.
   *
   * @param idVacancy the unique identifier of the vacancy.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  void activateVacancy(String idVacancy);

  /**
   * Method {@code deactivateVacancy} deactivates a specified vacancy and marks it as "not hot".
   *
   * @param idVacancy the unique identifier of the vacancy.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  void deactivateVacancy(String idVacancy);

  /**
   * Method {@code hotVacancy} marks a specified vacancy as "hot".
   *
   * @param idVacancy the unique identifier of the vacancy.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  void hotVacancy(String idVacancy);

  /**
   * Method {@code selectAllHotVacancy} retrieves all vacancies marked as "hot".
   *
   * @return a list of all hot vacancies.
   * @throws org.hibernate.HibernateException in case of any database or connection pool issues.
   */
  List<VacancyEntity> selectAllHotVacancy();
}
