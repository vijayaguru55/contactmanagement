package com.contacts.login;

import com.contacts.dto.User;

public abstract class LoginViewCallbcak {

	protected abstract void loginfailed(String message);

	protected abstract void loginSuccess(User user, String message);

}
