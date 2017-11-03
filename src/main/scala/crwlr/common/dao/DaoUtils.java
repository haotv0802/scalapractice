package crwlr.common.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public final class DaoUtils {

  private DaoUtils() {
  }
  public static void debugQuery(Logger logger, String sql, Map<String, Object> bindVars) {
    if (null != logger && logger.isDebugEnabled()) {
      logger.debug(StringUtils.normalizeSpace(sql) + "; bv:" + bindVars);
    }
  }

}
