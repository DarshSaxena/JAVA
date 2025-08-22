final class Logger 
{
void logMessage(String message) 
{
System.out.println("Message is: " + message);
}
}
class exp64
{
public static void main(String[] args) 
{
Logger log = new Logger();
log.logMessage("Hello! How are you?");
}
}
