import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Results results = new Results(11, "input", "output", 1000, 50000, 1000);

        long startTime = System.currentTimeMillis();
        results.generateResults();
        long endTime = System.currentTimeMillis();

        System.out.println("Time execution: " + (endTime - startTime) + " milliseconds.");
    }
}
