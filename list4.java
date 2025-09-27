import java.util.HashSet;
class list4
{
public static void main(String[] args)
{
String[] names = {"Darsh", "Vaishnavi", "Shreyash", "Vaishnavi", "shreyash"};
HashSet<String> list4 = new HashSet<>();
for (String name : names)
{
list4.add(name);
}
String checkName = "Darsh";
if (list4.contains(checkName))
{
System.out.println(checkName + " exists in the set.");
}
else
{
System.out.println(checkName + " does not exist in the set.");
}
System.out.println("Unique Names:");
for (String name : list4)
{
System.out.println(name);
}
}
}
