package by.training.hrsystem.dao.exception;

/**
 * Class {@code DAOException} extends class {@link java.lang.Exception Exception} and describes an
 * exception that indicates an error during operation in DAO layer.
 *
 * @author Vladislav
 */
public class DAOException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Creates {@code DAOException} with a specified message
   *
   * @param message message that will be show.
   */
  public DAOException(String message) {
    super(message);
  }

  /**
   * Creates {@code DAOException} with a specified message, the cause of witch is exception {@code
   * ex}.
   *
   * @param message message that will be show.
   * @param ex exception
   */
  public DAOException(String message, Exception ex) {
    super(message, ex);
  }
}
