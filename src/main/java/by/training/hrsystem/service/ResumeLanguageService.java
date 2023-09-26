package by.training.hrsystem.service;

import java.util.List;

import by.training.hrsystem.domain.ResumeLanguage;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.languagelevel.LanguageLevelServiceException;
import by.training.hrsystem.service.exeption.languagelevel.LanguageNameServiceException;

public interface ResumeLanguageService {
<<<<<<< Updated upstream
	void addLanguage(String name, String skillLevel, String idResume)
			throws LanguageNameServiceException, LanguageLevelServiceException, ServiceException;

	void updateLanguage(String name, String skillLevel, String idLanguage)
			throws LanguageNameServiceException, LanguageLevelServiceException, ServiceException;
=======
  void addLanguage(String name, String skillLevel, String idResume) throws ServiceException;

  void updateLanguage(String name, String skillLevel, String idLanguage) throws ServiceException;
>>>>>>> Stashed changes

  void deleteLanguage(String idLanguage) throws ServiceException;

  List<ResumeLanguage> selectLanguageByIdResume(String idResume) throws ServiceException;
}
