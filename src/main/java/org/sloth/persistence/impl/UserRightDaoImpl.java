package org.sloth.persistence.impl;

import java.util.Collection;
import javax.persistence.criteria.CriteriaQuery;
import org.sloth.model.UserRight;
import org.sloth.persistence.UserRightDao;

public class UserRightDaoImpl extends EntityManagerDao implements UserRightDao {

	@Override
	public Collection<UserRight> getAll() {
		CriteriaQuery<UserRight> cq = getEntityManager().getCriteriaBuilder().
				createQuery(UserRight.class);
		cq.select(cq.from(UserRight.class));
		Collection<UserRight> list = getEntityManager().createQuery(cq).
				getResultList();


		Collection<UserRight> col = getEntityManager().createQuery(
				"select o from USER_RIGHT o").getResultList();
		logger.info("Getting all UserRights; Found: {}", col.size());
		return col;
	}

	@Override
	public UserRight get(int Value) {
		logger.info("Searching for UserRight with Value: {}", Value);
		UserRight oc = getEntityManager().find(UserRight.class, Value);
		if (oc != null) {
			logger.info(
					"Found UserRight with Value {}; Name: {}; Description: {}", new Object[]{oc.
						getValue(), oc.getName(), oc.getDescription()});
		} else {
			logger.info("Can't find UserRight with Value {}", Value);
		}
		return oc;
	}

	@Override
	public void update(UserRight oc) {
		logger.info("Updating UserRight with Value: {}", oc.getValue());
		getEntityManager().merge(oc);
	}

	@Override
	public void delete(UserRight oc) {
		logger.info("Deleting UserRight with Value: {}", oc.getValue());
		getEntityManager().remove(oc);
	}

	@Override
	public void save(UserRight oc) {
		getEntityManager().persist(oc);
		logger.info("Persisting UserRight; Generated Value is: {}",
					oc.getValue());
	}

	@Override
	public void flush() {
		logger.info("Flushing EntityManager");
		getEntityManager().flush();
	}

	@Override
	public void delete(int id) {
		delete(get(id));
	}

}
