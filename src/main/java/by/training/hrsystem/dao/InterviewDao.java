package by.training.hrsystem.dao;

import by.training.hrsystem.dao.entity.InterviewEntity;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.Interview;
import java.util.List;

/**
 * Interface {@code InterviewDao} Specifies data access operations specific to {@link
 * InterviewEntity} entities. * Extends the {@link CommonDao} interface for basic CRUD operations.
 *
 * @author Uladzislau Hapeyenka
 */
public interface InterviewDao extends CommonDao<InterviewEntity> {
  /**
   * Method {@code selectInterviewByIdVerify} retrieves a list of interviews associated with the
   * given application ID.
   *
   * @param idApplication The application ID used to filter interview records.
   * @return A list of interviews related to the specified application ID.
   * @throws org.hibernate.HibernateException If an error occurs during the data retrieval
   *     operation.
   */
  List<InterviewEntity> selectInterviewByIdVerify(String idApplication);
}
