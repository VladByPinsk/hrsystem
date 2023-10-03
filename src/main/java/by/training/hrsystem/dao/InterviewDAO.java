package by.training.hrsystem.dao;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.Interview;
import java.util.List;

/**
 * Interface {@code InterviewDAO} extends {@link CommonDao} and declare method than appropriate
 * just for {@link by.training.hrsystem.domain.Interview Interview} objects.
 *
 * @author Vladislav
 * @see CommonDao
 * @see by.training.hrsystem.domain.Interview Interview
 */
public interface InterviewDao extends CommonDao<Interview> {
  /**
   * Method {@code selectInterviewByIdVerify} allow to find list of interview objects by idVerify.
   *
   * @param idVerify key of entity,it will use to find interview object from database.
   * @return list of interview that belong to the given idVerify.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding list of interview.
   * @see by.training.hrsystem.dao.exception.DAOException
   * @see by.training.hrsystem.domain.Interview
   */
  List<Interview> selectInterviewByIdVerify(int idVerify) throws DAOException;
}
