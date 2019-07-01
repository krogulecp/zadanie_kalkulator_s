package info.krogulec.calculator.service.country;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.Salary;
import info.krogulec.calculator.repository.ExchangeRateRepository;
import info.krogulec.calculator.service.SalaryBase;
import info.krogulec.calculator.service.SalaryStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
@Component
public class GermanSalaryCalculator extends SalaryBase implements SalaryStrategy {

    public GermanSalaryCalculator(ExchangeRateRepository exchangeRateRepository) {
        super(exchangeRateRepository);
    }

    @Override
    public Salary calculateSalary(BigDecimal dailyRate) {
        return null;
    }

    @Override
    public Country getCountry() {
        return null;
    }
}
