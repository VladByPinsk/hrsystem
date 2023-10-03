package by.training.hrsystem.dao.impl;

import by.training.hrsystem.dao.SkillDao;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.Skill;
import by.training.hrsystem.domain.type.SkillType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code DBSkillDAO} implements {@link SkillDao SkillDAO} and
 * override all methods located at the interface.
 *
 * @author Vladislav
 * @see SkillDao
 * @see by.training.hrsystem.domain.Skill
 */
public class DBSkillDao implements SkillDao {

  private static final Logger logger = LogManager.getLogger(DBSkillDao.class);
  private static final String SQL_ADD_SKILL =
      "INSERT INTO skill (name, raiting, id_resume) VALUES (?, ?, ?);";
  private static final String SQL_UPDATE_SKILL =
      "UPDATE skill SET name=?, raiting=? WHERE id_skill=?;";
  private static final String SQL_DELETE_SKILL = "DELETE FROM skill WHERE id_skill=?;";
  private static final String SQL_SELECT_SKILL_BY_ID_RESUME =
      "SELECT * FROM skill WHERE id_resume=?;";

  @Override
  public void add(Skill entity) throws DAOException {
    logger.debug("DBSkillDAO.addSkill() - skill = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_ADD_SKILL);
      ps.setString(1, entity.getName());
      ps.setString(2, entity.getRaiting().getSkillType());
      ps.setInt(3, entity.getIdResume());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild create skill: ", e);
    } catch (ConnectionPoolException e) {
      throw new DAOException("Connection pool problems!", e);
    } finally {
      try {
        ps.close();
        ConnectionPool.getInstance().closeConnection(conn);
      } catch (SQLException | ConnectionPoolException e) {
        logger.error("Faild to close connection or ps", e);
      }
    }
  }

  @Override
  public void update(Skill entity) throws DAOException {
    logger.debug("DBSkillDAO.updateSkill() - skill = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_UPDATE_SKILL);
      ps.setString(1, entity.getName());
      ps.setString(2, entity.getRaiting().getSkillType());
      ps.setInt(3, entity.getIdSkill());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild update skill: ", e);
    } catch (ConnectionPoolException e) {
      throw new DAOException("Connection pool problems!", e);
    } finally {
      try {
        ps.close();
        ConnectionPool.getInstance().closeConnection(conn);
      } catch (SQLException | ConnectionPoolException e) {
        logger.error("Faild to close connection or ps", e);
      }
    }
  }

  @Override
  public void delete(int id) throws DAOException {
    logger.debug("DBSkillDAO.deleteSkill() - idSkill = {}", id);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_DELETE_SKILL);
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild delete skill: ", e);
    } catch (ConnectionPoolException e) {
      throw new DAOException("Connection pool problems!", e);
    } finally {
      try {
        ps.close();
        ConnectionPool.getInstance().closeConnection(conn);

      } catch (SQLException | ConnectionPoolException e) {
        logger.error("Faild to close connection or ps", e);
      }
    }
  }

  @Override
  public List<Skill> getSkillByIdResume(int idResume) throws DAOException {
    List<Skill> skill = new ArrayList<Skill>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_SKILL_BY_ID_RESUME);
      ps.setInt(1, idResume);
      rs = ps.executeQuery();
      while (rs.next()) {
        skill.add(getSkillFromResultSet(rs));
      }
    } catch (SQLException e) {
      throw new DAOException("Faild to find langugae: ", e);
    } catch (ConnectionPoolException e) {
      throw new DAOException("Connection pool problems!", e);
    } finally {
      try {
        ps.close();
        ConnectionPool.getInstance().closeConnection(conn);
      } catch (SQLException | ConnectionPoolException e) {
        logger.error("Faild to close connection or ps or rs", e);
      }
    }
    return skill;
  }

  private Skill getSkillFromResultSet(ResultSet set) throws SQLException {
    Skill skill = new Skill();
    skill.setIdSkill(set.getInt(1));
    skill.setName(set.getString(2));
    skill.setRaiting(SkillType.valueOf(set.getString(3).toUpperCase()));
    skill.setIdResume(set.getInt(4));
    return skill;
  }
}
