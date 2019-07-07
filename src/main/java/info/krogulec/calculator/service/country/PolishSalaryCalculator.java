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
public class PolishSalaryCalculator extends SalaryBase {

    public PolishSalaryCalculator(PolandConfigurationProperties props, ToPlnConverter toPlnConverter) {
        super(Country.POLAND, props, toPlnConverter);
    }
}
