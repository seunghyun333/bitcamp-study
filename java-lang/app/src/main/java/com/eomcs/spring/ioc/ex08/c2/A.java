package com.eomcs.spring.ioc.ex08.c2;

public class A {
  public A() {
    System.out.println("A() 생성자 호출!");
  }

  static {
    System.out.println("A클렐스 로딩!:");
  }
}
