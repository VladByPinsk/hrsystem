package by.training.hrsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hrsystem.dao.InterviewDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.Interview;
import by.training.hrsystem.domain.Verify;
import by.training.hrsystem.domain.type.InterviewType;

/**
 * Class {@code DBInterviewDAO} implements {@link by.training.hrsystem.dao.InterviewDAO
 * InterviewDAO} and override all methods located at the interface.
 *
 * @author Vladislav
 * @see by.training.hrsystem.dao.InterviewDAO
 * @see by.training.hrsystem.domain.Interview
 */
public class DBInterviewDAO implements InterviewDAO {
  private static final Logger logger = LogManager.getLogger(DBInterviewDAO.class);
  private static final String SQL_ADD_INTERVIEW =
      "INSERT INTO interview (type, date_begin, id_verify) VALUES (?, ?, ?);";
  private static final String SQL_DELETE_INTERVIEW = "DELETE FROM interview WHERE id_interview=?;";
  private static final String SQL_SELECT_INTERVIEW_BY_ID_VERIFY =
      "SELECT * FROM interview WHERE id_verify=?;";

  @Override
  public void add(Interview entity) throws DAOException {
    logger.debug("DBInterviewDAO.addInterview() - interview = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_ADD_INTERVIEW);
      ps.setString(1, entity.getInterviewType().getInterviewType());
      ps.setDate(2, new java.sql.Date(entity.getDateBegin().getTime()));
      ps.setInt(3, entity.getVerify().getIdVerify());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild create interview: ", e);
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
  public void update(Interview entity) throws DAOException {}

  @Override
  public void delete(int id) throws DAOException {
    logger.debug("DBInterviewDAO.deleteInterview() - idinterview = {}", id);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_DELETE_INTERVIEW);
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild delete interview: ", e);
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
  public List<Interview> selectInterviewByIdVerify(int idVerify) throws DAOException {
    logger.debug("DBInterviewDAO.selectInterviewByIdVerify() - idVerify = {}", idVerify);
    List<Interview> interview = new ArrayList<Interview>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_INTERVIEW_BY_ID_VERIFY);
      ps.setInt(1, idVerify);
      rs = ps.executeQuery();
      while (rs.next()) {
        interview.add(getInterviewFromResultSet(rs));
      }
    } catch (SQLException e) {
      throw new DAOException("Faild to find Company: ", e);
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

    return interview;
  }

  private Interview getInterviewFromResultSet(ResultSet set) throws SQLException {
    Interview interview = new Interview();
    interview.setIdInterview(set.getInt(1));
    interview.setInterviewType(InterviewType.valueOf(set.getString(2).toUpperCase()));
    interview.setDateBegin(set.getDate(3));
    Verify verify = new Verify();
    verify.setIdVerify(set.getInt(4));
    interview.setVerify(verify);
    return interview;
  }
}
