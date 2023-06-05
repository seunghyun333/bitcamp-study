package bitcamp.myapp;

public class Test {
  public static void main(String[] args) {
    // 2 * 3 + 7 - 2 / 2 = ?
    // => 연산자 우선 순위를 고려하지 않고 앞에서부터 뒤로 순차적으로 계산한다.

    int result = (2 * 3 + 7 - 2) / 2;
    System.out.println(result);
  }
}
