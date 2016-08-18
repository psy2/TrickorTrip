package service.user;

import java.sql.Connection;


import dao.user.UserDAO;
import entity.user.UserEntity;

public class UserService {

	public boolean login(UserEntity entity) {
		boolean result=false;
		Connection con=common.DBTemplate.getConnection();
		UserDAO dao=new UserDAO(con);
		result=dao.search(entity);
		
		
		return result;
	}

}
