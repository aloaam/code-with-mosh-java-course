package coursepart1;

import coursepart2.mortgagecalculator.MortgageCalculatorComputations;
import coursepart2.mortgagecalculator.Console;
import coursepart2.mortgagecalculator.MortgageReporter;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class MortgageCalculator {


    public static void main(String[] args) {
        System.out.println("Mortgage Calculator");

        final NumberFormat usNumberFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        final NumberFormat integerFormatter = NumberFormat.getIntegerInstance();

        final Console console = new Console(new Scanner(System.in));

        final double principal = console.readNumber("Principal between $1K and $1M: ", 1_000, 1_000_000, usNumberFormatter);
        final double annualInterestRate = console.readNumber("Annual Interest Rate between 0% and 30%: ", 1, 30, integerFormatter);
        final double periodInYears = console.readNumber("Period in years between 1 and 30: ", 1, 30, integerFormatter);

        final MortgageCalculatorComputations mortgageComputations = new MortgageCalculatorComputations(
                principal, annualInterestRate, (int) periodInYears);

        final MortgageReporter reporter = new MortgageReporter(mortgageComputations, NumberFormat.getCurrencyInstance(Locale.US));
        reporter.printMortgageMonthlyPayment();
        reporter.printPaymentSchedule(1);

    }

}
