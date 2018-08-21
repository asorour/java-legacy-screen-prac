package com.neopragma.legacy.screen;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SsnTest {

    @Test
    public void ssnFormattingTest() {
        Ssn ssn = new Ssn("123456789");
        assertEquals("123-45-6789", ssn.formatSsn());
    }

    @Test
    public void validSsnWithNoDashes() {
        Ssn ssn = new Ssn("123456789");
        assertEquals(SsnValidationStatus.VALID_SSN, ssn.validateSsn());
    }

    @Test
    public void ssnWithDashesInWrongPlaces() {
        Ssn ssn = new Ssn("12-3456-789");
        assertEquals(SsnValidationStatus.INVALID_SSN_LENGTH, ssn.validateSsn());
    }

    @Test
    public void validSsnWithDashes() {
        Ssn ssn = new Ssn("123-45-6789");
        assertEquals(SsnValidationStatus.VALID_SSN, ssn.validateSsn());
    }

    @Test
    public void ssnIsTooShort() {
        Ssn ssn = new Ssn("12345678");
        assertEquals(SsnValidationStatus.INVALID_SSN_LENGTH, ssn.validateSsn());
    }

    @Test
    public void ssnIsTooLong() {
        Ssn ssn = new Ssn("1234567890");
        assertEquals(SsnValidationStatus.INVALID_SSN_LENGTH, ssn.validateSsn());
    }

    @Test
    public void ssnAreaNumberIs000() {
        Ssn ssn = new Ssn("000223333");
        assertEquals(SsnValidationStatus.INVALID_SSN_AREA, ssn.validateSsn());
    }

    @Test
    public void ssnAreaNumberIs666() {
        Ssn ssn = new Ssn("666223333");
        assertEquals(SsnValidationStatus.INVALID_SSN_AREA, ssn.validateSsn());
    }

    @Test
    public void ssnAreaNumberStartsWith9() {
        Ssn ssn = new Ssn("900223333");
        assertEquals(SsnValidationStatus.SSN_STARTS_WITH_NINE, ssn.validateSsn());
    }

    @Test
    public void ssnSerialNumberIs0000() {
        Ssn ssn = new Ssn("111220000");
        assertEquals(SsnValidationStatus.INVALID_SSN_SERIAL, ssn.validateSsn());
    }

    @Test
    public void itRejectsSsn078051120() {
        Ssn ssn = new Ssn("078051120");
        assertEquals(SsnValidationStatus.SSN_SPECIAL_CASE, ssn.validateSsn());
    }

    @Test
    public void itRejectsSsn219099999() {
        Ssn ssn = new Ssn("219099999");
        assertEquals(SsnValidationStatus.SSN_SPECIAL_CASE, ssn.validateSsn());
    }

}
