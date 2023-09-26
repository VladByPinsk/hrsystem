package by.training.hrsystem.dao;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.domain.Verify;
import java.util.List;

/**
 * 
 * Interface {@code VerifyDAO} declare method that appropriate just for
 * {@link by.training.hrsystem.domain.Verify Verify} objects.
 * 
 * @author Vladislav
 * 
 * @see by.training.hrsystem.domain.Verify Verify
 */
public interface VerifyDAO {
	/**
	 * Method {@code addResumeToVacancy} allows you to leave your resume for
	 * given vacancy.
	 * 
	 * @param verify
	 *            object that will be created.
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while creating verify.
	 * 
	 * @see by.training.hrsystem.dao.exception.DAOException
	 * @see by.training.hrsystem.domain.Verify
	 */
	void addResumeToVacancy(Verify verify) throws DAOException;

	/**
	 * Method {@code verifyResumePass} allows you to set status "pass" if you
	 * enjoyed given resume.
	 * 
	 * @param idVerify
	 *            key of entity,it will use to find verify object from database.
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while finding verify by id.
	 */
	void verifyResumePass(int idVerify) throws DAOException;

	/**
	 * Method {@code verifyResumeNotPass} allows you to set status " not pass"
	 * if you not enjoyed given resume.
	 * 
	 * @param idVerify
	 *            key of entity,it will use to find verify object from database.
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while finding verify by id.
	 */
	void verifyResumeNotPass(int idVerify) throws DAOException;

	/**
	 * Method {@code verifyList} allows you to find list of resume belonging to
	 * given vacancy.
	 * 
	 * @param idVacancy
	 *            key of entity,it will use to find verify object from database.
	 * @return list of verify objects.
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while finding verify by id.
	 */
	List<Verify> verifyList(int idVacancy) throws DAOException;

	/**
	 * 
	 * @param idVacancy
	 * @return
	 * @throws DAOException
	 */
	List<Verify> passVerifyList(int idVacancy) throws DAOException;

	/**
	 * 
	 * @param idVerify
	 * @return
	 * @throws DAOException
	 */
	Verify getVerifyById(int idVerify) throws DAOException;
}
