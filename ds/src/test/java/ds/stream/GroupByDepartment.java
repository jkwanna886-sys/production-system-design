package ds.stream;
import ds.dto.Employee;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;


public class GroupByDepartment {
    @Test
    public void group() {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 75000, 1),
                new Employee("Bob", "HR", 65000, 2),
                new Employee("Charlie", "IT", 80000, 3),
                new Employee("David", "Finance", 70000, 4),
                new Employee("Eve", "HR", 62000, 5),
                new Employee("Frank", "IT", 90000, 6),
                new Employee("Grace", "Finance", 72000, 7)
        );

        Map<String, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        map.forEach((dept, emps) -> {
            System.out.println(dept + ":" + emps);
        });

        ;
    }

    @Test
    public void groupWithName() {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 75000, 1),
                new Employee("Bob", "HR", 65000, 2),
                new Employee("Charlie", "IT", 80000, 3),
                new Employee("David", "Finance", 70000, 4),
                new Employee("Eve", "HR", 62000, 5),
                new Employee("Frank", "IT", 90000, 6),
                new Employee("Grace", "Finance", 72000, 7)
        );

        Map<String, List<String>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment
                        , Collectors.mapping(Employee::getName
                                , Collectors.toList()) // the result collection can be list/set etc.
                ));

        map.forEach((dept, emps) -> {
            System.out.println(dept + ":" + emps);
        });
    }
}



