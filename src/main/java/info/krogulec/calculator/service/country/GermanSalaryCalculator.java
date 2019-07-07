package info.krogulec.calculator.service.country;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.SalaryPln;
import info.krogulec.calculator.properties.GermanyConfigurationProperties;
import info.krogulec.calculator.service.SalaryBase;
import info.krogulec.calculator.service.SalaryStrategy;
import info.krogulec.calculator.service.ToPlnConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
@Component
public class GermanSalaryCalculator extends SalaryBase {

    public GermanSalaryCalculator(GermanyConfigurationProperties props, ToPlnConverter toPlnConverter) {
        super(Country.GERMANY, props, toPlnConverter);
    }
}
