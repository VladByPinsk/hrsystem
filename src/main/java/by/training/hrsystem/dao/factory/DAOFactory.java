package by.training.hrsystem.dao.factory;

import by.training.hrsystem.dao.EducationDAO;
import by.training.hrsystem.dao.InterviewDAO;
import by.training.hrsystem.dao.InterviewMarkDAO;
import by.training.hrsystem.dao.ResumeDAO;
import by.training.hrsystem.dao.ResumeLangugaeDAO;
import by.training.hrsystem.dao.SkillDAO;
import by.training.hrsystem.dao.UserDAO;
import by.training.hrsystem.dao.VacancyDAO;
import by.training.hrsystem.dao.VerifyDAO;
import by.training.hrsystem.dao.WorkPlaceDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;

public abstract class DAOFactory {
  private static final int MY_SQL = 1;

  private static final DAOFactory MY_SQL_DAOFACTORY = new MySQLDAOFactory();

  public static DAOFactory getInstance() throws DAOException {
    int factoryType = readConfig();
    switch (factoryType) {
      case MY_SQL:
        return MY_SQL_DAOFACTORY;
      default:
        throw new DAOException("Wrong config");
    }
  }

  private static int readConfig() {
    return MY_SQL;
  }

  public abstract EducationDAO getEducationDAO();

  public abstract InterviewDAO getInterviewDAO();

  public abstract InterviewMarkDAO getInterviewMarkDAO();

  public abstract ResumeDAO getResumeDAO();

  public abstract ResumeLangugaeDAO getResumeLanguageDAO();

  public abstract SkillDAO getSkillDAO();

  public abstract UserDAO getUserDAO();

  public abstract VacancyDAO getVacancyDAO();

  public abstract VerifyDAO getVerifyDAO();

  public abstract WorkPlaceDAO getWorkPlaceDAO();

  public abstract ConnectionPool getConnectionPool() throws ConnectionPoolException;
}
