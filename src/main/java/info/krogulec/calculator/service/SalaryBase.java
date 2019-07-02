package info.krogulec.calculator.service;

import info.krogulec.calculator.SalaryCalculatorConfigurationProperties;
import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.repository.ExchangeRateRepository;

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
    protected final ExchangeRateRepository exchangeRateRepository;
    protected final Country country;

    public SalaryBase(ExchangeRateRepository exchangeRateRepository, Country country, SalaryCalculatorConfigurationProperties props) {
        this.props = props;
        this.exchangeRateRepository = exchangeRateRepository;
        this.country = country;
    }

    public ExchangeRateRepository getExchangeRateRepository() {
        return exchangeRateRepository;
    }
}
