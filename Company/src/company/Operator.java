package company;

public class Operator implements Employee {
    Company company;

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        return MONTH_SALARY_OPERATOR;
    }

}
