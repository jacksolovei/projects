package company;

import java.util.ArrayList;
import java.util.Comparator;

public class Company {
    private double income;
    ArrayList<Employee> employeeList;

    public Company(double income, ArrayList<Employee> employeeList) {
        this.income = income;
        this.employeeList = employeeList;
    }

    public double getIncome() {
        return income;
    }

    public void hire(Employee employee) {
        if (!employeeList.contains(employee)) {
            employee.setCompany(this);
            employeeList.add(employee);
        } else {
            System.out.println("Сотрудник уже работает в данной компании");
        }
    }

    public void hireAll(ArrayList<Employee> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCompany(this);
        }
        employeeList.addAll(list);
    }

    public void fire(Employee employee) {
        if (employeeList.contains(employee)) {
            employee.setCompany(null);
            employeeList.remove(employee);
        } else {
            System.out.println("Сотрудник не работает в данной компании");
        }
    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {              //5 самых больших зарплат компании
        if (count > employeeList.size()) {
            System.out.println("Количество сотрудников меньше введенного числа");
        } else if (count <= 0) {
            System.out.println("Количество сотрудников должно быть больше 0");
        } else {
            employeeList.sort((o1, o2) -> {
                if (o1.getMonthSalary() > o2.getMonthSalary()) {
                    return -1;
                }
                if (o1.getMonthSalary() < o2.getMonthSalary()) {
                    return 1;
                }
                return 0;
            });
            for (int i = 0; i < count; i++) {
                System.out.println(employeeList.get(i).getMonthSalary() + " рублей");
            }
        }
        return employeeList;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {            //5 самых маленьких зарплат в компании
        if (count > employeeList.size()) {
            System.out.println("Количество сотрудников меньше введенного числа");
        } else if (count <= 0) {
            System.out.println("Количество сотрудников должно быть больше 0");
        } else {
            employeeList.sort((o1, o2) -> {
                if (o1.getMonthSalary() > o2.getMonthSalary()) {
                    return 1;
                }
                if (o1.getMonthSalary() < o2.getMonthSalary()) {
                    return -1;
                }
                return 0;
            });
            for (int i = 0; i < count; i++) {
                System.out.println(employeeList.get(i).getMonthSalary() + " рублей");
            }
        }
        return employeeList;
    }
}
