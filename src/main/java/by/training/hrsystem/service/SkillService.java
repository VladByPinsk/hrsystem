package by.training.hrsystem.service;

import by.training.hrsystem.domain.Skill;
import by.training.hrsystem.service.exeption.ServiceException;
import java.util.List;

public interface SkillService {
	void addSkill(String name, String raiting, String idResume)
			throws WrongSkillNameServiceException, WrongRaitingServiceException, ServiceException;

	void updateSkill(String name, String raiting, String idSkill)
			throws WrongSkillNameServiceException, WrongRaitingServiceException, ServiceException;

	void deleteSkill(String idSkill) throws ServiceException;

	List<Skill> selectSkillByIdResume(String idResume) throws ServiceException;
}
