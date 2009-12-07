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
package de.ifgi.sloth.geosoftware.io.wms;

import de.ifgi.sloth.geosoftware.data.BoundingBox;
import de.ifgi.sloth.geosoftware.data.Map;
import de.ifgi.sloth.geosoftware.util.Log;
import de.ifgi.sloth.geosoftware.util.Configuration;

/**
 *
 * @author Christian Autermann
 */
public abstract class WMSBinding {
	private static WMSBinding binding = null;

	public static WMSBinding getInstance() {
		if (binding == null) {
			try {
				binding = (WMSBinding) Class.forName(Configuration.get("WMS_INTERFACE")).newInstance();
			} catch (Exception e) {
				Log.throwing(e);
				System.exit(1);
			}
		}
		return binding;
	}

	/**
	 * 
	 * @param bb
	 * @return
	 */
	public abstract Map getMap(BoundingBox bb);
}
