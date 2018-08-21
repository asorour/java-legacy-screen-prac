package com.neopragma.legacy.screen;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class Address {
    public static final String CONTENT_ZIP_CODE_PREFIX = " content=\"Zip Code ";
    public static final String META_PREFIX = "<meta ";
    public static final int CONTENT_ZIP_CODE_PREFIX_LENGTH = 19;
    public static final String SPACE_HYPHEN_SPACE = " - ";
    public static final String STATE_PREFIX = " ";
    public static final int SPACE_HYPHEN_SPACE_LENGTH = 3;
    public static final int STATE_PREFIX_LENGTH = 1;
    public static final int STATE_ABBREVIATION_LENGTH = 2;

    private String zipCode;
    private String city;
    private String state;

    public Address(String zipCode) {
        try {
            LookupZipCodeAndSetCityAndState(zipCode);
            this.zipCode = zipCode;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LookupZipCodeAndSetCityAndState(String zipCode) throws URISyntaxException, IOException {
        city = "";
        state = "";

        URI uri = getUri(zipCode);

        CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet(uri));

        try {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(entity.getContent()));
                StringBuffer result = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                int metaOffset = result.indexOf(META_PREFIX);

                int contentOffset = result.indexOf(CONTENT_ZIP_CODE_PREFIX, metaOffset);
                contentOffset += CONTENT_ZIP_CODE_PREFIX_LENGTH;

                contentOffset = result.indexOf(SPACE_HYPHEN_SPACE, contentOffset);
                contentOffset += SPACE_HYPHEN_SPACE_LENGTH;

                int stateOffset = result.indexOf(STATE_PREFIX, contentOffset);
                city = result.substring(contentOffset, stateOffset);

                stateOffset += STATE_PREFIX_LENGTH;
                state = result.substring(stateOffset, stateOffset + STATE_ABBREVIATION_LENGTH);
            }
        } finally {
            response.close();
        }

    }

    private URI getUri(String zipCode) throws URISyntaxException {
        return new URIBuilder()
                    .setScheme("http")
                    .setHost("www.zip-codes.com")
                    .setPath("/search.asp")
                    .setParameter("fld-zip", zipCode)
                    .setParameter("selectTab", "0")
                    .setParameter("srch-type", "city")
                    .build();
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String ToString() {
        return String.format("%s, %s %s", city, state, zipCode);
    }
}
