package com.cjs.qa.utilities;

import org.apache.log4j.Logger;

public class QALogger extends Throwable {
  private static final long serialVersionUID = 1L;
  private static final Logger log = Logger.getLogger(QALogger.class);
  private static final Logger logger = Logger.getRootLogger();

  public QALogger(Exception e) {
    log.debug(e);
    logger.debug(e);
  }

  public QALogger(String message, Exception e) {
    log.debug(message + Constants.NEWLINE + e);
    logger.debug(message + Constants.NEWLINE + e);
  }

  public QALogger(String message) {
    log.debug(message);
    logger.debug(message);
  }
}
