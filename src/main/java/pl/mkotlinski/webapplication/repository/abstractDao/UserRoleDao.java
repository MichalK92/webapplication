package pl.mkotlinski.webapplication.repository.abstractDao;

import pl.mkotlinski.webapplication.service.UserRoleService;

public interface UserRoleDao extends UserRoleService{

	void initUserProfileRoles();

}
