/*
 * Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth,
 * 					 Christoph Fendrich, Christian Paluschek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sloth.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Christian Autermann
 */
public class Log {

	private static final String filename = Configuration.get("LOG_FILE");
	private static Logger log = null;
	private static Filter filter = new Filter() {

		@Override
		public boolean isLoggable(LogRecord record) {
			return true;
		}
	};

	private Log() {}

	static {
		if (log == null) {
			log = Logger.getLogger(Configuration.get("LOG_NAME"));
			log.setLevel(Level.parse(Configuration.get("LOG_LEVEL")));
			log.setFilter(filter);
			try {
				if (filename.endsWith("txt")) {
					Handler handler = new FileHandler(filename);
					handler.setFormatter(new SimpleFormatter());
					log.addHandler(handler);
				} else {
					log.addHandler(new FileHandler(filename));
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.throwing(filename, filename, null);
	}

	/**
	 * 
	 * @param t
	 */
	public static void throwing(Throwable t) {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		log.throwing(stackTrace[1].getClassName(), stackTrace[1].getMethodName(), t);

	}

	/**
	 * 
	 * @param params
	 */
	public static void entering(Object... params) {
		StackTraceElement stackTrace = getCaller();
		log.entering(stackTrace.getClassName(), stackTrace.getMethodName(), params);
	}

	/**
	 * 
	 */
	public static void exiting() {
		StackTraceElement stackTrace = getCaller();
		log.exiting(stackTrace.getClassName(), stackTrace.getMethodName());
	}

	/**
	 *
	 * @param result 
	 */
	public static void exiting(Object result) {
		StackTraceElement stackTrace = getCaller();
		log.exiting(stackTrace.getClassName(), stackTrace.getMethodName(), result);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void fine(String msg) {
		LogRecord record = new LogRecord(Level.FINE, msg);
		StackTraceElement e = getCaller();
		record.setSourceClassName(e.getClassName());
		record.setSourceMethodName(e.getMethodName());
		log.log(record);
	}

	/**
	 *
	 * @param msg
	 */
	public static void finer(String msg) {
		LogRecord record = new LogRecord(Level.FINER, msg);
		StackTraceElement e = getCaller();
		record.setSourceClassName(e.getClassName());
		record.setSourceMethodName(e.getMethodName());
		log.log(record);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void finest(String msg) {
		LogRecord record = new LogRecord(Level.FINEST, msg);
		StackTraceElement e = getCaller();
		record.setSourceClassName(e.getClassName());
		record.setSourceMethodName(e.getMethodName());
		log.log(record);
	}

	/**
	 *
	 * @param msg
	 */
	public static void info(String msg) {
		LogRecord record = new LogRecord(Level.INFO, msg);
		StackTraceElement e = getCaller();
		record.setSourceClassName(e.getClassName());
		record.setSourceMethodName(e.getMethodName());
		log.log(record);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void severe(String msg) {
		LogRecord record = new LogRecord(Level.SEVERE, msg);
		StackTraceElement e = getCaller();
		record.setSourceClassName(e.getClassName());
		record.setSourceMethodName(e.getMethodName());
		log.log(record);
	}

	/**
	 *
	 * @param msg
	 */
	public static void config(String msg) {
		LogRecord record = new LogRecord(Level.CONFIG, msg);
		StackTraceElement e = getCaller();
		record.setSourceClassName(e.getClassName());
		record.setSourceMethodName(e.getMethodName());
		log.log(record);
	}

	/**
	 *
	 * @param msg
	 */
	public static void warning(String msg) {
		LogRecord record = new LogRecord(Level.SEVERE, msg);
		StackTraceElement e = getCaller();
		record.setSourceClassName(e.getClassName());
		record.setSourceMethodName(e.getMethodName());
		log.log(record);
	}

	private static StackTraceElement getCaller() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		if (stackTrace.length >= 3) {
			return stackTrace[3];
		} else {
			return null;
		}
	}
}

