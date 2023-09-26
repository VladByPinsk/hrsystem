package by.training.hrsystem.dao.impl;

import by.training.hrsystem.dao.VerifyDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.Resume;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.Vacancy;
import by.training.hrsystem.domain.Verify;
import by.training.hrsystem.domain.type.PassType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code DBVerifyDAO} implements {@link by.training.hrsystem.dao.VerifyDAO VerifyDAO} and
 * override all methods located at the interface.
 *
 * @author Vladislav
 * @see by.training.hrsystem.dao.VerifyDAO
 * @see by.training.hrsystem.domain.Verify
 */
public class DBVerifyDAO implements VerifyDAO {

  private static final Logger logger = LogManager.getLogger(DBVerifyDAO.class);

  private static final String SQL_ADD_RESUME_TO_VACANCY =
      "INSERT INTO verify (id_vacancy, id_resume) VALUES (?, ?);";
  private static final String SQL_VERIFY_RESUME_PASS =
      "UPDATE verify SET pass='pass' WHERE id_verify=?;";
  private static final String SQL_VERIFY_RESUME_NOT_PASS =
      "UPDATE verify SET pass='not pass' WHERE id_verify=?;";
  private static final String SQL_SELECT_VERIFY_BY_ID_VACANCY =
      "SELECT v.id_verify, v.pass, v.id_vacancy, r.id_resume, r.name, r.publish_date, r.email FROM verify as v, resume as r "
          + "WHERE v.id_vacancy=? and r.id_resume=v.id_resume; ";
  private static final String SQL_SELECT_VERIFY_BY_ID =
      "SELECT verify.id_verify, user.email, user.surname, user.name, user.secondname, user.contact_phone, user.skype, vacancy.name, vacancy.publish_date "
          + "FROM user JOIN resume ON user.email= resume.email JOIN verify ON resume.id_resume=verify.id_resume JOIN vacancy ON verify.id_vacancy=vacancy.id_vacancy "
          + "WHERE verify.id_verify=?;";
  private static final String SQL_SELECT_PASS_VERIFY_BY_ID_VACANCY =
      "SELECT verify.id_verify, user.email, user.surname, user.name, user.secondname, user.contact_phone, user.skype "
          + "FROM user JOIN resume ON user.email= resume.email JOIN verify ON resume.id_resume=verify.id_resume "
          + "WHERE verify.id_vacancy=? and verify.pass='pass';";

  @Override
  public void addResumeToVacancy(Verify verify) throws DAOException {
    logger.debug("DBVerifyDAO.addResumeToVacancy() - verify = {}", verify);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_ADD_RESUME_TO_VACANCY);
      ps.setInt(1, verify.getVacancy().getIdVacancy());
      ps.setInt(2, verify.getResume().getIdResume());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild create to addResumeToVacancy: ", e);
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
  public void verifyResumePass(int idVerify) throws DAOException {
    logger.debug("DBVerifyDAO.verifyResumePass() - idverify = {}", idVerify);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_VERIFY_RESUME_PASS);
      ps.setInt(1, idVerify);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild to verify resume: ", e);
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
  public void verifyResumeNotPass(int idVerify) throws DAOException {
    logger.debug("DBVerifyDAO.verifyResumeNotPass() - idverify = {}", idVerify);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_VERIFY_RESUME_NOT_PASS);
      ps.setInt(1, idVerify);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild to verify resume: ", e);
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
  public List<Verify> verifyList(int idVacancy) throws DAOException {
    logger.debug("DBVerifyDAO.verifyList() - idVacancy = {}", idVacancy);
    List<Verify> verifyList = new ArrayList<Verify>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_VERIFY_BY_ID_VACANCY);
      ps.setInt(1, idVacancy);
      rs = ps.executeQuery();
      while (rs.next()) {
        verifyList.add(getVerifyFromResultSet(rs));
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
    return verifyList;
  }

  @Override
  public Verify getVerifyById(int idVerify) throws DAOException {
    Verify verify = null;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_VERIFY_BY_ID);
      ps.setInt(1, idVerify);
      rs = ps.executeQuery();
      if (rs.next()) {
        verify = getVerifyForInterviewFromResultSet(rs);
      }
    } catch (SQLException e) {
      throw new DAOException("Faild to find verify for resume: ", e);
    } catch (ConnectionPoolException e) {
      throw new DAOException("Connection pool problems!", e);
    } finally {
      try {
        rs.close();
        ps.close();
        ConnectionPool.getInstance().closeConnection(conn);
      } catch (SQLException | ConnectionPoolException e) {
        logger.error("Faild to close connection or ps", e);
      }
    }
    return verify;
  }

  private Verify getVerifyFromResultSet(ResultSet set) throws SQLException {
    Verify verify = new Verify();
    verify.setIdVerify(set.getInt(1));
    verify.setPass(PassType.valueOf(set.getString(2).toUpperCase().replace(' ', '_')));
    Vacancy vacancy = new Vacancy();
    vacancy.setIdVacancy(set.getInt(3));
    verify.setVacancy(vacancy);
    Resume resume = new Resume();
    resume.setIdResume(set.getInt(4));
    resume.setName(set.getString(5));
    resume.setPublishDate(set.getDate(6));
    User applicant = new User();
    applicant.setEmail(set.getString(7));
    resume.setApplicant(applicant);
    verify.setResume(resume);
    return verify;
  }

  @Override
  public List<Verify> passVerifyList(int idVacancy) throws DAOException {
    logger.debug("DBVerifyDAO.passVerifyList() - idVacancy = {}", idVacancy);
    List<Verify> verifyList = new ArrayList<Verify>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_PASS_VERIFY_BY_ID_VACANCY);
      ps.setInt(1, idVacancy);
      rs = ps.executeQuery();
      while (rs.next()) {
        verifyList.add(getPassVerifyFromResultSet(rs));
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
    return verifyList;
  }

  private Verify getVerifyForInterviewFromResultSet(ResultSet set) throws SQLException {
    Verify verify = new Verify();
    verify.setIdVerify(set.getInt(1));
    Resume resume = new Resume();
    User applicant = new User();
    applicant.setEmail(set.getString(2));
    applicant.setSurname(set.getString(3));
    applicant.setName(set.getString(4));
    applicant.setSecondName(set.getString(5));
    applicant.setContactPhone(set.getInt(6));
    applicant.setSkype(set.getString(7));
    resume.setApplicant(applicant);
    verify.setResume(resume);
    Vacancy vacancy = new Vacancy();
    vacancy.setName(set.getString(8));
    vacancy.setPublishDate(set.getDate(9));
    verify.setVacancy(vacancy);
    return verify;
  }

  private Verify getPassVerifyFromResultSet(ResultSet set) throws SQLException {
    Verify verify = new Verify();
    verify.setIdVerify(set.getInt(1));
    Resume resume = new Resume();
    User applicant = new User();
    applicant.setEmail(set.getString(2));
    applicant.setSurname(set.getString(3));
    applicant.setName(set.getString(4));
    applicant.setSecondName(set.getString(5));
    applicant.setContactPhone(set.getInt(6));
    applicant.setSkype(set.getString(7));
    resume.setApplicant(applicant);
    verify.setResume(resume);
    return verify;
  }
}
