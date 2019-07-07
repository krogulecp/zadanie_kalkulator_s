package info.krogulec.calculator.service.country;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.SalaryPln;
import info.krogulec.calculator.properties.PolandConfigurationProperties;
import info.krogulec.calculator.service.ToPlnConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author krogulecp
 */
class PolishSalaryPlnCalculatorTest {

    private PolishSalaryCalculator polishSalaryCalculator;

    @Mock
    private ToPlnConverter toPlnConverter;

    @Mock
    private PolandConfigurationProperties props;

    @BeforeEach
    void setUpBeforeEach(){
        MockitoAnnotations.initMocks(this);

        polishSalaryCalculator = new PolishSalaryCalculator(props, toPlnConverter);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "100, 19, 1000, 782.00",
            "100, 19, 1200, 582.00",
            "100, 0, 0, 2200.00",
            "100, 19, 0, 1782.00",
            "100, 0, 1200, 1000.00"
    })
    void should_calculate_salary(BigDecimal dailyRate, double taxPercentage, BigDecimal fixedCosts, BigDecimal expectedSalaryPln){

        Mockito.when(props.getTaxPercentage())
                .thenReturn(taxPercentage);

        Mockito.when(props.getFixedCosts())
                .thenReturn(fixedCosts);

        Mockito.when(toPlnConverter.convert(Mockito.any(), Mockito.any()))
                .thenCallRealMethod();

        SalaryPln salaryPln = polishSalaryCalculator.calculateSalary(dailyRate);

        assertThat(salaryPln.getAmount()).isEqualTo(expectedSalaryPln);
    }

    @Test
    void should_return_poland(){
        assertThat(polishSalaryCalculator.getCountry()).isEqualTo(Country.POLAND);
    }

}