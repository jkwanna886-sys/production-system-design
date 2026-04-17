package ds.stream;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PowerSum {
    @Test
    public void test() {
        List<Integer> list = List.of(2,4,6,8,10);

        int sum = list.stream()
                .map(x -> (int)Math.pow(x, 2))
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

}
