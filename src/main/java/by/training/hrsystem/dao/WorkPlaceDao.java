package by.training.hrsystem.dao;

import by.training.hrsystem.dao.entity.WorkplaceEntity;
import java.util.List;

/**
 * The {@code WorkPlaceDAO} interface is dedicated to operations associated with {@link
 * WorkplaceEntity} objects. It provides methods that specifically cater to the unique requirements
 * of {@link WorkplaceEntity}, extending the generic operations offered by {@link CommonDao}.
 *
 * @author Uladzislau Hapeyenka
 */
public interface WorkPlaceDao extends CommonDao<WorkplaceEntity> {
  /**
   * Method {@code getWorkPlaceByIdResume} fetches a list of {@link WorkplaceEntity} objects that
   * are associated with the specified resume. This allows retrieval of all workplaces where a
   * particular user (linked by the resume) has worked or is working.
   *
   * @param idResume The unique identifier of the resume which is used as the reference to fetch
   *     related work places from the database.
   * @return A list of work place entities that are tied to the provided resume ID. If no records
   *     are found, this could potentially return an empty list.
   * @throws org.hibernate.HibernateException If any database or connection pool error arises during
   *     the retrieval of the work place entities.
   */
  List<WorkplaceEntity> getWorkPlaceByIdResume(String idResume);
}
