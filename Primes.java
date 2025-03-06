package literatePrimes;

public class Primes {
    // The algorithm used here comes from Dijkstra via Knuth.  The basic
    // idea is to special case 2, as the first (and only even) prime
    // number, and then step through the odd integers, testing each as
    // a candidate for primality.  A number is prime iff it is not
    // evenly divisible by any smaller prime.
    //
    // To understand how it works, observe that for any composite
    // number j, the smallest prime factor of j will be less than or
    // equal to sqrt(j).  Hence, when testing a candidate for primality,
    // we need not consider whether primes larger than the candidate's
    // square root evenly divide the candidate.  As the number of
    // generated primes grows, the number of primes smaller than the
    // square root grows much more slowly, cutting down on the search
    // space and yielding a considerable performance benefit.
    //
    // When iterating, we are only concerned about the odd primes,
    // so we only need to consider odd candidates starting from 3.  By
    // definition such candidates are not divisible by 2 and so the
    // problem of determining primality is reduced to determining whether
    // a is candidate odd and divisible by some other prime less than or
    // equal to the square root of the candidate.
    //
    // As a further optimization, we avoid division by maintaining an
    // auxilliary array of multiples of primes; that is, a table,
    // `primeMultiples` of multiples of primes p_n.  By maintaining the
    // invariant that, for a given candidate j and prime p_n,
    // primeMultiples[n] < j + 2*p_n, we can quickly test j for
    // divisibility by p_n: if primeMultiples[n] < j, then clearly the
    // invariant holds if we add 2*p_n to primeMultiples[n].  However,
    // if primeMultiples[n] >= j + 2*p_n, then p_n is a factor of j if
    // and only if primeMultiples[n] = j.
    //
    // Note that the factor of 2 in 2*p_n ensures that `primeMultiples[n]`
    // always remains odd, for n > 0: 2*odd is even, and odd+even is odd.
    //
    // To avoid the performance impact of computing square roots, we keep
    // track of the square of the prime that's square root is greater
    // than the square root of the current candidate, and we use this to
    // define an upper bound on multiples table entries that we must
    // use to test a candidate for divisibility.  When we have increased
    // the candidate value such that it becomes equal to this tracked
    // square value, we increase the bound by 1, reset the tracked square
    // to that of the prime now indexed by the new upper bound, and add
    // an entry to the primeMultiples table.  It is safe to refer to the
    // next such prime because we know from "Bertrand's Postulate" that,
    // for a given prime p_n, the next prime p_{n+1} < 2*p_n, and
    // 2*p_n < p_n^2 for p_nn > 2, so such a prime must already have been
    // generated.  Therefore, we simply add the next prime's square to the
    // table as the first multiple of p_{n+1} and set `square` to that
    // same value.
    //
    // Readers who want a deeper explanation may refer to:
    // Knuth, Donald E. 1982. Literate Programming. _The Computer
    // Journal_ 27, 2 (1984), 97-111.
    // http://www.literateprogramming.com/knuthweb.pdf

    private int[] primeMultiples;
    private int[] primes;
    private int nPrimes;
    private int multiplesUpperBound;
    private int nextCandidate;
    private int square;

    // Resets generator state and fills the `primes` array in
    // ascending order.  Note that, as a special case, `reset`
    // returns the first prime number (2), so that we do not
    // have to consider even numbers later.  This simplifies
    // the `next` method, since it only needs to consider odd
    // numbers, and eliminating a conditional probably confers
    // a slight performance advantage.
    private void fill(int[] primes) {
        primes[0] = reset(primes);
        for (int i = 1; i < primes.length; i++) {
            primes[i] = next();
        }
    }

    // Resets the generator state and returns 2: the first, and
    // only even, prime number.
    //
    // The resulting state is set so that the first subsequent
    // invocation of `next` will return the first odd prime, 3.
    // Note that a user must call `reset` before the first
    // invocation of `next`.
    private int reset(int[] primes) {
        nextCandidate = 3;
        this.primes = primes;
        multiplesUpperBound = 1;
        nPrimes = 1;
        primeMultiples = new int[multiplesUpperBound + 1 + primes.length / 2];
        square = 9;
        primeMultiples[multiplesUpperBound] = square;
        return 2;
    }

    // Computes, stores, and returns the next odd prime.
    // The first time this is called, it will return 3,
    // then 5, 7, 11, and so on, up to the defined maximum
    // number of primes.  Attempts to invoke `next` beyond
    // the maximum will generate exceptions, as they try
    // to index beyond the end of the `primes` array.
    //
    // `reset` must be called before the first invocation
    // of `next`.
    private int next() {
        candidates: for (;;) {
            int candidate = nextCandidate;
            nextCandidate += 2;
            // If candidate is equal to square, which is a
            // composite by definition, it is obvioulsy
            // not prime.  Advance the upper bounds for the
            // primality conditions and continue.
            if (candidate == square) {
                multiplesUpperBound++;
                square = primes[multiplesUpperBound] * primes[multiplesUpperBound];
                primeMultiples[multiplesUpperBound] = square;
                continue;
            }
            for (int i = 1; i < multiplesUpperBound; i++) {
                while (primeMultiples[i] < candidate) {
                    primeMultiples[i] += 2*primes[i];
                }
                if (primeMultiples[i] == candidate) {
                    continue candidates;
                }
            }
            primes[nPrimes] = candidate;
            nPrimes++;
            return candidate;
        }
    }

    /**
     * Returns an array of integers holding the first _n_ prime
     * numbers, in ascending order.  Returns null if n < 1.
     *
     * @param n
     *     The number of primes to return.
     */
    public static int[] getFirst(int n) {
        if (n < 1)
            return null;
        var primes = new int[n];
        var generator = new Primes();
        generator.fill(primes);
        return primes;
    }
}
