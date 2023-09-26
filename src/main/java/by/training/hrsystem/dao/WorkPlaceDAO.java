package by.training.hrsystem.dao;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.WorkPlace;
import java.util.List;

/**
 * Interface {@code WorkPlaceDAO} extends {@link AbstractDAO} and declare method
 * than appropriate just for {@link by.training.hrsystem.domain.WorkPlace
 * WorkPlace} objects
 * 
 * @author Vladislav
 *
 * @see AbstractDAO
 * @see by.training.hrsystem.domain.WorkPlace
 */
public interface WorkPlaceDAO extends AbstractDAO<WorkPlace> {
	/**
	 * Method {@code getWorkPlaceByIdResume} allow to find list of work places
	 * objects by id resume.
	 * 
	 * @param idResume
	 *            key of entity,it will use to find list of work place objects
	 *            from database.
	 * @return list of work place objects that belong to the given id resume.
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while finding list of work place objects.
	 * 
	 * @see by.training.hrsystem.dao.exception.DAOException
	 * @see by.training.hrsystem.domain.WorkPlace
	 */
	List<WorkPlace> getWorkPlaceByIdResume(int idResume) throws DAOException;

}
