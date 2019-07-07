package info.krogulec.calculator;

import info.krogulec.calculator.properties.ApplicationProperties;
import info.krogulec.calculator.properties.GermanyConfigurationProperties;
import info.krogulec.calculator.properties.PolandConfigurationProperties;
import info.krogulec.calculator.properties.UnitedKingdomConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author krogulecp
 */
@SpringBootApplication
@EnableConfigurationProperties({
        GermanyConfigurationProperties.class,
        PolandConfigurationProperties.class,
        UnitedKingdomConfigurationProperties.class,
        ApplicationProperties.class
})
public class CalculatorTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculatorTaskApplication.class, args);
    }
}
