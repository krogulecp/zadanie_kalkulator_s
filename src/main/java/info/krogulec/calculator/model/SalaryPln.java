package info.krogulec.calculator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import info.krogulec.calculator.enums.Country;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
public final class SalaryPln {

    private final BigDecimal amount;

    public SalaryPln(BigDecimal amount) {
        this.amount = amount;
    }

    //region Getters

    public BigDecimal getAmount() {
        return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    //endregion
}
