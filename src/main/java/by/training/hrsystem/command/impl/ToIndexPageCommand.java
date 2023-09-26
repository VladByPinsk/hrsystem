package by.training.hrsystem.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hrsystem.command.Command;
import by.training.hrsystem.command.constant.Attribute;
import by.training.hrsystem.command.constant.PageName;
import by.training.hrsystem.command.util.QueryUtil;
import by.training.hrsystem.domain.Vacancy;
import by.training.hrsystem.service.ResumeService;
import by.training.hrsystem.service.UserService;
import by.training.hrsystem.service.VacancyService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;

public class ToIndexPageCommand implements Command {

  private static final Logger logger = LogManager.getLogger(ToIndexPageCommand.class);
  private static final int PAGE_NUMBER = 1;
  private static final int VACANCY_PER_PAGE = 9;

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    logger.debug("ToIndexPageCommand : execute() - start");
    String lang = (String) request.getSession().getAttribute(Attribute.LOCALE);
    HttpSession session = request.getSession(false);
    if (session == null) {
      request.getRequestDispatcher(PageName.ERROR_TIME_OUT_PAGE).forward(request, response);
      return;
    }
    int pageNumber = PAGE_NUMBER;
    if (request.getParameter(Attribute.PAGE) != null) {
      pageNumber = Integer.parseInt(request.getParameter(Attribute.PAGE));
    }
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    VacancyService vacancyService = serviceFactory.getVacancyService();
    UserService userService = serviceFactory.getUserService();
    ResumeService resumeService = serviceFactory.getResumeService();

    try {
      List<Vacancy> vacancyList =
          vacancyService.selectAllActiveVacancy(
              lang, (pageNumber - 1) * VACANCY_PER_PAGE, VACANCY_PER_PAGE);
      request.setAttribute(Attribute.VACANCIES, vacancyList);

      List<Vacancy> hotVacancyList = vacancyService.selectHotVacancy(lang);
      request.setAttribute(Attribute.HOT_VACANCIES, hotVacancyList);
      int vacancyAmount = vacancyService.countAllActiveVacancy();
      int pageAmount = (int) Math.ceil(vacancyAmount * 1.0 / VACANCY_PER_PAGE);
      request.setAttribute(Attribute.PAGE_AMONT, pageAmount);
      request.setAttribute(Attribute.PAGE, pageNumber);

      session.setAttribute(Attribute.COUNT_ALL_ACTIVE_VACANCY, vacancyAmount);
      int countApplicants = userService.countAllApplicants();
      session.setAttribute(Attribute.COUNT_ALL_APPLICANTS, countApplicants);
      int countResume = resumeService.countAllResume();
      session.setAttribute(Attribute.COUNT_ALL_RESUME, countResume);

      request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);

    } catch (ServiceException e) {
      request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
    }

    QueryUtil.saveHttpQuery(request);
    logger.debug("ToIndexPageCommand: execute() - end");
  }
}
