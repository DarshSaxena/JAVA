import java.util.Scanner;
public class arrayaccess
{
public static void main(String[] args)
{
Scanner scanner = new Scanner(System.in);
int numbers[]= {1,2,3,4,5};
try
{
System.out.print("Enter an index (0-4): ");
int index = scanner.nextInt();
System.out.println("Element at index " + index + ": " + numbers[index]);
}
catch (ArrayIndexOutOfBoundsException e)
{
System.out.println("Error: Invalid index.");
}
}
}
