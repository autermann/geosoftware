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
package org.sloth.service.impl;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.service.PasswordManager;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

/**
 *@todo
 * @author Christian Autermann
 */
public class PasswordManagerImpl implements PasswordManager {

	/**
	 * @todo
	 */
	protected static final Logger logger = LoggerFactory.getLogger(
			PasswordManager.class);
	/**
	 * @todo
	 */
	protected static String passwordRegex =
			"^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).*$";

	/**
	 * 
	 */
	static {
		try {
			Properties p = PropertiesLoaderUtils
					.loadAllProperties("regex.properties");
			String s = p.getProperty("regex.password");
			if (s != null && StringUtils.hasText(s)) {
				passwordRegex = s;
			} else {
				logger.info("Using default regex.");
			}
		} catch(IOException ex) {
			logger.warn("Using default regex", ex);
		}
	}

	/**
	 * @todo
	 * @param plain
	 * @return
	 */
	@Override
	public boolean meetsRecommendation(String plain) {
		/* 8 characters or more; atleast one majuscule, minuscule,
		 * digit and one non alphanumeric character */
		return plain.matches(passwordRegex);
	}

	/**
	 * @todo
	 * @param one
	 * @param two
	 * @return
	 */
	@Override
	public boolean test(String one, String two) {
		return one.equals(two);
	}

	/**
	 * @todo
	 * @param plain
	 * @return
	 */
	@Override
	public String hash(String plain) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
			md.update(plain.getBytes("UTF-8"));
		} catch(Exception e) {
			logger.trace("can not hash passwords", e);
		}
		return (new BASE64Encoder()).encode(md.digest());
	}

}
