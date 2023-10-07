package by.training.hrsystem.dao;

import by.training.hrsystem.dao.entity.EducationEntity;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.Education;
import java.util.List;

/**
 * Interface {@code EducationDAO} extends the {@link CommonDao} interface for basic CRUD operations.
 * Specifies the data access operations related to {@link EducationEntity EducationEntity} entities.
 *
 * @author Uladzislau Hapeyenka
 */
public interface EducationDao extends CommonDao<EducationEntity> {
  /**
   * Method {@code getEducationByIdResume} retrieves a list of education records associated with the
   * given resume ID.
   *
   * @param idResume The resume ID to filter education records.
   * @return A list of education entities associated with the provided resume ID.
   * @throws org.hibernate.HibernateException If an error occurs during the data retrieval
   *     operation.
   * @see by.training.hrsystem.dao.entity.EducationEntity
   */
  List<EducationEntity> getEducationByIdResume(String idResume);
}
