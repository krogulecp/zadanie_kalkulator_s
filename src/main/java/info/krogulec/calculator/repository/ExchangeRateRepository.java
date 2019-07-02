package info.krogulec.calculator.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
@Repository
public class ExchangeRateRepository {
    public BigDecimal getRateForCurrencyCode(String currencyCode) {
        return null;
    }
}
