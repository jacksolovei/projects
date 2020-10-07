package company;

public class Manager implements Employee {
    Company company;

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        double managerSalary = MONTH_SALARY_MANAGER + (Math.round(((Math.random() * (140000.0 - 115000.0) + 115000.0) * 100.0)) / 100.0) * 0.05;
        return managerSalary;
    }
}
