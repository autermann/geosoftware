/*
 * Copyright (C) 2009-2010  Stefan Arndt, Christian Autermann, Dustin Demuth,
 *                  Christoph Fendrich, Simon Ottenhues, Christian Paluschek
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
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Config {

	private static final Logger logger = LoggerFactory.getLogger(Config.class);
	private static Properties props;

	private Config() {
	}

	public static String getProperty(String key) {
		if (props == null)
			try {
				logger.info("Loading configuration.");
				props = PropertiesLoaderUtils.loadAllProperties("config.properties");
			} catch (IOException ex) {
				logger.warn("Can not load configuration", ex);
				return null;
			}
		return props.getProperty(key);
	}
}
