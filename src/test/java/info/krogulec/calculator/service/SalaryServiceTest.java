package info.krogulec.calculator.service;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.SalaryPln;
import info.krogulec.calculator.service.country.GermanSalaryCalculator;
import info.krogulec.calculator.service.country.PolishSalaryCalculator;
import info.krogulec.calculator.service.country.UnitedKingdomSalaryCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author krogulecp
 */
class SalaryServiceTest {

    private SalaryService salaryService;

    @Mock
    private GermanSalaryCalculator germanSalaryCalculator;

    @Mock
    private PolishSalaryCalculator polishSalaryCalculator;

    @Mock
    private UnitedKingdomSalaryCalculator unitedKingdomSalaryCalculator;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        Set<SalaryStrategy> strategies = new HashSet<>();
        strategies.add(germanSalaryCalculator);
        strategies.add(polishSalaryCalculator);
        strategies.add(unitedKingdomSalaryCalculator);
        salaryService = new SalaryService(strategies);
    }

    @Test
    void should_pick_german(){
        //given
        //when
        Mockito.when(germanSalaryCalculator.getCountry())
                .thenReturn(Country.GERMANY);

        Mockito.when(germanSalaryCalculator.calculateSalary(Mockito.any()))
                .thenReturn(new SalaryPln(new BigDecimal(100)));

        SalaryPln salaryPln = salaryService.calculateSalary(new BigDecimal(123), Country.GERMANY);

        //then
        assertThat(salaryPln).isNotNull();
    }

    @Test
    void should_pick_poland(){
        //given
        //when
        Mockito.when(polishSalaryCalculator.getCountry())
                .thenReturn(Country.POLAND);

        Mockito.when(polishSalaryCalculator.calculateSalary(Mockito.any()))
                .thenReturn(new SalaryPln(new BigDecimal(100)));

        SalaryPln salaryPln = salaryService.calculateSalary(new BigDecimal(123), Country.POLAND);

        //then
        assertThat(salaryPln).isNotNull();
    }

    @Test
    void should_pick_uk(){
        //given
        //when
        Mockito.when(unitedKingdomSalaryCalculator.getCountry())
                .thenReturn(Country.UNITED_KINGDOM);

        Mockito.when(unitedKingdomSalaryCalculator.calculateSalary(Mockito.any()))
                .thenReturn(new SalaryPln(new BigDecimal(100)));

        SalaryPln salaryPln = salaryService.calculateSalary(new BigDecimal(123), Country.UNITED_KINGDOM);

        //then
        assertThat(salaryPln).isNotNull();
    }
}