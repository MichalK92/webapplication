package pl.mkotlinski.webapplication.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.mkotlinski.webapplication.model.user.UserRole;
import pl.mkotlinski.webapplication.repository.abstractDao.UserRoleDao;
import pl.mkotlinski.webapplication.service.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public List<UserRole> findAll() {
		return userRoleDao.findAll();
	}

	@Override
	public UserRole findById(long id) {
		return userRoleDao.findById(id);
	}

	@Override
	public UserRole findByName(String name) {
		return userRoleDao.findByName(name);
	}
}
