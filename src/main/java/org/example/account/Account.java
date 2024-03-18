package org.example.account;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// ошибки тест кейсов: 1) избыточность дейтствий 2) несколько сценариев слеплены в один
// не стоит использовать стандартные Runtime исключения, может вызывать проблемы/коллиции. Лучше создавать свои типы исключений
// использовать final в сохранениях
// можно использовать аннотации @NotNull, но вдумчиво (???)

public class Account {
    private String name;
    private Map<Currency, Integer> balance = new HashMap<>();
    private ArrayDeque<Reversable> history = new ArrayDeque<>();

    public Account(String name) {
        checkName(name);
        this.name = name;
    }
    public Account(String name, Map<Currency, Integer> balance) {
        this(name);
        this.balance = balance;
    }
    protected Account setNameSimple(String name) {
        this.name = name;
        return this;
    }
    public Account setName(String name) {
        checkName(name);
        history.push(new NameHistory(this, this.name));
        return this.setNameSimple(name);
    }
    public String getName() {
        return this.name;
    }
    public Account setCurrencySum(Currency cur, Integer sum) {
        if (cur == null) throw new IllegalArgumentException("Валюта не может быть пустой!");
        if (sum<0) throw new IllegalArgumentException("Сумма не может быть < 0!");
        history.push(new BalanceHistory(this, this.getBalance()));
        this.balance.put(cur, sum);
        System.out.println("Текущий баланс "+this.balance);
        return this;
    }
    protected Account setBalanceSimple(Map<Currency, Integer> balance) {
        this.balance = balance;
        System.out.println("Текущий баланс 2 "+this.balance);
        return this;
    }

    public Map<Currency, Integer> getBalance() {
        return Map.copyOf(balance);
    }

    public Integer getCurMoney(Currency cur) {
        return this.balance.get(cur);
    }

    private void checkName(String name){
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Имя не может быть пустым!");
    }

    public Account undo() {
        if (!canUndo()) throw new UnsupportedOperationException("Нет действий для отмены!");
        return this.history.pop().undo();
    };
    public boolean canUndo() {
        return !this.history.isEmpty();
    };

    public AccountSave save() {
        return new AccountSave(this.name, this.balance);
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(name, account.name) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, balance);
    }
    public Account restoreAccount(AccountSave s){
        Account a = s.restoreAccount();
        this.name = a.name;
        this.balance = a.balance;
        return this;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
