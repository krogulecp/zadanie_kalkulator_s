package info.krogulec.calculator.repository;

import info.krogulec.calculator.exception.ExternalServiceConnectionException;
import info.krogulec.calculator.properties.ApplicationProperties;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
@Repository
public class ExchangeRateRepository {

    private static final String CONNECTION_ERROR_MSG = "Error while getting exchange rate for ";

    private final String apiRequestUrlPattern;
    private final RestTemplate restTemplate;

    public ExchangeRateRepository(ApplicationProperties props, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.apiRequestUrlPattern = props.getExchangeRateUrl();

    }

    public BigDecimal getRateForCurrencyCode(String currencyCode) {

        final String requestUrl = apiRequestUrlPattern + currencyCode;
        ResponseEntity<String> currencyRateEntity;

        try {
            currencyRateEntity = restTemplate.getForEntity(requestUrl, String.class);
        } catch (RestClientException e){
            throw new ExternalServiceConnectionException(CONNECTION_ERROR_MSG + currencyCode, e);
        }

        if (!currencyRateEntity.getStatusCode().is2xxSuccessful()){
            throw new ExternalServiceConnectionException(CONNECTION_ERROR_MSG + currencyCode);
        }

        try {
            return new BigDecimal(new JSONObject(currencyRateEntity.getBody())
                    .getJSONArray("rates")
                    .getJSONObject(0)
                    .getDouble("mid"));

        } catch (JSONException e) {
            throw new ExternalServiceConnectionException(CONNECTION_ERROR_MSG + currencyCode, e);
        }
    }
}
