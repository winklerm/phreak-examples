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
 
The tests use common scenario, where roughly 200,000 facts are inserted into working memory.

Laziness test
-------------

Illustrates RETE's fact propagation through the network with every insert, triggering re-evaluation of accumulate nodes.

Algorithm | Time (s)
----------|---------
PHREAK  | 5.7
RETE-OO | 52.4


Grouping test
-------------

Shows that the agenda-group can be used for real optimization with PHREAK. With RETE-OO, the facts are propagated event through the nodes belonging to rules not in the active agenda-group.

Algorithm | Time (s)
----------|---------
PHREAK  | 3.6
RETE-OO | 53.0

This test is the same as Laziness test, except it uses agenda-group to activate only the first rule. Please note, that the time taken by RETE-OO in both tests is nearly the same (which indicates that the agenda-group actually did not prevent evaluation of RETE nodes for the rules not enabled by the agenda-group).

Test (with RETE-OO) | Time (s)
--------------------|---------
laziness | 52.4
grouping | 53.0


