package lesson5;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Anagramm {
    private char[] word;
    private Set<String> result = new LinkedHashSet<>();

    public static void main(String[] args) {
        new Anagramm("abc").getAnagramm().forEach(System.out::println);
    }

    public Anagramm(String word) {
        this.word = word.toCharArray();
    }

    public Set<String> getAnagramm() {
        if (word.length <= 1) {
            return Collections.emptySet();
        }

        result.clear();
        consumeAnagramm(word.length);
        return  result;
    }

    private void consumeAnagramm(int length) {
        if (length == 1) {
            return;
        }

        for (int i = 0; i < length; i++) {
            consumeAnagramm(length - 1);
            result.add(String.valueOf(word));
            rotate(length);
        }
    }

    private void rotate(int length) {
        int pos = word.length - length;
        char temp = word[pos];
        for (int i = pos + 1; i < word.length; i++) {
            word[i - 1 ] = word[i];
        }

        word[word.length - 1] = temp;
    }


}
