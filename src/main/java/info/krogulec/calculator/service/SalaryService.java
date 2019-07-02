package info.krogulec.calculator.service;

import info.krogulec.calculator.CountryNotFoundException;
import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.Salary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Set;

/**
 * @author krogulecp
 */
@Service
public class SalaryService {
    private final Set<SalaryStrategy> salaryStrategies;

    public SalaryService(Set<SalaryStrategy> salaryStrategies) {
        this.salaryStrategies = salaryStrategies;
    }

    public Salary calculateSalary(BigDecimal dailyRate, Country country){
        return salaryStrategies
                .stream()
                .filter(salaryStrategy -> country.equals(salaryStrategy.getCountry()))
                .map(salaryStrategy -> salaryStrategy.calculateSalary(dailyRate))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException(String.format("Country %s not found", country.name())));
    }
}
