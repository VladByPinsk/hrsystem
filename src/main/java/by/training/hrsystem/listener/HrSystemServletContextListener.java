package by.training.hrsystem.listener;

import by.training.hrsystem.service.InitConnectionService;
import by.training.hrsystem.service.exeption.ServiceException;
import by.training.hrsystem.service.factory.ServiceFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HrSystemServletContextListener implements ServletContextListener {
  private static final Logger logger = LogManager.getLogger(HrSystemServletContextListener.class);

  public HrSystemServletContextListener() {}

  @Override
  public void contextInitialized(ServletContextEvent sce) {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    InitConnectionService poolService = serviceFactory.getInitPoolService();
    try {
      logger.debug("HrSystemServletContextListener.contextInitialized()");
      poolService.initConnection();
    } catch (ServiceException e) {
      logger.error("can not init connection");
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    InitConnectionService poolService = serviceFactory.getInitPoolService();
    try {
      logger.debug("HrSystemServletContextListener.contextDestroyed()");
      poolService.destroyConnection();
    } catch (ServiceException e) {
      logger.error("can not close connection");
    }
  }
}
