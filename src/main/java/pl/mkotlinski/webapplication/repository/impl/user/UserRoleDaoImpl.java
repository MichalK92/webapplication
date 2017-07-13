package pl.mkotlinski.webapplication.repository.impl.user;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import pl.mkotlinski.webapplication.model.user.UserProfile;
import pl.mkotlinski.webapplication.model.user.UserRole;
import pl.mkotlinski.webapplication.model.user.UserRoleEnum;
import pl.mkotlinski.webapplication.repository.abstractDao.AbstractDao;
import pl.mkotlinski.webapplication.repository.abstractDao.UserRoleDao;

@Repository("userRoleDao")
@Transactional
public class UserRoleDaoImpl extends AbstractDao<Long, UserRole> implements UserRoleDao{
	
	@Autowired
	@Qualifier("transactionManager")
	protected PlatformTransactionManager txManager;

	@PostConstruct
	private void init()
	{
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult()
		{
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status)
			{
				initUserProfileRoles();
			}
		});
	}

	@Override
	public List<UserRole> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole findByName(String name) {
		Session session = getEntityMenager().unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserRole.class).add(Restrictions.eq("role", name));
		UserRole userRole = (UserRole) criteria.uniqueResult();
		return userRole;
	}
	
	/*
	 * This is method to initializing default user profile roles.
	 */	
	@Transactional
	@Override
	public void initUserProfileRoles()
	{
		Session session = getEntityMenager().unwrap(Session.class);
		UserRoleEnum[] rolesTable = UserRoleEnum.class.getEnumConstants();
		for (UserRoleEnum roleName : rolesTable)
		{
			UserRole r = findByName(roleName.getUserRole());
			if (r == null)
			{
				UserRole role = new UserRole();
				role.setRole(roleName.getUserRole());
				persist(role);
			}
		}
	}

}
