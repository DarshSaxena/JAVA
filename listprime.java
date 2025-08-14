import java.util.ArrayList;
class listprime
{
public static void main(String[] args) 
{
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(2);
numbers.add(3); 
numbers.add(4);
numbers.add(5); 
numbers.add(56); 
numbers.add(172);
for (Integer num : numbers) 
{
int value = num;
System.out.print(value);
if (isPrime(value)) {
System.out.println(" is a prime number.");
} 
else 
{
System.out.println(" is not a prime number.");
}
}
}
public static boolean isPrime(int number) 
{
if (number <= 1) 
return false;
for (int i = 2; i <= Math.sqrt(number); i++) 
if (number % i == 0) 
return false;
return true;
}
}
