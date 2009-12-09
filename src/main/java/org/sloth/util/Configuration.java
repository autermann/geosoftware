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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Christian Autermann
 */
public class Configuration {

	private static final String fileName = "/config.xml";
	private static Properties props = new Properties();

	static {
		InputStream stream = null;
		try {
			stream = Configuration.class.getResourceAsStream(fileName);
			props.loadFromXML(stream);
		} catch (Exception e) {
			Log.throwing(e);
		} finally {
			try {
				stream.close();
			} catch (Exception e) {
				Log.throwing(e);
			}
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return props.getProperty(key);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public static void put(String key, String value) {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(fileName);
			props.storeToXML(stream, "Configuration File");
		} catch (Exception e) {
			Log.throwing(e);
		} finally {
			try {
				stream.close();
			} catch (Exception e) {
				Log.throwing(e);
			}
		}

	}
}
