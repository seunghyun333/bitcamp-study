package bitcamp.myapp;

public class App {
  public static void main(String[] args) {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("-----------------------------");

    int no = 100;
    String name = "홍길동";   //reference 변수: 변수에 저장된다 훔 .. 
    int age = 20;
    boolean working = true;
    char gender = "M";
    float leftEye = 1.5f;
    float rightEye= 1.0f;
    
    System.out.printf("번호: %d\n", no);
    System.out.printf("이름: %s\n", name);
    System.out.printf("나이: %d\n" + age);
    System.out.printf("재직여부: %b\n", working);
    System.out.printf("성별(남자(M),여자(W)): %c\n", gender);
    System.out.printf("좌우시력: %.1f,/%.1f\n", leftEye, rightEye);

  }
}
//데이터 표기: 스트링 리터럴(문자), 정수 인터럴, 불린 인터럴, 문자 리터럴, 부동소수점 리터럴