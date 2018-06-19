package streams.part2.exercise;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class Exercise3 {

    @Test
    public void createLimitedStringWithOddNumbersSeparatedBySpaces() {
        int countNumbers = 10;

        String result = Stream.iterate(1, n -> n + 2)
                              .limit(countNumbers)
                              .map(String::valueOf)
                              .collect(Collectors.joining(" "));

        assertEquals("1 3 5 7 9 11 13 15 17 19", result);
    }

    @Test
    public void extractEvenNumberedCharactersToNewString() {
        String source = "abcdefghijklm";

        String result = Stream.iterate(0, n -> n + 2)
                              .limit((source.length() + 1) / 2)
                              .map(source::charAt)
                              .map(String::valueOf)
                              .collect(Collectors.joining());

        assertEquals("acegikm", result);
    }
}
