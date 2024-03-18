package org.example.account;

public class NameHistory implements Reversable {
    private Account account;
    private String oldName;

    public NameHistory(Account a, String oldName) {
        this.account = a;
        this.oldName = oldName;
    }

    @Override
    public Account undo() {
        account.setNameSimple(oldName);
        return account;
    }

}
