package com.neopragma.legacy.screen;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NameTest {
    @Test
    public void completeNameProvided() {
        Name name = new Name("First", "Middle", "Last");
        assertEquals(0, name.validateName());
    }

    @Test
    public void firstAndLastNamesProvided() {
        Name name = new Name("First", null, "Last");
        assertEquals(0, name.validateName());
    }

    @Test
    public void missingFirstName() {
        Name name = new Name(null, null, "Last");
        assertEquals(6, name.validateName());
    }

    @Test
    public void missingLastName() {
        Name name = new Name("First", null, null);
        assertEquals(6, name.validateName());
    }

    @Test
    public void formatEnglishNameLastNameFirst() {
        Name name = new Name("First", "Middle", "Last");
        assertEquals("Last, First Middle", name.formatLastNameFirst());
    }
}
