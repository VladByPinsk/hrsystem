package by.training.hrsystem.service;

import by.training.hrsystem.dao.impl.DBEducationDao;
import by.training.hrsystem.domain.Education;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.education.WrongCourseServiceException;
import by.training.hrsystem.service.exeption.education.WrongDepartmentServiceException;
import by.training.hrsystem.service.exeption.education.WrongEducationServiceException;
import by.training.hrsystem.service.exeption.education.WrongFacultyServiceException;
import by.training.hrsystem.service.exeption.education.WrongGradYearServiceException;
import by.training.hrsystem.service.exeption.education.WrongInstitutionServiceException;
import by.training.hrsystem.service.exeption.education.WrongPostGraduateServiceException;
import java.util.List;

/**
 * Interface {@code EducationService} describes interaction logic command layer with DAO layer just
 * for {@link by.training.hrsystem.domain.Education Education} objects.
 *
 * @author Vladislav
 * @see by.training.hrsystem.domain.Education
 */
public interface EducationService {
  /**
   * Method {@code addEducation} receiving string fields from command layer, validate and parse
   * them(if its required), create an object on the basis of these fields and send it into method
   * {@code add} class {@link DBEducationDao DBEducationDAO} through
   * the abstract factory.
   *
   * @param institution String filed receiving from command layer.
   * @param faculty String filed receiving from command layer.
   * @param department String filed receiving from command layer.
   * @param education String filed receiving from command layer.
   * @param course String filed receiving from command layer.
   * @param grandYer String filed receiving from command layer.
   * @param postgraduate String filed receiving from command layer.
   * @param idResume String filed receiving from command layer.
   * @throws WrongInstitutionServiceException if parameter institution not valid.
   * @throws WrongFacultyServiceException if parameter faculty not valid.
   * @throws WrongDepartmentServiceException if parameter department not valid.
   * @throws WrongEducationServiceException if parameter education not valid.
   * @throws WrongCourseServiceException if parameter course not valid.
   * @throws WrongGradYearServiceException if parameter grandYer not valid.
   * @throws WrongPostGraduateServiceException if parameter postgraduate not valid.
   * @throws ServiceException if something goes wrong on DAO layer or when parse string fields.
   */
  void addEducation(
      String institution,
      String faculty,
      String department,
      String education,
      String course,
      String grandYer,
      String postgraduate,
      String idResume)
      throws WrongInstitutionServiceException,
          WrongFacultyServiceException,
          WrongDepartmentServiceException,
          WrongEducationServiceException,
          WrongCourseServiceException,
          WrongGradYearServiceException,
          WrongPostGraduateServiceException,
          ServiceException;

  /**
   * Method {@code updateEducation} receiving string fields from command layer, validate and parse
   * them(if its required), create an object on the basis of these fields and send it into method
   * {@code update} class {@link DBEducationDao DBEducationDAO}
   * through the abstract factory.
   *
   * @param institution String filed receiving from command layer.
   * @param faculty String filed receiving from command layer.
   * @param department String filed receiving from command layer.
   * @param education String filed receiving from command layer.
   * @param course String filed receiving from command layer.
   * @param grandYer String filed receiving from command layer.
   * @param postgraduate String filed receiving from command layer.
   * @param idEducation String filed receiving from command layer.
   * @throws WrongInstitutionServiceException if parameter institution not valid.
   * @throws WrongFacultyServiceException if parameter faculty not valid.
   * @throws WrongDepartmentServiceException if parameter department not valid.
   * @throws WrongEducationServiceException if parameter education not valid.
   * @throws WrongCourseServiceException if parameter course not valid.
   * @throws WrongGradYearServiceException if parameter grandYer not valid.
   * @throws WrongPostGraduateServiceException if parameter postgraduate not valid.
   * @throws ServiceException if something goes wrong on DAO layer or when parse string fields.
   */
  void updateEducation(
      String institution,
      String faculty,
      String department,
      String education,
      String course,
      String gradYear,
      String postgraduate,
      String idEducation)
      throws WrongInstitutionServiceException,
          WrongFacultyServiceException,
          WrongDepartmentServiceException,
          WrongEducationServiceException,
          WrongCourseServiceException,
          WrongGradYearServiceException,
          WrongPostGraduateServiceException,
          ServiceException;

  /**
   * Method {@code deleteEducation} receiving string field id education from the command layer,
   * parse it and sends it into method {@code delete} class {@link
   * DBEducationDao DBEducationDAO} through the abstract factory.
   *
   * @param idEducation String filed receiving from command layer.
   * @throws ServiceException if something goes wrong on DAO layer or when parse string fields.
   */
  void deleteEducation(String idEducation) throws ServiceException;

  /**
   * Method {@code updateEducation} receiving string field id resume from command layer, parse it,
   * and by that id receives collection of education objects from the DAO layer, by id resume.
   *
   * @param idResume String filed receiving from command layer.
   * @return list of education
   * @throws ServiceException if something goes wrong on DAO layer or when parse string fields.
   */
  List<Education> selectEducationbyIdResume(String idResume) throws ServiceException;
}
