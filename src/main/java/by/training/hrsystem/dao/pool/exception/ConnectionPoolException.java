package by.training.hrsystem.dao.pool.exception;

/**
 * Class <code>ConnectionPoolException</code> extends class <code>Exception</code> and describes an
 * exception that indicates an error during operation with pool of connection to the database
 *
 * @author Vladislav
 */
public class ConnectionPoolException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Creates <code>ConnectionPoolException</code> with a specified message, the cause of witch is
   * exception<code>ex</code>
   *
   * @param message
   * @param ex
   */
  public ConnectionPoolException(String message, Exception ex) {
    super(message, ex);
  }

  /**
   * Creates <code>ConnectionPoolException</code> with a specified message
   *
   * @param message
   */
  public ConnectionPoolException(String message) {
    super(message);
  }
}
