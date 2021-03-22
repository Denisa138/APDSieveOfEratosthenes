import java.util.stream.IntStream;

//Java class used to calculate all prime numbers small or equal than a given number, n.
public class SieveOfEratosthenes {
    private int n;

    public SieveOfEratosthenes(int n) {
        this.n = n;
    }

    public String sieveOfEratosthenes() {
        //use a boolean array in order to get all the prime numbers
        boolean prime[] = new boolean[n + 1];

        //initialize all elements of the array with true
        IntStream.range(0, n).forEach(i -> prime[i] = true);

        for (int i = 2; i * i <= n; i++) {
            if (prime[i]) {
                // Update all multiples of i
                for (int j = i * i; j <= n; j += i) {
                    //if value is changed, then the corresponding number isn't prime
                    prime[j] = false;
                }
            }
        }

        //use this string in order to write the prime numbers list in output files
        String primeNumbers = "";
        for (int i = 2; i <= n; i++) {
            if (prime[i]){
                primeNumbers += i + " ";
            }
        }
        return primeNumbers;
    }
}
