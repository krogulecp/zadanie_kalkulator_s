package info.krogulec.calculator.model;

import info.krogulec.calculator.enums.Country;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
public final class Salary {
    private final BigDecimal amount;
    private final Country country;

    public Salary(BigDecimal amount, Country country) {
        this.amount = amount;
        this.country = country;
    }

    //region Getters

    public BigDecimal getAmount() {
        return amount;
    }

    public Country getCountry() {
        return country;
    }

    //endregion
}
