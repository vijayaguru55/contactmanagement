package com.contacts.user;

import com.contacts.dto.Contact;
import com.contacts.dto.User;

public abstract class UserControllerCallback {

	protected abstract void addContact(String name, long mobileNum, User user);

	protected abstract void getAndDelete(User user, String name);

	protected abstract void getUsers(User user);

	protected abstract void deleteContact(Contact contact, User user);

}
