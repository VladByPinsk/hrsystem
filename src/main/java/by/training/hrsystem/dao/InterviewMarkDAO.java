package by.training.hrsystem.dao;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.InterviewMark;
import java.util.List;

/**
 * Interface {@code InterviewMarkDAO} extends {@link CommonDao} and declare method than
 * appropriate just for {@link by.training.hrsystem.domain.InterviewMark InterviewMark} objects.
 *
 * @author Vladislav
 * @see CommonDao
 * @see by.training.hrsystem.domain.InterviewMark InterviewMark
 */
public interface InterviewMarkDao extends CommonDao<InterviewMark> {
  /**
   * Method {@code selectMarkOfTechicalInterview} allow to find list of marks for technical
   * interview by idVerify.
   *
   * @param idVerify key of entity,it will use to find interview object from database.
   * @return list of interview that belong to the given idVerify and to technical interview.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding list of marks for technical interview.
   */
  List<InterviewMark> selectMarkOfTechicalInterview(int idVerify) throws DAOException;

  /**
   * Method {@code selectMarkOfTechicalInterview} allow to find list of marks for preliminary
   * interview by idVerify.
   *
   * @param idVerify key of entity,it will use to find interview object from database.
   * @return list of interview that belong to the given idVerify and to preliminary interview.
   * @throws DAOException if a database access error occurred or error interaction with connection
   *     pool while finding list of marks for preliminary interview.
   */
  List<InterviewMark> selectMarkOfPreliminaryInterview(int idVerify) throws DAOException;
}
