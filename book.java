class Library
{
class book
{
String title;
String authorname;
String isbn;
book()
{
this.title="ABCD FOR KIDS";
this.authorname="Darsh Saxena";
this.isbn="123090909";
}
void displaydetails()
{
System.out.println("The title of book is "+this.title);
System.out.println("The author name is "+this.authorname);
System.out.println("The ISBN of the above book is "+this.isbn);
}
}
public static void main(String args[])
{
Library library = new Library();
book obj = library.new book();
obj.displaydetails();
}
}
