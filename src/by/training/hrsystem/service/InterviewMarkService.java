package by.training.hrsystem.service;

import java.util.List;

import by.training.hrsystem.domain.InterviewMark;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.interviewmark.WrongMarkSkillServiceException;

public interface InterviewMarkService {

	void addMark(String skill, String mark, String idInterview) throws WrongMarkSkillServiceException, ServiceException;

	void deleteInterviewMark(String idMark) throws ServiceException;

	List<InterviewMark> selectMarkOfTechicalInterview(String idVerify) throws ServiceException;

	List<InterviewMark> selectMarkOfPreliminaryInterview(String idVerify) throws ServiceException;

}
