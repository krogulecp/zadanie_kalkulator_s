package info.krogulec.calculator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author krogulecp
 */
@ConfigurationProperties(prefix = "salary.calculator")
public class ApplicationProperties {
    private String exchangeRateUrl;

    //region Getters and Setters

    public String getExchangeRateUrl() {
        return exchangeRateUrl;
    }

    public void setExchangeRateUrl(String exchangeRateUrl) {
        this.exchangeRateUrl = exchangeRateUrl;
    }

    //endregion
}
