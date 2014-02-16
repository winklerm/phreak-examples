phreak-examples
===============

Examples of situations highlighting differences between the new Drools 6 rule engine algorithm called PHREAK and its predecessor RETE-OO.

You can run the tests with default rule engine (PHREAK) using Maven:

    mvn clean test


The rule engine can be specified by setting the property drools.ruleEngine:

    mvn clean test -Ddrools.ruleEngine=phreak

or

    mvn clean test -Ddrools.ruleEngine=reteoo


Notes
-----

Please note, that this is not intended as a benchmark. This project illustrates some cases when using PHREAK results in more efficient evaluation than using RETE-OO. Although the rules in the examples could be rewritten into a form that the difference between the two algorithms would be minimal, these examples show how unforgiving RETE-OO can be.
 
The tests use common scenario, where roughly 200,000 facts (the default for the test) are inserted into working memory. The JVM arguments were set to -server -Xms6144m -Xmx6144m. 

Note: The results below have been updated to reflect setting the JVM arguments (previously the defaults were used): RETE-OO stayed roughly the same, while PHREAK got faster - from 5.7 to 1.9 secs in Laziness 3 test and from 3.6 to 1.6 secs in Grouping test.

Laziness test (3 rules)
-------------

Illustrates RETE's fact propagation through the network with every insert, triggering re-evaluation of accumulate nodes with 3 rules.

Algorithm | Time (s)
----------|---------
PHREAK  | 1.9
RETE-OO | 52.4 


Laziness test (6 rules)
-------------

Illustrates RETE's fact propagation through the network with every insert, triggering re-evaluation of accumulate nodes with 6 rules.

Algorithm | Time (s)
----------|---------
PHREAK  | 3.1
RETE-OO | 52.2


Grouping test
-------------

Shows that the agenda-group can be used for real optimization with PHREAK. With RETE-OO, the facts are propagated event through the nodes belonging to rules not in the active agenda-group.

Algorithm | Time (s)
----------|---------
PHREAK  | 1.6
RETE-OO | 52.6

This test is the same as Laziness (3 rules) test, except it uses agenda-group to activate only the first rule. Please note, that the time taken by RETE-OO in all three tests is nearly the same (which indicates that the agenda-group actually did not prevent evaluation of RETE nodes for the rules not enabled by the agenda-group).

Test | RETE-OO time (s) | PHREAK time (s)
-----|------------------|--------
laziness 3 | 52.4  | 1.9 
laziness 6 | 52.2* | 3.1
grouping   | 52.6  | 1.6

* to be investigated

Further experiments
-------------------

You can play with the number of facts (the number of Transactions summed by accumulate in the rules) by setting the phreak.examples.transactions system property (default value is 10100):

    mvn clean test -Ddrools.ruleEngine=phreak -Dphreak.examples.transactions=20000

More results to come soon (hopefully).

