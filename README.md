phreak-examples
===============

Examples of situations highlighting differences between the new Drools 6 rule engine algorithm called PHREAK and its predecessor RETE-OO.

You can run the tests with default rule engine (PHREAK) using Maven:

    mvn clean test


The rule engine can be specified by setting the property drools.ruleEngine:

    mvn clean test -Ddrools.ruleEngine=phreak

or

    mvn clean test -Ddrools.ruleEngine=reteoo

