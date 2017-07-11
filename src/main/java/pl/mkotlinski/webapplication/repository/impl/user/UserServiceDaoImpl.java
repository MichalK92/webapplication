package pl.mkotlinski.webapplication.repository.impl.user;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.mkotlinski.webapplication.model.user.UserProfile;
import pl.mkotlinski.webapplication.repository.UserServiceDao;
import pl.mkotlinski.webapplication.repository.abstractDao.AbstractDao;

@Repository("userDaoRepo")
public class UserServiceDaoImpl extends AbstractDao<Long, UserProfile> implements UserServiceDao{

	@Override
	public UserProfile findUserById(long id_user) {
		return getByPrimaryKey(id_user);
	}

	@Override
	public UserProfile findByLogin(String sso) {
		Session session = getEntityMenager().unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserProfile.class).add(Restrictions.eq("login", sso));
		UserProfile user = (UserProfile) criteria.uniqueResult();
		return user;
	}

	@Override
	public void addUser(UserProfile user) {
		persist(user);		
	}
}
