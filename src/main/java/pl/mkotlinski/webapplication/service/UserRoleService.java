package pl.mkotlinski.webapplication.service;

import java.util.List;

import pl.mkotlinski.webapplication.model.user.UserRole;

public interface UserRoleService {
	
	public List<UserRole> findAll();

	public UserRole findById(long id);

	public UserRole findByName(String name);
}
