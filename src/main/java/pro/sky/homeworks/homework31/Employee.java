package pro.sky.homeworks.homework31;

import java.util.Objects;

import static org.springframework.util.StringUtils.capitalize;

public class Employee {
    //Поля
    private final String firstName;
    private final String lastName;
    private int department;
    private double salary;

    //Конструктор
    public Employee(String firstName, String lastName, int department, double salary) {

        this.firstName = capitalize(firstName.toLowerCase());
        this.lastName = capitalize(lastName.toLowerCase());
        this.department = department;
        this.salary = salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    //Геттеры
    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    //Остальные методы

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " ";
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastName(), getFirstName());
    }

    @Override
    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Employee c2 = (Employee) other;
        return Objects.equals(toString(), c2.toString());
    }
}
