package info.krogulec.calculator.api;

import info.krogulec.calculator.enums.Country;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
public class SalaryRequest {

    private BigDecimal dailyRate;
    private Country country;

    //region Getters and Setters

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        if (dailyRate.doubleValue() >= 0){
            this.dailyRate = dailyRate;
        }else {
            throw new IllegalArgumentException("Daily rate not valid");
        }
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

//endregion
}
