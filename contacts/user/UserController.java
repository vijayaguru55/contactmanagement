package com.contacts.user;

import java.util.ArrayList;

import com.contacts.dto.Contact;
import com.contacts.dto.User;
import com.contacts.user.UserModel.UserModelControllercallback;

public class UserController extends UserControllerCallback implements UserModelControllercallback{
	private UserViewCallback viewCallback;
	private UserModelCallback modelCallback;
	
	public UserController(UserView userView) {
		this.viewCallback = userView;
		this.modelCallback = new UserModel(this);
	}

	@Override
	protected void addContact(String name, long mobileNum, User user) {
		Contact contact = new Contact(name, mobileNum);
		modelCallback.addContact(contact,user);
	}

	@Override
	public void addedSuccess(User user, Contact contact) {
		viewCallback.addSuccess(user,contact);
	}

	@Override
	protected void getAndDelete(User user, String name) {
		modelCallback.getAndDelete(user,name);
	}

	@Override
	public void noContacts(User user) {
		viewCallback.noContacts(user);
	}

	@Override
	public void viewContacts(User user, ArrayList<Contact> contacts,boolean forDelete) {
		viewCallback.viewConatcs(user,contacts, forDelete);
	}

	@Override
	protected void getUsers(User user) {
		modelCallback.getUsers(user);
	}

	@Override
	protected void deleteContact(Contact contact, User user) {
		modelCallback.deleteContact(contact,user);
		
	}

	@Override
	public void deletionSucccess(User user) {
		viewCallback.deletionSuccess(user);
	}
}
