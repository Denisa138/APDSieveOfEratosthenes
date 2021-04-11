package ParallelImplementation;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/*Acesta clasa este utilizata pentru a determina numerele prime, existente in coada receiveNumbers (continutul acesteia fiind incarcat de un obiect de tip "Numbers").
 Daca un numbar nu este prim este eliminat, insa daca nu se imparte exact la siftValue atunci numarul este transfert urmatorului sifter. Daca numarul curent este < radacina patrata a nextShiftValue atunci un nou
 obiect de tip Sifter este creat.
 Daca nu se mai creeaza un nou sifter atunci toate numerele obtinute sunt prime.
 Atributul shiftValue este numarul prim( 2, 3, 5, 7, 11, 13, 17, etc.) utilizat pentru a determina lista finala de numere.
 Atributul nextShiftValue este utilizat pentru a determina daca se creeaza un nou Sifter.
*/
public class Sifter extends Thread {
    protected int siftValue;
    protected static int nextSiftValue;
    protected Sifter nextSifter = null;

    protected BlockingQueue<Integer> receiveNumbers = new LinkedBlockingQueue<Integer>();
    protected BlockingQueue<Integer> sendNumbers = null;
    protected PrimeNumbers primeNumbers;

    public Sifter(PrimeNumbers primeNumbers)
    {
        this.primeNumbers = primeNumbers;
    }

    public Sifter(int inValue, PrimeNumbers primeNumbers)
    {
        this.primeNumbers = primeNumbers;
        this.siftValue = inValue;
    }

    public void run()
    {
        Integer sentValue;
        int sentInt;
        try {
            boolean loopFlag = true;
            while(loopFlag)
            {
                sentValue = receiveNumbers.take();
                sentInt = sentValue.intValue();
                if (sentInt == -1)
                {
                    loopFlag = false;
                    if (sendNumbers != null)
                    {
                        sendNumbers.put(sentValue);
                    }
                } else {
                    processNumber(sentValue.intValue());
                }
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public BlockingQueue<Integer> getSendQueue()
    {
        return sendNumbers;
    }

    public void setReceiveNumbers(BlockingQueue<Integer> inQueue)
    {
        receiveNumbers = inQueue;
    }

    public void setSiftValues(int inValue)
    {
        siftValue = inValue;
    }

    public void setMaxValue(int inValue)
    {
        double dMaxValue = Math.sqrt(inValue);
        nextSiftValue = (int)dMaxValue;
    }

    public void processNumber(int inValue) throws IOException {
        if (inValue % siftValue == 0)
        {
//            System.out.println("Dropped " + inValue + " by" + Thread.currentThread().getName());
        }
        else
        {
            if(nextSifter != null)
            {
                try {
                    sendNumbers.put(new Integer(inValue));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("Passed "+ inValue + " from" + Thread.currentThread().getName());
            }
            else
            {
                primeNumbers.setPrimeNumbers(primeNumbers.getPrimeNumbers() + " " + inValue);
//                System.out.println("prime num " + primeNumbers.getPrimeNumbers() + " " + Thread.currentThread().getName());
                if ( inValue <= nextSiftValue) {
                    nextSifter = new Sifter(inValue, this.primeNumbers);
                    nextSifter.setSiftValues(inValue);

                    sendNumbers = new LinkedBlockingQueue<Integer>();
                    nextSifter.setReceiveNumbers(sendNumbers);
                    new Thread(nextSifter).start();
//                    System.out.println("New sifter created by " + Thread.currentThread().getName());
                }
                else
                {
//                    System.out.println(("Prime numbers " + primeNumbers.getPrimeNumbers() + " " + Thread.currentThread().getName()));



                    /*int count = Thread.activeCount();
                    System.out.println("currently active threads = " + count);

                    Thread th[] = new Thread[count];
                    // returns the number of threads put into the array
                    System.out.println(Thread.enumerate(th));*/

                    // prints active threads
                    /*for (int i = 0; i < count; i++) {
                        System.out.println(i + ": " + th[i]);
                    }*/
                }
            }
        }
    }
}
