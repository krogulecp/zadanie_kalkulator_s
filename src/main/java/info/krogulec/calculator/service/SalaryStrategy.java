package info.krogulec.calculator.service;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.Salary;

import java.math.BigDecimal;
import java.time.Period;

/**
 * @author krogulecp
 */
public interface SalaryStrategy {

    Salary calculateSalary(BigDecimal dailyRate);

    Country getCountry();
}
