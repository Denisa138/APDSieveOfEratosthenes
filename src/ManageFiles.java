import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class ManageFiles {
    private String inputFileName;
    private String outputFileName;
    private int[] inputNumbers;

    public ManageFiles(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    private void createFile(String fileName) {
        try {
            File myObj = new File(fileName + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //create input and output file for single test
    public void createFiles() {
        createFile(inputFileName);
        createFile(outputFileName);
    }

    public void generateNumbers(int startInterval, int endInterval, int totalNumbers) {
       inputNumbers = new int[totalNumbers];

        // generate and write numbers in the input file
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputFileName + ".txt"))) {
            for (int i = 0; i < totalNumbers; i++) {
                int randomNum = ThreadLocalRandom.current().nextInt(startInterval, endInterval + 1);
                bufferedWriter.write(randomNum + " ");
            }
        } catch (IOException e) {
            // Exception handling
            System.out.println("Error while writing in input file.");
        }
    }

    //find the prime numbers and write them in the output file
    public void sieveOfEratosthenes() throws IOException {
        FileWriter writeOutput = new FileWriter(outputFileName + ".txt");
        int iterator = 0;
        // read the input numbers
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName + ".txt"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] currencies = line.split(" ");
                for (int i = 0; i < currencies.length; i++) {
                    inputNumbers[iterator++] = Integer.parseInt(currencies[i]);
                }

                line = bufferedReader.readLine();
            }
        } catch (
                FileNotFoundException e) {
            // Exception handling
            System.out.println("Input file not found.");
        }

        for (int i = 0; i < iterator; i++) {
            writeOutput.write("Following are the prime numbers ");
            writeOutput.write("smaller than or equal to " + inputNumbers[i] + ":\n");
            SieveOfEratosthenes g = new SieveOfEratosthenes(inputNumbers[i]);
            writeOutput.write(g.sieveOfEratosthenes() + "\n");
        }

        writeOutput.close();
    }

}
