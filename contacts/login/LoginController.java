package com.contacts.login;

import com.contacts.dto.User;
import com.contacts.login.LoginModel.LoginModelControllerCallback;

public class LoginController extends LoginControllerCallbak implements LoginModelControllerCallback {
	private LoginViewCallbcak viewCallbcak;
	private LoginModelCallback modelCallback;

	public LoginController(LoginView loginView) {
		this.viewCallbcak = loginView;
		this.modelCallback = new LoginModel(this);

	}

	@Override
	protected void checkAndlogin(String name, String password) {
		modelCallback.checkAndLogin(name, password);
	}

	@Override
	protected void adduser(String name, long mobileNumber, String password) {
		modelCallback.addUser(name, mobileNumber, password);
	}

	@Override
	public void loginFailed(String message) {

		viewCallbcak.loginfailed(message);
	}

	@Override
	public void loginSuccess(User user, String message) {
		viewCallbcak.loginSuccess(user, message);
	}

}
