package com.cjs.qa.utilities;

import org.apache.log4j.Logger;

public class QALogger extends Throwable {
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = Logger.getLogger(QALogger.class);
  private static final Logger LOGGER = Logger.getRootLogger();

  public QALogger(Exception e) {
    LOG.debug(e);
    LOGGER.debug(e);
  }

  public QALogger(String message, Exception e) {
    LOG.debug(message + Constants.NEWLINE + e);
    LOGGER.debug(message + Constants.NEWLINE + e);
  }

  public QALogger(String message) {
    LOG.debug(message);
    LOGGER.debug(message);
  }
}
