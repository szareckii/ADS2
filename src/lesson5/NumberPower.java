package lesson5;

import static java.lang.Math.abs;

public class NumberPower {

    public static void main(String[] args) {
        System.out.println(power(-4,-3));
    }

    private static double power(double value, double degree) {
        if (degree == 0) {
            return 1;
        }

        if (degree < 0) {
            value = 1 / value;
        }

        return value * power(value, abs(degree) - 1);
    }
}
