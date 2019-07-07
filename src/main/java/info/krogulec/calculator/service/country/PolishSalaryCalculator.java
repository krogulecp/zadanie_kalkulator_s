package info.krogulec.calculator.service.country;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.SalaryPln;
import info.krogulec.calculator.properties.PolandConfigurationProperties;
import info.krogulec.calculator.service.SalaryBase;
import info.krogulec.calculator.service.SalaryStrategy;
import info.krogulec.calculator.service.ToPlnConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
@Component
public class PolishSalaryCalculator extends SalaryBase implements SalaryStrategy {

    public PolishSalaryCalculator(PolandConfigurationProperties props, ToPlnConverter toPlnConverter) {
        super(Country.POLAND, props, toPlnConverter);
    }

    @Override
    public SalaryPln calculateSalary(BigDecimal dailyRate) {
        BigDecimal monthlyGross = dailyRate.multiply(new BigDecimal(WORKING_DAYS_COUNT.getDays()));
        double taxMultiplier = 1 - (props.getTaxPercentage()/100);


        BigDecimal monthlySalary = (monthlyGross.multiply(new BigDecimal(taxMultiplier)))
                .subtract(props.getFixedCosts());

        return new SalaryPln(toPlnConverter.convert(monthlySalary, country));
    }

    @Override
    public Country getCountry() {
        return country;
    }
}
