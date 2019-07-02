package info.krogulec.calculator.service;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.SalaryPln;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
public interface SalaryStrategy {

    SalaryPln calculateSalary(BigDecimal dailyRate);

    Country getCountry();
}
