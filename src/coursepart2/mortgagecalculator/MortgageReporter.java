package coursepart2.mortgagecalculator;

import java.text.NumberFormat;
import java.util.Map;
import java.util.stream.IntStream;

public class MortgageReporter {

    final MortgageCalculatorComputations mortgageComputations;
    private NumberFormat formatter;

    public MortgageReporter(MortgageCalculatorComputations mortgageComputations, NumberFormat formatter) {
        this.mortgageComputations = mortgageComputations;
        this.formatter = formatter;
    }

    public void printMortgageMonthlyPayment() {
        this.formatter = formatter;
        printTitle("MORTGAGE");
        System.out.println(formatter.format(mortgageComputations.computeMortgagePayment()));
    }

    public void printPaymentSchedule(int numberOfPaymentsDone) {
        printTitle("PAYMENT SCHEDULE");

        Map<Integer, Double> remainingBalances = mortgageComputations.getRemainingBalances();
        IntStream
                .rangeClosed(numberOfPaymentsDone, remainingBalances.size())
                        .forEach(integer ->
                                System.out.println(formatter.format(remainingBalances.get(integer))));
    }

    private void printTitle(String title) {
        System.out.println();
        System.out.println(title);
        System.out.println("-".repeat(title.length()));
    }


}
