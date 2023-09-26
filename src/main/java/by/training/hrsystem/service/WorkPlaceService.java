package by.training.hrsystem.service;

import by.training.hrsystem.domain.WorkPlace;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.workplace.ListWorkPlaceIsEmptyServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongCompanyNameServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateBeginServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateEndServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongDateServiceException;
import by.training.hrsystem.service.exeption.workplace.WrongPositionServiceException;
import java.util.List;

public interface WorkPlaceService {
	void addWorkplace(String companyName, String position, String dateBegin, String dateEnd, String idResume)
			throws
            ServiceException;

	void updateWorkplace(String companyName, String position, String dateBegin, String dateEnd, String idWorkPlace)
			throws
            ServiceException;

	void deleteWorkplace(String idWorPlace) throws ServiceException;

	List<WorkPlace> selectWorkPlaceByIdResume(String idResume)
			throws ServiceException;
}
