package by.training.hrsystem.service;

import by.training.hrsystem.service.exeption.ServiceException;

public interface InitConnectionService {
	void initConnection() throws ServiceException;

	void destroyConnection() throws ServiceException;
}
