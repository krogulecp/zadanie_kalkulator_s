package info.krogulec.calculator;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

/**
 * @author krogulecp
 */
@ConfigurationProperties(prefix = "salary.calculator")
public class SalaryCalculatorConfigurationProperties {
    private Germany germany;
    private UK uk;
    private Poland poland;

    //region Getters and Setters

    public Germany getGermany() {
        return germany;
    }

    public void setGermany(Germany germany) {
        this.germany = germany;
    }

    public UK getUk() {
        return uk;
    }

    public void setUk(UK uk) {
        this.uk = uk;
    }

    public Poland getPoland() {
        return poland;
    }

    public void setPoland(Poland poland) {
        this.poland = poland;
    }

    //endregion

    public static class Germany {
        private double taxPercentage;
        private BigDecimal fixedCostsEuro;

        //region Getters and Setters

        public double getTaxPercentage() {
            return taxPercentage;
        }

        public void setTaxPercentage(double taxPercentage) {
            this.taxPercentage = taxPercentage;
        }

        public BigDecimal getFixedCostsEuro() {
            return fixedCostsEuro;
        }

        public void setFixedCostsEuro(BigDecimal fixedCostsEuro) {
            this.fixedCostsEuro = fixedCostsEuro;
        }

        //endregion
    }

    public static class UK {
        private double taxPercentage;
        private BigDecimal fixedCostsGbp;

        //region Getters and Setters

        public double getTaxPercentage() {
            return taxPercentage;
        }

        public void setTaxPercentage(double taxPercentage) {
            this.taxPercentage = taxPercentage;
        }

        public BigDecimal getFixedCostsGbp() {
            return fixedCostsGbp;
        }

        public void setFixedCostsGbp(BigDecimal fixedCostsGbp) {
            this.fixedCostsGbp = fixedCostsGbp;
        }

        //endregion
    }

    public static class Poland {
        private double taxPercentage;
        private BigDecimal fixedCostsPln;

        //region Getters and Setters

        public double getTaxPercentage() {
            return taxPercentage;
        }

        public void setTaxPercentage(double taxPercentage) {
            this.taxPercentage = taxPercentage;
        }

        public BigDecimal getFixedCostsPln() {
            return fixedCostsPln;
        }

        public void setFixedCostsPln(BigDecimal fixedCostsPln) {
            this.fixedCostsPln = fixedCostsPln;
        }

        //endregion
    }
}
