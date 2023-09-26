package by.training.hrsystem.service.impl;

import by.training.hrsystem.dao.SkillDAO;
import by.training.hrsystem.dao.exception.DAOException;
import by.training.hrsystem.dao.factory.DAOFactory;
import by.training.hrsystem.domain.Skill;
import by.training.hrsystem.service.SkillService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.exeption.skill.WrongRaitingServiceException;
import by.training.hrsystem.service.exeption.skill.WrongSkillNameServiceException;
import by.training.hrsystem.service.parser.Parser;
import by.training.hrsystem.service.parser.exception.ParserException;
import by.training.hrsystem.service.validation.Validation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SkillServiceImpl implements SkillService {
	private static final Logger logger = LogManager.getLogger(SkillServiceImpl.class);

	@Override
	public void addSkill(String name, String raiting, String idResume)
			throws WrongSkillNameServiceException, WrongRaitingServiceException, ServiceException {

		logger.debug("SkillServiceImpl.addSkill() : user's data is valid (name = {}, raiting={}, idResume={})", name,
				raiting, idResume);

		if (!Validation.validateStringField(name)) {
			throw new WrongSkillNameServiceException("wrong skillName");
		}
		if (raiting == null) {
			throw new WrongRaitingServiceException("wrong raiting");
		}

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			SkillDAO skillDAO = daoFactory.getSkillDAO();

			Skill skill = new Skill();
			skill.setName(name);
			skill.setRaiting(Parser.fromStringToSkill(raiting));
			skill.setIdResume(Parser.parseStringtoInt(idResume));

			skillDAO.add(skill);

		} catch (DAOException | ParserException e) {
			throw new ServiceException("Service layer: cannot make a new skill", e);
		}

	}

	@Override
	public void updateSkill(String name, String raiting, String idSkill)
			throws WrongSkillNameServiceException, WrongRaitingServiceException, ServiceException {
		if (!Validation.validateStringField(name)) {
			logger.debug("SkillServiceImpl.updateSkill() : user's data is valid (name = {}, raiting={}, idSkill={})",
					name, raiting, idSkill);
			throw new WrongSkillNameServiceException("wrong skillName");
		}
		if (raiting == null) {
			throw new WrongRaitingServiceException("wrong raiting");
		}
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			SkillDAO skillDAO = daoFactory.getSkillDAO();
			Skill skill = new Skill();
			skill.setName(name);
			skill.setRaiting(Parser.fromStringToSkill(raiting));
			skill.setIdSkill(Parser.parseStringtoInt(idSkill));
			skillDAO.update(skill);
		} catch (DAOException | ParserException e) {
			throw new ServiceException("Service layer: cannot update skill", e);
		}

	}

	@Override
	public void deleteSkill(String idSkill) throws ServiceException {
		logger.debug("SkillServiceImpl.deleteSkill() : idSkill={}", idSkill);
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			SkillDAO skillDAO = daoFactory.getSkillDAO();
			skillDAO.delete(Parser.parseStringtoInt(idSkill));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can not delete skill");
		}

	}

	@Override
	public List<Skill> selectSkillByIdResume(String idResume) throws ServiceException {
		logger.debug("SkillServiceImpl.selectSkillByIdResume(): idResume={}", idResume);
		List<Skill> listSkill = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			SkillDAO skillDAO = daoFactory.getSkillDAO();

			listSkill = skillDAO.getSkillByIdResume(Parser.parseStringtoInt(idResume));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can not show list of skills");
		}
		return listSkill;
	}
}
