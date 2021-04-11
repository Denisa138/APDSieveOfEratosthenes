import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Results results = new Results(10, "input", "output", 9500, 12000, 11000);

        long startTime = System.currentTimeMillis();
        results.generateResults();
        long endTime = System.currentTimeMillis();

        System.out.println("Time execution: " + (endTime - startTime) + " milliseconds.");
    }
}
