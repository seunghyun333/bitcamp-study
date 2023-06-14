package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler {

  private static final int MAX_SIZE = 100; 
  //variable initializer 변수초기화 문장 => static 블럭으로 이동. 
  //단 final 변수는 static에서 블럭에서 값을 할당하지 않고 그냥 상수로 취급한다.

  private Prompt prompt;
  
  private Member[] members = new Member[MAX_SIZE]; 
  // variable initializer 변수초기화 문장 => 생성자로 이동
  
  private int length;
  
  //생성자: 인스턴스를 사용할 수 있도록 유효한 값으로 초기화시키는 일을 한다. 
  // => 필요한 값을 외부에서 받고 싶으면 파라미터를 선언하라. 
  // 바로 아랫줄은 멤버핸들러가 실행되려면 prompt를 반드시 이용해야한다는 뜻임.. 
  public MemberHandler(Prompt prompt) {
	this.prompt = prompt;
  }

  public void inputMember() {
    if (!this.available()) {
      System.out.println("더 이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member();
    m.setName(this.prompt.inputString("이름? "));
    m.setEmail(this.prompt.inputString("이메일? "));
    m.setPassword(this.prompt.inputString("암호? "));
    m.setGender(inputGender((char) 0));

    this.members[this.length++] = m;
  }

  public void printMembers() {
    System.out.println("============================");
    System.out.printf("번호, 이름, 이메일 , 성별 \n");
    System.out.println("============================");

    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      System.out.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(),
          toGenderString(m.getGender())); // i번째

    }
  }

  public void viewMemeber() {
    String memberNo = this.prompt.inputString("회원번호? ");
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("이름 : %s \n", m.getName());
        System.out.printf("이메일 : %s \n", m.getEmail());
        System.out.printf("성별 : %s \n", toGenderString(m.getGender()));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다.");
  }

  public void updateMember() {
    String memberNo = this.prompt.inputString("수정할 번호? ");
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        m.setName(this.prompt.inputString("이름(%s)? >", m.getName()));
        m.setEmail(this.prompt.inputString("이메일(%s)? >", m.getEmail()));

        m.setPassword(this.prompt.inputString("새암호?"));
        m.setGender(inputGender(m.getGender()));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다.");
  }

  public static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  private char inputGender(char gender) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }

    while (true) {
      String menuNo = this.prompt.inputString(label + "  1. 남자\n" + "  2. 여자\n" + "> ");

      switch (menuNo) {
        case "1":
          return Member.MALE;
        case "2":
          return Member.FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public void deletMember() {
    int memberNo = this.prompt.inputInt("삭제할 번호? ");

    int delectedIndex = indexOf(memberNo);
    if (delectedIndex == -1) {
      System.out.println("무효한 번호입니다.");
      return;
    }

    for (int i = delectedIndex; i < this.length - 1; i++) {
      this.members[i] = this.members[i + 1];
    }

    this.members[--this.length] = null;
  }

  private int indexOf(int memberNo) {
    for (int i = 0; i < this.length; i++) {
      Member m = members[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private boolean available() {
    return this.length < MAX_SIZE;
  } // 외부에서 import 하지 않는 메서드이기 때문에 public 취소하기, private 붙이면 이 클래스 안에서만 쓸 수 있음
}
