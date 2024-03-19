package org.task4;

// стандартный Consumer<T> не подходит, т.к. не возвращает результат, как бин не привязывается
public interface MyConsumer<T> {
    boolean accept(T t);

}
