package org.example.account;

import java.util.HashMap;
import java.util.Map;

public class AccountSave {

    private final String name;
    private Map<Currency, Integer> balance = new HashMap<>();

    public AccountSave(String name, Map<Currency, Integer> balance) {
        this.balance = Map.copyOf(balance);
        this.name = new String(name);
    }

    public Account restoreAccount(){
        return new Account(this.name, this.balance);
    }
}

