package by.training.hrsystem.dao.impl;

import by.training.hrsystem.dao.UserDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.role.Role;
import org.apache.commons.codec.binary.Base64;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code DBUserDAO} implements {@link by.training.hrsystem.dao.UserDAO UserDAO} and override
 * all methods located at the interface.
 *
 * @author Vladislav
 * @see by.training.hrsystem.dao.UserDAO
 * @see by.training.hrsystem.domain.User
 */
public class DBUserDAO implements UserDAO {

  private static final Logger logger = LogManager.getRootLogger();
  private static final String SQL_ADD_USER =
      "INSERT INTO user (email, password, surname, name, secondname, photo, skype, contact_phone, birth_date, role) "
          + "VALUES (?, md5(?), ?, ?, ?, ?, ?, ?, ?, ?);";
  private static final String SQL_UPDATE_USER =
      "UPDATE user SET surname=?, name=?, secondname=?, skype=?, contact_phone=?, birth_date=? WHERE email=?;";
  private static final String SQL_GET_USER_BY_EMAIL_PASS =
      "SELECT * FROM user WHERE email=? and password=md5(?);";
  private static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?;";
  private static final String SQL_DELETE_USER = "DELETE FROM user WHERE email=?;";
  private static final String SQL_SELECT_USER_BY_ID_VACANCY =
      "SELECT user.email, user.surname, user.name, user.secondname, user.birth_date, user.skype,user.contact_phone,user.photo "
          + "FROM user LEFT JOIN vacancy on user.email= vacancy.email WHERE vacancy.id_vacancy=?;";
  private static final String SQL_SELECT_USER_BY_ID_RESUME =
      "SELECT user.email, user.surname, user.name, user.secondname, user.birth_date, user.skype,user.contact_phone,user.photo "
          + "FROM user LEFT JOIN resume on user.email= resume.email WHERE resume.id_resume=?;";
  private static final String SQL_SELECT_COUNT_APPLICANTS =
      "SELECT COUNT(email) FROM user WHERE role='applicant';";

  @Override
  public void add(User entity) throws DAOException {
    logger.debug("DBUserDAO.addUser() - user = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_ADD_USER);
      ps.setString(1, entity.getEmail());
      ps.setString(2, entity.getPassword());
      ps.setString(3, entity.getSurname());
      ps.setString(4, entity.getName());
      ps.setString(5, entity.getSecondName());
      ps.setString(6, entity.getPhoto());
      ps.setString(7, entity.getSkype());
      ps.setInt(8, entity.getContactPhone());
      ps.setDate(9, new Date(entity.getBirthDate().getTime()));
      ps.setString(10, entity.getRole().getRole());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild insert new User: ", e);
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
  public void update(User entity) throws DAOException {
    logger.debug("DBUserDAO.updateUser() - user = {}", entity);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_UPDATE_USER);
      ps.setString(1, entity.getSurname());
      ps.setString(2, entity.getName());
      ps.setString(3, entity.getSecondName());
      ps.setString(4, entity.getSkype());
      ps.setInt(5, entity.getContactPhone());
      ps.setDate(6, new Date(entity.getBirthDate().getTime()));
      ps.setString(7, entity.getEmail());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild update new User: ", e);
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
  public void delete(int id) throws DAOException {}

  @Override
  public void deleteUser(String email) throws DAOException {
    logger.debug("DBUserDAO.delete() - email = {}", email);
    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_DELETE_USER);
      ps.setString(1, email);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("Faild delete new User: ", e);
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
  public User userAuthentication(String email, String password) throws DAOException {
    User user = null;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_GET_USER_BY_EMAIL_PASS);
      ps.setString(1, email);
      ps.setString(2, password);
      rs = ps.executeQuery();
      if (rs.next()) {
        user = getUserFromResultSet(rs);
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
        logger.error("Faild to close connection or ps or rs", e);
      }
    }
    return user;
  }

  @Override
  public User getUserByEmail(String email) throws DAOException {
    logger.debug("DBUserDAO.getUserByEmail - email = {}", email);
    User user = null;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_GET_USER_BY_EMAIL);
      ps.setString(1, email);
      rs = ps.executeQuery();
      if (rs.next()) {
        user = getUserFromResultSet(rs);
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
    return user;
  }

