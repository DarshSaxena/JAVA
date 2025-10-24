import java.util.Random;

public class RandomQuoteGenerator {
    public static void main(String[] args) {
        String[] quotes = {
            "Believe you can and you're halfway there.",
            "Do something today that your future self will thank you for.",
            "The best way to predict the future is to create it.",
            "Don’t watch the clock; do what it does. Keep going.",
            "Success is not final, failure is not fatal: it is the courage to continue that counts."
        };

        Random random = new Random();
        int index = random.nextInt(quotes.length);

        System.out.println("💬 Random Quote of the Day:");
        System.out.println("\"" + quotes[index] + "\"");
    }
}
