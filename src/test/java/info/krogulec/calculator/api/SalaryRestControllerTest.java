package info.krogulec.calculator.api;

import info.krogulec.calculator.enums.Country;
import info.krogulec.calculator.model.Salary;
import info.krogulec.calculator.service.SalaryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author krogulecp
 */
@WebMvcTest(controllers = SalaryRestController.class)
class SalaryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalaryService salaryService;

    @Test
    void should_return_salary_proper_contract() throws Exception {

        //given
        final Country COUNTRY = Country.POLAND;
        final BigDecimal AMOUNT = new BigDecimal("9999.99");

        final String URL = "/api/salary?dailyRate=100&country=" + COUNTRY;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON);

        Mockito.when(salaryService.calculateSalary(Mockito.any(), Mockito.any()))
                .thenReturn(new Salary(AMOUNT, COUNTRY));

        //when and then
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.amount", is(AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.country.countryCode", is(COUNTRY.getCountryCode())))
                .andExpect(jsonPath("$.country.currencyCode", is(COUNTRY.getCurrencyCode())));
    }

    @Test
    void should_throw_exception_for_invalid_daily_amount() throws Exception {

        //given
        final Country COUNTRY = Country.POLAND;
        final BigDecimal AMOUNT = new BigDecimal("9999.99");

        final String URL = "/api/salary?dailyRate=notValid&country=" + COUNTRY;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON);

        Mockito.when(salaryService.calculateSalary(Mockito.any(), Mockito.any()))
                .thenReturn(new Salary(AMOUNT, COUNTRY));

        //when and then
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isInternalServerError());
    }

}