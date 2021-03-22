import java.io.IOException;

public class Results {
    private int noFiles;
    private String inputFileName;
    private String outputFileName;

    public Results(int numberOfFiles, String inputFileName, String outputFileName) {
        this.noFiles = numberOfFiles;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    //generate input and output files, also display the given results
    public void generateResults() throws IOException {
        for (int i = 0; i < noFiles; i++) {
            ManageFiles manageFiles = new ManageFiles(inputFileName + String.valueOf(i),
                    outputFileName + String.valueOf(i), 1000 * (i + 1), 5000 * (i + 1), 1000);
            manageFiles.createFiles();
            manageFiles.generateNumbers();
            manageFiles.sieveOfEratosthenes();
        }
    }
}
