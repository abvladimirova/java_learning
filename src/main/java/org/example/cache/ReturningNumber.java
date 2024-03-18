package org.example.cache;

public interface ReturningNumber {
    @Cache
    public int getResult();
    public int getAnotherResult();
    @Mutator
    public void modify(int a, int b);

}
