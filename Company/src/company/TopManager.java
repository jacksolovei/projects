package company;

public class TopManager implements Employee {
    Company company;

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        double topManagerSalary;
        if (company.getIncome() > 10000000.0) {
            topManagerSalary = MONTH_SALARY_TOP_MANAGER + MONTH_SALARY_TOP_MANAGER * 1.5;
        } else {
            topManagerSalary = MONTH_SALARY_TOP_MANAGER;
        }
        return topManagerSalary;
    }
}
