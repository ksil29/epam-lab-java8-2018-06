package streams.part2.exercise.data;

import lambda.data.Employee;

public class EmployeeEmployerPair {

    private final Employee employee;
    private final String employer;

    public EmployeeEmployerPair(Employee employee, String employer) {
        this.employee = employee;
        this.employer = employer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getEmployer() {
        return employer;
    }
}
