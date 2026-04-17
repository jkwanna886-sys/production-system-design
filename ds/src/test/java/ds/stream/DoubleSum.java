package ds.stream;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DoubleSum {
    @Test
    public void test() {
        List<Integer> list = List.of(1,2,3,4,5);

        int sum = list.stream()
                .map(x -> 2 * x)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

}
