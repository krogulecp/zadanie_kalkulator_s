package info.krogulec.calculator.properties;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
public class SalaryCalculatorConfigurationProperties {
    private double taxPercentage;
    private BigDecimal fixedCosts;

    //region Getters and Setters

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public BigDecimal getFixedCosts() {
        return fixedCosts;
    }

    public void setFixedCosts(BigDecimal fixedCosts) {
        this.fixedCosts = fixedCosts;
    }

}
