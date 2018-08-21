package com.neopragma.legacy.screen;

public enum SsnValidationStatus {
    VALID_SSN,
    INVALID_SSN_LENGTH,
    INVALID_SSN_AREA,
    INVALID_SSN_SERIAL,
    SSN_SPECIAL_CASE,
    SSN_STARTS_WITH_NINE;
}
