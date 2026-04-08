package ds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConcatenateStrings {
    @Test
    public void join() {
        List<String> words = Arrays.asList("Hello", "World", "Java", "Streams");

        String result = words.stream().collect(Collectors.joining(",", "[", "]"));

        System.out.println(result);
    }

    @Test
    public void reduce() {
        List<String> words = Arrays.asList("Hello", "World", "Java", "Streams");
        // With identity - returns the identity value
        String result = words.stream().reduce("", (a, b) -> a + b);

        System.out.println(result);

        // Without identity - returns empty Optional
        Optional<String> optionalResult = words.stream().reduce((a, b) -> a + b);
        System.out.println(optionalResult.orElse(""));
    }
}
