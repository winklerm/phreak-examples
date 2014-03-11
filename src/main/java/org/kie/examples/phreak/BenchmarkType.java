package org.kie.examples.phreak;

import org.kie.api.runtime.KieSession;
import org.kie.examples.phreak.util.DataGenerator;

public enum BenchmarkType {

    GROUPING("groupingKBase") {
        @Override
        public void execute(final DataGenerator data, final KieSession ksession) {
            data.insertInto(ksession);
            ksession.getAgenda().getAgendaGroup("gold").setFocus();
            ksession.fireAllRules();
        }
    },
    MODIFICATION("modificationKBase"), LAZINESS3("laziness3KBase"), LAZINESS6("laziness6KBase");

    private final String kbaseName;

    BenchmarkType(final String kbaseName) {
        this.kbaseName = kbaseName;
    }

    public void execute(final DataGenerator data, final KieSession ksession) {
        data.insertInto(ksession);
        ksession.fireAllRules();
    }

    public String getKieBaseName() {
        return this.kbaseName;
    }

}
