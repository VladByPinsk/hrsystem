package by.training.hrsystem.controller;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.impl.ChangeLocaleCommand;
import by.training.hrsystem.command.impl.ToIndexPageCommand;
import by.training.hrsystem.command.impl.ToRegstrationPageCommand;
import by.training.hrsystem.command.impl.UserLogOutCommand;
import by.training.hrsystem.command.impl.UserLoginCommand;
import by.training.hrsystem.command.impl.UserRegistrationCommand;
import by.training.hrsystem.command.impl.applicant.AddEducationCommand;
import by.training.hrsystem.command.impl.applicant.AddResumeCommand;
import by.training.hrsystem.command.impl.applicant.AddResumeLanguageCommand;
import by.training.hrsystem.command.impl.applicant.AddResumeToVacancyCommand;
import by.training.hrsystem.command.impl.applicant.AddSkillCommand;
import by.training.hrsystem.command.impl.applicant.AddWorkplaceCommand;
import by.training.hrsystem.command.impl.applicant.DeleteEducationCommand;
import by.training.hrsystem.command.impl.applicant.DeleteResumeCommand;
import by.training.hrsystem.command.impl.applicant.DeleteResumeLanguageCommand;
import by.training.hrsystem.command.impl.applicant.DeleteSkillCommand;
import by.training.hrsystem.command.impl.applicant.DeleteWorkPlaceCommand;
import by.training.hrsystem.command.impl.applicant.EditEducationCommand;
import by.training.hrsystem.command.impl.applicant.EditResumeCommand;
import by.training.hrsystem.command.impl.applicant.EditResumeLanguageCommand;
import by.training.hrsystem.command.impl.applicant.EditSkillCommand;
import by.training.hrsystem.command.impl.applicant.EditWorkPlaceCommand;
import by.training.hrsystem.command.impl.applicant.ToApplicantAddResumeCommand;
import by.training.hrsystem.command.impl.applicant.ToApplicantEditResumeCommand;
import by.training.hrsystem.command.impl.applicant.ToApplicantListResumeCommand;
import by.training.hrsystem.command.impl.common.EditProfileCommand;
import by.training.hrsystem.command.impl.common.SearchVacancyByNameCommand;
import by.training.hrsystem.command.impl.common.ShowResumeCommand;
import by.training.hrsystem.command.impl.common.ShowVacancyCommand;
import by.training.hrsystem.command.impl.common.ToEditProfileCommand;
import by.training.hrsystem.command.impl.common.ToPrivateOfficeCommand;
import by.training.hrsystem.command.impl.humanresources.ActivateVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.AddInterviewToApplicantCommand;
import by.training.hrsystem.command.impl.humanresources.AddMarkCommand;
import by.training.hrsystem.command.impl.humanresources.AddTranslVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.AddVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.DeactivateVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.DeleteInterviewCommand;
import by.training.hrsystem.command.impl.humanresources.DeleteInterviewMarkCommand;
import by.training.hrsystem.command.impl.humanresources.DeleteVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.EditTranslVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.EditVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.HotVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.HrPrivateOfficeShowVacancy;
import by.training.hrsystem.command.impl.humanresources.ShowApplicantsWhoLeaveResume;
import by.training.hrsystem.command.impl.humanresources.ToAddTranslVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.ToApplicantInerviewPageCommand;
import by.training.hrsystem.command.impl.humanresources.ToHrAddVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.ToHrEditVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.ToHrListVacancyCommand;
import by.training.hrsystem.command.impl.humanresources.ToPassVerifyApplicantCommand;
import by.training.hrsystem.command.impl.humanresources.ToVerifyListCommand;
import by.training.hrsystem.command.impl.humanresources.VerifyResumeNotPassCommand;
import by.training.hrsystem.command.impl.humanresources.VerifyResumePassCommand;
import java.util.HashMap;
import java.util.Map;

public final class CommandHelper {
  private static final CommandHelper INSTANCE = new CommandHelper();
  private final Map<CommandName, Command> commands = new HashMap<>();

