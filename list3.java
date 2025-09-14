import java.util.HashMap;
class list3
{
public static void main(String[] args)
{
HashMap<Integer, Integer> inventory = new HashMap<>();
inventory.put(1031,350);
inventory.put(123,320);
inventory.put(10332,220);
inventory.put(1022,360);
inventory.remove(101);
System.out.println("Final Inventory:");
for (Integer productId : inventory.keySet())
{
Integer quantity = inventory.get(productId);
System.out.println("Product ID: " + productId + ", Quantity: " + quantity);
}
}
}
