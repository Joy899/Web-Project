package test;

import org.junit.Test;

import service.UsersDAO;
import service.impl.UsersDAOImpl;
import entity.Users;

public class TestUsersImpl {

	@Test
	public void testQueryUsers(){
		Users u = new Users(2011,"��","1230");
		UsersDAO udao = new UsersDAOImpl();
		System.out.println("��¼:"+udao.usersLogin(u));
	}
}
