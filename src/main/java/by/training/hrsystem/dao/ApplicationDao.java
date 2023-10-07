package by.training.hrsystem.dao;

import by.training.hrsystem.dao.entity.ApplicationEntity;
import by.training.hrsystem.dao.exception.DAOException;
import java.util.List;

/**
 * The {@code ApplicationDao} interface extends {@link CommonDao} and outlines methods specifically
 * for operations related to {@link ApplicationEntity} objects.
 *
 * @author Uladzislau Hapeyenka
 */
public interface ApplicationDao extends CommonDao<ApplicationEntity> {

  /**
   * Method {@code selectApplicationForProvidedUser} retrieves a list of {@link ApplicationEntity}
   * objects for a given user based on their unique identifier.
   *
   * @param idUser the unique identifier of the user.
   * @return a list of interview marks associated with the provided user.
   * @throws DAOException in case of any database or connection pool issues.
   */
  List<ApplicationEntity> selectApplicationForProvidedUser(String idUser) throws DAOException;
}
