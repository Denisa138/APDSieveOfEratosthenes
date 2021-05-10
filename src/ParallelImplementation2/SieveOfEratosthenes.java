package ParallelImplementation2;

import java.util.concurrent.*;

public class SieveOfEratosthenes {

    // Number of workers.
    private static final int THREADS = 4000;

    // array must be shared between master and workers threads so make it class property.
    private boolean[] primeNumbers;
    // Create blocking queue with size equal to number of workers.
    private BlockingQueue<Job> jobs = new ArrayBlockingQueue<Job>(THREADS);
    private Semaphore semaphore = new Semaphore(0);
    // Create executor service in order to reuse worker threads.
    // we can use just new Thread(new Worker()).start(). But using thread pools more effective.
    private ExecutorService executor = Executors.newFixedThreadPool(THREADS);
    private int noPerProcess;
    private int remainingElements = 0;

    public void runEratosthenesSieve(int number) throws InterruptedException {
        primeNumbers = new boolean[number + 1];

        // Start workers.
        for (int i = 0; i < THREADS; i++) {
            executor.submit(new Worker());
        }

        if(number % THREADS == 0){
            noPerProcess = number / THREADS;
        }else{
            noPerProcess = number / THREADS;
            remainingElements = number - (noPerProcess * THREADS);
        }

        Job job;
        if(remainingElements == 0){
            for(int i = 1; i <= THREADS; i++){
                if(i == 1) job = new Job(1, (noPerProcess * i));
                else{
                   job = new Job((noPerProcess * (i - 1)) + 1, noPerProcess * i);
                }
                jobs.put(job);
            }


        }else{
            for(int i = 1; i <= THREADS; i++){
                if(i == 1){
                    job = new Job(1, (noPerProcess * i));
                }else if(i == THREADS) {
                    job = new Job((noPerProcess * (i - 1)) + 1, (noPerProcess * i) + remainingElements );
                }else
                {
                    job = new Job((noPerProcess * (i - 1)) + 1, noPerProcess * i);
                }
                jobs.put(job);
            }
        }

        // Wait until all jobs are done.
        semaphore.acquire(THREADS);
        //close workers
        jobs.put(new Job(-1));

        for (int m = 1; m <= number; m++) {
            if (!primeNumbers[m]) {
                System.out.print(m + " ");
            }
        }
    }

    private class Job {
        public int from, to;

        public Job(int from, int to) {
            this.from = from;
            this.to = to;
        }
        public Job(int from){
            this.from = from;
        }
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                Job job = null;
                try {
                    job = jobs.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // -1 means that workers must shutdown
                if (job.from == -1) {
                    return;
                }
                for (int i = job.from; i <= job.to; i++) {
                    if (!primeNumbers[i] && !isPrime(i)) {
                        //update with true values that aren't prime
                        primeNumbers[i] = true;
                    }
                }
                // Notify master thread that a job was done.
                semaphore.release();
            }
        }
    }
    static boolean isPrime(int n)
    {
        // Check if number is less than
        // equal to 1
        if (n <= 1)
            return false;

            // Check if number is 2
        else if (n == 2)
            return true;

            // Check if n is a multiple of 2
        else if (n % 2 == 0)
            return false;

        // If not, then just check the odds
        for (int i = 3; i <= Math.sqrt(n); i += 2)
        {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
