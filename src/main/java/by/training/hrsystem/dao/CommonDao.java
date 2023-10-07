package by.training.hrsystem.dao;

/**
 * Interface {@code CommonDao} defines the basic CRUD operations for interacting with the database.
 * The interface uses generics to be adaptable for multiple entity types.
 *
 * @author Uladzislau Hapeyenka
 * @param <T> type of the entity
 */
public interface CommonDao<T> {

  /**
   * Method {@code add} persists the given entity in the database.
   *
   * @param entity The entity to be persisted.
   * @throws org.hibernate.HibernateException If an error occurs during the persist operation.
   */
  void add(T entity);

  /**
   * Method {@code update} Updates the state of the given entity in the database.
   *
   * @param entity The entity with updated state to be applied.
   * @throws org.hibernate.HibernateException If an error occurs during the update operation.
   */
  void update(T entity);

  /**
   * Method {@code delete} deletes the entity with the specified primary key from the database.
   *
   * @param id The primary key of the entity to be deleted.
   * @throws org.hibernate.HibernateException If an error occurs during the delete operation.
   */
  void delete(String id);
}
