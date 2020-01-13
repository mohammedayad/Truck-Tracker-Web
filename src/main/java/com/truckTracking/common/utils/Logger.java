package com.truckTracking.common.utils;

import java.util.HashMap;
import java.util.Map;

public class Logger implements Informative {

	private String loggerId;
	private org.apache.log4j.Logger log;

	public static String focusLog = null;

	private static final Map<String, Logger> cache = new HashMap<String, Logger>(20);

//    static {
//
//        ClassLoader cl = Logger.class.getClassLoader();
//        URL log4jCfg = cl.getResource("log4j.xml");
//        if (log4jCfg != null) {
//            DOMConfigurator.configure(log4jCfg);
//        }
//        System.out.println("log4j has been loaded successfully>>>>>>>>");
//
//    }
	private Logger(String loggerId) {
		this.setLoggerId(loggerId);
		log = org.apache.log4j.Logger.getLogger(loggerId);
	}

	public static final Logger getLogger(String loggerId) {
		System.out.println("loggerId " + loggerId);
		Logger logger = null;
		if (cache.containsKey(loggerId)) {
			logger = cache.get(loggerId);
		} else {
			logger = new Logger(loggerId);
			cache.put(loggerId, logger);
		}
		return logger;
	}

	public static final Logger getLogger(Class class1) {
		return getLogger(class1.getName());
	}

	private String getLogPrefix() {
//		return loggerId;
		return "";
	}

	private enum LOG_LEVEL {
		DEBUG, INFO, WARNING, ERROR, FATAL;

	}

	@Override
	public void debug(String... msg) {
		if (logEnable(LOG_LEVEL.DEBUG)) {
			String message = concat(getLogPrefix(), "[DEBUG]", msg);
			log.debug(message);
		}
	}

	private boolean logEnable(LOG_LEVEL level) {
		if (focusLog == null || loggerId.equalsIgnoreCase(focusLog)) {
			return true;
		}

		return false;
	}

	@Override
	public void debug(Object... objects) {
		if (logEnable(LOG_LEVEL.DEBUG)) {
			String message = concat(getLogPrefix(), "[DEBUG]", objects);
			log.debug(message);
		}
	}

	@Override
	public void info(String... msg) {
		if (logEnable(LOG_LEVEL.INFO)) {
			Object message = concat(getLogPrefix(), "[INFO]", msg);
			log.info(message);
		}
	}

	// private String concat(String logPrefix, String level, String... strings)
	// {
	// return concat(logPrefix, level, strings);
	// }
	private String concat(String logPrefix, String level, Object... objects) {
		StringBuilder builder = new StringBuilder(objects.length + 2);
		builder.append(logPrefix);
		builder.append(level);
		for (Object obj : objects) {
			String str = toString(obj);
			builder.append(str);
		}

		return builder.toString();
	}

	private String toString(Object obj) {
		return String.valueOf(obj);
	}

	@Override
	public void warn(String... msg) {
		if (logEnable(LOG_LEVEL.WARNING)) {
			Object message = concat(getLogPrefix(), "[WARN]", msg);
			log.warn(message);
		}
	}

	@Override
	public void error(String... msg) {
		if (logEnable(LOG_LEVEL.ERROR)) {
			Object message = concat(getLogPrefix(), "[ERROR]", msg);
			log.error(message);
		}
	}

	@Override
	public void error(Throwable e) {
		if (logEnable(LOG_LEVEL.ERROR)) {
			error(e, e.getClass().getSimpleName());
		}
	}

	@Override
	public void error(Throwable e, String... msg) {
		if (logEnable(LOG_LEVEL.ERROR)) {
			Object message = concat(getLogPrefix(), "[ERROR]", concat(getLogPrefix(), "[ERROR]", msg));
			log.error(message, e);
		}
	}

	@Override
	public void fatal(String... msg) {
		if (logEnable(LOG_LEVEL.FATAL)) {
			String message = concat(getLogPrefix(), "[FATAL]", msg);
			log.fatal(message);
		}
	}

	public String getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(String loggerId) {
		this.loggerId = loggerId;
	}

}
