package com.eomcs.spring.ioc.ex08.c2;

public class B {
  public B() {
    System.out.println("B() 생성자 호출!");
  }

  static {
    System.out.println("b클렐스 로딩!:");
  }
}
