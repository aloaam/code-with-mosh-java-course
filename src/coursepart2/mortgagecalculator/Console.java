package coursepart2.mortgagecalculator;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Console {

    final Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public double readNumber(String prompt, int minValue, int maxValue, NumberFormat formatter) {
        while (true) {
            System.out.print(prompt);
            double value = scanner.nextDouble();

            if (value >= minValue && value <= maxValue) {
                return value;
            }
            System.out.printf(
                    "The given input is outside the range: [%s, %s]%n",
                    formatter.format(minValue),
                    formatter.format(maxValue));
        }

    }

}
