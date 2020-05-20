package lesson3.stack;

public class Invers {

    public static void main(String[] args) {
        String text = "public static void main(String[] args) { testFunc(); test2()}";
        System.out.println(text);
        new Invers(text).check();
    }

    private final String text;

    public Invers(String text) {
        this.text = text;
    }

    private void check() {
        Stack<Character> stack = new StackImpl<>(text.length());
        for (int index = 0; index < text.length(); index++) {
            char currentChar = text.charAt(index);
            stack.push(currentChar);
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}

