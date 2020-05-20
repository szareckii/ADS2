package lesson5;

public class Countdown {
    public static void main(String[] args) {
        countDownRecursion(5);
        System.out.println("-------------");
        coutDownLoop(5);
    }

    private static void coutDownLoop(int n) {
        while (n > 0) {
            System.out.println(n--);
        }
    }

    private static void countDownRecursion(int n) {
        if (n > 0) {
            System.out.println(n);
            countDownRecursion(--n);
        }
    }
}
