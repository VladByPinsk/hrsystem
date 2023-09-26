package by.training.hrsystem.command.util;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code QueryUtil} is a helper class for command layer. An instance can not be created, as
 * have a constructor is private. It represents the method for save user-http request in the context
 * of the request.
 *
 * @author Vladislav
 */
public class QueryUtil {
  private static final String PREV_QUERY_ATTR = "prev-query";
  private static final String EMPTY_STRING = "";
  private static final String AMPERSAND = "&";
  private static final String EQUAL_SIGN = "=";
  private static final String QUESTION_MARK = "?";

  private QueryUtil() {}

  /**
   * The method preserves the http-request in the context of the request.
   *
   * @param request http-request.
   */
  public static void saveHttpQuery(HttpServletRequest request) {

    Enumeration<String> params = request.getParameterNames();
    String query = EMPTY_STRING;

    String key;
    String value;
    while (params.hasMoreElements()) {
      key = params.nextElement();
      value = request.getParameter(key);
      query = query + AMPERSAND + key + EQUAL_SIGN + value;
    }

    query = request.getRequestURL() + QUESTION_MARK + query;
    request.getSession(true).setAttribute(PREV_QUERY_ATTR, query);
  }
}
