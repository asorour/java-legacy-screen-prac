package com.neopragma.legacy.screen;

public class Ssn {
    private String ssn;

    private String[] specialCases = new String[] {
            "219099999", "078051120"
    };

    public Ssn(String ssnString) {
        setSsn(ssnString);
    }

    public void setSsn(String ssn) {
        if ( ssn.matches("(\\d{3}-\\d{2}-\\d{4}|\\d{9})") ) {
            this.ssn = ssn.replaceAll("-", "");
        } else {
            this.ssn = "";
        }
    }

    public String formatSsn() {
        StringBuilder sb = new StringBuilder(ssn.substring(0,3));
        sb.append("-");
        sb.append(ssn.substring(3,5));
        sb.append("-");
        sb.append(ssn.substring(5));
        return sb.toString();
    }

    public SsnValidationStatus validateSsn() {
        if ( !ssn.matches("\\d{9}") ) {
            return SsnValidationStatus.INVALID_SSN_LENGTH;
        }
        if ( "000".equals(ssn.substring(0,3)) ||
                "666".equals(ssn.substring(0,3)) ) {
            return SsnValidationStatus.INVALID_SSN_AREA;
        }
        if ( "9".equals(ssn.substring(0,1)) ) {
            return SsnValidationStatus.SSN_STARTS_WITH_NINE;
        }
        if ( "0000".equals(ssn.substring(5)) ) {
            return SsnValidationStatus.INVALID_SSN_SERIAL;
        }
        for (int i = 0 ; i < specialCases.length ; i++ ) {
            if ( ssn.equals(specialCases[i]) ) {
                return SsnValidationStatus.SSN_SPECIAL_CASE;
            }
        }
        return SsnValidationStatus.VALID_SSN;
    }
    
}
