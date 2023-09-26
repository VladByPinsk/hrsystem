package by.training.hrsystem.service;

import by.training.hrsystem.domain.Interview;
import by.training.hrsystem.service.exeption.ServiceException;
import java.util.List;

public interface InterviewService {
  void addInterview(String interivewType, String dateInterview, String idVerify)
      throws ServiceException;

  void deleteInterview(String idInterview) throws ServiceException;

  List<Interview> selectInterviewByVerify(String idVerify) throws ServiceException;
}
