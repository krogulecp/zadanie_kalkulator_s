package info.krogulec.calculator.model;

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
