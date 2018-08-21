package com.neopragma.legacy.screen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class JobApplicant {

	private Name name;
	private Ssn ssn;
	private Address address;

	public void add(String firstName,
			       String middleName,
			       String lastName,
			       String ssn,
			       String zipCode) throws URISyntaxException, IOException {
		setName(firstName, middleName, lastName);
		setSsn(ssn);
		setZipCode(zipCode);
		save();
	}

	private void save() {
		//TODO save information to a database
		System.out.println("Saving to database: " + name.formatLastNameFirst());
	}
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		JobApplicant jobApplicant = new JobApplicant();
		boolean done = false;
		Scanner scanner = new Scanner(System.in);

		String firstName;
		String middleName;
		String lastName;
		String ssn;
		String zipCode;

		while (!done) {
			System.out.println("Please enter info about a job candidate or 'quit' to quit");
			System.out.println("First name?");
            firstName = scanner.nextLine();		
            if (firstName.equals("quit")) {
            	scanner.close();
            	System.out.println("Bye-bye!");
            	done = true;
            	break;
            }
			System.out.println("Middle name?");
            middleName = scanner.nextLine();
			System.out.println("Last name?");
            lastName = scanner.nextLine();			
			System.out.println("SSN?");
            ssn = scanner.nextLine();			
			System.out.println("Zip Code?");
            zipCode = scanner.nextLine();			
            jobApplicant.setName(firstName, middleName, lastName);          
            jobApplicant.setSsn(ssn);
            jobApplicant.setZipCode(zipCode);
            jobApplicant.save();
		}
	}

	private void setName(String firstName, String middleName, String lastName) {
		name = new Name (firstName, middleName, lastName);
	}

	private void setSsn(String ssnString) {
		ssn = new Ssn(ssnString);
	}

	private void setZipCode(String zipCode) throws IOException, URISyntaxException {
		address = new Address(zipCode);
	}

}
