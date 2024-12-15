package emp_sal;

public class Emp_Sal_Cal_System {
    // Instance Variables
     String name;
    String dept;
    double basicSal;

    // Static Variables
     static double taxRate = 5.0; 
     static double perkRate = 10.0; 

    public Emp_Sal_Cal_System(String name, String dept, double basicSal) {
        this.name = name;
        this.dept = dept;
        this.basicSal = basicSal;
    }

    public double cal_HRA() {
        return basicSal * 0.2;
    }

    public double cal_TA() {
        return basicSal * 0.1; 
    }

    public double cal_DA() {
        return basicSal * 0.15; 
    }

    public double cal_total_Salary() {
        return basicSal + cal_HRA() + cal_TA() + cal_DA() + cal_Perks();
    }

    
    public static double cal_Tax(double grossSalary) {
        return grossSalary * (taxRate / 100);
    }

    
    public double cal_Perks() {
        return basicSal * (perkRate / 100);
    }

    public void displaySalaryDetails() {
        double grossSalary = cal_total_Salary();
        double tax = cal_Tax(grossSalary);
        double netSalary = grossSalary - tax;

        System.out.println("Employee Name: " + name);
        System.out.println("Department: " + dept);
        System.out.println("Basic Salary: " + basicSal);
        System.out.println("HRA: " + cal_HRA());
        System.out.println("TA: " + cal_TA());
        System.out.println("DA: " + cal_DA());
        System.out.println("Perks: " + cal_Perks());
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("Tax Deduction: " + tax);
        System.out.println("Net Salary: " + netSalary);
    }

    public static void main(String[] args) {
        Emp_Sal_Cal_System emp = new Emp_Sal_Cal_System("Akshitha", "IT", 54000);
        emp.displaySalaryDetails();
    }
}

