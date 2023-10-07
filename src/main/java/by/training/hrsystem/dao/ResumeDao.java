package by.training.hrsystem.dao;

import by.training.hrsystem.dao.entity.ResumeEntity;
import by.training.hrsystem.domain.Resume;
import java.util.List;

/**
 * The {@code ResumeDAO} interface provides methods specifically tailored for operations on {@link
 * ResumeEntity} objects.
 *
 * @author Uladzislau Hapeyenka
 */
public interface ResumeDao extends CommonDao<ResumeEntity> {
  /**
   * Retrieves the total count of all resumes present in the database.
   *
   * @return the total number of resumes.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the retrieval process.
   */
  int selectCountResume();

  /**
   * Method {@code activateResume} activates the resume corresponding to the provided id.
   *
   * @param idResume the unique identifier of the resume to be activated.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the update operation.
   */
  void activateResume(String idResume);

  /**
   * Method {@code selectResumeById} retrieves a resume based on its unique identifier.
   *
   * @param idResume the unique identifier of the resume.
   * @return the {@link ResumeEntity} object corresponding to the provided id, or null if not found.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the retrieval process.
   */
  ResumeEntity selectResumeById(String idResume);

  /**
   * Method {@code selectResumeByApplicant} retrieves a limited list of resumes associated with a
   * specific applicant.
   *
   * @param applicantId the unique identifier of the applicant.
   * @param amount the number of resumes to retrieve.
   * @return a limited list of resumes.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database
   */
  List<ResumeEntity> selectResumeByApplicant(String applicantId, int amount);

  /**
   * Method {@code selectResumeForVacancy} retrieves all resumes associated with a specific
   * applicant that can be added to a vacancy.
   *
   * @param applicantId the unique identifier of the applicant.
   * @return a list of all resumes corresponding to the provided applicant id.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the retrieval process.
   */
  List<ResumeEntity> selectResumeForVacancy(String applicantId);

  /**
   * Method {@code selectListResumeByVacancy} retrieves all resumes associated with a specific
   * vacancy based on its unique identifier.
   *
   * @param idVacancy the unique identifier of the vacancy.
   * @return a list of resumes corresponding to the provided vacancy id.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the retrieval process.
   */
  List<ResumeEntity> selectListResumeByVacancy(String idVacancy);

  /**
   * Method {@code selectCountResumeByEmail} retrieves the count of resumes associated with a
   * specific applicant.
   *
   * @param applicantId the unique identifier of the applicant.
   * @return the number of resumes linked to the provided applicant.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the retrieval process.
   */
  int selectCountResumeByEmail(String applicantId);

  /**
   * Method {@code selectResumeThatLeftUntouched} retrieves resumes for a given vacancy that haven't
   * been acted upon by a specific applicant.
   *
   * @param idVacancy the unique identifier of the vacancy.
   * @param applicantId the unique identifier of the applicant.
   * @return a list of untouched resumes for the given criteria.
   */
  List<Resume> selectResumeThatLeftUntouched(String idVacancy, String applicantId);
}
