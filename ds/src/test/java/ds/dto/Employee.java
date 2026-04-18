package ds.dto;

public class Employee {
    private String name;
    private String department;
    private double salary;
    private int id;

    // Constructor
    public Employee(String name, String department, double salary, int id) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.id = id;
    }

    // Getters
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', dept='%s', salary=%.2f}",
                id, name, department, salary);
    }
}