package coursepart2.mortgagecalculator;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MortgageCalculator {

    final static int MONTHS_IN_YEARS = 12;
    final static double PERCENT = 100;


    public static void main(String[] args) {

        final NumberFormat usNumberFormatter = NumberFormat.getCurrencyInstance(Locale.US);

        System.out.println("Mortgage Calculator");

        System.out.println(greetUser("Alo"));

//        final Double principal = getPrincipal(new Scanner(System.in));
//        final Double monthlyInterestRate = getMonthlyInterestRate(new Scanner(System.in));
//        final int monthlyPayments = getNumberOfMonthlyPayments(new Scanner(System.in));



        final Double principal = 180_000.0;
        final Double monthlyInterestRate = 4.0 / PERCENT / MONTHS_IN_YEARS;
        final int monthlyPayments = 10 * MONTHS_IN_YEARS;

        final Double monthlyPayment = computeMortgage(
                principal,
                monthlyInterestRate,
                monthlyPayments
        );

        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + usNumberFormatter.format(monthlyPayment));

        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");

        IntStream.range(1, monthlyPayments + 1)
                .forEach(i -> System.out.println(
                        usNumberFormatter.format(
                                computeRemainingBalance(
                                        principal,
                                        monthlyInterestRate,
                                        monthlyPayments,
                                        i))));
    }


    public static String greetUser(String name) {
        return "Hello " + name;
    }


    public static class MortgagePaymentEvaluator {

        private double principal;
        private double monthlyInterestRate;
        private double numberOfMonthlyPayments;

        public MortgagePaymentEvaluator(double principal, double monthlyInterestRate, double numberOfMonthlyPayments) {
            this.principal = principal;
            this.monthlyInterestRate = monthlyInterestRate;
            this.numberOfMonthlyPayments = numberOfMonthlyPayments;
        }
    }

    public static Double computeMortgage(
            Double principal, Double monthlyInterestRate, int numberOfMonthlyPayments) {

        double factor = Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments);
        return principal * (monthlyInterestRate * factor) / (factor - 1);
    }

    public static Double computeRemainingBalance(
            Double principal, Double monthlyInterestRate, int numberOfMonthlyPayments, int numberOfPaymentsMade) {

        final double rate = (1 + monthlyInterestRate);
        return principal
                * (Math.pow(rate, numberOfMonthlyPayments) - Math.pow(rate, numberOfPaymentsMade))
                / (Math.pow(rate, numberOfMonthlyPayments) - 1);
    }

    public static Double getPrincipal(Scanner scanner) {
        final String messageQuestion = "Principal ($1K -$1M): ";
        System.out.print(messageQuestion);
        while (true) {
            double principal = scanner.nextDouble();
            if (principal >= 1_000 & principal <= 1_000_000) {
                return principal;
            }
            System.out.println("Enter a value between 1,000 and 1,000,000.");
        }
    }

    public static Double getMonthlyInterestRate(Scanner scanner) {
        final String messageQuestion = "Annual Interest Rate (0 - 30): ";
        System.out.print(messageQuestion);
        while (true) {
            double annualInterestRate = scanner.nextDouble();
            if (annualInterestRate > 0 & annualInterestRate <= 30) {
                return annualInterestRate / PERCENT / MONTHS_IN_YEARS;
            }
            System.out.println("Enter a value greater than 0 and less or equal to 30");
        }
    }

    public static Integer getNumberOfMonthlyPayments(Scanner scanner) {
        final String messageQuestion = "Period (years; 0 - 30): ";
        System.out.print(messageQuestion);
        while (true) {
            int periodYears = scanner.nextInt();
            if (periodYears > 0 & periodYears <= 30) {
                return periodYears * MONTHS_IN_YEARS;
            }
            System.out.println("Enter a value greater than 0 and less or equal to 30");
        }
    }
}
