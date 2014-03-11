package org.kie.examples.phreak;

import org.kie.api.runtime.KieSession;
import org.kie.examples.phreak.util.DataGenerator;

public enum Benchmark {

    GROUPING("groupingKSession") {
        @Override
        public void execute(final KieSession ksession) {
            new DataGenerator(ksession).insertTestFacts();
            ksession.getAgenda().getAgendaGroup("gold").setFocus();
            ksession.fireAllRules();
        }
    },
    MODIFICATION("modificationKSession"), LAZINESS3("laziness3KSession"), LAZINESS6("laziness6KSession");

    private final String sessionName;

    Benchmark(final String sessionName) {
        this.sessionName = sessionName;
    }

    public void execute(final KieSession ksession) {
        new DataGenerator(ksession).insertTestFacts();
        ksession.fireAllRules();
    }

    public String getSessionName() {
        return this.sessionName;
    }

}
