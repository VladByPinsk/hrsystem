package by.training.hrsystem.dao;

import java.util.List;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.Skill;

/**
 * Interface {@code SkillDAO} extends {@link AbstractDAO} and declare method
 * than appropriate just for {@link by.training.hrsystem.domain.Skill Skill}
 * objects.
 * 
 * @author Vladislav
 *
 * @see AbstractDAO
 * @see by.training.hrsystem.domain.Skill Skill
 */
public interface SkillDAO extends AbstractDAO<Skill> {
	/**
	 * Method {@code getSkillByIdResume} allow to find list of skills objects by
	 * idResume
	 * 
	 * @param idResume
	 *            key of entity,it will use to find list of resume languages
	 *            objects from database
	 * @return list of skills that belong to the given id resume.
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while finding list of education.
	 * 
	 * @see by.training.hrsystem.dao.exception.DAOException
	 * @see by.training.hrsystem.domain.Skill
	 */
	List<Skill> getSkillByIdResume(int idResume) throws DAOException;

}
