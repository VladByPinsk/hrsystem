package by.training.hrsystem.service;

import java.util.List;

import by.training.hrsystem.domain.Verify;
import by.training.hrsystem.service.exeption.ServiceException;

public interface VerifyService {
	void addResumeToVacancy(String idVacancy, String idResume) throws ServiceException;

	List<Verify> verifyList(String idVacancy) throws ServiceException;

	List<Verify> passVerifyList(String idVacancy) throws ServiceException;

	Verify selectVerifyById(String idVerify) throws ServiceException;

	void verifyResumePass(String idVerify) throws ServiceException;

	void verifyResumeNotPass(String idVerify) throws ServiceException;
}
