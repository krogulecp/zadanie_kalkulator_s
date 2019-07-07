package info.krogulec.calculator.repository;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.exception.ExternalServiceConnectionException;
import info.krogulec.calculator.properties.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author krogulecp
 */
class ExchangeRateRepositoryTest {

    private static final String EXCHANGE_RATE_BASE_URL = "/exchangerates/rates/a/";

    private ExchangeRateRepository exchangeRateRepository;
    private MockRestServiceServer mockServer;


    @BeforeEach
    void estUpBeforeEach(){
        ApplicationProperties props = new ApplicationProperties();
        props.setExchangeRateUrl(EXCHANGE_RATE_BASE_URL);
        RestTemplate restTemplate = new RestTemplate();
        exchangeRateRepository = new ExchangeRateRepository(props, restTemplate);
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void should_return_rate(){

        //given
        final BigDecimal EXPECTED_RATE = new BigDecimal(4.42);
        final String EXPECTED_RETURN = "{\"table\":\"A\",\"currency\":\"euro\",\"code\":\"EUR\",\"rates\":[{\"no\":\"129/A/NBP/2019\",\"effectiveDate\":\"2019-07-05\",\"mid\":" + EXPECTED_RATE.doubleValue() + "}]}";

        mockServer.expect(requestTo(EXCHANGE_RATE_BASE_URL + Country.POLAND.getCurrencyCode())).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(EXPECTED_RETURN, MediaType.APPLICATION_JSON));

        //when
        BigDecimal rateForCurrencyCode = exchangeRateRepository.getRateForCurrencyCode(Country.POLAND.getCurrencyCode());

        //then
        assertThat(rateForCurrencyCode).isEqualTo(EXPECTED_RATE);

    }

    @Test
    void should_throw_exception_with_not_valid_response_body(){

        //given
        final String EXPECTED_RETURN = "not valid schema";

        mockServer.expect(requestTo(EXCHANGE_RATE_BASE_URL + Country.POLAND.getCurrencyCode())).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(EXPECTED_RETURN, MediaType.APPLICATION_JSON));

        //when
        //then
        assertThrows(ExternalServiceConnectionException.class, () -> exchangeRateRepository.getRateForCurrencyCode(Country.POLAND.getCurrencyCode()));

    }

    @Test
    void should_throw_exception_with_response_other_than_2xx(){

        //given
        mockServer.expect(requestTo(EXCHANGE_RATE_BASE_URL + Country.POLAND.getCurrencyCode())).andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        //when
        //then
        assertThrows(ExternalServiceConnectionException.class, () -> exchangeRateRepository.getRateForCurrencyCode(Country.POLAND.getCurrencyCode()));

    }

}