package lesson3.stack;

public class Brackets {

    public static void main(String[] args) {
        String text = "    public static void main(String[] args) { testFunc(); test2()}";
        new Brackets(text).check();
    }

    private final String text;

    public Brackets(String text) {
        this.text = text;
    }

    public void check() {
        Stack<Character> stack = new StackImpl<>(text.length());
        for (int index = 0; index < text.length(); index++) {
            char currentChar = text.charAt(index);
            checkCurrentChar(currentChar, index, stack);
        }
        if (!stack.isEmpty()) {
            System.out.println("Error: missing right delimiter");
        }
    }

    private void checkCurrentChar(char currentChar, int charIndex, Stack<Character> stack) {
        switch (currentChar) {
            case '(':
            case '{':
            case '[':
                stack.push(currentChar);
                break;
            case ')':
            case '}':
            case ']':
                if (stack.isEmpty()) {
                    System.out.println("Error: " + currentChar + " at " + charIndex);
                    break;
                }
                Character lastOpenedBracket = stack.pop();
                if (lastOpenedBracket == '{' && currentChar != '}' ||
                lastOpenedBracket == '[' && currentChar != ']' ||
                lastOpenedBracket == '(' && currentChar != ')') {
                    System.out.println("Error: " + currentChar + " at " + charIndex);
                }
        }
    }
}
