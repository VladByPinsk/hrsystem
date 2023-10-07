package by.training.hrsystem.dao;

import by.training.hrsystem.dao.entity.UserEntity;

/**
 * The {@code UserDAO} interface extends {@link CommonDao} and provides methods specifically
 * tailored for operations on {@link UserEntity} objects.
 *
 * @author Uladzislau Hapeyenka
 */
public interface UserDao extends CommonDao<UserEntity> {
  /**
   * Method {@code userAuthentication} authenticates a user based on the provided login credentials.
   *
   * @param login the login of the user.
   * @param password the password associated with the provided login.
   * @return an instance of {@link UserEntity} if authentication is successful; otherwise, null.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the authentication process.
   */
  UserEntity userAuthentication(String login, String password);

  /**
   * Method {@code userAuthentication} checks if a user with the provided credentials exists in the
   * system.
   *
   * @param login the user's login.
   * @param password the password associated with the provided login.
   * @return {@code true} if a user with the provided credentials exists; {@code false} otherwise.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the check.
   */
  boolean userExist(String login, String password);

  /**
   * Method {@code getUserByEmail} retrieves a user based on their email address.
   *
   * @param email the email of the user to retrieve.
   * @return an instance of {@link UserEntity} if a user with the given email exists; otherwise,
   *     null.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the retrieval process.
   */
  UserEntity getUserByEmail(String email);

  /**
   * Method {@code getUserByIdVacancy} retrieves a user associated with a specific vacancy,
   * identified by its unique ID.
   *
   * @param idVacancy the unique identifier of the vacancy.
   * @return an instance of {@link UserEntity} if a user associated with the given vacancy ID
   *     exists; otherwise, null.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the retrieval process.
   */
  UserEntity getUserByIdVacancy(String idVacancy);

  /**
   * Method {@code getUserByIdResume} retrieves a user associated with a specific resume, identified
   * by its unique ID.
   *
   * @param idResume the unique identifier of the resume.
   * @return an instance of {@link UserEntity} if a user associated with the given resume ID exists;
   *     otherwise, null.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the retrieval process.
   */
  UserEntity getUserByIdResume(String idResume);

  /**
   * Method {@code countAllApplicants} calculates the total number of applicants in the system.
   *
   * @return the total number of applicants.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the calculation.
   */
  int countAllApplicants();
}
