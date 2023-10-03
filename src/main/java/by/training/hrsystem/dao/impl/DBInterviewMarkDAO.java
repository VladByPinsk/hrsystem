package by.training.hrsystem.dao.impl;

import by.training.hrsystem.dao.InterviewMarkDao;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.InterviewMark;
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
 * Class {@code DBInterviewMarkDAO} implements {@link InterviewMarkDao
 * InterviewMarkDAO} and override all methods located at the interface.
 *
 * @author Vladislav
 * @see InterviewMarkDao
 * @see by.training.hrsystem.domain.InterviewMark
 */
public class DBInterviewMarkDao implements InterviewMarkDao {
  private static final Logger logger = LogManager.getLogger(DBInterviewMarkDao.class);
  private static final String SQL_ADD_INTERVIEW_MARK =
      "INSERT INTO interview_mark (skill, mark, id_interview) VALUES (?, ?, ?);";
  private static final String SQL_DELETE_INTERVIEW_MARK =
      "DELETE FROM interview_mark WHERE id_mark=?;";
  private static final String SQL_SELECT_MARK_OF_TECHNICAL_INTERVIEW =
      "SELECT im.id_mark, im.skill, im.mark, im.id_interview FROM interview_mark as im JOIN interview as i "
          + "ON im.id_interview=i.id_interview WHERE i.id_verify=? and i.type='techical';";
  private static final String SQL_SELECT_MARK_OF_PRELIMINARY_INTERVIEW =
      "SELECT im.id_mark, im.skill, im.mark, im.id_interview FROM interview_mark as im JOIN interview as i "
          + "ON im.id_interview=i.id_interview WHERE i.id_verify=? and i.type='preliminary';";

  @Override
  public void add(InterviewMark entity) throws DAOException {
    logger.debug("DBInterviewMarkDAO.addMark() - entity = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_ADD_INTERVIEW_MARK);
      ps.setString(1, entity.getSkill());
      ps.setString(2, entity.getMark().getSkillType());
      ps.setInt(3, entity.getIdInterview());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild create Interview mark: ", e);
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
  public void update(InterviewMark entity) throws DAOException {}

  @Override
  public void delete(int id) throws DAOException {
    logger.debug("DBInterviewMarkDAO.deleteMark() - idMark = {}", id);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_DELETE_INTERVIEW_MARK);
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild delete mark: ", e);
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
  public List<InterviewMark> selectMarkOfTechicalInterview(int idVerify) throws DAOException {
    List<InterviewMark> mark = new ArrayList<InterviewMark>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_MARK_OF_TECHNICAL_INTERVIEW);
      ps.setInt(1, idVerify);
      rs = ps.executeQuery();
      while (rs.next()) {
        mark.add(getMarkFromResultSet(rs));
      }
    } catch (SQLException e) {
      throw new DAOException("Faild to find mark: ", e);
    } catch (ConnectionPoolException e) {
      throw new DAOException("Connection pool problems!", e);
    } finally {
      try {
        rs.close();
        ps.close();
        ConnectionPool.getInstance().closeConnection(conn);
      } catch (SQLException | ConnectionPoolException e) {
        logger.error("Faild to close connection or ps or rs", e);
      }
    }
    return mark;
  }

  @Override
  public List<InterviewMark> selectMarkOfPreliminaryInterview(int idVerify) throws DAOException {
    List<InterviewMark> mark = new ArrayList<InterviewMark>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_MARK_OF_PRELIMINARY_INTERVIEW);
      ps.setInt(1, idVerify);
      rs = ps.executeQuery();
      while (rs.next()) {
        mark.add(getMarkFromResultSet(rs));
      }
    } catch (SQLException e) {
      throw new DAOException("Faild to find mark: ", e);
    } catch (ConnectionPoolException e) {
      throw new DAOException("Connection pool problems!", e);
    } finally {
      try {
        rs.close();
        ps.close();
        ConnectionPool.getInstance().closeConnection(conn);
      } catch (SQLException | ConnectionPoolException e) {
        logger.error("Faild to close connection or ps or rs", e);
      }
    }
    return mark;
  }

  private InterviewMark getMarkFromResultSet(ResultSet set) throws SQLException {
    InterviewMark skill = new InterviewMark();
    skill.setIdMark(set.getInt(1));
    skill.setSkill(set.getString(2));
    skill.setMark(SkillType.valueOf(set.getString(3).toUpperCase()));
    skill.setIdInterview(set.getInt(4));
    return skill;
  }
}
