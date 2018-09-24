package by.training.hrsystem.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.training.hrsystem.dao.UserDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.domain.User;
import by.training.hrsystem.domain.role.Role;
import junit.framework.Assert;

public class DBUserDAOTest {
	private static ConnectionPool connectionPool;
	private static UserDAO userDAO;

	@BeforeClass
	public static void init() throws ConnectionPoolException, DAOException {
		connectionPool = ConnectionPool.getInstance();
		connectionPool.initConnectionPool();
		userDAO = DAOFactory.getInstance().getUserDAO();
	}

	@AfterClass
	public static void dispose() throws ConnectionPoolException {
		connectionPool.dispose();
	}

	@Test
	public void testRegistrateUser() throws DAOException, ParseException {
		User user = createUser();
		userDAO.add(user);

		User actualUser = userDAO.getUserByEmail(user.getEmail());
		Assert.assertEquals(user.getEmail(), actualUser.getEmail());
		Assert.assertEquals(user.getSurname(), actualUser.getSurname());
		Assert.assertEquals(user.getName(), actualUser.getName());
		Assert.assertEquals(user.getSecondName(), actualUser.getSecondName());
		Assert.assertEquals(user.getSkype(), actualUser.getSkype());
		Assert.assertEquals(user.getContactPhone(), actualUser.getContactPhone());
		Assert.assertEquals(user.getBirthDate(), actualUser.getBirthDate());
		Assert.assertEquals(user.getRole(), actualUser.getRole());
		userDAO.deleteUser(user.getEmail());
	}

	@Test
	public void testUpdateUser() throws ParseException, DAOException {
		User user = createUser();
		userDAO.add(user);

		String value = "2014-02-05";
		SimpleDateFormat originForm = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = originForm.parse(value);
		User updateUser = new User();

		updateUser.setPassword("Vlad214248");
		updateUser.setSurname("SurnameUpdate");
		updateUser.setName("NameUpdate");
		updateUser.setSecondName("SecondNameUpdate");
		updateUser.setSkype("SkypeUpdate");
		updateUser.setContactPhone(5458748);
		updateUser.setBirthDate(date);
		updateUser.setEmail("test2");

		userDAO.update(updateUser);
		User actualUser = userDAO.getUserByEmail(updateUser.getEmail());
		Assert.assertEquals(updateUser.getEmail(), actualUser.getEmail());
		Assert.assertEquals(updateUser.getSurname(), actualUser.getSurname());
		Assert.assertEquals(updateUser.getName(), actualUser.getName());
		Assert.assertEquals(updateUser.getSecondName(), actualUser.getSecondName());
		Assert.assertEquals(updateUser.getSkype(), actualUser.getSkype());
		Assert.assertEquals(updateUser.getContactPhone(), actualUser.getContactPhone());
		Assert.assertEquals(updateUser.getBirthDate(), actualUser.getBirthDate());

		userDAO.deleteUser(user.getEmail());
	}

	@Test
	public void testGetUserByEmail() throws DAOException, ParseException {
		User user = createUser();
		userDAO.add(user);
		User actualUser = userDAO.getUserByEmail(user.getEmail());
		Assert.assertEquals(user.getEmail(), actualUser.getEmail());
		userDAO.deleteUser(user.getEmail());
	}

	@Test
	public void testDeleteUser() throws DAOException, ParseException {
		User user = createUser();
		userDAO.add(user);
		userDAO.deleteUser(user.getEmail());
		Assert.assertNull(userDAO.getUserByEmail(user.getEmail()));
	}

	private User createUser() throws ParseException {
		String value = "2014-02-05";
		SimpleDateFormat originForm = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = originForm.parse(value);
		User user = new User();
		user.setEmail("test2");
		user.setPassword("Vlad214248");
		user.setSurname("Surname");
		user.setName("Name");
		user.setSecondName("SecondName");
		user.setSkype("Skype");
		user.setContactPhone(5458748);
		user.setBirthDate(date);
		user.setRole(Role.HR);
		return user;
	}

}
