package com.contacts.user;

import java.util.ArrayList;

import com.contacts.dto.Contact;
import com.contacts.dto.User;
import com.contacts.repository.Contactsrepository;

public class UserModel extends UserModelCallback{
	private UserModelControllercallback controllercallback;
	
	public UserModel(UserModelControllercallback controllercallback) {
		this.controllercallback=controllercallback;
	}
	interface UserModelControllercallback{

		void addedSuccess(User user, Contact contact);

		void noContacts(User user);

		void viewContacts(User user, ArrayList<Contact> contacts,boolean forDelete);

		void deletionSucccess(User user);
		
	}
	@Override
	protected void addContact(Contact contact, User user) {
		Contactsrepository.getInsatnce().addContact(user,contact);
		controllercallback.addedSuccess(user,contact); 
	}
	@Override
	protected void getAndDelete(User user, String name) {
		ArrayList<Contact> contacts = Contactsrepository.getInsatnce().getContacts(user,name);
		if(contacts==null || contacts.size()==0) {
			controllercallback.noContacts(user);
		}else {
			controllercallback.viewContacts(user,contacts,true);
		}
	}
	@Override
	protected void getUsers(User user) {
		ArrayList<Contact> contacts = Contactsrepository.getInsatnce().getContacts(user);
		if(contacts==null || contacts.size()==0) {
			controllercallback.noContacts(user);
		}else {
			controllercallback.viewContacts(user,contacts,false);
		}
	}
	@Override
	protected void deleteContact(Contact contact, User user) {
		Contactsrepository.getInsatnce().deleteConatact(contact,user);
		controllercallback.deletionSucccess(user);
	}
	
}
