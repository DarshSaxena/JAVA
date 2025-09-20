class Worker
{
    protected String name;
    protected double salaryRate;
    public Worker(String name, double salaryRate) {
        this.name = name;
        this.salaryRate = salaryRate;
    }
    public double computePay(int hours) {
        return 0;
 }
}

class DailyWorker extends Worker {
    public DailyWorker(String name, double salaryRate) {
        super(name, salaryRate);
    }
    public double computePay(int hours) {
        int daysWorked = hours / 8;
        return daysWorked * salaryRate;
    }
}
class SalariedWorker extends Worker {
    public SalariedWorker(String name, double salaryRate) {
        super(name, salaryRate);
    }
    public double computePay(int hours) {
        return salaryRate * 40;
    }
}
class WorkerTest {
    public static void main(String[] args) {
        Worker dailyWorker = new DailyWorker("John", 100);
        Worker salariedWorker = new SalariedWorker("Alice", 50);
        System.out.println("Daily Worker's weekly pay: " + dailyWorker.computePay(40));
        System.out.println("Salaried Worker's weekly pay: " + salariedWorker.computePay(40));
    }
}
