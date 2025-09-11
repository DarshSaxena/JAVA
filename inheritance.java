class java 
{
private int privateNumber = 42;
public void showPrivateNumber()
 {
System.out.println("Private number in superclass: " + privateNumber);
}
}
class oracle extends java 
{
public static void main(String[] args) 
{
oracle obj = new oracle();
obj.showPrivateNumber();
}
}
