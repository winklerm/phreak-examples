package org.kie.examples.phreak;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.examples.phreak.util.DataGenerator;

/**
 * Illustrates different behavior of PHREAK vs. RETE-OO on a larger set of facts
 * when using agenda groups.
 */
public class GroupingTest {

    private KieSession ksession;

    @Before
    public void initSession() {
        final KieContainer kcontainer = KieServices.Factory.get().getKieClasspathContainer();
        this.ksession = kcontainer.newKieSession("groupingKSession");
    }

    @After
    public void disposeSession() {
        if (this.ksession != null) {
            this.ksession.dispose();
        }
    }

    @Test
    public void testGrouping() {
        new DataGenerator(this.ksession).insertTestFacts();
        this.ksession.getAgenda().getAgendaGroup("gold").setFocus();
        this.ksession.fireAllRules();
    }
}
