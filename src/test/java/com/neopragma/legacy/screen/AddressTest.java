package com.neopragma.legacy.screen;

import org.junit.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import static org.junit.Assert.assertEquals;

public class AddressTest {

    private Address address;

    @Test
    public void itFindsAddisonTexasBy5DigitZipCode() throws URISyntaxException, IOException {
        address = new Address("75001");
        assertEquals("Addison", address.getCity());
        assertEquals("TX", address.getState());
        assertEquals("75001", address.getZipCode());
    }

    @Test
    public void itFindsMaranaArizonaBy9DigitZipCode() throws URISyntaxException, IOException {
        address = new Address("856585578");
        assertEquals("Marana", address.getCity());
        assertEquals("AZ", address.getState());
        assertEquals("856585578", address.getZipCode());
    }
}
