package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.UsersDAO;
import service.impl.UsersDAOImpl;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;

public class UsersAction extends SuperAction implements ModelDriven<Users> {

	private Users user = new Users();
	
	public String login(){
		UsersDAO udao = new UsersDAOImpl();
	//	System.out.println(user.getUsername());
		session.setAttribute("loginUserName", user.getUsername());
		if(udao.usersLogin(user)){
			return "login_success";
		}else{
			return "login_failure";
		}
	}
	@SkipValidation
	public String logout(){
		if(session.getAttribute("loginUserName")!=null){
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if("".equals(user.getUsername())){
			this.addFieldError("usernameError", "�û�������Ϊ�գ�");
		}
		if("".equals(user.getPassword())){
			this.addFieldError("passwordError", "���벻��Ϊ�գ�");
		}else {
			if(user.getPassword().length()<6){
				this.addFieldError("passwordError", "���벻������6λ��");
			}
		}


	}

	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

}
