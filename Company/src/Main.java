import company.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> companyEmployees = new ArrayList<>();
        Company company = new Company(20000000.0, companyEmployees);
        for (int i = 0; i < 80; i++) {       // добавление сотрудников в компанию методом hire()
            company.hire(new Manager());
        }
        for (int i = 0; i < 10; i++) {
            company.hire(new TopManager());
        }
        ArrayList<Employee> companyNewEmployees = new ArrayList<>();      // добавление сотрудников в компанию методом hireAll()
        Company companyNew = new Company(5000000, companyNewEmployees);
        for (int i = 0; i < 180; i++) {
            companyNew.hire(new Operator());
        }
        company.hireAll(companyNewEmployees);
        company.getTopSalaryStaff(15); // 15 самых высоких зарплат
        company.getLowestSalaryStaff(30); // 30 самых низких зарплат
        System.out.println(companyEmployees.size());
        for (int i = companyEmployees.size() - 1; i >= 0; i--) {  // увольнение 50% сотрудников
            if (i % 2 == 0) {
                company.fire(companyEmployees.get(i));
            }
        }
        System.out.println(companyEmployees.size());
    }
}
