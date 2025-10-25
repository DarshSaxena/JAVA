import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score = 0;

        System.out.println("Welcome to the Quiz!");
        System.out.println("----------------------");

        // Question 1
        System.out.println("Q1. What is the capital of India?");
        System.out.println("a) Mumbai");
        System.out.println("b) Delhi");
        System.out.println("c) Kolkata");
        System.out.print("Your answer: ");
        String ans1 = sc.nextLine();

        if (ans1.equalsIgnoreCase("b") || ans1.equalsIgnoreCase("Delhi")) {
            System.out.println("Correct! +2 points");
            score += 2;
        } else {
            System.out.println("Wrong answer!");
        }

        // Question 2
        System.out.println("\nQ2. Who invented Java?");
        System.out.println("a) Dennis Ritchie");
        System.out.println("b) Bjarne Stroustrup");
        System.out.println("c) James Gosling");
        System.out.print("Your answer: ");
        String ans2 = sc.nextLine();

        if (ans2.equalsIgnoreCase("c") || ans2.equalsIgnoreCase("James Gosling")) {
            System.out.println("Correct! +2 points");
            score += 2;
        } else {
            System.out.println("Wrong answer!");
        }

        // Final score
        System.out.println("\nYour total score: " + score);
        System.out.println("Thanks for playing!");
        sc.close();
    }
}
