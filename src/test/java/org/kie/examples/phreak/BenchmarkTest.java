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
import org.kie.api.runtime.KieSession;
import org.kie.examples.phreak.util.DataGenerator;

@RunWith(Parameterized.class)
public class BenchmarkTest {

    // will verify various configs of log watch
    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        final List<Object[]> result = new LinkedList<Object[]>();
        for (final BenchmarkType b : BenchmarkType.values()) {
            result.add(new Object[] { b });
        }
        return result;
    }

    private KieSession ksession;
    private final DataGenerator data;
    private final BenchmarkType benchmarkType;

    public BenchmarkTest(final BenchmarkType benchmarkType) {
        this.benchmarkType = benchmarkType;
        this.data = new DataGenerator(10000);
        this.data.fillWithTestFacts();
    }

    @Before
    public void initSession() {
        this.ksession = this.benchmarkType.getPhreakKieBase().newKieSession();
    }

    @After
    public void disposeSession() {
        if (this.ksession != null) {
            this.ksession.dispose();
        }
    }

    @Test
    public void testGrouping() {
        this.benchmarkType.execute(this.data, this.ksession);
    }

}
