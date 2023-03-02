package com.contacts.login;

public abstract class LoginModelCallback {

	protected abstract void addUser(String name, long mobileNumber, String password);

	protected abstract void checkAndLogin(String name, String password);

}
