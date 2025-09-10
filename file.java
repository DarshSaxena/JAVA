import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class file
{
public static void readFile(String fileName) throws FileNotFoundException {
File file = new File(fileName);
Scanner fileScanner = new Scanner(file);
System.out.println("File contents:");
while (fileScanner.hasNextLine()) {
System.out.println(fileScanner.nextLine());
}
fileScanner.close();
}
public static void main(String[] args) {
Scanner inputScanner = new Scanner(System.in);
System.out.print("Enter the file name to read: ");
String fileName = inputScanner.nextLine();
try {
readFile(fileName);  // May throw FileNotFoundException
} catch (FileNotFoundException e) {
System.out.println("Error: File not found.");
}
System.out.println("File operation attempted.");
inputScanner.close();
}
}
