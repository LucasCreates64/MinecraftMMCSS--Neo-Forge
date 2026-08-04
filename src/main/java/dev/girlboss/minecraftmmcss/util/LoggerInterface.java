package dev.girlboss.minecraftmmcss.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// stupid fallback for old versions
public interface LoggerInterface {
    void info(String message);
    
    void error(String message);

    void error(String message, Throwable throwable);
    
    static LoggerInterface getLogger() {
        try {
            Logger logger = LoggerFactory.getLogger("Windows Priority Boost");

            return new LoggerInterface() {
                @Override
                public void info(String message) {
                    logger.info(message);
                }

                @Override
                public void error(String message) {
                    logger.error(message);
                }

                @Override
                public void error(String message, Throwable throwable) {
                    logger.error(message, throwable);
                }
            };
        } catch (Throwable e) {
            return new LoggerInterface() {
                @Override
                public void info(String message) {
                    System.out.println("(Windows Priority Boost) " + message);
                }

                @Override
                public void error(String message) {
                    System.err.println("(Windows Priority Boost)");
                }

                @Override
                public void error(String message, Throwable throwable) {
                    System.err.println("(Windows Priority Boost) " + message);
                    throwable.printStackTrace();
                }
            };
        }
    }
}
