import SequentialImplementation.Results;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Results results = new Results(11, "input", "output", 10000, 11500, 13000);

        long startTime = System.currentTimeMillis();
        results.generateResults();
        long endTime = System.currentTimeMillis();

        System.out.println("Time execution: " + (endTime - startTime) + " milliseconds.");
    }
}
