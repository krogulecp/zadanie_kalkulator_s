package info.krogulec.calculator.service;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.SalaryPln;
import info.krogulec.calculator.properties.SalaryCalculatorConfigurationProperties;

import java.math.BigDecimal;
import java.time.Period;

/**
 * @author krogulecp
 */
public abstract class SalaryBase implements SalaryStrategy {

    /**
     * Number of working days in month
     */
    protected static final Period WORKING_DAYS_COUNT = Period.ofDays(22);

    protected final SalaryCalculatorConfigurationProperties props;
    protected final Country country;
    protected final ToPlnConverter toPlnConverter;

    public SalaryBase(Country country, SalaryCalculatorConfigurationProperties props, ToPlnConverter toPlnConverter) {
        this.props = props;
        this.country = country;
        this.toPlnConverter = toPlnConverter;
    }

    @Override
    public SalaryPln calculateSalary(BigDecimal dailyRate) {
        BigDecimal monthlyGross = dailyRate.multiply(new BigDecimal(WORKING_DAYS_COUNT.getDays()));
        double taxMultiplier = 1 - (props.getTaxPercentage()/100);


        BigDecimal monthlySalary = (monthlyGross.multiply(new BigDecimal(taxMultiplier))).subtract(props.getFixedCosts());

        return new SalaryPln(toPlnConverter.convert(monthlySalary, country));
    }

    @Override
    public Country getCountry() {
        return country;
    }
}
