import java.util.Scanner;

class Fibonacci {
public static void main(String args[]) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter the number of terms for the Fibonacci series: ");
int number = sc.nextInt();
int l1 = 0, l2 = 1;
System.out.print("Fibonacci Series: " + l1 + ", " + l2);

for (int i = 2; i < number; i++) {
int next = l1 + l2;
System.out.print(", " + next);
l1 = l2;
l2 = next;
 }}}
