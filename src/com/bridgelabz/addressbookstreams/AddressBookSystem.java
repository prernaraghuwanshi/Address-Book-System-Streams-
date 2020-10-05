package com.bridgelabz.addressbookstreams;

import java.util.HashMap;
import java.util.Scanner;

public class AddressBookSystem {
	HashMap<String,AddressBookMain> addressBookMap;
	private Scanner sc;
	private static Scanner q;
	
	public AddressBookSystem()
	{
		addressBookMap = new HashMap<String, AddressBookMain>();
	}
	
	public void addAddressBook(String name)
	{
		AddressBookMain a = new AddressBookMain();
		boolean flag =true;
		while(flag)
		{
			System.out.println("Choose action on Address Book \n\n 1. Add Contact \n 2. Edit Contact \n 3. Delete Contact \n 4. Display Address Book \n 5. Exit \n");
			sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch(choice)
			{
				case 1:
					//Add contact
					a.addContact();
					break;
				case 2:
					//Edit contact
					System.out.println("Enter first name of contact to be edited: ");
					Scanner s= new Scanner(System.in);
					String firstNameEdit = s.nextLine();
					a.editContact(firstNameEdit);
					break;
				case 3:
					//Delete Contact
					System.out.println("Enter first name of contact to be deleted: ");
					Scanner s1= new Scanner(System.in);
					String firstNameDelete = s1.nextLine();
					a.deleteContact(firstNameDelete);
					break;
				case 4:
					//Display
					for(int i=0;i<a.contactList.size();i++)
					{
						a.displayContact(i);
					}
					break;
				case 5:
					//Exit
					System.out.println("-----Exiting from Address Book-----");
					flag = false;
					break;
			}
		}
		addressBookMap.put(name, a);
		System.out.println("-----Address Book "+name+" added-----");
		
	}
	
	public static void main(String args[]) {
		System.out.println("Welcome to Address Book Program!");
		AddressBookSystem ads = new AddressBookSystem();
		boolean flag1 = true;
		while(flag1)
		{
			System.out.println("Choose what to do in the system/n/n 1. Add Address Book /n 2. Exit /n");
			q = new Scanner(System.in);
			int choice = q.nextInt();
			switch(choice)
			{
				case 1:
					//Add address book
					System.out.println("Enter name of Address Book");
					Scanner s = new Scanner(System.in);
					String addressBookName = s.nextLine();
					if(ads.addressBookMap.containsKey(addressBookName))
					{
						System.out.println("Sorry! Address book "+addressBookName+" already exists!");
						break;
					}
					ads.addAddressBook(addressBookName);
					break;
				case 2:
					//Exit
					System.out.println("-----Exiting from Address Book System-----");
					flag1 = false;
					break;
				
			}
		}
		
	}

}
