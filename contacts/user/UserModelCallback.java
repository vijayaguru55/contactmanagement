package com.contacts.user;

import com.contacts.dto.Contact;
import com.contacts.dto.User;

public abstract class UserModelCallback {

	protected abstract void addContact(Contact contact, User user);

	protected abstract void getAndDelete(User user, String name);

	protected abstract void getUsers(User user);

	protected abstract void deleteContact(Contact contact, User user);

	
	
}
