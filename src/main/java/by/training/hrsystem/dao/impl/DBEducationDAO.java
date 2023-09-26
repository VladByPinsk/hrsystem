package by.training.hrsystem.dao.impl;

import by.training.hrsystem.dao.EducationDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.Education;
import by.training.hrsystem.domain.type.EducationType;
import by.training.hrsystem.domain.type.PostgraduateType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code DBEducationDAO} implements {@link by.training.hrsystem.dao.EducationDAO
 * EducationDAO} and override all methods located at the interface.
 *
 * @author Vladislav
 * @see by.training.hrsystem.dao.EducationDAO
 * @see by.training.hrsystem.domain.Education
 */
public class DBEducationDAO implements EducationDAO {
  private static final Logger logger = LogManager.getLogger(DBEducationDAO.class);

  private static final String SQL_ADD_EDUCATION =
      "INSERT INTO education (institution, faculty, department, education, course, grad_year, postgraduate, id_resume) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
  private static final String SQL_UPDATE_EDUCATION =
      "UPDATE education SET institution=?, faculty=?, department=?, education=?, course=?, grad_year=?, postgraduate=? WHERE id_education=?;";
  private static final String SQL_DELETE_EDUCATION = "DELETE FROM education WHERE id_education=?;";
  private static final String SQL_SELECT_EDUC_BY_ID_RESUME =
      "SELECT * FROM education WHERE id_resume=?;";

  @Override
  public void add(Education entity) throws DAOException {
    logger.debug("DBEducationDAO.addEducation() - user = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_ADD_EDUCATION);
      ps.setString(1, entity.getInstitution());
      ps.setString(2, entity.getFaculty());
      ps.setString(3, entity.getDepartment());
      ps.setString(4, entity.getEducation().getEducationType());
      ps.setInt(5, entity.getCourse());
      ps.setInt(6, entity.getGradYear());
      ps.setString(7, entity.getPostGraduate().getPostgraduateType());
      ps.setInt(8, entity.getIdResume());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild create Education: ", e);
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
  public void update(Education entity) throws DAOException {
    logger.debug("DBEducationDAO.updateEducation() - user = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_UPDATE_EDUCATION);
      ps.setString(1, entity.getInstitution());
      ps.setString(2, entity.getFaculty());
      ps.setString(3, entity.getDepartment());
      ps.setString(4, entity.getEducation().getEducationType());
      ps.setInt(5, entity.getCourse());
      ps.setInt(6, entity.getGradYear());
      ps.setString(7, entity.getPostGraduate().getPostgraduateType());
      ps.setInt(8, entity.getIdEducation());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild to update education: ", e);
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
    logger.debug("DBEducationDAO.deleteEducation() - idEducation = {}", id);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_DELETE_EDUCATION);
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild delete education: ", e);
    } catch (ConnectionPoolException e) {
      throw new DAOException("Connection pool problems!", e);
    } finally {
      try {
        ConnectionPool.getInstance().closeConnection(conn);
        ps.close();
      } catch (SQLException | ConnectionPoolException e) {
        logger.error("Faild to close connection or ps", e);
      }
    }
  }

  @Override
  public List<Education> getEducationByIdResume(int idResume) throws DAOException {
    logger.debug("DBEducationDAO.getEducationByIdResume() - idResume = {}", idResume);
    List<Education> education = new ArrayList<Education>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_EDUC_BY_ID_RESUME);
      ps.setInt(1, idResume);
      rs = ps.executeQuery();
      while (rs.next()) {
        education.add(getEducationFromResultSet(rs));
      }
    } catch (SQLException e) {
      throw new DAOException("Faild to find Company: ", e);
    } catch (ConnectionPoolException e) {
      throw new DAOException("Connection pool problems!", e);
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (ps != null) {
          ps.close();
        }
        ConnectionPool.getInstance().closeConnection(conn);

      } catch (SQLException | ConnectionPoolException e) {
        logger.error("Faild to close connection or ps or rs", e);
      }
    }

    return education;
  }

  private Education getEducationFromResultSet(ResultSet set) throws SQLException {
    Education education = new Education();
    education.setIdEducation(set.getInt(1));
    education.setInstitution(set.getString(2));
    education.setFaculty(set.getString(3));
    education.setDepartment(set.getString(4));
    education.setEducation(EducationType.valueOf(set.getString(5).toUpperCase().replace(' ', '_')));
    education.setCourse(set.getInt(6));
    education.setGradYear(set.getInt(7));
    education.setPostGraduate(
        PostgraduateType.valueOf(set.getString(8).toUpperCase().replace(' ', '_')));
    education.setIdResume(set.getInt(9));
    return education;
  }
}
