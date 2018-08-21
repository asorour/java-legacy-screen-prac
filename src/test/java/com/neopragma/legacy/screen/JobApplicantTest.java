package com.neopragma.legacy.screen;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.neopragma.legacy.screen.JobApplicant;

/**
 * Automated unit checks for the base version of the JobApplicant application.
 * This version of the code contains a number of code smells that may point to
 * potential improvements in the design of the code.
 * 
 * @author neopragma
 * @version 1.0.0
 * @since 1.7
 */

public class JobApplicantTest {
	
	private JobApplicant jobApplicant;
	
	@Before
	public void beforeEach() {
		jobApplicant = new JobApplicant();
	}

	@Test
	public void itFindsAddisonTexasBy5DigitZipCode() throws URISyntaxException, IOException {
		jobApplicant.setZipCode("75001");
		assertEquals("Addison", jobApplicant.getCity());
		assertEquals("TX", jobApplicant.getState());
	}
	
	@Test
	public void itFindsMaranaArizonaBy9DigitZipCode() throws URISyntaxException, IOException {
		jobApplicant.setZipCode("856585578");
		assertEquals("Marana", jobApplicant.getCity());
		assertEquals("AZ", jobApplicant.getState());
	}

}
