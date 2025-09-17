import java.util.Scanner;

class TrunkCall {
    protected int duration;
    protected double rate;
    
    public TrunkCall(int duration, double rate) {
        this.duration = duration;
        this.rate = rate;
    }
    
    public double calculateCharge() {
        return duration * rate;
    }
}

class OrdinaryCall extends TrunkCall {
    public OrdinaryCall(int duration) {
        super(duration, 1.5);
    }
}

class UrgentCall extends TrunkCall {
    public UrgentCall(int duration) {
        super(duration, 2.5);
    }
}

class LightningCall extends TrunkCall {
    public LightningCall(int duration) {
        super(duration, 4.0);
    }
}

public class TrunkCallCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter call duration (in minutes): ");
        int duration = scanner.nextInt();
        
        System.out.println("Select call type: 1. Ordinary  2. Urgent  3. Lightning");
        int choice = scanner.nextInt();
        
        TrunkCall call;
        
        switch (choice) {
            case 1:
                call = new OrdinaryCall(duration);
                break;
            case 2:
                call = new UrgentCall(duration);
                break;
            case 3:
                call = new LightningCall(duration);
                break;
            default:
                System.out.println("Invalid choice! Exiting program.");
                scanner.close();
                return;
        }
        
        double charge = call.calculateCharge();
        System.out.println("Total charge: Rs. " + charge);
        
        scanner.close();
    }
}