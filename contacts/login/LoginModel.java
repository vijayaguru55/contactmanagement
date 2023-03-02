package com.contacts.login;

import com.contacts.dto.Credentials;
import com.contacts.dto.User;
import com.contacts.repository.Contactsrepository;

public class LoginModel extends LoginModelCallback {
	private LoginModelControllerCallback controllerCallback;

	public LoginModel(LoginModelControllerCallback controllerCallback) {
		this.controllerCallback = controllerCallback;
	}

	interface LoginModelControllerCallback {

		void loginFailed(String string);

		void loginSuccess(User user, String message);

	}

	@Override
	protected void addUser(String name, long mobileNumber, String password) {

		User user = new User(name, mobileNumber);
		if (Contactsrepository.getInsatnce().isExistingUser(name, password)) {
			controllerCallback.loginFailed("user Aready exists...");
		} else {
			Credentials credentials = new Credentials(password, user.getUserId(), user.getName());
			Contactsrepository.getInsatnce().adduser(user, credentials);
			controllerCallback.loginSuccess(user, "user Added Success...");
		}
	}

	@Override
	protected void checkAndLogin(String name, String password) {
		if (Contactsrepository.getInsatnce().isExistingUser(name, password)) {
			
			User user = Contactsrepository.getInsatnce().getuser(name, password);
			if (user != null)
				controllerCallback.loginSuccess(user, "Login Success..");
			else
				controllerCallback.loginFailed("Invalid Credentiasl");
		} else {

			controllerCallback.loginFailed("No user found..");
		}
	}
}
