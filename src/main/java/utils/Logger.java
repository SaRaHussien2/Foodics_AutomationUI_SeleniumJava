package utils;

import org.slf4j.LoggerFactory;

public class Logger {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

	public static void info(String message) {
		logger.info(message);
	}

	public static void error(String message, Throwable t) {
		logger.error(message, t);
	}

	public static void warn(String message) {
		logger.warn(message);
	}
}

