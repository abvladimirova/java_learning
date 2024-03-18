package org.example.account;

import java.util.Map;

public class BalanceHistory implements Reversable {
    private Account account;
    private Map<Currency,Integer> unmodifiableBalance;

    public BalanceHistory(Account a, Map<Currency, Integer> balance) {
        this.account = a;
        this.unmodifiableBalance = balance;
    }
    @Override
    public Account undo() {
        return account.setBalanceSimple(unmodifiableBalance);
    }

}
