package ds.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MapException {
    @Test
    public void test() {
        List<String> list = List.of("a", "2");

        List<Integer> result = list.stream().map(x-> {
            try {
                return Optional.of(Integer.parseInt(x));
            }catch (Exception e) {
                System.out.println("e:" + e);
                return Optional.<Integer>empty();
            }
        }).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        System.out.println(result);
    }
}
