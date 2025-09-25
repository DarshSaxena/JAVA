import java.util.Scanner;
class DivisionHandler
{
public static void main(String args[])
{
Scanner scanner = new Scanner(System.in);
try
{
System.out.print("Enter the numerator");
int numer = scanner.nextInt();
System.out.print("Enter the denominator: ");
int deno = scanner.nextInt();
int result = numer / deno;
System.out.println("Result"+ result);
}
catch (ArithmeticException e)
{
System.out.println("Error Cannot divide by zero.");
}
System.out.println("Operation completed.");
}
}