package by.training.hrsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hrsystem.dao.ResumeDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.Resume;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.type.ActiveType;
import by.training.hrsystem.domain.type.MilitaryType;

/**
 * Class {@code DBResumeDAO} implements
 * {@link by.training.hrsystem.dao.ResumeDAO ResumeDAO} and override all methods
 * located at the interface.
 * 
 * @author Vladislav
 *
 * @see by.training.hrsystem.dao.ResumeDAO
 * @see by.training.hrsystem.domain.Resume
 * 
 *
 */
public class DBResumeDAO implements ResumeDAO {
	private static final Logger logger = LogManager.getLogger(DBResumeDAO.class);

	private static final String SQL_ADD_RESUME = "INSERT INTO resume (name, publish_date, military, email) VALUES (?, ?, ?, ?);";
	private static final String SQL_UPDATE_RESUME = "UPDATE resume SET name=?, publish_date=?, military=? WHERE id_resume=?;";
	private static final String SQL_DELETE_RESUME = "DELETE FROM resume WHERE id_resume=?;";
	private static final String SQL_SELECT_COUNT_RESUME = "SELECT count(id_resume) AS resume_count FROM resume;";
	private static final String SQL_ACTIVATE_RESUME = "UPDATE resume SET active=active WHERE id_resume=?;";
	private static final String SQL_SELECT_RESUME_BY_ID = "SELECT * FROM resume WHERE id_resume=?;";
	private static final String SQL_SELECT_RESUME_BY_APPLICANT = "SELECT * FROM resume WHERE email=? LIMIT ?,?;";
	private static final String SQL_SELECT_RESUME_FOR_VACANCY = "SELECT * FROM resume WHERE email=?;";
	private static final String SQL_SELECT_COUNT_RESUME_BY_APPLIC_EMAIL = "SELECT count(id_resume) AS resume_count FROM resume WHERE email=?;";
	private static final String SQL_SELECT_RESUME_BY_ID_VACANCY = "SELECT r.id_resume, r.name, r.publish_date, r.military, r.active, r.email FROM verify as v, resume as r "
			+ "WHERE v.id_vacancy=? and r.id_resume=v.id_resume; ";
	private static final String SQL_SELECT_LEFT_RESUME = "SELECT * FROM resume as r JOIN verify as v ON r.id_resume=v.id_resume WHERE id_vacancy=? and email=?;";

	@Override
	public void add(Resume entity) throws DAOException {
		logger.debug("DBResumeDAO.addResume() - resume = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_ADD_RESUME);
			ps.setString(1, entity.getName());
			ps.setDate(2, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			ps.setString(3, entity.getMilitatyType().getMillatryType());
			ps.setString(4, entity.getApplicant().getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild to create resume: ", e);
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
	public void update(Resume entity) throws DAOException {
		logger.debug("DBResumeDAO.updateResume() - resume = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_RESUME);
			ps.setString(1, entity.getName());
			ps.setDate(2, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			ps.setString(3, entity.getMilitatyType().getMillatryType());
			ps.setInt(4, entity.getIdResume());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild update resume: ", e);
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
		logger.debug("DBResumeDAO.deleteResume() - idResume = {}", id);
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_DELETE_RESUME);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild delete Resume: ", e);
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
	public int selectCountResume() throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		int countResume = 0;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_COUNT_RESUME);
			rs = ps.executeQuery();
			if (rs.next()) {
				countResume = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find count: ", e);
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

		return countResume;
	}

	@Override
	public void activateResume(Resume resume) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_ACTIVATE_RESUME);
			ps.setInt(1, resume.getIdResume());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild activate Resume: ", e);
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
	public Resume selectResumeById(int idResume) throws DAOException {
		Resume resume = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_RESUME_BY_ID);
			ps.setInt(1, idResume);
			rs = ps.executeQuery();
			if (rs.next()) {
				resume = getResumeFromResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find user: ", e);
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
		return resume;

	}

	@Override
	public List<Resume> selectResumeByApplicant(String applicantEmail, int pageNum, int amountPerPage)
			throws DAOException {
		logger.debug("DBResumeDAO.selectResumeByApplicant() - applicantEmail = {}, lang={}, pageNum={},amountPerPage",
				applicantEmail, pageNum, amountPerPage);
		List<Resume> resume = new ArrayList<Resume>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_RESUME_BY_APPLICANT);
			ps.setString(1, applicantEmail);
			ps.setInt(2, pageNum);
			ps.setInt(3, amountPerPage);
			rs = ps.executeQuery();
			while (rs.next()) {
				resume.add(getResumeFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find resume: ", e);
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

		return resume;
	}

	@Override
	public int selectCountResumeByEmail(String applicantEmail) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		int countVacancy = 0;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_COUNT_RESUME_BY_APPLIC_EMAIL);
			ps.setString(1, applicantEmail);
			rs = ps.executeQuery();
			if (rs.next()) {
				countVacancy = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find count: ", e);
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
		return countVacancy;

	}

	private Resume getResumeFromResultSet(ResultSet set) throws SQLException {
		Resume resume = new Resume();
		resume.setIdResume(set.getInt(1));
		resume.setName(set.getString(2));
		resume.setPublishDate(set.getDate(3));
		resume.setMilitatyType(MilitaryType.valueOf(set.getString(4).toUpperCase().replace(' ', '_')));
		resume.setActiveType(ActiveType.valueOf(set.getString(5).toUpperCase().replace(' ', '_')));
		User applicant = new User();
		applicant.setEmail(set.getString(6));
		return resume;

	}

	@Override
	public List<Resume> selectResumeForVacancy(String applicantEmail) throws DAOException {
		logger.debug("DBResumeDAO.selectResumeByApplicant() - applicantEmail = {}", applicantEmail);
		List<Resume> resume = new ArrayList<Resume>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_RESUME_FOR_VACANCY);
			ps.setString(1, applicantEmail);
			rs = ps.executeQuery();
			while (rs.next()) {
				resume.add(getResumeFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find resume: ", e);
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
		return resume;
	}

	@Override
	public List<Resume> selectListResumeByVacancy(int idVacancy) throws DAOException {
		logger.debug("DBResumeDAO.selectListResumeByVacancy() - applicantEmail = {}", idVacancy);
		List<Resume> resume = new ArrayList<Resume>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_RESUME_BY_ID_VACANCY);
			ps.setInt(1, idVacancy);
			rs = ps.executeQuery();
			while (rs.next()) {
				resume.add(getResumeFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find resume: ", e);
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
		return resume;
	}

	@Override
	public List<Resume> selectLeftResume(int idVacancy, String applicantEmail) throws DAOException {
		logger.debug("DBResumeDAO.selectleftResume() - idVacancy={}, applicantEmail = {}", idVacancy, applicantEmail);
		List<Resume> resume = new ArrayList<Resume>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_LEFT_RESUME);
			ps.setInt(1, idVacancy);
			ps.setString(2, applicantEmail);
			rs = ps.executeQuery();
			while (rs.next()) {
				resume.add(getResumeFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find resume: ", e);
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
		return resume;

	}

}
