import java.util.Scanner;
class course
{
Scanner sc=new Scanner(System.in);
String coursename;
String courseid;
String sname;

course()
{
System.out.println("enter the coursename");
this.coursename=sc.nextLine();
System.out.println("enter the courseid");
this.courseid=sc.nextLine();
System.out.println("enter the name of student");
this.sname=sc.nextLine();
}
void display()
{
System.out.println("the course name is"+coursename);
System.out.println("the course name is"+courseid);
System.out.println("the student enrolled is"+sname);
}
public static void main(String args[])
{
course obj=new course();
obj.display();
}
}