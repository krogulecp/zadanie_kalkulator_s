package info.krogulec.calculator.service;

import info.krogulec.calculator.SalaryCalculatorConfigurationProperties;
import info.krogulec.calculator.enums.Country;

import java.time.Period;

/**
 * @author krogulecp
 */
public abstract class SalaryBase {

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
}
