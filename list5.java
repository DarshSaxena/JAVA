import java.util.ArrayList;
import java.util.HashSet;
class list5
{
public static void main(String[] args)
{
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(7);
numbers.add(2);
numbers.add(7);
numbers.add(4);
numbers.add(9);
numbers.add(2);
HashSet<Integer> listnew = new HashSet<>(numbers);
int sum = 0;
for (int num : listnew)
{
sum += num;
}
System.out.println("Sum of unique values: " + sum);
}
}
