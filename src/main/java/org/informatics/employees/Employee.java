package org.informatics.employees;

public class Employee { ;
    private EmployeeType employeeType;
    private double baseSalary;
    public double sumSalary = 0;
    public Employee(EmployeeType employeeType, double baseSalary) {

        this.employeeType = employeeType;
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
        return baseSalary;
    }


    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeType=" + employeeType +
                ", baseSalary=" + baseSalary +
                ", sumSalary=" + sumSalary +
                '}';
    }
}
