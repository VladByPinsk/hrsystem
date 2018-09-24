package by.training.hrsystem.dao;

import java.util.List;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.ResumeLanguage;

/**
 * Interface {@code ResumeLangugaeDAO} extends {@link AbstractDAO} and declare
 * method than appropriate just for
 * {@link by.training.hrsystem.domain.ResumeLanguage ResumeLanguage} objects.
 * 
 * @author Vladislav
 *
 * @see AbstractDAO
 * @see by.training.hrsystem.domain.ResumeLanguage ResumeLanguage
 */
public interface ResumeLangugaeDAO extends AbstractDAO<ResumeLanguage> {
	/**
	 * Method {@code getResumeLangByIdResume} allow to find list of resume
	 * languages objects by idResume
	 * 
	 * @param idResume
	 *            key of entity,it will use to find list of resume languages
	 *            objects from database
	 * @return list of resume languages that belong to the given id resume.
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while finding list of education.
	 * 
	 * @see by.training.hrsystem.dao.exception.DAOException
	 * @see by.training.hrsystem.domain.ResumeLanguage
	 */
	List<ResumeLanguage> getResumeLangByIdResume(int idResume) throws DAOException;

}
