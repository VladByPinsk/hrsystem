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

import by.training.hrsystem.dao.VacancyDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.Vacancy;
import by.training.hrsystem.domain.type.ActiveType;
import by.training.hrsystem.domain.type.CurrencyType;
import by.training.hrsystem.domain.type.EmploymentType;
import by.training.hrsystem.domain.type.HotType;

/**
 * Class {@code DBVacancyDAO} implements
 * {@link by.training.hrsystem.dao.VacancyDAO VacancyDAO} and override all
 * methods located at the interface.
 * 
 * @author Vladislav
 *
 * @see by.training.hrsystem.dao.VacancyDAO
 * @see by.training.hrsystem.domain.Vacancy
 * 
 *
 */
public class DBVacancyDAO implements VacancyDAO {
	private static final Logger logger = LogManager.getLogger(DBVacancyDAO.class);
	private static final String SQL_ADD_VACANCY = "INSERT INTO vacancy (name, salary, currency, description, duties, conditions, employment_type, email) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE_VACANCY = "UPDATE vacancy SET name=?, salary=?, currency=?, description=?, duties=?, conditions=?, employment_type=? "
			+ "WHERE id_vacancy=?;";
	private static final String SQL_DELETE_VACANCY = "DELETE FROM vacancy WHERE id_vacancy=?;";
	private static final String SQL_ADD_TRANSLATION_VACANCY = "INSERT INTO tvacancy (id_vacancy, lang, name, description, duties, conditions) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE_TRANSLATION_VACANCY = "UPDATE tvacancy SET name=?, description=?, duties=?, conditions=? WHERE id_vacancy=? and lang=?;";
	private static final String SQL_SELECT_COUNT_VACANCY = "SELECT count(id_vacancy) as vacancy_count FROM vacancy;";
	private static final String SQL_SELECT_VACANCY_BY_ID = "SELECT * FROM vacancy WHERE id_vacancy=?;";
	private static final String SQL_SELECT_TRANSLATE_VACANCY_BY_ID = "SELECT v.id_vacancy, coalesce(tv.name, v.name) AS name, "
			+ "v.salary, v.currency, v. publish_date, coalesce(tv.description, v.description) AS description, coalesce(tv.duties, v.duties) AS duties, "
			+ "coalesce(tv.conditions, v.conditions) AS conditions, v.employment_type, v.active, v.hot, v.email "
			+ "FROM vacancy AS v LEFT JOIN (SELECT * FROM tvacancy WHERE lang = ?) AS tv USING(id_vacancy)  WHERE id_vacancy=?;";
	private static final String SQL_SELECT_ALL_VACANCY = "SELECT * FROM vacancy";
	private static final String SQL_SELECT_ONLY_TRANSLATE_VACANCY_BY_ID = "SELECT id_vacancy, name, description, duties, conditions FROM db_hr_system.tvacancy WHERE id_vacancy=?;";
	private static final String SQL_SELECT_ALL_TRANSL_VACANCY = "SELECT v.id_vacancy, coalesce(tv.name, v.name) AS name, "
			+ "v.salary, v.currency, v. publish_date, coalesce(tv.description, v.description) AS description, coalesce(tv.duties, v.duties) AS duties, "
			+ "coalesce(tv.conditions, v.conditions) AS conditions, v.employment_type, v.active, v.email "
			+ "FROM vacancy AS v LEFT JOIN (SELECT * FROM tvacancy WHERE lang = ?) AS tv USING(id_vacancy);";
	private static final String SQL_SELECT_VACANCY_BY_HREMAIL = "SELECT * FROM vacancy WHERE email=? ORDER BY id_vacancy DESC LIMIT ?,?;";
	private static final String SQL_SELECT_TRANSL_VAC_BY_HREMAIL = "SELECT v.id_vacancy, coalesce(tv.name, v.name) AS name, "
			+ "v.salary, v.currency, v. publish_date, coalesce(tv.description, v.description) AS description, coalesce(tv.duties, v.duties) AS duties, "
			+ "coalesce(tv.conditions, v.conditions) AS conditions, v.employment_type, v.active, v.hot, v.email "
			+ "FROM vacancy AS v LEFT JOIN (SELECT * FROM tvacancy WHERE lang = ?) AS tv USING(id_vacancy)  WHERE v.email=? ORDER BY id_vacancy DESC LIMIT ?,?;";
	private static final String SQL_SELECT_COUNT_VACANCY_BY_HR_EMAIL = "SELECT count(id_vacancy) as vacancy_count FROM vacancy WHERE email=?;";
	private static final String SQL_SELECT_VACANCY_LIKE = "SELECT * FROM vacancy WHERE active='active' and name LIKE ?;";
	// private static final String SQL_SELECT_TRASL_VACANCY_LIKE = "?";
	private static final String SQL_COUNT_ALL_ACTIVE_VACANCY = "SELECT count(id_vacancy) as countResume FROM vacancy WHERE active='active';";
	private static final String SQL_SELECT_ALL_ACTIVE_VACANCY = "SELECT * FROM vacancy WHERE active='active' ORDER BY publish_date DESC, id_vacancy LIMIT ?,?;";
	private static final String SQL_SELECT_ALL_TRANSL_ACTIVE_VACANCY = "SELECT v.id_vacancy, coalesce(tv.name, v.name) AS name, "
			+ "v.salary, v.currency, v. publish_date, coalesce(tv.description, v.description) AS description, coalesce(tv.duties, v.duties) AS duties, "
			+ "coalesce(tv.conditions, v.conditions) AS conditions, v.employment_type, v.active, v.hot, v.email "
			+ "FROM vacancy AS v LEFT JOIN (SELECT * FROM tvacancy WHERE lang = ?) AS tv USING(id_vacancy)  WHERE active='active' ORDER BY publish_date DESC, id_vacancy LIMIT ?, ?;";
	private static final String SQL_SELECT_ALL_HOT_VACANCY = "SELECT * FROM vacancy WHERE hot='hot' LIMIT 5;";
	private static final String SQL_SELECT_ALL_TRANSL_HOT_VACANCY = "SELECT v.id_vacancy, coalesce(tv.name, v.name) AS name, "
			+ "v.salary, v.currency, v. publish_date, coalesce(tv.description, v.description) AS description, coalesce(tv.duties, v.duties) AS duties, "
			+ "coalesce(tv.conditions, v.conditions) AS conditions, v.employment_type, v.active, v.hot, v.email "
			+ "FROM vacancy AS v LEFT JOIN (SELECT * FROM tvacancy WHERE lang = ?) AS tv USING(id_vacancy)  WHERE hot='hot' LIMIT 7;";
	private static final String SQL_ACTIVATE_VACANCY = "UPDATE vacancy SET active='active', publish_date=? WHERE id_vacancy=?;";
	private static final String SQL_HOT_VACANCY = "UPDATE vacancy SET hot='hot' WHERE id_vacancy=?;";
	private static final String SQL_DEACTIVATE_VACANCY = "UPDATE vacancy SET active='non active', hot='non hot' WHERE id_vacancy=?;";

