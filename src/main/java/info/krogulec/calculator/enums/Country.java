package info.krogulec.calculator.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author krogulecp
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Country {
    POLAND ("PL", "PLN"), GERMANY ("DE", "EUR"), UNITED_KINGDOM ("UK","GBP");

    private final String countryCode;
    private final String currencyCode;

    Country(String countryCode, String currencyCode) {
        this.countryCode = countryCode;
        this.currencyCode = currencyCode;
    }

    //region Getters

    public String getCountryCode() {
        return countryCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    //endregion
}
