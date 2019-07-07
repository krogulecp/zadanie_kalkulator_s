package info.krogulec.calculator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author krogulecp
 */
@ConfigurationProperties(prefix = "salary.calculator.germany")
public class GermanyConfigurationProperties extends SalaryCalculatorConfigurationProperties {
}
