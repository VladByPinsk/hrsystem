package by.training.hrsystem.dao;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.User;

/**
 * Interface {@code UserDAO} extends {@link CommonDao} and declare method than appropriate just
 * for {@link by.training.hrsystem.domain.User User} objects.
 *
 * @author Vladislav
 * @see CommonDao
 * @see by.training.hrsystem.domain.User User
 */
public interface UserDao extends CommonDao<User> {
  /**
   * Method {@code userAuthentication} allow login in the human resource system
   *
   * @param email email of user
   * @param password password of user
   * @return user if that user exist.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while login in the system.
   * @see CommonDao
   * @see by.training.hrsystem.domain.User User
   */
  User userAuthentication(String email, String password) throws DAOException;

  /**
   * Method {@code userAuthentication} allow to know that user exist or not
   *
   * @param email user email
   * @param password user password
   * @return true if user exist or false if user not exist
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while login in the system.
   * @see CommonDao
   * @see by.training.hrsystem.domain.User User
   */
  boolean userExist(String email, String password) throws DAOException;

  /**
   * Method {@code getUserByEmail} allow to find user by email.
   *
   * @param email user email
   * @return user object if user exist; else return null;
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding user by email.
   * @see CommonDao
   * @see by.training.hrsystem.domain.User User
   */
  User getUserByEmail(String email) throws DAOException;

  /**
   * Method {@code getUserByIdVacancy} allow to find user by id vacancy.
   *
   * @param idVcancy id vacancy
   * @return user object if user exist; else return null;
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding user by id vacancy.
   * @see CommonDao
   * @see by.training.hrsystem.domain.User User
   */
  User getUserByIdVacancy(int idVcancy) throws DAOException;

  /**
   * Method {@code getUserByIdResume} allow to find user by id resume.
   *
   * @param idResume key of entity,it will use to find user object from database
   * @return user user object if user exist; else return null;
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding user by id resume.
   * @see CommonDao
   * @see by.training.hrsystem.domain.User User
   */
  User getUserByIdResume(int idResume) throws DAOException;

  /**
   * Method {@code countAllApplicants} allow to find count of all applicants.
   *
   * @return count of applicants.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding count of applicants.
   * @see CommonDao
   * @see by.training.hrsystem.domain.User User
   */
  int countAllApplicants() throws DAOException;

  /**
   * Method {@code deleteUser} allow to delete user.
   *
   * @param email user email
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding count of applicants.
   * @see CommonDao
   * @see by.training.hrsystem.domain.User User
   */
  void deleteUser(String email) throws DAOException;
}
