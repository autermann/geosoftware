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
package org.sloth.io.wms;

import org.sloth.data.BoundingBox;
import org.sloth.data.Map;
import org.sloth.util.Log;
import org.sloth.util.Configuration;

/**
 *
 * @author Christian Autermann
 * @deprecated 
 */
@Deprecated
public abstract class WMSBinding {
	private static WMSBinding binding = null;

	/**
	 * 
	 * @return
	 */
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
