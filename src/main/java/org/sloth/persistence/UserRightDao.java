/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.persistence;

import java.util.Collection;
import org.sloth.model.UserRight;

public interface UserRightDao {

	public Collection<UserRight> getAll();

	public UserRight get(int id);

	public void update(UserRight t);

	public void delete(UserRight t);

	public void save(UserRight t);

	public void flush();

	public void delete(int id);
}
