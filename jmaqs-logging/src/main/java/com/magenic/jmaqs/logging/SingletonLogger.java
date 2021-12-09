package com.magenic.jmaqs.logging;

import javax.inject.Singleton;

public class SingletonLogger {
  private static Logger logger;

  public static Logger getLogger() {
    if (logger == null) {
      logger = createLogger();
    }
    return logger;
  }

  private static Logger createLogger() {

    // config dictionary of logging options
    // Add to a collection based on FQDN of the class
    // Iterate over the collection at runtime to log the message across the collection


  }

}
  private SingletonLogger() {
  }
}
