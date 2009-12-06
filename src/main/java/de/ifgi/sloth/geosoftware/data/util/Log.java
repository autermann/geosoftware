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
package de.ifgi.sloth.geosoftware.data.util;

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

	private Log() {
	}

	/**
	 * 
	 * @return
	 */
	public static Logger getLogger() {
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
		return log;
	}
}

