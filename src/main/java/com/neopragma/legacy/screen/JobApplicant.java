package com.neopragma.legacy.screen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class JobApplicant {

	public static final String QUIT_MESSAGE = "quit";
	public static final String BYE_BYE_MESSAGE = "Bye-bye!";
	public static final String PROMPT_PLEASE_ENTER_INFO_ABOUT_A_JOB_CANDIDATE_OR_QUIT_TO_QUIT = "Please enter info about a job candidate or 'quit' to quit";
	public static final String FIRST_NAME_PROMPT = "First name?";
	public static final String MIDDLE_NAME_PROMPT = "Middle name?";
	public static final String LAST_NAME_PROMPT = "Last name?";
	public static final String SSN_PROMPT = "SSN?";
	public static final String ZIP_CODE_PROMPT = "Zip Code?";

	private Name name;
	private Ssn ssn;
	private Address address;

	private void save() {
		//TODO save information to a database
		System.out.println("Saving to database: " + name.formatLastNameFirst() + " with address: " + address.ToString());
	}
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		JobApplicant jobApplicant = new JobApplicant();
		boolean done = false;
		Scanner scanner = new Scanner(System.in);

		String firstName, middleName, lastName, ssn, zipCode;

		while (!done) {
			System.out.println(PROMPT_PLEASE_ENTER_INFO_ABOUT_A_JOB_CANDIDATE_OR_QUIT_TO_QUIT);
			
			firstName = getInput(scanner, FIRST_NAME_PROMPT);
            if (firstName.equals(QUIT_MESSAGE)) {
            	done = true;
            }else {

				middleName = getInput(scanner, MIDDLE_NAME_PROMPT);
				lastName = getInput(scanner, LAST_NAME_PROMPT);
				ssn = getInput(scanner, SSN_PROMPT);
				zipCode = getInput(scanner, ZIP_CODE_PROMPT);

				jobApplicant.setName(firstName, middleName, lastName);
				jobApplicant.setSsn(ssn);
				jobApplicant.setAddressBasedOnZipCode(zipCode);

				jobApplicant.save();
			}
		}

		scanner.close();
		System.out.println(BYE_BYE_MESSAGE);
	}

	private static String getInput(Scanner scanner, String prompt) {
		String firstName;
		System.out.println(prompt);
		firstName = scanner.nextLine();
		return firstName;
	}

	private void setName(String firstName, String middleName, String lastName) {
		name = new Name (firstName, middleName, lastName);
	}

	private void setSsn(String ssnString) {
		ssn = new Ssn(ssnString);
	}

	private void setAddressBasedOnZipCode(String zipCode) throws IOException, URISyntaxException {
		address = new Address(zipCode);
	}

}
