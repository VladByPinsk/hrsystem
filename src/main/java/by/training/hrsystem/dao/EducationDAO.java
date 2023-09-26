package by.training.hrsystem.dao;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.Education;
import java.util.List;

/**
 * Interface {@code EducationDAO} extends {@link AbstractDAO} and declare method
 * than appropriate just for {@link by.training.hrsystem.domain.Education
 * Education} objects
 * 
 * @author Vladislav
 *
 * @see AbstractDAO
 * @see by.training.hrsystem.domain.Education
 */
public interface EducationDAO extends AbstractDAO<Education> {
	/**
	 * Method {@code getEducationByIdResume} allow to find list of education
	 * objects by idResume
	 * 
	 * @param idResume
	 *            key of entity,it will use to find education object from
	 *            database
	 * @return list of education that belong to the given id resume.
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while finding list of education.
	 * 
	 * @see by.training.hrsystem.dao.exception.DAOException
	 * @see by.training.hrsystem.domain.Education
	 */
	List<Education> getEducationByIdResume(int idResume) throws DAOException;

}
