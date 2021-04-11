import java.io.IOException;

public class Results {
    private int noFiles;
    private String inputFileName;
    private String outputFileName;
    private int startInterval;
    private int endInterval;
    private int totalNumbers;

    public Results(int numberOfFiles, String inputFileName, String outputFileName, int startInterval, int endInterval, int totalNumbers) {
        this.noFiles = numberOfFiles;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.startInterval = startInterval;
        this.endInterval = endInterval;
        this.totalNumbers = totalNumbers;
    }

    //generate input and output files, also display the given results
    public void generateResults() throws IOException {
        for (int i = 0; i < noFiles; i++) {
            ManageFiles manageFiles = new ManageFiles(inputFileName + String.valueOf(i),
                    outputFileName + String.valueOf(i));
            manageFiles.createFiles();
            manageFiles.generateNumbers(startInterval, endInterval, totalNumbers);
            manageFiles.sieveOfEratosthenes();
        }
    }
}
