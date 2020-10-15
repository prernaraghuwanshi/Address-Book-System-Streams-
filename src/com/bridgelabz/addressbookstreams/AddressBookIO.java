package com.bridgelabz.addressbookstreams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookIO {

	// Write to file
	public void writeData(List<Contacts> contactList, String addressBookName) {

		// String ADDRESS_BOOK_NAME = "payroll-file.txt";
		StringBuffer contactBuffer = new StringBuffer();
		contactList.forEach(contact -> {
			String contactDataString = contact.toString().concat("\n");
			contactBuffer.append(contactDataString);
		});
		try {
			Files.write(Paths.get(addressBookName + ".txt"), contactBuffer.toString().getBytes());
		} catch (IOException e) {
		}
	}

	// Count entries in file
	public int countEntries(String addressBookName) {
		int entries = 0;
		try {
			entries = (int) Files.lines(new File(addressBookName + ".txt").toPath()).count();
		} catch (IOException e) {
		}
		return entries;
	}

	// Read data from File
	public ArrayList<Contacts> readData(String addressBookName) {
		ArrayList<Contacts> contactList = new ArrayList<>();
		try {
			Files.lines(Paths.get(addressBookName + ".txt")).forEach(line -> {
				line = line.trim();
				line = line.replace(":", "");
				String[] arr = line.split(" ");
				contactList.add(new Contacts(arr[1], arr[3], arr[5], arr[7], arr[9], arr[11], arr[13], arr[15]));
			});
		} catch (IOException e) {

		}
		return contactList;
	}
}
