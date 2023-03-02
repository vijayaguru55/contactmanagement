package com.contacts.user;

import java.util.ArrayList;

import com.contacts.dto.Contact;
import com.contacts.dto.User;

public abstract class UserViewCallback {

	protected abstract void addSuccess(User user, Contact contact);

	protected abstract void noContacts(User user);

	protected abstract void viewConatcs(User user, ArrayList<Contact> contacts,boolean forDelete);

	protected abstract void deletionSuccess(User user);

}
