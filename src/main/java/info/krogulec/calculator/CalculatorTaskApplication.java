package info.krogulec.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author krogulecp
 */
@SpringBootApplication
@EnableConfigurationProperties(SalaryCalculatorConfigurationProperties.class)
public class CalculatorTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculatorTaskApplication.class, args);
    }
}
