abstract class Employee
{
String name;
String role;
double salary;
abstract void calculateSalary();
abstract void displayDetails();
}
class Manager extends Employee
{
double fixedSalary;
Manager(String name,double fixedSalary)
{
this.name=name;
this.role="Manager";
this.fixedSalary=fixedSalary;
}
void calculateSalary()
{
salary=fixedSalary;
}
void displayDetails()
{
System.out.println("Name: "+name);
System.out.println("Role: "+role);
System.out.println("Salary: "+salary);
}
}
class Developer extends Employee
{
double hourlyRate;
int hoursWorked;
Developer(String name,double hourlyRate,int hoursWorked)
{
this.name=name;
this.role="Developer";
this.hourlyRate=hourlyRate;
this.hoursWorked=hoursWorked;
}
void calculateSalary()
{
salary=hourlyRate*hoursWorked;
}
void displayDetails()
{
System.out.println("Name: "+name);
System.out.println("Role: "+role);
System.out.println("Salary: "+salary);
}
public static void main(String[] args)
{
Manager m=new Manager("Darsh",50000);
Developer d=new Developer("Shreyash",5,3160);
m.calculateSalary();
d.calculateSalary();
m.displayDetails();
d.displayDetails();
}
}