  private CommandHelper() {
    commands.put(CommandName.USER_REGISTRARION, new UserRegistrationCommand());
    commands.put(CommandName.USER_LOGIN, new UserLoginCommand());
    commands.put(CommandName.USER_LOGOUT, new UserLogOutCommand());
    commands.put(CommandName.SWITCH_LOCALE, new ChangeLocaleCommand());
    commands.put(CommandName.TO_REGISTRATION_PAGE, new ToRegstrationPageCommand());
    commands.put(CommandName.TO_PRIVATE_OFFICE, new ToPrivateOfficeCommand());
    commands.put(CommandName.TO_INDEX_PAGE, new ToIndexPageCommand());
    commands.put(CommandName.TO_EDIT_PROFILE, new ToEditProfileCommand());
    commands.put(CommandName.EDIT_PROFILE, new EditProfileCommand());
    commands.put(CommandName.TO_APPLICANT_ADD_RESUME, new ToApplicantAddResumeCommand());
    commands.put(CommandName.ADD_RESUME, new AddResumeCommand());
    commands.put(CommandName.TO_APPLICANT_LIST_RESUME, new ToApplicantListResumeCommand());
    commands.put(CommandName.SHOW_VACANCY, new ShowVacancyCommand());
    commands.put(CommandName.TO_HR_LIST_VACANCY, new ToHrListVacancyCommand());
    commands.put(CommandName.TO_HR_ADD_VACANCY_PAGE, new ToHrAddVacancyCommand());
    commands.put(CommandName.ADD_VACANCY, new AddVacancyCommand());
    commands.put(CommandName.HR_PRIVATE_OFFICE_SHOW_VACANCY, new HrPrivateOfficeShowVacancy());
    commands.put(CommandName.DELETE_VACANCY, new DeleteVacancyCommand());
    commands.put(CommandName.ACTIVATE_VACANCY, new ActivateVacancyCommand());
    commands.put(CommandName.DEACTIVATE_VACANCY, new DeactivateVacancyCommand());
    commands.put(CommandName.HOT_VACANCY, new HotVacancyCommand());
    commands.put(CommandName.TO_APPLICANT_EDIT_RESUME, new ToApplicantEditResumeCommand());
    commands.put(CommandName.SHOW_RESUME, new ShowResumeCommand());
    commands.put(CommandName.EDIT_RESUME, new EditResumeCommand());
    commands.put(CommandName.ADD_EDUCATION, new AddEducationCommand());
    commands.put(CommandName.DELETE_EDUCATION, new DeleteEducationCommand());
    commands.put(CommandName.EDIT_EDUCATION, new EditEducationCommand());
    commands.put(CommandName.ADD_RESUME_LANGUAGE, new AddResumeLanguageCommand());
    commands.put(CommandName.DELETE_RESUME_LANGUAGE, new DeleteResumeLanguageCommand());
    commands.put(CommandName.EDIT_RESUME_LANGUAGE, new EditResumeLanguageCommand());
    commands.put(CommandName.ADD_SKILL, new AddSkillCommand());
    commands.put(CommandName.DELETE_SKILL, new DeleteSkillCommand());
    commands.put(CommandName.EDIT_SKILL, new EditSkillCommand());
    commands.put(CommandName.ADD_WORKPLACE, new AddWorkplaceCommand());
    commands.put(CommandName.EDIT_WORKPLACE, new EditWorkPlaceCommand());
    commands.put(CommandName.DELETE_WORKPLACE, new DeleteWorkPlaceCommand());
    commands.put(CommandName.SEARCH_VACANCY, new SearchVacancyByNameCommand());
    commands.put(CommandName.ADD_RESUME_TO_VACANCY, new AddResumeToVacancyCommand());
    commands.put(CommandName.TO_VERIFY_LIST, new ToVerifyListCommand());
    commands.put(CommandName.SHOW_APPLICANT_WHO_LEAVE_RESUME, new ShowApplicantsWhoLeaveResume());
    commands.put(CommandName.TO_PASS_VERIFY_APPLICANT, new ToPassVerifyApplicantCommand());
    commands.put(CommandName.TO_APPLICANT_INTERVIEW, new ToApplicantInerviewPageCommand());
    commands.put(CommandName.ADD_INTERVIEW_TO_APPLICANT, new AddInterviewToApplicantCommand());
    commands.put(CommandName.ADD_MARK, new AddMarkCommand());
    commands.put(CommandName.DELETE_RESUME, new DeleteResumeCommand());
    commands.put(CommandName.TO_ADD_TRANSL_VACANCY, new ToAddTranslVacancyCommand());
    commands.put(CommandName.ADD_TRANSL_VACANCY, new AddTranslVacancyCommand());
    commands.put(CommandName.TO_HR_EDIT_VACANCY, new ToHrEditVacancyCommand());
    commands.put(CommandName.EDIT_VACANCY, new EditVacancyCommand());
    commands.put(CommandName.VERIFY_PASS, new VerifyResumePassCommand());
    commands.put(CommandName.VERIFY_NOT_PASS, new VerifyResumeNotPassCommand());
    commands.put(CommandName.DELETE_INTERVIEW_MARK, new DeleteInterviewMarkCommand());
    commands.put(CommandName.DELETE_INTERVIEW, new DeleteInterviewCommand());
    commands.put(CommandName.EDIT_TRANSL_VACANCY, new EditTranslVacancyCommand());
  }

  public static CommandHelper getInstance() {
    return INSTANCE;
  }

  public Command getCommand(String name) {
    name = name.replace('-', '_');
    CommandName commandName = CommandName.valueOf(name.toUpperCase());
    Command command = commands.get(commandName);
    return command;
  }
}
