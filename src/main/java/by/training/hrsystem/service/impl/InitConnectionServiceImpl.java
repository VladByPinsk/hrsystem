package by.training.hrsystem.service.impl;

import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.dao.pool.ConnectionPool;
import by.training.hrsystem.dao.pool.exception.ConnectionPoolException;
import by.training.hrsystem.service.InitConnectionService;
import by.training.hrsystem.service.exeption.InitConnectionPoolServiceException;

public class InitConnectionServiceImpl implements InitConnectionService {
	@Override
	public void initConnection() throws InitConnectionPoolServiceException {

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			ConnectionPool connectionPool = daoFactory.getConnectionPool();
			connectionPool.initConnectionPool();
		} catch (ConnectionPoolException | DAOException e) {
			throw new InitConnectionPoolServiceException("Cannot init a pool", e);
		}
	}

	@Override
	public void destroyConnection() throws InitConnectionPoolServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			ConnectionPool connectionPool = daoFactory.getConnectionPool();
			connectionPool.dispose();
		} catch (ConnectionPoolException | DAOException e) {
			throw new InitConnectionPoolServiceException("Cannot destroy pool", e);
		}

	}
}
