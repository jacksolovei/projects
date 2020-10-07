package company;

public interface Employee {

    public static final double MONTH_SALARY_OPERATOR = 60000.0;
    public static final double MONTH_SALARY_MANAGER = 100000.0;
    public static final double MONTH_SALARY_TOP_MANAGER = 200000.0;

    public double getMonthSalary();
    public void setCompany(Company company);
}
