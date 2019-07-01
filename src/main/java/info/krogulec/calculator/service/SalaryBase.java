package info.krogulec.calculator.service;

import info.krogulec.calculator.repository.ExchangeRateRepository;

/**
 * @author krogulecp
 */
public class SalaryBase {

    private final ExchangeRateRepository exchangeRateRepository;

    public SalaryBase(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public ExchangeRateRepository getExchangeRateRepository() {
        return exchangeRateRepository;
    }
}
