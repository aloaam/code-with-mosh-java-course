package coursepart2.mortgagecalculator;

import java.util.HashMap;
import java.util.Map;

public class MortgageCalculatorComputations {

    private final Double principal;
    private final Double annualInterestRate;
    private final int periodInYears;

    private final int MONTHS_IN_YEARS = 12;
    private final int PERCENT = 100;

    public MortgageCalculatorComputations(Double principal, Double annualInterestRate, int periodInYears) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.periodInYears = periodInYears;

    }

    public Double computeMortgagePayment() {
        double factor = Math.pow(1 + getMonthlyInterestRate(), getPeriodInMonths());
        return principal * (getMonthlyInterestRate() * factor) / (factor - 1);
    }

    public double computeRemainingBalance(int numberOfPaymentsMade) {
        final double rate = (1 + getMonthlyInterestRate());
        return principal
                * (Math.pow(rate, getPeriodInMonths()) - Math.pow(rate, numberOfPaymentsMade))
                / (Math.pow(rate, getPeriodInMonths()) - 1);
    }

    //TODO: stream to map,
    public Map<Integer, Double> getRemainingBalances() {

        final Map<Integer, Double> remainingBalances = new HashMap<>();

        for (int i = 1; i <= getPeriodInMonths(); i++) {
            remainingBalances.put(i, computeRemainingBalance(i));
        }

        return remainingBalances;
    }

    private Double getMonthlyInterestRate() {
        return annualInterestRate / PERCENT / MONTHS_IN_YEARS;
    }

    private int getPeriodInMonths() {
        return periodInYears * MONTHS_IN_YEARS;
    }
}
