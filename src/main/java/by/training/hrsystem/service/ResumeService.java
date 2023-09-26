package by.training.hrsystem.service;

import java.util.List;

import by.training.hrsystem.domain.Resume;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.resume.ListResumeIsEmptyServiceException;
import by.training.hrsystem.service.exeption.resume.WrongResumeNameServiceException;

public interface ResumeService {
  void addResume(String name, String military, String email)
      throws ServiceException;

  void updateResume(String name, String military, String idResume)
      throws ServiceException;

  void deleteResume(String idResume) throws ServiceException;

  List<Resume> selectResumeByEmail(String email, int first, int perPage)
      throws ServiceException;

  List<Resume> selectResumeForVacancy(String applicantEmail) throws ServiceException;

  int countAllResume() throws ServiceException;

  int countVacancyByEmail(String hrEmail) throws ServiceException;

  Resume selectResumeById(String idResume) throws ServiceException;

  List<Resume> selectListResumeByVacancy(String idVacancy) throws ServiceException;

  List<Resume> selectLeftResume(String idVacancy, String applicantEmail) throws ServiceException;
}
