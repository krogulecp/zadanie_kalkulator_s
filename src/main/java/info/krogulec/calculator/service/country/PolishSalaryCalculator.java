package info.krogulec.calculator.service.country;

import info.krogulec.calculator.SalaryCalculatorConfigurationProperties;
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
public class PolishSalaryCalculator extends SalaryBase implements SalaryStrategy {

    public PolishSalaryCalculator(ExchangeRateRepository exchangeRateRepository, SalaryCalculatorConfigurationProperties props) {
        super(exchangeRateRepository, Country.POLAND, props);
    }

    @Override
    public Salary calculateSalary(BigDecimal dailyRate) {
        BigDecimal monthlyGross = dailyRate.multiply(new BigDecimal(WORKING_DAYS_COUNT.getDays()));
        double taxMultiplier = 1 - (props.getPoland().getTaxPercentage()/100);


        BigDecimal monthlySalary = (monthlyGross.multiply(new BigDecimal(taxMultiplier)))
                .subtract(props.getPoland().getFixedCostsPln());

        return new Salary(monthlySalary, country);
    }

    @Override
    public Country getCountry() {
        return country;
    }
}
