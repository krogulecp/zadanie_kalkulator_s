package info.krogulec.calculator.service;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.repository.ExchangeRateRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
@Component
public class ToPlnConverter {
    private final ExchangeRateRepository exchangeRateRepository;

    public ToPlnConverter(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public BigDecimal convert(BigDecimal in, Country country){
        return in.multiply(exchangeRateRepository.getRateForCurrencyCode(country.getCurrencyCode()));
    }


}
