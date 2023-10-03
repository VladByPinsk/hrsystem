package by.training.hrsystem.service.impl;

import by.training.hrsystem.dao.UserDao;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.service.UserService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.user.PasswordNotEqualsServiceException;
import by.training.hrsystem.service.exeption.user.UserWithThisEmailExistServiceException;
import by.training.hrsystem.service.exeption.user.WrongBirthDateServiceException;
import by.training.hrsystem.service.exeption.user.WrongEmailServiceException;
import by.training.hrsystem.service.exeption.user.WrongNameServiceException;
import by.training.hrsystem.service.exeption.user.WrongPasswordServiceException;
import by.training.hrsystem.service.exeption.user.WrongPhoneServiceException;
import by.training.hrsystem.service.exeption.user.WrongSecondnameServiceException;
import by.training.hrsystem.service.exeption.user.WrongSkypeServiceException;
import by.training.hrsystem.service.exeption.user.WrongSurnameServiceException;
import by.training.hrsystem.service.parser.Parser;
import by.training.hrsystem.service.parser.exception.ParserException;
import by.training.hrsystem.service.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public User login(String email, String password)
			throws ServiceException {
		logger.debug("UserServiceImpl.login() : user's data is valid (email = {}, password = {})", email, password);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDao userDAO = daoFactory.getUserDAO();
			User user = userDAO.userAuthentication(email, password);
			return user;
		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a login operation", e);
		}
	}

	@Override
	public User registration(String email, String password, String copyPass, String surname, String name,
			String secondName, String skype, String contcactPhone, String birthDate, String role)
			throws ServiceException {
		logger.debug(
				"UserServiceImpl.registration() : user's data is valid (email = {}, password = {}, "
						+ "surname = {}, name={}, secondname={}, skype={}, phone={}, birthDate={}, role={})",
				email, password, surname, name, secondName, skype, contcactPhone, birthDate, role);

		if (!Validation.validateEmail(email)) {
			throw new WrongEmailServiceException("Wrong email");
		}
		if (!Validation.validatePassword(password)) {
			throw new WrongPasswordServiceException("Wrong password");
		}
		if (!password.equals(copyPass)) {
			throw new PasswordNotEqualsServiceException("Password not equals");
		}
		if (!Validation.validateStringField(surname)) {
			throw new WrongSurnameServiceException("Wrong surname");
		}
		if (!Validation.validateStringField(name)) {
			throw new WrongNameServiceException("Wrong name");
		}
		if (!Validation.validateStringField(secondName)) {
			throw new WrongSecondnameServiceException("Wrong Surname");
		}
		if (!Validation.validateStringField(skype)) {
			throw new WrongSkypeServiceException("Wrong Skype");
		}
		if (!Validation.validatePhoneField(contcactPhone)) {
			throw new WrongPhoneServiceException("Wrong contact phone");
		}
		if (!Validation.validateFullDateField(birthDate)) {
			throw new WrongBirthDateServiceException("Wrong birht date");
		}

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDao userDAO = daoFactory.getUserDAO();
			User userWithThisEmail = userDAO.getUserByEmail(email);
			if (userWithThisEmail != null) {
				throw new UserWithThisEmailExistServiceException("User with this email exist");
			}

			User newUser = new User();
			newUser.setEmail(email);
			newUser.setPassword(password);
			newUser.setSurname(surname);
			newUser.setName(name);
			newUser.setSecondName(secondName);
			newUser.setSkype(skype);
			newUser.setContactPhone(Parser.parseStringtoInt(contcactPhone));
			newUser.setBirthDate(Parser.parseToFullDate(birthDate));
			newUser.setRole(Parser.fromStringToRole(role));
			userDAO.add(newUser);

			return newUser;
		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a registration", e);
		} catch (ParserException e) {
			throw new ServiceException("Service layer: can not parse date");
		}
	}

	@Override
	public void updateProfile(String surname, String name, String secondName, String skype, String contcactPhone,
			String birthDate, String email)
			throws
            ServiceException {
		logger.debug(
				"UserServiceImpl.updateProfile() : user's data is valid ( "
						+ "surname = {}, name={}, secondname={}, skype={}, phone={}, birthdate={},email = {})",
				surname, name, secondName, skype, contcactPhone, birthDate, email);

		if (!Validation.validateStringField(surname)) {
			throw new WrongSurnameServiceException("Wrong surname");
		}
		if (!Validation.validateStringField(name)) {
			throw new WrongNameServiceException("Wrong name");
		}
		if (!Validation.validateStringField(secondName)) {
			throw new WrongSecondnameServiceException("Wrong Surname");
		}
		if (!Validation.validateStringField(skype)) {
			throw new WrongSkypeServiceException("Wrong Skype");
		}
		if (!Validation.validatePhoneField(contcactPhone)) {
			throw new WrongPhoneServiceException("Wrong contact phone");
		}
		if (!Validation.validateFullDateField(birthDate)) {
			throw new WrongBirthDateServiceException("Wrong birht date");
		}

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDao userDAO = daoFactory.getUserDAO();
			User updateUser = new User();
			updateUser.setSurname(surname);
			updateUser.setName(name);
			updateUser.setSecondName(secondName);
			updateUser.setSkype(skype);
			updateUser.setContactPhone(Parser.parseStringtoInt(contcactPhone));
			updateUser.setBirthDate(Parser.parseToFullDate(birthDate));
			updateUser.setEmail(email);
			userDAO.update(updateUser);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a registration", e);
		} catch (ParserException e) {
			throw new ServiceException("Service layer: can not parse date");
		}

	}

	@Override
	public User selectUserByEmail(String email) throws ServiceException {
		logger.debug("UserServiceImpl.selectUserByEmail() : email = {}", email);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDao userDAO = daoFactory.getUserDAO();
			User user = userDAO.getUserByEmail(email);
			return user;
		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a selectUserByEmail operation", e);
		}
	}

	@Override
	public User selectUserByIdVacancy(String idVacancy) throws ServiceException {
		logger.debug("UserServiceImpl.selectUserByIdVacancyl() : idVacancy = {}", idVacancy);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDao userDAO = daoFactory.getUserDAO();
			User user = userDAO.getUserByIdVacancy(Parser.parseStringtoInt(idVacancy));
			return user;
		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a selectUserByIdVacancy operation", e);
		}

	}

	@Override
	public int countAllApplicants() throws ServiceException {
		int countAllApplicants = 0;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDao userDAO = daoFactory.getUserDAO();
			countAllApplicants = userDAO.countAllApplicants();
		} catch (DAOException e) {

			throw new ServiceException("Service layer: cant show count of applicants");
		}
		logger.debug("UserServiceImpl.countAllApplicants() : count={}", countAllApplicants);
		return countAllApplicants;

	}

	@Override
	public User selectUserByIdResume(String idResume) throws ServiceException {
		logger.debug("UserServiceImpl.selectUserByIdResume() : idResume = {}", idResume);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDao userDAO = daoFactory.getUserDAO();
			User user = userDAO.getUserByIdResume(Parser.parseStringtoInt(idResume));
			return user;
		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a selectUserByIdVacancy operation", e);
		}
	}

	@Override
	public boolean userExist(String email, String password) throws ServiceException {
		logger.debug("UserServiceImpl.userExist login() : user's data is valid (email = {}, password = {})", email,
				password);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDao userDAO = daoFactory.getUserDAO();
			if (userDAO.userExist(email, password)) {
				return true;
			} else {
				throw new PasswordNotEqualsServiceException("password or user not eq");
			}

		} catch (DAOException e) {
			throw new ServiceException("Service layer: cannot make a login operation", e);
		}
	}

}
