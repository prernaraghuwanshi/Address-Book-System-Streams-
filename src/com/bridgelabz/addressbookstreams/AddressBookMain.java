package com.bridgelabz.addressbookstreams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AddressBookMain {
	Scanner sc = new Scanner(System.in);
	public ArrayList<Contacts> contactList;
	public HashMap<String,Contacts> contactMap;
	//HashMap<name,AddressBookMain> addressBookSystem = new HashMap<name,AddressBookMain>();
	//private static Scanner i;
	
	public AddressBookMain()
	{
		contactList = new ArrayList<Contacts>();
		contactMap = new HashMap<String,Contacts>();
	}
	public void addContact()
	{
		System.out.println("-----Add Contact Details-----");
		System.out.println("Enter First Name: ");
		String firstName = sc.nextLine();
		System.out.println("Enter Last Name: ");
		String lastName = sc.nextLine();
		System.out.println("Enter Address: ");
		String address = sc.nextLine();
		System.out.println("Enter City: ");
		String city = sc.nextLine();
		System.out.println("Enter State:");
		String state = sc.nextLine();
		System.out.println("Enter ZIP Code: ");
		String zip = sc.nextLine();
		System.out.println("Enter Phone Number: ");
		String phone =sc.nextLine();
		System.out.println("Enter Email Address: ");
		String email = sc.nextLine();
		
		Contacts c = new Contacts(firstName, lastName, address, city, state, zip, phone, email);
		contactList.add(c);
		contactMap.put(firstName, c);
		System.out.println("-----Contact Added Successfully-----");
		System.out.println("Number of contacts in Address Book: " + contactList.size());
	}
	
	public void editContact(String firstNameEdit)
	{
		System.out.println("-----Edit Contact-----");
		
		if(contactMap.containsKey(firstNameEdit))
		{
			Contacts cedit = contactMap.get(firstNameEdit);
			System.out.println("Enter First Name: ");
			cedit.firstName = sc.nextLine();
			System.out.println("Enter Last Name: ");
			cedit.lastName = sc.nextLine();
			System.out.println("Enter Address: ");
			cedit.address = sc.nextLine();
			System.out.println("Enter City: ");
			cedit.city = sc.nextLine();
			System.out.println("Enter State:");
			cedit.state = sc.nextLine();
			System.out.println("Enter ZIP Code: ");
			cedit.zip = sc.nextLine();
			System.out.println("Enter Phone Number: ");
			cedit.phoneNo =sc.nextLine();
			System.out.println("Enter Email Address: ");
			cedit.email = sc.nextLine();
			contactMap.remove(firstNameEdit);
			contactMap.put(cedit.firstName, cedit);
			System.out.println("-----Contact Details Updated-----");
			System.out.println("Number of contacts in Address Book: " + contactList.size());
			
		}
		else
			System.out.println("-----Name not found in Address Book-----");
		
	}
	public void displayContact(int i)
	{
		System.out.println("-----Displaying Contact-----");
		System.out.println("First Name: "+contactList.get(i).firstName);
		System.out.println("Last Name: "+contactList.get(i).lastName);
		System.out.println("Address: "+contactList.get(i).address);
		System.out.println("City: "+contactList.get(i).city);
		System.out.println("State: "+contactList.get(i).state);
		System.out.println("ZIP Code: "+contactList.get(i).zip);
		System.out.println("Phone Number: "+contactList.get(i).phoneNo);
		System.out.println("Email: "+contactList.get(i).email);
		System.out.println("  ");
		
	}
	public void deleteContact(String firstName)
	{
		System.out.println("-----Deleting Contact-----");
		if(contactMap.containsKey(firstName))
		{
			Contacts c = contactMap.get(firstName);
			contactMap.remove(firstName);
			contactList.remove(c);
			System.out.println("-----Contact Deleted Successfully-----");
			System.out.println("Number of contacts in Address Book: " + contactList.size());
			
		}
		else
			System.out.println("-----Name not found in Address Book-----");
	}

}
