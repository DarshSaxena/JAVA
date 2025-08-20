interface eventHandler
{
void handleEvent();
}

class eventHandlingSystem
{
void registerEvent()
{
class localEventHandler implements eventHandler
{
public void handleEvent()
{
System.out.println("Event handled by local inner class");
}
}
localEventHandler nest = new localEventHandler();
nest.handleEvent();
}

public static void main(String[] args)
{
eventHandlingSystem ob = new eventHandlingSystem();
ob.registerEvent();

eventHandler obj = new eventHandler()
{
public void handleEvent()
{
System.out.println("Event handled by anonymous inner class");
}
};
obj.handleEvent();
}
}
