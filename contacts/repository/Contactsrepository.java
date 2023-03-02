package com.contacts.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.contacts.dto.Contact;
import com.contacts.dto.Credentials;
import com.contacts.dto.User;

public class Contactsrepository {
	private Connection database = database();

	private static Contactsrepository contactsrepository;
//	private ArrayList<Credentials> credentials = new ArrayList<Credentials>();
//	private ArrayList<User> userlList = new ArrayList<User>();
//	private HashMap<User, ArrayList<Contact>> contacts = new HashMap<User, ArrayList<Contact>>();

	private Connection database() {
		String url = "";//Mention your url here
		String username = ""; //Mention userName here
		String password = ""; //Menton password here.
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static Contactsrepository getInsatnce() {
		if (contactsrepository == null) {
			contactsrepository = new Contactsrepository();
			return contactsrepository;
		}
		return contactsrepository;
	}

	public boolean isExistingUser(String name, String password) {
		String query = "select name, password from credential where name ='"+name+"' and password ='"+password+"'";
		PreparedStatement preparedStatement =null;
		
		try {
			preparedStatement = database.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(credentials.size()==0)return false;
//		for (Credentials credentials : credentials) {
//			if (credentials.getUsername().equals(name) && credentials.getPassword().equals(password))
//				return true;
//		}
		return false;
	}

	public void adduser(User user, Credentials credentials) {
		String query = "insert into credential(name,password,userId) values(?,?,?)";

		String name = user.getName();
		String password = credentials.getPassword();
		String userid = user.getUserId();
		long mobile = user.getMobileNumber();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = database.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, userid);
			preparedStatement.executeUpdate();
			query = "insert into user values(?,?,?)";
			preparedStatement = database.prepareStatement(query);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, name);
			preparedStatement.setLong(3, mobile);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public User getuser(String name, String password) {
		String query = "select userId from credential where name = '" + name + "' and password = '" + password + "'";
		PreparedStatement preparedStatement = null;
		String userId = null;
		try {
			preparedStatement = database.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return null;
			}
			userId = resultSet.getString(1);

			query = "select name,mobileNumber from user where userId = '" + userId + "'";
			preparedStatement = database.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return new User(resultSet.getString(1), resultSet.getLong(2));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	public void addContact(User user, Contact contact) {
		String query = "insert into contacts values(?,?,?)";
		String name = contact.getName();
		long mobileNumber = contact.getMobileNumber();
		String userId = user.getUserId();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = database.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setLong(2, mobileNumber);
			preparedStatement.setString(3, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		if(this.contacts.containsKey(user)) {
//			ArrayList<Contact> contacts = this.contacts.get(user);
//			contacts.add(contact);
//			this.contacts.put(user, contacts);	
//		}else {
//			ArrayList<Contact> contacts = new ArrayList<Contact>();
//			contacts.add(contact);
//			this.contacts.put(user, contacts);
//		}
	}

	public ArrayList<Contact> getContacts(User user, String name) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		String query = "select name,mobileNumber from contacts where userId = '" + user.getUserId() + "' and name like '"
				+ name + "%'";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = database.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				contacts.add(new Contact(resultSet.getString(1), resultSet.getLong(2)));
			}

		} catch (SQLException e) {
			return null;
		}

//		if (this.contacts.containsKey(user)) {
//			for (Contact contact : this.contacts.get(user)) {
//				if (contact.getName().equals(name))
//					contacts.add(contact);
//			}
//		}
		return contacts;
	}

	public ArrayList<Contact> getContacts(User user) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		String query = "select name,mobileNumber from contacts where userId = '" + user.getUserId() + "' order by name asc";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = database.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				contacts.add(new Contact(resultSet.getString(1), resultSet.getLong(2)));
			}

		} catch (SQLException e) {
			return null;
		}

		return contacts;
	}

	public void deleteConatact(Contact contact, User user) {
	
		String query = "delete from contacts where name='" + contact.getName() + "' and mobileNumber = '"
				+ contact.getMobileNumber() + "'";
		PreparedStatement preparedStatement =null;
		try {
			preparedStatement = database.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
