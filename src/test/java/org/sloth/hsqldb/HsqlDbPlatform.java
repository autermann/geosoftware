/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.hsqldb;

import java.io.IOException;
import java.io.Writer;
import org.eclipse.persistence.platform.database.HSQLPlatform;

/**
 * This is a workaround HSQL DB platform implementation which omits unique constraints
 * since they are not supported in the syntax that eclipselink is using.<br />
 * See: <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=240618">issue 240618</a>.
 *
 * @author Olaf Otto
 * @see org.eclipse.persistence.platform.database.HSQLPlatform
 */
public class HsqlDbPlatform extends HSQLPlatform {

	/**
	 * Workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=240618.
	 *
	 * @return <code>false</code>.
	 */
	@Override
	public boolean supportsUniqueKeyConstraints() {
		return false;
	}

	/**
	 * Workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=240618.
	 * <br />
	 * Does nothing.
	 */
	@Override
	public void printFieldUnique(Writer writer, boolean shouldPrintFieldIdentityClause) throws IOException {
		// Do nothing.
	}
}
