package by.training.hrsystem.dao;

import by.training.hrsystem.dao.entity.SkillEntity;
import java.util.List;

/**
 * The {@code SkillDAO} interface extends {@link CommonDao} and provides methods specifically
 * designed for operations on {@link SkillEntity Skill Entity} objects.
 *
 * @author Uladzislau Hapeyenka
 */
public interface SkillDao extends CommonDao<SkillEntity> {
  /**
   * Method {@code getSkillByIdResume} retrieves a list of skills associated with a specific resume,
   * identified by its unique ID.
   *
   * @param idResume the unique identifier of the resume whose associated skills are to be fetched.
   * @return a list of {@link SkillEntity} objects corresponding to the provided resume ID.
   * @throws org.hibernate.HibernateException if there's an issue accessing the database or
   *     interacting with the connection pool during the retrieval process.
   */
  List<SkillEntity> getSkillByIdResume(String idResume);
}
