package info.krogulec.calculator.service.country;

import info.krogulec.calculator.SalaryCalculatorConfigurationProperties;
import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.SalaryPln;
import info.krogulec.calculator.repository.ExchangeRateRepository;
import info.krogulec.calculator.service.SalaryBase;
import info.krogulec.calculator.service.SalaryStrategy;
import info.krogulec.calculator.service.ToPlnConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
@Component
public class GermanSalaryCalculator extends SalaryBase implements SalaryStrategy {

    public GermanSalaryCalculator(SalaryCalculatorConfigurationProperties props, ToPlnConverter toPlnConverter) {
        super(Country.GERMANY, props, toPlnConverter);
    }

    @Override
    public SalaryPln calculateSalary(BigDecimal dailyRate) {
        BigDecimal monthlyGross = dailyRate.multiply(new BigDecimal(WORKING_DAYS_COUNT.getDays()));
        double taxMultiplier = 1 - (props.getPoland().getTaxPercentage()/100);


        BigDecimal monthlySalary = (monthlyGross.multiply(new BigDecimal(taxMultiplier))).subtract(props.getGermany().getFixedCostsEuro());

        return new SalaryPln(toPlnConverter.convert(monthlySalary, country));
    }

    @Override
    public Country getCountry() {
        return country;
    }
}
