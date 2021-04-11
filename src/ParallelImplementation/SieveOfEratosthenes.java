package ParallelImplementation;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class SieveOfEratosthenes {
    protected Numbers numbers;

    public void StartSieving() throws IOException {
        System.out.println("Enter a number:");
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        if (value < 3)
            return;

        Sifter firstSifter = new Sifter(new PrimeNumbers(""));
        firstSifter.setSiftValues(2);
        firstSifter.setMaxValue(value);

        numbers = new Numbers(value);
        firstSifter.setReceiveNumbers(numbers.getQueue());
        numbers.start();
        firstSifter.start();
       // firstSifter.outputFile.close();
    }

    public static void main(String[] args) throws IOException {
        SieveOfEratosthenes mainSieveOfEratosthenes = new SieveOfEratosthenes();

        long startTime = System.currentTimeMillis();
        mainSieveOfEratosthenes.StartSieving();
        long endTime = System.currentTimeMillis();

        System.out.println("\nTime execution: " + (endTime - startTime) + " milliseconds.");
        //Thread.getAllStackTraces().keySet().forEach((t) -> System.out.println(t.getName() + "\nIs Daemon " + t.isDaemon() + "\nIs Alive " + t.isAlive()));
    }
}

