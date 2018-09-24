package by.training.hrsystem.service.factory;

import by.training.hrsystem.service.EducationService;
import by.training.hrsystem.service.InitConnectionService;
import by.training.hrsystem.service.InterviewMarkService;
import by.training.hrsystem.service.InterviewService;
import by.training.hrsystem.service.ResumeLanguageService;
import by.training.hrsystem.service.ResumeService;
import by.training.hrsystem.service.SkillService;
import by.training.hrsystem.service.UserService;
import by.training.hrsystem.service.VacancyService;
import by.training.hrsystem.service.VerifyService;
import by.training.hrsystem.service.WorkPlaceService;
import by.training.hrsystem.service.impl.EducationServiceImpl;
import by.training.hrsystem.service.impl.InitConnectionServiceImpl;
import by.training.hrsystem.service.impl.InterviewMarkServiceImpl;
import by.training.hrsystem.service.impl.InterviewServiceImpl;
import by.training.hrsystem.service.impl.ResumeLanguageServiceImpl;
import by.training.hrsystem.service.impl.ResumeServiceImpl;
import by.training.hrsystem.service.impl.SkillServiceImpl;
import by.training.hrsystem.service.impl.UserServiceImpl;
import by.training.hrsystem.service.impl.VacancyServiceImpl;
import by.training.hrsystem.service.impl.VerifyServiceImpl;
import by.training.hrsystem.service.impl.WorkPlaceServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private final UserService userService = new UserServiceImpl();
	private final InitConnectionService initPoolService = new InitConnectionServiceImpl();
	private final EducationService educationService = new EducationServiceImpl();
	private final ResumeLanguageService resumeLanguageService = new ResumeLanguageServiceImpl();
	private final SkillService skillService = new SkillServiceImpl();
	private final VacancyService vacancyService = new VacancyServiceImpl();
	private final WorkPlaceService workPlaceService = new WorkPlaceServiceImpl();
	private final ResumeService resumeService = new ResumeServiceImpl();
	private final VerifyService verifyService = new VerifyServiceImpl();
	private final InterviewService interviewService = new InterviewServiceImpl();
	private final InterviewMarkService interviewMarkService = new InterviewMarkServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public UserService getUserService() {
		return userService;
	}

	public InitConnectionService getInitPoolService() {
		return initPoolService;
	}

	public EducationService getEducationService() {
		return educationService;
	}

	public ResumeLanguageService getResumeLanguageService() {
		return resumeLanguageService;
	}

	public SkillService getSkillService() {
		return skillService;
	}

	public VacancyService getVacancyService() {
		return vacancyService;
	}

	public WorkPlaceService getWorkPlaceService() {
		return workPlaceService;
	}

	public ResumeService getResumeService() {
		return resumeService;
	}

	public VerifyService gerVerifyService() {
		return verifyService;
	}

	public InterviewService getInterviewService() {
		return interviewService;
	}

	public InterviewMarkService getInterviewMarkService() {
		return interviewMarkService;
	}
}
