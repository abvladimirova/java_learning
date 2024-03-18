package org.example.cache;

public class SomeOperation implements ReturningNumber {

    private int a, b;
    private int cnt = 0;

    public int getCnt() {
        return cnt;
    }

    public SomeOperation(int a, int b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public void modify(int a, int b) {
        this.a = a;
        this.b = b;
        this.cnt = 0;
    }

    @Override
    public int getResult() {
        cnt++;
        return a+b;
    }
    @Override
    public int getAnotherResult() {
        return 0;
    }
}
