package org.example;

import org.example.cache.*;

public class Main {
 public static void main(String[] args) {

  var oper = new SomeOperation(1,2);
  ReturningNumber s = CachingHandler.CacheFactory.makeCachable(oper);

  int sum = s.getResult();
  s.getResult();
  System.out.println("количество расчётов: "+oper.getCnt());
  s.modify(2,3);
  System.out.println("количество расчётов: "+oper.getCnt());
  s.getResult();
  s.getResult();
  System.out.println("количество расчётов: "+oper.getCnt());

 }
 }