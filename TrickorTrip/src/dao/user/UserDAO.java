package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.user.UserEntity;

public class UserDAO {
	Connection con;
	
	public UserDAO(Connection con) {
		this.con=con;
	}

	public boolean search(UserEntity entity) {
		boolean result=false;
		try {
			String sql="select * from user where u_id=? and u_pw=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, entity.getUid());
			pstmt.setString(2, entity.getUpw());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				result=true;
			}else{
				result=false;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

}
