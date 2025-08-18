class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
   public double add(double a, double b) {
        return a + b;
    }
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        int sum1 = calc.add(2, 3);
        double sum2 = calc.add(2.5, 3.5);
        int sum3 = calc.add(1, 2, 3);
        System.out.println("Sum of 2 and 3 (integers): " + sum1);
        System.out.println("Sum of 2.5 and 3.5 (doubles): " + sum2);
        System.out.println("Sum of 1, 2, and 3 (integers): " + sum3);
    }
}
