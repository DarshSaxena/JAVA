import java.util.Scanner;
class employee
 {
    Scanner sc = new Scanner(System.in);
    int id;
    String name;
    String dept;
    int salary;

    employee()
 {
        this.id = 1234;
        this.name = "Darsh";
        this.dept = "cse";
        this.salary = 10000;
    }

    employee(int id, String name, String dept, int salary) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    void calculateSalary() {
        System.out.println("The salary is " + salary);
    }

    void employeeInfo() {
        System.out.println("The id is " + id);
        System.out.println("The name is " + name);
        System.out.println("The dept is " + dept);
        System.out.println("The salary is " + salary);
    }

    public static void main(String[] args) {
        employee obj = new employee();
        obj.employeeInfo();
        obj.calculateSalary();
    }
}

