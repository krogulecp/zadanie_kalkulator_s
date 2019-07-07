package info.krogulec.calculator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author krogulecp
 */
@ConfigurationProperties(prefix = "salary.calculator.poland")
public class PolandConfigurationProperties extends SalaryCalculatorConfigurationProperties {
}
