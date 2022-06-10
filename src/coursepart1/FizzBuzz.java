package coursepart1;

import java.util.stream.IntStream;

public class FizzBuzz {

    public static void main(String[] args) {
        IntStream.range(1, 31)
                .forEach(value -> System.out.println(value + ": " + evaluateFizzBuzz(value)));
    }

    private static String evaluateFizzBuzz(Integer number) {
        if (number % 15 == 0) {
            return "FizzBuzz";
        }
        if (number % 5 == 0) {
            return "Fizz";
        }
        if (number % 3 == 0) {
            return "Buzz";
        }
        return String.valueOf(number);
    }
}
