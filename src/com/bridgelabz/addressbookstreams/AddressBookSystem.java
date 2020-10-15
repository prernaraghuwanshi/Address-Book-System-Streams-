package com.bridgelabz.addressbookstreams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookSystem {
	HashMap<String, AddressBookMain> addressBookMap;
	private Scanner sc;
	private static Scanner q;

	// Constructor
	public AddressBookSystem() {
		addressBookMap = new HashMap<String, AddressBookMain>();
	}

	// Add address book to Address Book System
	public void addAddressBook(String name) {
		AddressBookMain a = new AddressBookMain();
		boolean flag = true;
		while (flag) {
			System.out.println(
					"Choose action on Address Book \n\n 1. Add Contact \n 2. Edit Contact \n 3. Delete Contact \n 4. Display Address Book \n 5. Exit \n");
			sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				// Add contact
				a.addContact();
				break;
			case 2:
				// Edit contact
				System.out.println("Enter first name of contact to be edited: ");
				Scanner s = new Scanner(System.in);
				String firstNameEdit = s.nextLine();
				a.editContact(firstNameEdit);
				break;
			case 3:
				// Delete Contact
				System.out.println("Enter first name of contact to be deleted: ");
				Scanner s1 = new Scanner(System.in);
				String firstNameDelete = s1.nextLine();
				a.deleteContact(firstNameDelete);
				break;
			case 4:
				// Display
				for (int i = 0; i < a.contactList.size(); i++) {
					a.displayContact(i);
				}
				break;
			case 5:
				// Exit
				System.out.println("-----Exiting from Address Book-----");
				flag = false;
				break;
			}
		}
		addressBookMap.put(name, a);
		new AddressBookIO().writeData(a.contactList, name); // Write to file
		if (a.contactList.size() == new AddressBookIO().countEntries(name)) {
			System.out.println("Data successfully written to File!!");
		}
		System.out.println("-----Address Book " + name + " added-----");
	}

	// Get person by city (without using hashMap)
	public void getPersonByCity(String cityName) {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain addr = entry.getValue();
			List<Contacts> contactsList = addr.contactList.stream().filter(contact -> contact.city.equals(cityName))
					.collect(Collectors.toList());
			System.out.println("Person from " + cityName + " in Address Book " + entry.getKey() + " are: ");
			contactsList.forEach(contact -> System.out.println(contact.firstName + " " + contact.lastName));
		}
	}

	// Get person by state (without using hashMap)
	public void getPersonByState(String stateName) {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain addr = entry.getValue();
			List<Contacts> contactsList = addr.contactList.stream().filter(contact -> contact.state.equals(stateName))
					.collect(Collectors.toList());
			System.out.println("Person from " + stateName + " in Address Book " + entry.getKey() + " are: ");
			contactsList.forEach(contact -> System.out.println(contact.firstName + " " + contact.lastName));
		}
	}

	// View person by city (using hashMap)
	public void viewPersonByCity(String cityName) {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain addr = entry.getValue();
			ArrayList<String> names = addr.cityMap.entrySet().stream().filter(str -> str.getKey().equals(cityName))
					.map(Map.Entry::getValue).findFirst().orElse(null);
			if (names == null) {
				System.out.println("City does not exist!");
				return;
			}
			System.out.println(names);
		}
	}

	// View person by city (using hashMap)
	public void viewPersonByState(String stateName) {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain addr = entry.getValue();
			ArrayList<String> names = addr.stateMap.entrySet().stream().filter(str -> str.getKey().equals(stateName))
					.map(Map.Entry::getValue).findFirst().orElse(null);
			if (names == null) {
				System.out.println("State does not exist!");
				return;
			}
			System.out.println(names);
		}
	}

	// Count of all contacts in a city
	public void countByCity(String cityName) {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain addr = entry.getValue();
			Integer count = (int) addr.cityMap.entrySet().stream().filter(str -> str.getKey().equals(cityName))
					.map(Map.Entry::getValue).findFirst().orElse(null).size();
			System.out.println("Count for address book " + entry.getKey() + " is: " + count);
		}
	}

	// Count of all contacts in a state
	public void countByState(String stateName) {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain addr = entry.getValue();
			Integer count = (int) addr.stateMap.entrySet().stream().filter(str -> str.getKey().equals(stateName))
					.map(Map.Entry::getValue).findFirst().orElse(null).size();
			System.out.println("Count for address book " + entry.getKey() + " is: " + count);
		}
	}

	// Sort by name in alphabetical order
	public void sortByName() {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain addr = entry.getValue();
			List<Contacts> sortedList = addr.contactList.stream().sorted(Comparator.comparing(Contacts::getFirstName))
					.collect(Collectors.toList());
			sortedList.forEach(contact -> System.out.println(contact.firstName + " " + contact.lastName));
		}
	}

	// Sort by city in alphabetical order
	public void sortByCity() {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain addr = entry.getValue();
			List<Contacts> sortedList = addr.contactList.stream().sorted(Comparator.comparing(Contacts::getCity))
					.collect(Collectors.toList());
			sortedList.forEach(contact -> System.out.println(contact.firstName + " " + contact.lastName));
		}
	}

	// Sort by state in alphabetical order
	public void sortByState() {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain addr = entry.getValue();
			List<Contacts> sortedList = addr.contactList.stream().sorted(Comparator.comparing(Contacts::getState))
					.collect(Collectors.toList());
			sortedList.forEach(contact -> System.out.println(contact.firstName + " " + contact.lastName));
		}
	}

	// Sort by ZIP in alphabetical order
	public void sortByZip() {
		for (Map.Entry<String, AddressBookMain> entry : addressBookMap.entrySet()) {
			AddressBookMain addr = entry.getValue();
			List<Contacts> sortedList = addr.contactList.stream().sorted(Comparator.comparing(Contacts::getZip))
					.collect(Collectors.toList());
			sortedList.forEach(contact -> System.out.println(contact.firstName + " " + contact.lastName));
		}
	}

	public static void main(String args[]) {
		System.out.println("Welcome to Address Book Program!");
		AddressBookSystem ads = new AddressBookSystem();
		boolean flag1 = true;
		while (flag1) {
			System.out.println(
					"Choose what to do in the system\n\n 1. Add Address Book \n 2. Search person by City \n 3. Search person by State \n 4. View Person by City \n "
							+ "5. View Person By State \n 6. Count by City \n 7. Count By State \n 8. Sorted list of names \n 9. Sort by city \n 10. Sort by state \n 11. Sort by ZIP \n"
							+ " 12. Read From File \n" + " 13. Exit \n");
			q = new Scanner(System.in);
			int choice = q.nextInt();
			switch (choice) {
			case 1:
				// Add address book
				System.out.println("Enter name of Address Book");
				Scanner s = new Scanner(System.in);
				String addressBookName = s.nextLine();
				if (ads.addressBookMap.containsKey(addressBookName)) {
					System.out.println("Sorry! Address book " + addressBookName + " already exists!");
					break;
				}
				ads.addAddressBook(addressBookName);
				break;
			case 2:
				// Search person by city
				if (ads.addressBookMap.size() == 0) {
					System.out.println("No address book in system!");
					break;
				}
				System.out.println("Enter city name: ");
				Scanner sc = new Scanner(System.in);
				String cityName = sc.nextLine();
				ads.getPersonByCity(cityName);
				break;
			case 3:
				// Search person by state
				if (ads.addressBookMap.size() == 0) {
					System.out.println("No address book in system!");
					break;
				}
				System.out.println("Enter state name: ");
				Scanner s1 = new Scanner(System.in);
				String stateName = s1.nextLine();
				ads.getPersonByState(stateName);
				break;
			case 4:
				// View Person by city
				if (ads.addressBookMap.size() == 0) {
					System.out.println("No address book in system!");
					break;
				}
				System.out.println("Enter city name: ");
				Scanner s2 = new Scanner(System.in);
				String cityName1 = s2.nextLine();
				ads.viewPersonByCity(cityName1);
				break;

			case 5:
				// View Person By State
				if (ads.addressBookMap.size() == 0) {
					System.out.println("No address book in system!");
					break;
				}
				System.out.println("Enter state name: ");
				Scanner s3 = new Scanner(System.in);
				String stateName1 = s3.nextLine();
				ads.viewPersonByState(stateName1);
				break;
			case 6:
				// Count by city
				if (ads.addressBookMap.size() == 0) {
					System.out.println("No address book in system!");
					break;
				}
				System.out.println("Enter city name: ");
				Scanner s4 = new Scanner(System.in);
				String cityName2 = s4.nextLine();
				ads.countByCity(cityName2);
				break;
			case 7:
				// Count by state
				if (ads.addressBookMap.size() == 0) {
					System.out.println("No address book in system!");
					break;
				}
				System.out.println("Enter state name: ");
				Scanner s5 = new Scanner(System.in);
				String stateName2 = s5.nextLine();
				ads.countByState(stateName2);
				break;
			case 8:
				// Sort by first name
				ads.sortByName();
				break;
			case 9:
				// Sort by city
				ads.sortByCity();
				break;
			case 10:
				// Sort by state
				ads.sortByState();
				break;
			case 11:
				// Sort by ZIP
				ads.sortByZip();
				break;
			case 12:
				// Read from file
				System.out.println("Enter name of address book for which you need to read from file: ");
				Scanner s6 = new Scanner(System.in);
				String nameAddressBook = s6.nextLine();
				ArrayList<Contacts> readFromFileContactList = new AddressBookIO().readData(nameAddressBook);
				if (readFromFileContactList.size() == new AddressBookIO().countEntries(nameAddressBook))
					System.out.println("Reading from File successful!!");
			case 13:
				// Exit
				System.out.println("-----Exiting from Address Book System-----");
				flag1 = false;
				break;
			}
		}
	}
}
