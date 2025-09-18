import java.util.ArrayList;
import java.util.Scanner;
class Wrapper 
{
public static void main(String[] args) 
{
Scanner sc=new Scanner(System.in);
ArrayList<Integer> numbers = new ArrayList<>();
System.out.println("enter the value of n");
int n=sc.nextInt();
for (int i = 1; i <= n; i++)
 {
numbers.add(i);
}
for (Integer num : numbers) 
{
int value = num;
if (isPrime(value)) 
{
System.out.println(value + " is a prime number.");
}
}
}
public static boolean isPrime(int n) 
{
if (n <= 1)
return false;
for (int i = 2; i <= Math.sqrt(n); i++) 
{
if (n % i == 0)
return false;
}
return true;
}
}