	@Override
	public void add(Vacancy entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_ADD_VACANCY);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getSalary());
			ps.setString(3, entity.getCurrency().getCurrencyType());
			ps.setString(4, entity.getDescription());
			ps.setString(5, entity.getDuty());
			ps.setString(6, entity.getCondition());
			ps.setString(7, entity.getEmploymentType().getCurrencyType());
			ps.setString(8, entity.getHrEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild create Vacancy: ", e);
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
	public void update(Vacancy entity) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_VACANCY);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getSalary());
			ps.setString(3, entity.getCurrency().getCurrencyType());
			ps.setString(4, entity.getDescription());
			ps.setString(5, entity.getDuty());
			ps.setString(6, entity.getCondition());
			ps.setString(7, entity.getEmploymentType().getCurrencyType());
			ps.setInt(8, entity.getIdVacancy());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild to update Vacancy: ", e);
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
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_DELETE_VACANCY);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild delete Vacancy: ", e);
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
	public void addTranslateVacancy(Vacancy vacancy, String lang) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_ADD_TRANSLATION_VACANCY);
			ps.setInt(1, vacancy.getIdVacancy());
			ps.setString(2, lang);
			ps.setString(3, vacancy.getName());
			ps.setString(4, vacancy.getDescription());
			ps.setString(5, vacancy.getDuty());
			ps.setString(6, vacancy.getCondition());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild create translation of Vacancy: ", e);
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
	public void updateTranslateVacancy(Vacancy vacancy, String lang) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_UPDATE_TRANSLATION_VACANCY);
			ps.setString(1, vacancy.getName());
			ps.setString(2, vacancy.getDescription());
			ps.setString(3, vacancy.getDuty());
			ps.setString(4, vacancy.getCondition());
			ps.setInt(5, vacancy.getIdVacancy());
			ps.setString(6, lang);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild update translation of Vacancy: ", e);
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
	public int selectCountVacancy() throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		int countVacancy = 0;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_COUNT_VACANCY);
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

	@Override
	public Vacancy selectVacancyById(int idVacancy, String lang) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			if (lang.equals(SQLField.DEFAULT_LANGUAGE)) {
				ps = conn.prepareStatement(SQL_SELECT_VACANCY_BY_ID);
				ps.setInt(1, idVacancy);
			} else {
				ps = conn.prepareStatement(SQL_SELECT_TRANSLATE_VACANCY_BY_ID);
				ps.setString(1, lang);
				ps.setInt(2, idVacancy);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				Vacancy vacancy = new Vacancy();
				vacancy.setIdVacancy(rs.getInt(1));
				vacancy.setName(rs.getString(2));
				vacancy.setSalary(rs.getInt(3));
				vacancy.setCurrency(CurrencyType.valueOf(rs.getString(4).toUpperCase()));
				vacancy.setPublishDate(rs.getDate(5));
				vacancy.setDescription(rs.getString(6));
				vacancy.setDuty(rs.getString(7).replace("\n", "<br>"));
				vacancy.setCondition(rs.getString(8).replace("\n", "<br>"));
				vacancy.setEmploymentType(EmploymentType.valueOf(rs.getString(9).toUpperCase().replace(' ', '_')));
				vacancy.setActive(ActiveType.valueOf(rs.getString(10).toUpperCase().replace(' ', '_')));
				vacancy.setHotType(HotType.valueOf(rs.getString(11).toUpperCase().replace(' ', '_')));
				vacancy.setHrEmail(rs.getString(12));
				return vacancy;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find vacancy: ", e);
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

	}

	@Override
	public List<Vacancy> selectAllVacancy(String lang) throws DAOException {
		List<Vacancy> vacancy = new ArrayList<Vacancy>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			if (lang.equals(SQLField.DEFAULT_LANGUAGE)) {
				ps = conn.prepareStatement(SQL_SELECT_ALL_VACANCY);
			} else {
				ps = conn.prepareStatement(SQL_SELECT_ALL_TRANSL_VACANCY);
				ps.setString(1, lang);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				vacancy.add(getVacacnyFromResultSet(rs));
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

		return vacancy;
	}

	@Override
	public List<Vacancy> selectVacancyByHrEmail(String hrEmail, String lang, int pageNum, int amountPerPage)
			throws DAOException {
		List<Vacancy> vacancy = new ArrayList<Vacancy>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			if (lang.equals(SQLField.DEFAULT_LANGUAGE)) {
				ps = conn.prepareStatement(SQL_SELECT_VACANCY_BY_HREMAIL);
				ps.setString(1, hrEmail);
				ps.setInt(2, pageNum);
				ps.setInt(3, amountPerPage);

			} else {
				ps = conn.prepareStatement(SQL_SELECT_TRANSL_VAC_BY_HREMAIL);
				ps.setString(1, lang);
				ps.setString(2, hrEmail);
				ps.setInt(3, pageNum);
				ps.setInt(4, amountPerPage);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				vacancy.add(getVacacnyFromResultSet(rs));
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

		return vacancy;
	}

	@Override
	public int selectCountVacancyByHrEmail(String hrEmail) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		int countVacancy = 0;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_COUNT_VACANCY_BY_HR_EMAIL);
			ps.setString(1, hrEmail);
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

	@Override
	public List<Vacancy> selectVacancyLike(String name) throws DAOException {
		List<Vacancy> vacancy = new ArrayList<Vacancy>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_VACANCY_LIKE);
			ps.setString(1, name);

			rs = ps.executeQuery();
			while (rs.next()) {
				vacancy.add(getVacacnyFromResultSet(rs));
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

		return vacancy;
	}

	@Override
	public List<Vacancy> selectAllActiveVacancy(String lang, int pageNum, int amountPerPage) throws DAOException {
		List<Vacancy> vacancyList = new ArrayList<Vacancy>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			if (lang.equals(SQLField.DEFAULT_LANGUAGE)) {
				ps = conn.prepareStatement(SQL_SELECT_ALL_ACTIVE_VACANCY);
				ps.setInt(1, pageNum);
				ps.setInt(2, amountPerPage);
			} else {
				ps = conn.prepareStatement(SQL_SELECT_ALL_TRANSL_ACTIVE_VACANCY);
				ps.setString(1, lang);
				ps.setInt(2, pageNum);
				ps.setInt(3, amountPerPage);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				vacancyList.add(getVacacnyFromResultSet(rs));
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

		logger.debug("DBVacancyDAO.selectAllActiveResume() - pageNum={},amountPerPage={},vacancy = {}", pageNum,
				amountPerPage, vacancyList);
		return vacancyList;
	}

	@Override
	public int selectCountActiveVacancy() throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		int countActiveVacancy = 0;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_COUNT_ALL_ACTIVE_VACANCY);
			rs = ps.executeQuery();
			if (rs.next()) {
				countActiveVacancy = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find count: ", e);
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

		logger.debug("DBVacancyDAO.selectCountAllActiveUser() - count = {}", countActiveVacancy);
		return countActiveVacancy;

	}

	@Override
	public void activateVacancy(int idVacancy) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_ACTIVATE_VACANCY);
			ps.setDate(1, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			ps.setInt(2, idVacancy);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild to activate vacancy: ", e);
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
	public void deactivateVacancy(int idVacancy) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_DEACTIVATE_VACANCY);
			ps.setInt(1, idVacancy);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild to deactivate vacancy: ", e);
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
	public void hotVacancy(int idVacancy) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_HOT_VACANCY);
			ps.setInt(1, idVacancy);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Faild to hot vacancy: ", e);
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
	public List<Vacancy> selectAllHotVacancy(String lang) throws DAOException {
		List<Vacancy> vacancyList = new ArrayList<Vacancy>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			if (lang.equals(SQLField.DEFAULT_LANGUAGE)) {
				ps = conn.prepareStatement(SQL_SELECT_ALL_HOT_VACANCY);
			} else {
				ps = conn.prepareStatement(SQL_SELECT_ALL_TRANSL_HOT_VACANCY);
				ps.setString(1, lang);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				vacancyList.add(getVacacnyFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find hot vacancy: ", e);
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

		logger.debug("DBVacancyDAO.selectAllHotResume() - vacancy = {}", vacancyList);
		return vacancyList;

	}

	private Vacancy getVacacnyFromResultSet(ResultSet set) throws SQLException {
		Vacancy vacancy = new Vacancy();
		vacancy.setIdVacancy(set.getInt(1));
		vacancy.setName(set.getString(2));
		vacancy.setSalary(set.getInt(3));
		vacancy.setCurrency(CurrencyType.valueOf(set.getString(4).toUpperCase()));
		vacancy.setPublishDate(set.getDate(5));
		vacancy.setDescription(set.getString(6));
		vacancy.setDuty(set.getString(7));
		vacancy.setCondition(set.getString(8));
		vacancy.setEmploymentType(EmploymentType.valueOf(set.getString(9).toUpperCase().replace(' ', '_')));
		vacancy.setActive(ActiveType.valueOf(set.getString(10).toUpperCase().replace(' ', '_')));
		vacancy.setHotType(HotType.valueOf(set.getString(11).toUpperCase().replace(' ', '_')));
		vacancy.setHrEmail(set.getString(12));
		return vacancy;
	}

	@Override
	public Vacancy selectNormalVacancyById(int idVacancy) throws DAOException {
		Vacancy vacancy = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_VACANCY_BY_ID);
			ps.setInt(1, idVacancy);
			rs = ps.executeQuery();
			if (rs.next()) {
				vacancy = getVacacnyFromResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find vacancy: ", e);
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
		return vacancy;

	}

	@Override
	public Vacancy selectTranslVacancyById(int idVacancy) throws DAOException {
		Vacancy vacancy = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_ONLY_TRANSLATE_VACANCY_BY_ID);
			ps.setInt(1, idVacancy);
			rs = ps.executeQuery();
			if (rs.next()) {
				vacancy = getTraslVacacnyFromResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find vacancy: ", e);
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
		return vacancy;

	}

	private Vacancy getTraslVacacnyFromResultSet(ResultSet set) throws SQLException {
		Vacancy vacancy = new Vacancy();
		vacancy.setIdVacancy(set.getInt(1));
		vacancy.setName(set.getString(2));
		vacancy.setDescription(set.getString(3));
		vacancy.setDuty(set.getString(4));
		vacancy.setCondition(set.getString(5));
		return vacancy;
	}

	@Override
	public boolean translExist(int idVacancy) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_ONLY_TRANSLATE_VACANCY_BY_ID);
			ps.setInt(1, idVacancy);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException("Faild to find vacancy: ", e);
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
	}

}