  @Override
  public User getUserByIdVacancy(int idVcancy) throws DAOException {
    logger.debug("DBUserDAO.getUserByIdVacancy - idVacancy = {}", idVcancy);
    User user = null;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_USER_BY_ID_VACANCY);
      ps.setInt(1, idVcancy);
      rs = ps.executeQuery();
      if (rs.next()) {
        user = new User();
        user.setEmail(rs.getString(1));
        user.setSurname(rs.getString(2));
        user.setName(rs.getString(3));
        user.setSecondName(rs.getString(4));
        user.setBirthDate(rs.getDate(5));
        user.setSkype(rs.getString(6));
        user.setContactPhone(rs.getInt(7));
      }
      return user;
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
  }

  @Override
  public int countAllApplicants() throws DAOException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    int countApplicants = 0;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_COUNT_APPLICANTS);
      rs = ps.executeQuery();
      if (rs.next()) {
        countApplicants = rs.getInt(1);
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

    return countApplicants;
  }

  private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
    User user = new User();
    user.setEmail(resultSet.getString(1));
    user.setPassword(resultSet.getString(2));
    user.setSurname(resultSet.getString(3));
    user.setName(resultSet.getString(4));
    user.setSecondName(resultSet.getString(5));
    if (resultSet.getString(6) != null) {
      user.setPhoto(Base64.encodeBase64String(resultSet.getBytes(6)));
    }
    user.setSkype(resultSet.getString(7));
    user.setContactPhone(resultSet.getInt(8));
    user.setBirthDate(resultSet.getDate(9));
    user.setRole((Role.valueOf(resultSet.getString(10).toUpperCase())));
    return user;
  }

<<<<<<< Updated upstream
	@Override
	public User getUserByIdVacancy(int idVcancy) throws DAOException, DAOException {
		logger.debug("DBUserDAO.getUserByIdVacancy - idVacancy = {}", idVcancy);
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_USER_BY_ID_VACANCY);
			ps.setInt(1, idVcancy);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setEmail(rs.getString(1));
				user.setSurname(rs.getString(2));
				user.setName(rs.getString(3));
				user.setSecondName(rs.getString(4));
				user.setBirthDate(rs.getDate(5));
				user.setSkype(rs.getString(6));
				user.setContactPhone(rs.getInt(7));
			}
			return user;
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

	}

	@Override
	public int countAllApplicants() throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		int countApplicants = 0;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_COUNT_APPLICANTS);
			rs = ps.executeQuery();
			if (rs.next()) {
				countApplicants = rs.getInt(1);
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

		return countApplicants;
	}

	private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setEmail(resultSet.getString(1));
		user.setPassword(resultSet.getString(2));
		user.setSurname(resultSet.getString(3));
		user.setName(resultSet.getString(4));
		user.setSecondName(resultSet.getString(5));
		if (resultSet.getString(6) != null) {
			user.setPhoto(Base64.encodeBase64String(resultSet.getBytes(6)));
		}
		user.setSkype(resultSet.getString(7));
		user.setContactPhone(resultSet.getInt(8));
		user.setBirthDate(resultSet.getDate(9));
		user.setRole((Role.valueOf(resultSet.getString(10).toUpperCase())));
		return user;
	}

	@Override
	public User getUserByIdResume(int idResume) throws DAOException, DAOException {
		logger.debug("DBUserDAO. getUserByIdResume - idResume = {}", idResume);
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_SELECT_USER_BY_ID_RESUME);
			ps.setInt(1, idResume);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setEmail(rs.getString(1));
				user.setSurname(rs.getString(2));
				user.setName(rs.getString(3));
				user.setSecondName(rs.getString(4));
				user.setBirthDate(rs.getDate(5));
				user.setSkype(rs.getString(6));
				user.setContactPhone(rs.getInt(7));
			}
			return user;
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
	}

	@Override
	public boolean userExist(String email, String password) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		try {
			pool = ConnectionPool.getInstance();
			conn = pool.takeConnection();
			ps = conn.prepareStatement(SQL_GET_USER_BY_EMAIL_PASS);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else
				return false;
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
				logger.error("Faild to close connection or ps or rs", e);
			}
		}
	}
=======
  @Override
  public User getUserByIdResume(int idResume) throws DAOException {
    logger.debug("DBUserDAO. getUserByIdResume - idResume = {}", idResume);
    User user = null;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_SELECT_USER_BY_ID_RESUME);
      ps.setInt(1, idResume);
      rs = ps.executeQuery();
      if (rs.next()) {
        user = new User();
        user.setEmail(rs.getString(1));
        user.setSurname(rs.getString(2));
        user.setName(rs.getString(3));
        user.setSecondName(rs.getString(4));
        user.setBirthDate(rs.getDate(5));
        user.setSkype(rs.getString(6));
        user.setContactPhone(rs.getInt(7));
      }
      return user;
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
  }
>>>>>>> Stashed changes

  @Override
  public boolean userExist(String email, String password) throws DAOException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConnectionPool pool = null;
    try {
      pool = ConnectionPool.getInstance();
      conn = pool.takeConnection();
      ps = conn.prepareStatement(SQL_GET_USER_BY_EMAIL_PASS);
      ps.setString(1, email);
      ps.setString(2, password);
      rs = ps.executeQuery();
      return rs.next();
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
        logger.error("Faild to close connection or ps or rs", e);
      }
    }
  }
}
