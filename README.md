# Reaction to APOSD vs Clean Code

These are my reactions to the discussion between John Ousterhout, author
of, "A Philosophy of Software Design" and Robert Martin, author of
"Clean Code" and other programming books.

I admit my biases up front: I don't think particularly highly of the
"Agile" people, and I have an especially low opinion of Robert Martin,
who is frankly not a very good programmer. I was mildly surprised that
Ousterhout chose to give him this opportunity, as he strikes me as
someone who peddles bad advice from a position of unearned authority.
Also, I'm not going to call him "Uncle Bob": he is not my uncle, and he
is not some venerable elder of software development who automatically
commands respect; I feel no need to indulge him by participating in that
pompous and ridiculous marketing conceit. That said....

I am struck by how he is both unable to defend his positions, and
simultaneously (generally) unable to admit their flaws. An example is
his complaint about the labeled continue from the inner loop of the
prime number generator; he describes it as "_awful_" (emphasis his), but
when challenged on this he doesn't bother to explain himself, and just
goes on to repeat the assertion in the next exchange. Based on reading
some of his books, seeing his writing online (blogs, social media, etc),
and having a few very unpleasant interactions with him, I think this is
typical of his approach: state an opinion as an assertion of fact, and
don't bother to provide evidence or a persuasive argument. If
challenged, either claim authority (usually based on length of
experience) or simply ignore and repeat the original assertion. Appeals
to authority are bad enough, appeals to length of experience are worse:
just because someone has been doing something for a long time does not
mean they are good at it. I've been shooting baskets for more than 40
years, but I'll never be a point guard in the NBA.

We see this again in the discussion around test-driven development. He
makes vague allusions to his experience, but never really presents any
evidence for his stance on the practice, nor does he seem capable of
acknowledging the potential downsides. There is no excuse for someone
like Martin, who advocates heavily for TDD, to not be aware of, or able
to readily cite, relevant empirical evidence for his claims. And in
fact, there have been some studies of TDD: Nachi Nagapan at MSR
published a study in 2009 where the findings were that defect rates
decreased when using TDD, up to almost a factor of two, but at a
significant cost in development time: 15-35% longer
(https://www.microsoft.com/en-us/research/blog/exploding-software-engineering-myths/;
I do wish that there should be more studies like Nagapan's: perhaps a
thesis topic for a student working at the intersection of social
psychology and software engineering?).

Going further here, either Martin or one of the other agile influencer
types once wrote a blog post about TDD where they TDD'd their way into
"designing" bubble sort; of course, I can't find it now (Martin's
material, as with much of the material from the consultant types, often
moves around or disappears). The conclusion was weirdly exultant: "look
at how bubble sort just falls out of using TDD!" But this reinforces the
thesis that the methodology often leads to poor results by favoring
shallow, tactical thinking over deeper inquiry and insight: why would I
want to use a methodology that drives design towards bubble sort? For
that matter, why wouldn't I pop open a book on algorithms and implement
something better? Or, why wouldn't I look up the documentation of my
language and libraries and pick one that's already been implemented for
my environment?

As an aside, personally, I've found that TDD _can_ be useful as a way to
overcome writer's block when starting a project. If I find myself stuck
in the throes of analysis paralysis, sometimes writing some failing
tests and making them work after the fact can help move past the mental
block. But the "rules" of TDD seem ridiculous to me, and the way the
whole practice is often wrapped in martial arts metaphors is just weird:
"katas"? Really? Implementing something trivial like the rules of
bowling over and over again does not make me a better engineer the way
throwing a thousand punches against a heavy bag may make me a better
boxer; the two things are not alike. This, and the "software
craftsmanship" stuff, are mostly shallow marketing fluff.  It's
interesting to me that he didn't try to bring any of that up; I suspect
he realized it wouldn't fly.

When Martin does admit he's wrong about something, he tends to minimize
or excuse it with comments of the form, "I wrote this as an afterthought
18 years ago...". Ok, but he also published it in a book that he claims
exemplifies best practices for writing software. One can reasonably
expect better.

