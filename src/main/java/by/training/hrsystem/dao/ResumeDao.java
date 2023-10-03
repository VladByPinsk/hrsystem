package by.training.hrsystem.dao;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.Resume;
import java.util.List;

/**
 * Interface {@code ResumeDAO} extends {@link CommonDao} and declare method than appropriate just
 * for {@link by.training.hrsystem.domain.Resume Resume} objects
 *
 * @author Vladislav
 * @see CommonDao
 * @see by.training.hrsystem.domain.Resume
 */
public interface ResumeDao extends CommonDao<Resume> {
  /**
   * Method {@code selectCountResume} allow to find count of all resume.
   *
   * @return count of resume
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding count of resume.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Resume
   */
  int selectCountResume() throws DAOException;

  /**
   * Method {@code activateResume} allow to activate resume.
   *
   * @param resume object that will be update to active state
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while update resume to active state.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Resume
   */
  void activateResume(Resume resume) throws DAOException;

  /**
   * Method {@code selectResumeById} allow to find resume by idResume.
   *
   * @param idResume primary key of resume,it will use to find resume object from database
   * @return object of {@link by.training.hrsystem.domain.Resume Resume} class if in database exist
   *     resume with given id;otherwise returns {@link
   *     by.training.hrsystem.dao.exception.DAODataDoesNotExistException
   *     DAODataDoesNotExistException}
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while update resume to active state.
   * @throws DAODataDoesNotExistException if in database not exist resume with given id.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Resume
   */
  Resume selectResumeById(int idResume) throws DAOException;

  /**
   * Method {@code selectResumeByApplicant} allow to find limit resume by applicant email.
   *
   * @param applicantEmail email of applicant
   * @param pageNum start field
   * @param amountPerPage how many field i need
   * @return limit list of resume
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding limit list of resume.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Resume
   */
  List<Resume> selectResumeByApplicant(String applicantEmail, int pageNum, int amountPerPage)
      throws DAOException;

  /**
   * Method {@code selectResumeForVacancy} allow to find all resume by applicant to add to vacancy.
   *
   * @param applicantEmail email of applicant
   * @return list of all resume
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding all resume.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Resume
   */
  List<Resume> selectResumeForVacancy(String applicantEmail) throws DAOException;

  /**
   * Method {@code selectListResumeByVacancy} allow to find all resume by idVacancy
   *
   * @param idVacancy key of entity,it will use to find list of resume objects from database.
   * @return list of resume objects
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding all resume by id vacancy.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Resume
   */
  List<Resume> selectListResumeByVacancy(int idVacancy) throws DAOException;

  /**
   * Method {@code selectCountResumeByEmail} allow to find count of resume that have applicant.
   *
   * @param applicantEmail email of applicant
   * @return count of resume that have applicant
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding count of resume.
   */
  int selectCountResumeByEmail(String applicantEmail) throws DAOException;

  List<Resume> selectLeftResume(int idVacancy, String applicantEmail) throws DAOException;
}
