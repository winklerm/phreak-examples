package org.kie.examples.phreak;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.examples.phreak.util.DataGenerator;

/**
 * Illustrates laziness of PHREAK vs. eagerness of RETE-OO on a larger set of facts
 * with 3 rules.
 */
public class Laziness3Test {

    private KieSession ksession;

    @Before
    public void initSession() {
        final KieContainer kcontainer = KieServices.Factory.get().getKieClasspathContainer();
        this.ksession = kcontainer.newKieSession("laziness3KSession");
    }

    @After
    public void disposeSession() {
        if (this.ksession != null) {
            this.ksession.dispose();
        }
    }

    @Test
    public void testLaziness() {
        new DataGenerator(this.ksession).insertTestFacts();
        this.ksession.fireAllRules();
    }
}