In fairness to Martin, he does have a point about names and
decomposition enhancing understanding and making comments redundant.
_If_ a comment is obviated by a better name, that seems useful to me,
and if a comment is entirely redundant with the code, I favor
eliminating the comment. But Martin misses the mark by focusing on
comments as being a "necessary evil" instead of a useful tool, and once
again resorts to dogma instead of producing a convincing argument. I
suppose creating a convincing argument is hard and that as a self-styled
"master software craftsman" he may not feel it is necessary to explain
himself to lesser beings.

I was disappointed that the discussion touched on neither types nor
tooling, and the focus on comments went too far to either extreme as a
result.

For instance, John you say that comments are an "fundamental and
irreplaceable" part of building systems. I would rephrase this and say
that _documentation_ in the form of written prose in a natural language
is the essential thing, but whether such documentation is expressed in
the form of comments or something else is an implementation detail. As
an alternative, Common Lisp has the notion of "documentation strings"
that can be attached to e.g. functions, methods in classes, and so on
(https://www.lispworks.com/documentation/HyperSpec/Body/26_glo_d.htm#documentation_string).
Such strings are separate from comments, and I would argue that they are
more useful as they can be inspected from the repl for any object in the
image that defines one, potentially parsed and interpreted, and so on.

Related, other languages make a distinction between "doc comments" and
other kinds of comments; the former can usually be processed by tools
that generate documentation from source code, while the latter may act
as guides to aspects of the implementation. Consider the documentation
for the Rust standard library, most of which is generated from the
source code itself (e.g. https://doc.rust-lang.org/std/index.html; note
the link to the source at the top of the page). Without this sort of
documentation, using the standard library would be significantly harder,
but it is not necessary to resort to source code directly to read it.

Rust even has a facility for running _documentation tests_ embedded in
comments, which helps to ensure that comments stay up to date as
interfaces change
(https://doc.rust-lang.org/rustdoc/write-documentation/documentation-tests.html).
Martin had quipped that comments are not checked by the
compiler...except that sometimes they are. It's odd to me that Martin
focused on the color settings in his IDE, asserting that the default
subdued color in whatever tool he's using is an invitation to skip
comments entirely (incidentally, as he himself implicitly acknowledged,
this is obviously a configurable setting. And perhaps the default is to
make them easier to read as prose?), without also acknowledging that
many IDEs can use doc comments to generate documentation directly from
source. Perhaps he doesn't know how to use his tools to their full
effect?

I think you alluded to the semantic difference by making the header
comment for the prime generator a doc comment; Martin seemed befuddled
by this, and it would have been worth explicitly hammering home that
such comments are processed by tools to generate comprehensive
documentation for a module that can be inspected _outside of the source
code_, aiding understanding and discoverability. By focusing on
comments, as the means of expression of documentation, instead of
documentation as the desired artifact, I think the point was lost.

On the flip side, Martin seems averse to any sort of documentation _at
all_, believing that the code _is_ the documentation. This is just
silly, and doesn't scale beyond toy examples.

The point here is that not all comments are created equal, and different
kinds serve different purposes. Moreover, the undifferentiated focus on
comments specifically detracts from the critical and essential role of
documentation generally.

It would have been useful to highlight the different types of comments
and their different uses, perhaps mention other facilities, and show how
these things interact with external tooling to produce system-level
documentation.

A related point that I think deserved explicit attention is
discoverability. Navigating a large system and finding the information
needed to work with it effectively is dramatically easier with good
documentation, but the focus mostly seemed confined to localized use of
an interface or understanding of the implementation of something (a
method, class, whatever). In the discussion of the prime number
generator, Martin talks about "intimacy" with the code, which I
interpreted to mean deep knowledge and understanding. But he gives no
consideration to how a programmer becomes "intimate" with code, and how
on real-world teams people come and go and must acquire that intimacy to
be effective at their jobs. This is where documentation and
discoverability really shine and endless decomposition hurts.

With respect to types, Martin is infamously averse to them, so much so
that Shriram Krishnamurthi called him out about it on twitter
(https://x.com/shriramkmurthi/status/1136411753590472707). Shriram is
right: Martin has no clue what he's talking about. Obviously, with a
statically typed language, I don't have to "test" whether a function
that expects an integer behaves properly when passed a list --- or vice
versa; such programs are not representable in the first place, and this
is enforced by the language environment. As such, my "testing burden" is
reduced by orders of magnitude since the language eliminates entire
categories of errors. And yes, these sorts of things happen all the time
in production systems implemented in dynamic languages (I've personally
encountered both of the examples I mentioned above). But in this
exchange, better use of a type system could also be used to define
better interfaces.

Martin writes that he thinks, `addSongToLibrary(String title, String[]
authors, int durationInSeconds);` is a fine, self-documenting interface.
His words:

|This seems like a very nice abstraction to me, and I cannot
|imagine how a comment might improve it.

Ousterhout rightly takes him to task about this, asking among other
things: "Is there any expected format for an author string?" A great
question, but one that could be obviated by passing an instance of an
`Author` object that abstracts that away. Here, the problem may not be a
lack of comments, but rather a lack of structure in the types accepted
by the function.

Two other things jumped out to me about this example: first, just as
using a string for authors is insufficiently precise to effectively use
the interface, using an integer for "durationInSeconds" is unnecessarily
low-level, and complects the interface with an irrelevant detail (the
time unit). Moreover, the interface is less flexible as a result: I, as
a listener of music, probably only care about song durations in units of
seconds. But a recording engineer may well want something more granular.
Similarly, what if this is called from somewhere that has the duration
as milliseconds, or a (minutes,seconds) pair? So instead of taking an
`int` for duration and giving it a name that ties it to a particular
unit, why not take an instance of a more generic `Duration` type,
instead? Many languages have them; this example is in Java, so passing a
`java.time.Duration` here would be superior to the `int`.

Second, I'm no musician, but I don't think one usually refers to the
creators or performers of a song as the "authors"; "writers" seems more
common in the domain (at least given my teenage experience pouring over
liner notes). So in this case, the interface is poor because it doesn't
match the domain's expected nomenclature. And while one often reads the
term "songwriter", songs are also written by producers or composers or
others in the studio, so perhaps this should take instances of a
"Creator" object. The point here is that the abstraction ought to match
the domain, and it doesn't. Is this unfair to Martin? After all, he's a
consultant and author, not (as far as I'm aware) a musician. Perhaps,
but it's also an engineer's professional obligation to make sure they're
familiar with the domain they're working in: if I were designing that
interface, at a minimum I'd ask a couple of musicians what I should call
my arguments.

In one of Martin's books, there's a chapter where he sits down with
another agile consultant type and they "pair" to write a program that
implements the rules for scoring a bowling match. Something that struck
me about this is that they don't seem to start with a written
specification of those rules, but rather just feel their way through
based on their intuition; they conclude by saying something like, "Yeah,
I think that's about it", perhaps drawing from a memory of a childhood
birthday party? That may be fine for a consultant pedding trivial
"katas", but it's no way to write professional software. I see a
similarly flippant attitude with the presentation of `addSongToLibrary`.

Related to the first point, Alexis King wrote a wonderful blog post
several years ago about what she called, "type-driven design"
(https://lexi-lambda.github.io/blog/2019/11/05/parse-don-t-validate/).
If I were to try and distill this to its essence, it says that a type
system can and should be usefully employed to encode semantic meaning
into data in such a way that it cannot be misused. Such encoding should
happen at the edges of a system, so that preconditions on data need not
be re-checked when the data is used.

This abstraction Martin proposed is bad not only because it's
undocumented, but because it entangles the interface with irrelevant
implementation details about the format of the arguments that a caller
must now know about and take care to enforce: the burden is now on the
caller to convert to the expected format at the call site. So while
documenting the interface would be an improvement, using King's model
would be even better. Internally, the system should pass around
`Duration` objects to represent lengths of time, only converting to and
from at the boundaries of the system. In fact, in this case, the
interface should be both improved _and_ documented so that it's easily
discoverable and more usable.

## Returning to the Prime Number Generator

On my first reading, I somewhat preferred Martin's final version of the
prime number program, but looking at it again now, I see that it is
deficient in several significant ways.

First, there's the whole matter of attribution. The algorithm is not due
to Knuth, but rather to Dijkstra (this is the example used in section 9,
"A First Example of Step-Wise Program Composition" from "Structured
Programming"). Knuth himself acknowledges this in the source paper on
literate programming that this whole thing is based on
(http://www.literateprogramming.com/knuthweb.pdf); Martin should do
better.

Next, Martin leaves it entirely undocumented, even after repeated pleas
to, at least, explain the rationale for squaring numbers. I find the
insistence on not documenting particularly odd given that he got the
algorithm from a paper on literate programming, a technique designed not
just to document a program, but to derive the program from the
documentation. If a curious reader goes back to Knuth's paper, they will
find that the whole business around squares and multiples and the
properties of both with respect to this algorithm is actually very well
explained in a way that is highly accessible to someone with a lay
knowledge of the mathematics involved. No need for hour-long bike rides
or long contemplative sits in one's chair with one's eyes closed when
one could spend 15 minutes reading a program's documentation. Seriously;
the irony here is deafening.

As an aside, note that Dijkstra _also_ explains use of squares and the
multiples array; however, he quotes Knuth (who presumably read a draft
copy of "Structured Programming") and commented, "Here you [Dijkstra]
are guilty of a serious omission! Your program makes use of a deep
result of number theory, namely that if p_n denotes the $n$th prime
number, we _always_ have $p_{n+1} < p_n^2$." Dijkstra responds,
"Peccavi." Note that careful reading of the original could also let us
make a _memory_ optimization; observe that the "multiples" array doesn't
need to be the same size as the output array (length n), but rather can
be ~sqrt(n) entries; Knuth notes that, for n=1000, the index into the
multiples array is never more than 30; he's using Pascal, and the array
bounds go from 2..30, so the size is actually smaller.

Martin also fails to validate input: the way it is defined, a caller
could ask for 0 primes and the program would generate an exception (I
believe the same is true of your version as well, John). I get that this
is a pedagogical example, but that actually amplifies the point: this is
a pedagogical example in a book targeted towards professionals, not a
first example in an introductory programming text. In this context, I
should reasonably expect to see best practices, while in an introductory
book one may introduce those later so as not to overwhelm the reader
early on.

His choices around decomposition lead to "spooky action at a distance":
he makes `candidate` a data member of the class. So it's incremented in
the loop, but not passed to `candidateIsPrime` or
`registerTheCandidateAsPrime` methods. To understand what the main loop
does, I also have to understand the other two methods. Frankly, I don't
see why so many things need to be data members of a class.  Indeed, even
the structure of the `for` loop he uses is misleading, in that the
initialization and increment parts are manipulating `candidate`, but the
test portion is against `primesFound`.

Still, I rather liked the concision of his main loop and the semantic
pairing of, "if the number is prime, add it to the list of primes". It
struck me that the program might be better written by keeping that as
the central structure of the loop while separating out the primality
testing state into an auxiliary object. But because of the tight
coupling between that state and the iteration through candidates, I
became convinced that this was hopeless. What little benefit being able
to write something like:

```java
    candidate = 3;
    for (i = 0; i < n; i++) {
        while (!auxState.isPrime(candidate))
            candidate += 2;
        primes[i] = candidate;
    }
````

...just isn't repaid by the complexity of maintaining that state
separately. Moreover, it's no longer easy to verify that the state stays
in sync with the context of the rest of the program. Trying to decompose
this program into smaller functions that share state in the parent
object is not useful, and just increases the cognitive load on the
programmer, who as you pointed out must now read and internalize the
decomposed methods to convince themselves both that they work, and to
maintain the invariants of the generator across invocations. It occurs
to me that this complication may be somewhat alleviated by passing that
state to a function by argument, and allowing the function to update the
state as needed, but I wonder if, instead, more of an "iterator-like"
interface wouldn't be better: provide a 'nextPrime()' method and just
wrap all the state up into that. Repeated invocations would simply
return the next prime. This would certainly let the programmer detangle
the outer loop, which is accumulating primes in a buffer, from the
details of what prime we're searching for.

I also mentioned that Knuth's reference program did not append to the
prime multiples array until a candidate exceeded the currently tracked
square, while Martin's does so eagerly (John's does, as well).  However,
what I didn't realize at the time was that this means that the prime
multiples overflow their signed, 32-bit representation once the
algorithm gets to 46349, which is only the 4793th prime. In some sense,
this doesn't matter much, since they won't likely be used, but in
addition to being wasteful, it is an error waiting to happen.

Finally, Martin's version puts state into a class, in order to
facilitate method decomposition. However, that state is stored in static
members, meaning that the prime generator is not thread safe.  (I also
keep track of state via a class abstraction, but in member variables in
an instance of the generator object.)

To give proper attribution, I read about some of these issues (overflow,
for example) either on "Hacker News" or Reddit, though I can't remember
which one.

In short, not only does Martin's version have the problems I mentioned
in my earlier notes, it is incorrect.

Also, with respect to comments, this is the sort of program that is
better served with an interface doc comment that describes how to use
it, and a "theory statement" comment near the top of the implementation
that explains the algorithm, while omitting most of the inline comments.

As a very minor quibble: Ousterhout imports `java.util.ArrayList` in his
version but don't seem to use it.

I spent some time poking at this, and my "iterator" idea, and have
attached the result. I'm still not happy with some aspects of it such as
the way that state is distributed across methods in a class; I'd rather
it was all localized to one function.  However, I found the main loop in
both versions from the exchange with Martin confusing in that it
iterated over candidates and tested against the number of factors found;
I really wanted to separate those.  So what I have now, while imperfect,
at least does that. A careful reader may note that this code resurrected
Knuth's (and Dijkstra's) `square` variable, and reduced the size of the
prime multiples array; I found going back to Knuth's paper very useful,
and studying Martin's refactoring far less so.

In support of comments, I did find them extremely valuable for both
explaining what was going on, and also documenting the expectations for
what can be called when.

### Performance Measurements and More Bugs

I readily admit that I am not a Java programmer, nor am I really set up
to profile Java programs. But I did write a little driver around the
generator to test it, and captured timestamps around a run to get some
timing numbers relative to the other versions.  I took care to warm up
the JVM before timestamping the final run, which calculates the first
10,000 primes. Mine is slightly faster than John's, but not
significantly so; I imagine the delta is well within the margin of
error. This was all on a 2022 Mac Studio with OpenJDK 23.0.2 (installed
from Homebrew).

```
term% diff -u a b
--- a   2025-03-01 14:16:42
+++ b   2025-03-01 14:16:59
@@ -9998,4 +9998,4 @@
 104717
 104723
 104729
-Primes.getFirst(10000) took 1019959 ns
+Primes.getFirst(10000) took 1013000 ns
term%
```

Note that in the exchange, Martin claimed that his final version of the
prime generator had a significant performance advantage over your
version. I see no reason to believe that his version would be faster,
though, so I ran and timed it as well.

On my first go, in my warmup routine, his version threw an exception
because of lack of checking preconditions, resulting in an out-of-bounds
exception when indexing into an array, as I mentioned above (the warmup
just ran a loop from 1 to 10000, computing that number of primes and
checking the size of the returned array). The specific circumstance was
when n==1; this means that Martin's version cannot be used to return a
list consisting of only the first prime. One can see this trivially by
trying to run Martin's version with a simple exerciser:

```java
    public static void main(String[] args) {
        int[] ps = generateFirstNPrimes(1);
        System.out.println(ps[0]);
    }
```

Which throws:

```
    Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 1
            at rcmLiteratePrimes.PrimeGenerator.initializeTheGenerator(PrimeGenerator.java:23)
            at rcmLiteratePrimes.PrimeGenerator.generateFirstNPrimes(PrimeGenerator.java:30)
            at rcmLiteratePrimes.PrimeGenerator.main(PrimeGenerator.java:80)
```

Easy enough to fix, of course: just skip that case in the warmup for his
version.  Anyway, the best I saw his clock in at on my machine is
1028334 ns; insignificantly slower than either, but certainly not
faster, let alone faster by ~30% or whatever he claimed. Increasing the
number of calculated primes to 1,000,000 yields similar results, though
of course the whole thing takes longer for all three versions. In this
case, your version was fastest, then Martin's, then mine. But the
absolute delta between all three was very small, around 1.2% between
yours and mine.

```
term% tail -1 a b c
==> a <==
Primes.getFirst(1000000) took 419105625 ns

==> b <==
Primes.getFirst(1000000) took 424275167 ns

==> c <==
Primes.getFirst(1000000) took 421752292 ns
term%
```

Put bluntly, I don't trust that Martin performed whatever benchmarking
he did competently; he certainly gave no details about his experimental
setup or execution environment.

