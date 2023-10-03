package by.training.hrsystem.dao.impl;

import by.training.hrsystem.dao.WorkPlaceDao;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.WorkPlace;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code DBWorkPlaceDAO} implements {@link WorkPlaceDao
 * WorkPlaceDAO} and override all methods located at the interface.
 *
 * @author Vladislav
 * @see WorkPlaceDao
 * @see by.training.hrsystem.domain.WorkPlace
 */
public class DBWorkPlaceDao implements WorkPlaceDao {
  private static final Logger logger = LogManager.getLogger(DBWorkPlaceDao.class);
  private static final String SQL_ADD_WORKPLACE =
      "INSERT INTO workplace (company_name, position, date_begin, date_end, id_resume) VALUES (?, ?, ?, ?, ?);";
  private static final String SQL_UPDATE_WORKPLACE =
      "UPDATE workplace SET company_name=?, position=?, date_begin=?, date_end=? WHERE id_workplace=?;";
  private static final String SQL_DELETE_WORKPLACE = "DELETE FROM workplace WHERE id_workplace=?;";
  private static final String SQL_SELECT_WORKPLACE_BY_ID_RESUME =
      "SELECT * FROM workplace WHERE id_resume=?;";

  @Override
  public void add(WorkPlace entity) throws DAOException {
    logger.debug("DBWorkPlaceDAO.addWorkPlace() - workPlace = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_ADD_WORKPLACE);
      ps.setString(1, entity.getCompanyName());
      ps.setString(2, entity.getPosition());
      ps.setDate(3, new java.sql.Date(entity.getDateBegin().getTime()));
      ps.setDate(4, new java.sql.Date(entity.getDateEnd().getTime()));
      ps.setInt(5, entity.getIdResume());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild create WorkPlace: ", e);
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
  public void update(WorkPlace entity) throws DAOException {
    logger.debug("DBWorkPlaceDAO.updateWorkPlace() - workPlace = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_UPDATE_WORKPLACE);
      ps.setString(1, entity.getCompanyName());
      ps.setString(2, entity.getPosition());
      ps.setDate(3, new java.sql.Date(entity.getDateBegin().getTime()));
      ps.setDate(4, new java.sql.Date(entity.getDateEnd().getTime()));
      ps.setInt(5, entity.getIdWorkPlace());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild to update WorkPlace: ", e);
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
    logger.debug("DBWorkPlaceDAO.deleteWorkPlace() - idWorkPlace = {}", id);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_DELETE_WORKPLACE);
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild delete WorkPlace: ", e);
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
  public List<WorkPlace> getWorkPlaceByIdResume(int idResume) throws DAOException {
    List<WorkPlace> workplace = new ArrayList<WorkPlace>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_WORKPLACE_BY_ID_RESUME);
      ps.setInt(1, idResume);
      rs = ps.executeQuery();
      while (rs.next()) {
        workplace.add(getWorkPlaceFromResultSet(rs));
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

    return workplace;
  }

  private WorkPlace getWorkPlaceFromResultSet(ResultSet set) throws SQLException {
    WorkPlace workPlace = new WorkPlace();
    workPlace.setIdWorkPlace(set.getInt(1));
    workPlace.setCompanyName(set.getString(2));
    workPlace.setPosition(set.getString(3));
    workPlace.setDateBegin(set.getDate(4));
    workPlace.setDateEnd(set.getDate(5));
    workPlace.setIdResume(set.getInt(6));
    return workPlace;
  }
}
