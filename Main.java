package literatePrimes;

import literatePrimes.Primes;

public class Main {
    public static void warmUp() {
        for (int i = -1; i < 10000; i++) {
            var primes = Primes.getFirst(i);
            if (primes != null && primes.length != i) {
                System.err.println("warmUp was weird!?");
            }
        }
    }
    // A simple `main` just to run from the command line.
    public static void main(String[] args) {
        warmUp();
        System.gc();
        var start = System.nanoTime();
        var primes = Primes.getFirst(1000000);
        var end = System.nanoTime();
        for (int i = 0; i < primes.length; i++) {
            System.out.println(primes[i]);
        }
        System.out.printf("Primes.getFirst(1000000) took %d ns\n", end - start);
    }
}
