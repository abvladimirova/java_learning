package org.task4.writer;

// стандартный Consumer<T> не подходит, т.к. не возвращает результат, так бин не привязывается
public interface MyConsumer<T> {
    boolean accept(T t);

}
