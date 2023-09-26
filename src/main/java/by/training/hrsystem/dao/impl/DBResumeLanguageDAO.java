package by.training.hrsystem.dao.impl;

import by.training.hrsystem.dao.ResumeLangugaeDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.ResumeLanguage;
import by.training.hrsystem.domain.type.LanguageLevelType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code ResumeLanguageDAO} implements {@link by.training.hrsystem.dao.ResumeLanguageDAO
 * ResumeLanguageDAO} and override all methods located at the interface.
 *
 * @author Vladislav
 * @see by.training.hrsystem.dao.ResumeLanguageDAO
 * @see by.training.hrsystem.domain.ResumeLanguage
 */
public class DBResumeLanguageDAO implements ResumeLangugaeDAO {

  private static final Logger logger = LogManager.getLogger(DBResumeLanguageDAO.class);

  private static final String SQL_ADD_RESEUME_LANGUAGE =
      "INSERT INTO resumelanguage (name, raiting, id_resume) VALUES (?, ?, ?);";
  private static final String SQL_UPDATE_RESUME_LANGUAGE =
      "UPDATE resumelanguage SET name=?, raiting=? WHERE id_language=?;";
  private static final String SQL_DELETE_RESUME_LANGUAGE =
      "DELETE FROM resumelanguage WHERE id_language=?;";
  private static final String SQL_SELECT_RESUME_LANG_BY_ID_RESUME =
      "SELECT * FROM resumelanguage WHERE id_resume=?;";

  @Override
  public void add(ResumeLanguage entity) throws DAOException {
    logger.debug("DBResumeLanguageDAO.addResume() - language = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_ADD_RESEUME_LANGUAGE);
      ps.setString(1, entity.getName());
      ps.setString(2, entity.getRaiting().getLanguageLevelType());
      ps.setInt(3, entity.getIdResume());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild create Language for resume: ", e);
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
  public void update(ResumeLanguage entity) throws DAOException {
    logger.debug("DBResumeLanguageDAO.updateResumeLang() - language = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_UPDATE_RESUME_LANGUAGE);
      ps.setString(1, entity.getName());
      ps.setString(2, entity.getRaiting().getLanguageLevelType());
      ps.setInt(3, entity.getIdLanguage());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild to update Language for resume: ", e);
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
    logger.debug("DBResumeLanguageDAO.deleteResumeLang() - idLanguage = {}", id);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_DELETE_RESUME_LANGUAGE);
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild delete Language: ", e);
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
  public List<ResumeLanguage> getResumeLangByIdResume(int idResume) throws DAOException {
    List<ResumeLanguage> language = new ArrayList<ResumeLanguage>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_RESUME_LANG_BY_ID_RESUME);
      ps.setInt(1, idResume);
      rs = ps.executeQuery();
      while (rs.next()) {
        language.add(getLanguageFromResultSet(rs));
      }
    } catch (SQLException e) {
      throw new DAOException("Faild to find langugae: ", e);
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
    return language;
  }

  private ResumeLanguage getLanguageFromResultSet(ResultSet set) throws SQLException {
    ResumeLanguage language = new ResumeLanguage();
    language.setIdLanguage(set.getInt(1));
    language.setName(set.getString(2));
    language.setRaiting(
        LanguageLevelType.valueOf(set.getString(3).toUpperCase().replace(' ', '_')));
    language.setIdResume(set.getInt(4));
    return language;
  }
}
