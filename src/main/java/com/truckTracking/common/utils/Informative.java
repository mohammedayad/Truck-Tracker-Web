package com.truckTracking.common.utils;

public interface Informative {

	void debug(String... msg);

	void debug(Object... objects);

	void info(String... msg);

	void warn(String... msg);

	void error(String... msg);

	void error(Throwable e);

	void error(Throwable e, String... msg);

	void fatal(String... msg);
}
