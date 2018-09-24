package by.training.hrsystem.service;

import by.training.hrsystem.domain.User;
import by.training.hrsystem.service.exeption.ServiceException;

public interface UserService {
	User login(String email, String password) throws ServiceException;

	User registration(String email, String password, String copyPass, String surname, String name, String secondName,
			String skype, String contcactPhone, String birthDate, String role) throws ServiceException;

	void updateProfile(String surname, String name, String secondName, String skype,
			String contcactPhone, String birthDate, String email) throws ServiceException;

	User selectUserByEmail(String email) throws ServiceException;

	User selectUserByIdVacancy(String idVacancy) throws ServiceException;

	User selectUserByIdResume(String idResume) throws ServiceException;

	int countAllApplicants() throws ServiceException;

	boolean userExist(String email, String password) throws ServiceException;
}
