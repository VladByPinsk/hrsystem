package by.training.hrsystem.service;

import java.util.List;

import by.training.hrsystem.domain.Interview;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.interview.InterviewServiceException;
import by.training.hrsystem.service.exeption.interview.WrongDateInterviewServiceException;

public interface InterviewService {
	void addInterview(String interivewType, String dateInterview, String idVerify)
			throws WrongDateInterviewServiceException, InterviewServiceException, ServiceException;

	void deleteInterview(String idInterview) throws ServiceException;

	List<Interview> selectInterviewByVerify(String idVerify) throws ServiceException;

}
