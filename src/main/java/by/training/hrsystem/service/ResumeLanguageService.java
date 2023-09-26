package by.training.hrsystem.service;

import by.training.hrsystem.domain.ResumeLanguage;
import by.training.hrsystem.service.exeption.ServiceException;
import java.util.List;

public interface ResumeLanguageService {
	void addLanguage(String name, String skillLevel, String idResume)
			throws ServiceException;

	void updateLanguage(String name, String skillLevel, String idLanguage)
			throws ServiceException;

	void deleteLanguage(String idLanguage) throws ServiceException;

	List<ResumeLanguage> selectLanguageByIdResume(String idResume) throws ServiceException;

}
