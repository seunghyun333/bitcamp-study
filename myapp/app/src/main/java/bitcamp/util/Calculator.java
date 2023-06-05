package bitcamp.util;

public class Calculator {
  public static int result; // 스태틱 변수는 기본 값 0으로 초기화 된다. 로컬 변수는 자동초기화 없음 꼭 값을 줘야함

  public static void init(int a) {
    result = a;
  }

  public static void plus(int a) {
    result += a;
  }

  public static void minus(int a) {
    result -= a;
  }

  public static void multiple(int a) {
    result *= a;
  }

  public static void divide(int a) {
    result /= a;
  }

}
