package com.bridgelabz.addressbookstreams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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
	
	public void getPersonByCity(String cityName)
	{
		
		for(Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet())
		{
			AddressBookMain addr = entry.getValue();
			List<Contacts> contactsList = addr.contactList.stream().filter(str -> str.city.equals(cityName)).collect(Collectors.toList());
			System.out.println("Person from "+cityName+" in Address Book "+entry.getKey()+" are: ");
			for(Contacts c:contactsList)
			{
				System.out.println(c.firstName+" "+ c.lastName);
			}
		}
	}
	public void getPersonByState(String stateName)
	{
		for(Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet())
		{
			AddressBookMain addr = entry.getValue();
			List<Contacts> contactsList = addr.contactList.stream().filter(str -> str.state.equals(stateName)).collect(Collectors.toList());
			System.out.println("Person from "+stateName+" in Address Book "+entry.getKey()+" are: ");
			for(Contacts c:contactsList)
			{
				System.out.println(c.firstName+" "+ c.lastName);
			}
		}
	}
	
	public void viewPersonByCity(String cityName)
	{
		//List<AddressBookMain> names = addressBookMap.entrySet().stream().flatMap(entry -> entry.getValue().stream()).filter(str -> str.cityMap.containsKey(cityName)).collect(Collectors.toList());
		for(Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet())
		{
			AddressBookMain addr = entry.getValue();
			ArrayList<String> names = addr.cityMap.entrySet().stream().filter(str -> str.getKey().equals(cityName)).map(Map.Entry::getValue).findFirst().orElse(null);
			if(names == null)
			{
				System.out.println("City does not exist!");
				return;
			}
			System.out.println(names);
		}
	}
	public void viewPersonByState(String stateName)
	{
	
		for(Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet())
		{
			AddressBookMain addr = entry.getValue();
			ArrayList<String> names = addr.stateMap.entrySet().stream().filter(str -> str.getKey().equals(stateName)).map(Map.Entry::getValue).findFirst().orElse(null);
			if(names== null)
			{
				System.out.println("State does not exist!");
				return;
			}
			System.out.println(names);
		}
	}
	public void countByCity(String cityName)
	{
		for(Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet())
		{
			AddressBookMain addr = entry.getValue();
			Integer count = (int) addr.cityMap.entrySet().stream().filter(str -> str.getKey().equals(cityName)).map(Map.Entry::getValue).findFirst().orElse(null).size();
			System.out.println("Count for address book "+entry.getKey()+" is: "+count);
		}
	}
	public void countByState(String stateName)
	{
		for(Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet())
		{
			AddressBookMain addr = entry.getValue();
			Integer count = (int) addr.stateMap.entrySet().stream().filter(str -> str.getKey().equals(stateName)).map(Map.Entry::getValue).findFirst().orElse(null).size();
			System.out.println("Count for address book "+entry.getKey()+" is: "+count);
		}
	}
	public void sortByName()
	{
		for(Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet())
		{
			AddressBookMain addr = entry.getValue();
			List<Contacts> sortedList = addr.contactList.stream().sorted(Comparator.comparing(Contacts::getFirstName)).collect(Collectors.toList());
			for(Contacts c:sortedList)
			{
				System.out.println(c.firstName+" "+ c.lastName);
			}
		}
	}
		
	
	public static void main(String args[]) {
		System.out.println("Welcome to Address Book Program!");
		AddressBookSystem ads = new AddressBookSystem();
		boolean flag1 = true;
		while(flag1)
		{
			System.out.println("Choose what to do in the system\n\n 1. Add Address Book \n 2. Search person by City \n 3. Search person by State \n 4. View Person by City \n "
					+ "5. View Person By State \n 6. Count by City \n 7. Count By State \n 8. Sorted list of names \n 9. Exit \n");
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
					//Search person by city
					if(ads.addressBookMap.size()==0)
					{
						System.out.println("No address book in system!");
						break;
					}
					System.out.println("Enter city name: ");
					Scanner sc = new Scanner(System.in);
					String cityName = sc.nextLine();
					ads.getPersonByCity(cityName);
					break;
				case 3:
					//Search person by state
					if(ads.addressBookMap.size()==0)
					{
						System.out.println("No address book in system!");
						break;
					}
					System.out.println("Enter state name: ");
					Scanner s1 = new Scanner(System.in);
					String stateName = s1.nextLine();
					ads.getPersonByState(stateName);
					break;
				case 4:
					//View Person by city
					if(ads.addressBookMap.size()==0)
					{
						System.out.println("No address book in system!");
						break;
					}
					System.out.println("Enter city name: ");
					Scanner s2 = new Scanner(System.in);
					String cityName1 = s2.nextLine();
					ads.viewPersonByCity(cityName1);
					break;
					
				case 5:	
					//View Person By State
					if(ads.addressBookMap.size()==0)
					{
						System.out.println("No address book in system!");
						break;
					}
					System.out.println("Enter state name: ");
					Scanner s3 = new Scanner(System.in);
					String stateName1 = s3.nextLine();
					ads.viewPersonByState(stateName1);
					break;
				case 6:
					//Count by city
					if(ads.addressBookMap.size()==0)
					{
						System.out.println("No address book in system!");
						break;
					}
					System.out.println("Enter city name: ");
					Scanner s4 = new Scanner(System.in);
					String cityName2 = s4.nextLine();
					ads.countByCity(cityName2);
					break;
				case 7:
					//Count by state
					if(ads.addressBookMap.size()==0)
					{
						System.out.println("No address book in system!");
						break;
					}
					System.out.println("Enter state name: ");
					Scanner s5 = new Scanner(System.in);
					String stateName2 = s5.nextLine();
					ads.countByState(stateName2);
					break;
				case 8:
					ads.sortByName();
					break;
				case 9:
					//Exit
					System.out.println("-----Exiting from Address Book System-----");
					flag1 = false;
					break;
				
			}
		}
		
	}
}
