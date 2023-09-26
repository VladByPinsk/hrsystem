package by.training.hrsystem.service;

import java.util.List;

import by.training.hrsystem.domain.WorkPlace;
import by.training.hrsystem.service.exeption.ServiceException;
<<<<<<< Updated upstream
import by.training.hrsystem.service.exeption.workplace.ListWorkPlaceIsEmptyServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongCompanyNameServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateBeginServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateEndServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongPositionServiceException;
=======
import java.util.List;
>>>>>>> Stashed changes

public interface WorkPlaceService {
	void addWorkplace(String companyName, String position, String dateBegin, String dateEnd, String idResume)
			throws WrongCompanyNameServiceException, WrongPositionServiceException, WrongDateBeginServiceException,
			WrongDateEndServiceException, WrongDateServiceException, ServiceException;

	void updateWorkplace(String companyName, String position, String dateBegin, String dateEnd, String idWorkPlace)
			throws WrongCompanyNameServiceException, WrongPositionServiceException, WrongDateBeginServiceException,
			WrongDateEndServiceException, WrongDateServiceException, ServiceException;

	void deleteWorkplace(String idWorPlace) throws ServiceException;

	List<WorkPlace> selectWorkPlaceByIdResume(String idResume)
			throws ListWorkPlaceIsEmptyServiceException, ServiceException;
}
