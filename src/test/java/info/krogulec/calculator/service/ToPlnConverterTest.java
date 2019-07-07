package info.krogulec.calculator.service;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.repository.ExchangeRateRepository;
import org.junit.jupiter.api.BeforeEach;
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
class ToPlnConverterTest {

    private ToPlnConverter toPlnConverter;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @BeforeEach
    void setUpConverter(){
        MockitoAnnotations.initMocks(this);
        toPlnConverter = new ToPlnConverter(exchangeRateRepository);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0, GERMANY, 4.15, 0.00",
            "0, UNITED_KINGDOM, 5.34, 0.00",
            "0.00, POLAND, 1, 0.00",
            "10000, GERMANY, 4.15, 41500.00",
            "10000, UNITED_KINGDOM, 5.34, 53400.00",
            "10000.00, POLAND, 1, 10000.00",
            "10000, GERMANY, 1, 10000",
            "10000, UNITED_KINGDOM, 1, 10000",
            "10000, GERMANY, 0, 0",
            "10000, UNITED_KINGDOM, 0, 0"
    })
    void should_convert_currency(BigDecimal payment, Country country, BigDecimal exchangeRate, BigDecimal paymentInPln){

        //when
        Mockito.when(exchangeRateRepository.getRateForCurrencyCode(country.getCurrencyCode()))
                .thenReturn(exchangeRate);

        BigDecimal result = toPlnConverter.convert(payment, country);

        //then
        assertThat(result).isEqualTo(paymentInPln);
    }
}