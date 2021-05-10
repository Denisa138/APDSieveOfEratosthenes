import ParallelImplementation2.SieveOfEratosthenes;
import SequentialImplementation.Results;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        SieveOfEratosthenes sieveOfEratosthenes = new SieveOfEratosthenes();

        long startTime = System.currentTimeMillis();
        sieveOfEratosthenes.runEratosthenesSieve(600000);
        long endTime = System.currentTimeMillis();

        System.out.println("\nTime execution: " + (endTime - startTime) + " milliseconds.");

//        Results results = new Results(10, "input", "output", 9500, 12000, 11000);
//
//        long startTime = System.currentTimeMillis();
//        results.generateResults();
//        long endTime = System.currentTimeMillis();
//
//        System.out.println("Time execution: " + (endTime - startTime) + " milliseconds.");


    }
}
