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
import by.training.hrsystem.dao.impl.DBEducationDAO;
import by.training.hrsystem.dao.impl.DBInterviewDAO;
import by.training.hrsystem.dao.impl.DBInterviewMarkDAO;
import by.training.hrsystem.dao.impl.DBResumeDAO;
import by.training.hrsystem.dao.impl.DBResumeLanguageDAO;
import by.training.hrsystem.dao.impl.DBSkillDAO;
import by.training.hrsystem.dao.impl.DBUserDAO;
import by.training.hrsystem.dao.impl.DBVacancyDAO;
import by.training.hrsystem.dao.impl.DBVerifyDAO;
import by.training.hrsystem.dao.impl.DBWorkPlaceDAO;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;

public class MySQLDAOFactory extends DAOFactory {

	private final EducationDAO dbEducationDAO = new DBEducationDAO();
	private final InterviewDAO dbInterviewDAO = new DBInterviewDAO();
	private final InterviewMarkDAO dbInterviewMarkDAO = new DBInterviewMarkDAO();
	private final ResumeDAO dbResumeDAO = new DBResumeDAO();
	private final ResumeLangugaeDAO dbResumeLanguageDAO = new DBResumeLanguageDAO();
	private final SkillDAO dbSkillDAO = new DBSkillDAO();
	private final UserDAO dbUserDAO = new DBUserDAO();
	private final VacancyDAO dbVacancyDAO = new DBVacancyDAO();
	private final VerifyDAO dbVerifyDAO = new DBVerifyDAO();
	private final WorkPlaceDAO dbWorkPlaceDAO = new DBWorkPlaceDAO();

	@Override
	public EducationDAO getEducationDAO() {
		return dbEducationDAO;
	}

	@Override
	public InterviewDAO getInterviewDAO() {
		return dbInterviewDAO;
	}

	@Override
	public InterviewMarkDAO getInterviewMarkDAO() {
		return dbInterviewMarkDAO;
	}

	@Override
	public ResumeDAO getResumeDAO() {
		return dbResumeDAO;
	}

	@Override
	public ResumeLangugaeDAO getResumeLanguageDAO() {
		return dbResumeLanguageDAO;
	}

	@Override
	public SkillDAO getSkillDAO() {
		return dbSkillDAO;
	}

	@Override
	public UserDAO getUserDAO() {
		return dbUserDAO;
	}

	@Override
	public VacancyDAO getVacancyDAO() {
		return dbVacancyDAO;
	}

	@Override
	public VerifyDAO getVerifyDAO() {
		return dbVerifyDAO;
	}

	@Override
	public WorkPlaceDAO getWorkPlaceDAO() {
		return dbWorkPlaceDAO;
	}

	@Override
	public ConnectionPool getConnectionPool() throws ConnectionPoolException {
		return ConnectionPool.getInstance();
	}

}
