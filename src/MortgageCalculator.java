import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {

    final static int MONTHS_IN_YEARS = 12;
    final static double PERCENT = 100;


    public static void main(String[] args) {
        System.out.println("Mortgage Calculator");

        System.out.println(greetUser("Alo"));

        final Double principal = getPrincipal(new Scanner(System.in));
        final Double monthlyInterestRate = getMonthlyInterestRate(new Scanner(System.in));
        final Double monthlyPayments = getNumberOfMonthlyPayments(new Scanner(System.in));

        final Double monthlyPayment = computeMortgage(
                principal,
                monthlyInterestRate,
                monthlyPayments,
                new Scanner(System.in));

        System.out.println(NumberFormat.getInstance().format( monthlyPayment));

        System.out.println("monthlyPayment * months: " + monthlyPayment * monthlyPayments);
    }


    public static String greetUser(String name) {
        return "Hello " + name;
    }

    public static Double computeMortgage(
            Double principal, Double monthlyInterestRate, Double numberOfMonthlyPayments, Scanner scanner) {

        double factor = Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments);
        return principal * (monthlyInterestRate * factor) / (factor - 1);
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

    public static Double getNumberOfMonthlyPayments(Scanner scanner) {
        final String messageQuestion = "Period (years; 0 - 30): ";
        System.out.print(messageQuestion);
        while (true) {
            double periodYears = scanner.nextDouble();
            if (periodYears > 0 & periodYears <= 30) {
                return periodYears * MONTHS_IN_YEARS;
            }
            System.out.println("Enter a value greater than 0 and less or equal to 30");
        }
    }
}
