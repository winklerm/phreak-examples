phreak-examples: benchmark
==========================

Thanks to triceo, here is a JMH benchmark measuring PHREAK vs. RETE-OO performance on specific use-cases, where PHREAK should demonstrate its lazy (and more efficient) behaviour.

You can run the benchmark by building the project using Maven:

    mvn clean install

Then run the generated shaded jar:

    java -jar target/benchmark-1.0.0-SNAPSHOT-shaded.jar

Results
=======

Results from run on dedicated machine with OracleJDK 1.7.0_45 (thanks to psiroky for running it!).

| Benchmark | numOfTransactions | ruleEngine | Mode | Samples | Mean | Mean error | Units |
|-----------|-------------------|------------|------|---------|------|------------|-------|
| o.k.e.p.Benchmark.grouping | 10 | phreak | avgt | 200 | 1.558 | 0.025 | ms/op |
| o.k.e.p.Benchmark.grouping | 10 | reteoo | avgt | 200 | 2.535 | 0.015 | ms/op |
| o.k.e.p.Benchmark.grouping | 100 | phreak | avgt | 200| 5.470 | 0.024 | ms/op |
| o.k.e.p.Benchmark.grouping | 100 | reteoo | avgt | 200 | 15.668 | 0.052 | ms/op |
| o.k.e.p.Benchmark.grouping | 1000 | phreak | avgt | 200 | 54.144 | 0.152 | ms/op |
| o.k.e.p.Benchmark.grouping | 1000 | reteoo | avgt | 200 | 151.570 | 0.699 | ms/op |
| o.k.e.p.Benchmark.laziness3 | 10 | phreak | avgt | 200 | 2.085 | 0.022 | ms/op |
| o.k.e.p.Benchmark.laziness3 | 10 | reteoo | avgt | 200 | 2.496 | 0.013 | ms/op |
| o.k.e.p.Benchmark.laziness3 | 100 | phreak | avgt | 200 | 9.914 | 0.031 | ms/op |
| o.k.e.p.Benchmark.laziness3 | 100 | reteoo | avgt | 200 | 15.637 | 0.047 | ms/op |
| o.k.e.p.Benchmark.laziness3 | 1000 | phreak | avgt | 200 | 118.413 | 0.806 | ms/op |
| o.k.e.p.Benchmark.laziness3 | 1000 | reteoo | avgt | 200 | 151.621 | 0.492 | ms/op |
| o.k.e.p.Benchmark.laziness6 | 10 | phreak | avgt | 200 | 2.889 | 0.025 | ms/op |
| o.k.e.p.Benchmark.laziness6 | 10 | reteoo | avgt | 200 | 3.872 | 0.027 | ms/op |
| o.k.e.p.Benchmark.laziness6 | 100 | phreak | avgt | 200 | 17.661 | 0.057 | ms/op |
| o.k.e.p.Benchmark.laziness6 | 100 | reteoo | avgt | 200 | 29.599 | 0.088 | ms/op |
| o.k.e.p.Benchmark.laziness6 | 1000 | phreak | avgt | 200 | 260.396 | 2.086 | ms/op |
| o.k.e.p.Benchmark.laziness6 | 1000 | reteoo | avgt | 200 | 330.591 | 1.145 | ms/op |
| o.k.e.p.Benchmark.modification | 10 | phreak | avgt | 200 | 2.047 | 0.030 | ms/op |
| o.k.e.p.Benchmark.modification | 10 | reteoo | avgt | 200 | 2.537 | 0.017 | ms/op |
| o.k.e.p.Benchmark.modification | 100 | phreak | avgt | 200 | 10.062 | 0.038 | ms/op |
| o.k.e.p.Benchmark.modification | 100 | reteoo | avgt | 200 | 15.571 | 0.051 | ms/op |
| o.k.e.p.Benchmark.modification | 1000 | phreak | avgt | 200 | 116.113 | 0.537 | ms/op |
| o.k.e.p.Benchmark.modification | 1000 | reteoo | avgt | 200 | 153.470 | 0.689 | ms/op |

More measurements to come. I am going to run the benchmark with numOfTransactions=10000 (it has to be restricted somehow to take reasonable time) to align the measurements with the original phreak-examples.

