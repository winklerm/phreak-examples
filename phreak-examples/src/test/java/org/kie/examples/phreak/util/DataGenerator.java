package org.kie.examples.phreak.util;

import org.kie.api.runtime.KieSession;
import org.kie.examples.phreak.domain.Account;
import org.kie.examples.phreak.domain.Person;
import org.kie.examples.phreak.domain.Transaction;

/**
 * Utility class generating test facts and inserting them into given KieSession:
 * <ul>
 *   <li>2 facts of type Person,</li>
 *   <li>each having 10 Accounts,</li>
 *   <li>each Account is associated with a number of Transactions (see below).</li>
 * </ul>
 *
 * The number of transactions associated with each account is specified using
 * <em>phreak.examples.transactions</em> system property (default 10100).
 */
public class DataGenerator {

    private static final Long TRANSACTIONS_PER_ACCOUNT =
            Long.valueOf(System.getProperty("phreak.examples.transactions", "10100"));
    
    private static final int ACCOUNTS_PER_PERSON = 10;

    private final KieSession ksession;

    public DataGenerator(final KieSession ksession) {
        this.ksession = ksession;
    }

    public void insertTestFacts() {
        final Person person1 = this.insertPerson("Carl");
        final Person person2 = this.insertPerson("Egon");
        
        this.insertPersonAccounts(person1, person2);
        this.insertPersonAccounts(person2, person1);
    }

    private Person insertPerson(final String name) {
        final Person person = new Person(name);
        this.ksession.insert(person);
        return person;
    }
    
    private void insertPersonAccounts(final Person owner, final Person partner) {
        for (long i = 0; i < ACCOUNTS_PER_PERSON; i++) {
            this.insertAccount(i, owner, partner);
        }
    }

    private void insertAccount(final long id, final Person owner, final Person partner) {
        final Account account = new Account(id, owner);
        this.ksession.insert(account);

        final Account partnerAccount = new Account(id, partner);
        this.ksession.insert(partnerAccount);
        
        for (long t = 0; t < TRANSACTIONS_PER_ACCOUNT; t++) {
            final Transaction transaction = new Transaction(account, partnerAccount, 5);
            this.ksession.insert(transaction);
        }
    }
}
