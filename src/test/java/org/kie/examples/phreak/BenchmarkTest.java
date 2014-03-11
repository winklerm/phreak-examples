package org.kie.examples.phreak;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.examples.phreak.util.DataGenerator;

@RunWith(Parameterized.class)
public class BenchmarkTest {

    // will verify various configs of log watch
    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        final List<Object[]> result = new LinkedList<Object[]>();
        for (final Benchmark b : Benchmark.values()) {
            result.add(new Object[] { b });
        }
        return result;
    }

    private KieSession ksession;
    private final DataGenerator data;
    private final Benchmark benchmark;

    public BenchmarkTest(final Benchmark benchmark) {
        this.benchmark = benchmark;
        this.data = new DataGenerator(10000);
        this.data.fillWithTestFacts();
    }

    @Before
    public void initSession() {
        final KieContainer kcontainer = KieServices.Factory.get().getKieClasspathContainer();
        this.ksession = kcontainer.newKieSession(this.benchmark.getSessionName());
    }

    @After
    public void disposeSession() {
        if (this.ksession != null) {
            this.ksession.dispose();
        }
    }

    @Test
    public void testGrouping() {
        this.benchmark.execute(this.data, this.ksession);
    }

}
