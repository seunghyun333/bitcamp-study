package bitcamp.test.step05;

// 1) 낱개의 변수 사용
// 2) 낱개의 변수 재사용
// 3) 배열 사용
// 4) 클래스를 이용하여 데이터 타입 정의 (중첩클래스: 로컬 클래스)
// 5) 출력 기능을 별도의 메서드로 분리 (중첩클래스: 스태틱 중첩 클래스)
public class App {
  
  static class Score {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float aver;
  }
  
  public static void main(String[] args) {

    final int MAX_SIZE = 10;
    Score[] scores = new Score[MAX_SIZE]; //레퍼런스 10개 준비
    int length = 0;

    Score s = new Score() ; //인스턴스 생성 
    s.name = "홍길동";
    s.kor = 100;
    s.kor = 100;
    s.kor = 100;
    s.sum = s.kor + s.eng + s.math;
    s.aver = s.sum / 3f;
    scores[length++] = s;

    s = new Score(); // 레퍼런스 재사용 
    s.name = "임꺽정";
    s.kor = 90;
    s.kor = 90;
    s.kor = 90;
    s.sum = s.kor + s.eng + s.math;
    s.aver = s.sum / 3f;
    scores[length++] = s;

    s = new Score(); // 레퍼런스 재사용 
    s.name = "유관순 ";
    s.kor = 80;
    s.kor = 80;
    s.kor = 80;
    s.sum = s.kor + s.eng + s.math;
    s.aver = s.sum / 3f;
    scores[length++] = s;

    for (int i = 0; i < length; i++) {
      printScore(scores[i]);
    }
  }

  static void printScore(Score s){
    System.out.printf("%s: 합계=%d, 평균=%.1f\n", 
        s.name, s.sum, s.aver);
  }

}
