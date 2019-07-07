package info.krogulec.calculator.service.country;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.SalaryPln;
import info.krogulec.calculator.properties.ApplicationProperties;
import info.krogulec.calculator.properties.GermanyConfigurationProperties;
import info.krogulec.calculator.repository.ExchangeRateRepository;
import info.krogulec.calculator.service.ToPlnConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author krogulecp
 */
class GermanSalaryPlnCalculatorTest {

    private static final String EXCHANGE_RATE_URL = "/exchangerates/rates/a/";
    private static final BigDecimal EXPECTED_RATE = new BigDecimal(4.00);
    private static final String EXPECTED_RETURN = "{\"table\":\"A\",\"currency\":\"euro\",\"code\":\"EUR\",\"rates\":[{\"no\":\"129/A/NBP/2019\",\"effectiveDate\":\"2019-07-05\",\"mid\":" + EXPECTED_RATE.doubleValue() + "}]}";

    private RestTemplate restTemplate;
    private GermanSalaryCalculator germanSalaryCalculator;
    private ToPlnConverter toPlnConverter;
    private GermanyConfigurationProperties germanyProps;
    private ApplicationProperties appProps;
    private MockRestServiceServer mockServer;
    private ExchangeRateRepository exchangeRateRepository;

    @BeforeEach
    void setUpBeforeEach(){
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        germanyProps = new GermanyConfigurationProperties();
        appProps = new ApplicationProperties();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "100, 20, 800, 3840.00",
            "100, 0, 800, 5600.00",
            "100, 20, 0, 7040.00",
            "100, 0, 0, 8800.00"
    })
    void should_calculate_salary(BigDecimal dailyRate, double taxPercentage, BigDecimal fixedCosts, BigDecimal expectedSalaryPln){
        //given
        appProps.setExchangeRateUrl(EXCHANGE_RATE_URL);
        germanyProps = new GermanyConfigurationProperties();
        germanyProps.setFixedCosts(fixedCosts);
        germanyProps.setTaxPercentage(taxPercentage);
        exchangeRateRepository = new ExchangeRateRepository(appProps, restTemplate);
        toPlnConverter = new ToPlnConverter(exchangeRateRepository);
        germanSalaryCalculator = new GermanSalaryCalculator(germanyProps, toPlnConverter);

        mockServer.expect(requestTo(EXCHANGE_RATE_URL + Country.GERMANY.getCurrencyCode())).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(EXPECTED_RETURN, MediaType.APPLICATION_JSON));

        //when
        SalaryPln salaryPln = germanSalaryCalculator.calculateSalary(dailyRate);

        //then
        assertThat(salaryPln.getAmount()).isEqualTo(expectedSalaryPln);
    }

    @Test
    void should_return_germany(){
        germanSalaryCalculator = new GermanSalaryCalculator(germanyProps, toPlnConverter);
        assertThat(germanSalaryCalculator.getCountry()).isEqualTo(Country.GERMANY);
    }

}