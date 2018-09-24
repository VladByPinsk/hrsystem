package by.training.hrsystem.dao;

import by.training.hrsystem.dao.exception.DAOException;

/**
 * Interface {@code AbstractDAO} describe the basic methods interaction with the
 * database 
 * 
 * @author Vladislav
 *
 * @param <T>
 *            type of entity
 */
public interface AbstractDAO<T> {

	/**
	 * Method {@code add} allow to create entity object.
	 * 
	 * @param entity
	 *            object that will be created.
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while creating entity.
	 * 
	 * @see by.training.hrsystem.dao.exception.DAOException
	 */
	void add(T entity) throws DAOException;

	/**
	 * Method {@code update} allow to update entity object.
	 * 
	 * @param entity
	 *            object that will be updated
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while updating entity.
	 * 
	 * @see by.training.hrsystem.dao.exception.DAOException
	 */
	void update(T entity) throws DAOException;

	/**
	 * Method {@code delete} allow to delete entity object.
	 * 
	 * @param id
	 *            primary key of entity,it will use for deleting entity from
	 *            database
	 * @throws DAOException
	 *             if a database access error occurred or error interaction with
	 *             connection pool while deleting entity.
	 * 
	 * @see by.training.hrsystem.dao.exception.DAOException
	 */
	void delete(int id) throws DAOException;

}
