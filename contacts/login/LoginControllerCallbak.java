package com.contacts.login;

public abstract class LoginControllerCallbak {

	protected abstract void checkAndlogin(String name, String password);

	protected abstract void adduser(String name, long mobileNumber, String password);

}
