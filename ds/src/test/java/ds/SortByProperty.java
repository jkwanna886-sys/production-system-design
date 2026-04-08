package ds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sort list of objects by property using comparator
 */
public class SortByProperty {
    @Test
    public void test() {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 75000, 1),
                new Employee("Bob", "HR", 65000, 2),
                new Employee("Charlie", "IT", 80000, 3),
                new Employee("David", "Finance", 70000, 4),
                new Employee("Eve", "HR", 62000, 5),
                new Employee("Frank", "IT", 90000, 6),
                new Employee("Grace", "Finance", 72000, 7)
        );

        List<Employee> sorted = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        for (Employee e : sorted) {
            System.out.println(e);
        }
    }
}
