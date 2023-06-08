package bitcamp.myapp.handler;

import bitcamp.util.Prompt;
import bitcamp.myapp.vo.Member;

public class MemberHandler {

  static final int MAX_SIZE = 100;
  static Member[] members = new Member[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  static final char MALE = 'M';
  static final char FEMALE = 'W';

  public static void inputMember() {
    if (!available()) {
      System.out.println("더 이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member(); // Member 설계도에 따라 생성된 것이 m에 인스턴스주소가 들어감
    m.name = Prompt.inputString("이름? ");
    m.email = Prompt.inputString("이메일? ");
    m.password = Prompt.inputString("암호? ");
    m.gender = inputGender((char) 0);
    m.no = userId++;

    // 위에서 만든 Member 인스턴스의 주소를 잃어버리지 않게 레퍼런스 배열에 담는다.
    members[length++] = m; // 0번째에 담는다.
  }

  public static void printMembers() {
    System.out.println("============================");
    System.out.printf("번호, 이름, 이메일 , 성별 \n");
    System.out.println("============================");

    for (int i = 0; i < length; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s\n",
          m.no, m.name, m.email,
          toGenderString(m.gender)); // i번째 주소의 gender를 불러옴
    }
  }

  public static void viewMemeber() {
    String memberNo = Prompt.inputString("회원번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.no == Integer.parseInt(memberNo)) {
        System.out.printf("이름 : %s \n", m.name);
        System.out.printf("이메일 : %s \n", m.email);
        System.out.printf("성별 : %s \n", toGenderString(m.gender));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다.");
  }

  public static void updateMember() {
    String memberNo = Prompt.inputString("수정할 번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.no == Integer.parseInt(memberNo)) {
        System.out.printf("이름(%s)? >", m.name);
        m.name = Prompt.inputString("");
        System.out.printf("이메일(%s)? >", m.email);
        m.email = Prompt.inputString("");
        System.out.printf("비밀번호(%s)? >", m.password);
        m.password = Prompt.inputString("");
        m.gender = inputGender(m.gender);
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다.");
  }

  public static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  private static char inputGender(char gender) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }

    loop: while (true) {
      String menuNo = Prompt.inputString(label +
          "  1. 남자\n" +
          "  2. 여자\n" +
          "> ");

      switch (menuNo) {
        case "1":
          return MALE;
        case "2":
          return FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deletMember() {
    int memberNo = Prompt.inputInt("삭제할 번호? ");

    int delectedIndex = indexOf(memberNo);
    if (delectedIndex == -1) {
      System.out.println("무효한 번호입니다.");
      return;
    }

    for (int i = delectedIndex; i < length - 1; i++) {
      members[i] = members[i + 1];
    }

    members[--length] = null;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.no == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() {
    return length < MAX_SIZE;
  } // 외부에서 import 하지 않는 메서드이기 때문에 public 취소하기, private 붙이면 이 클래스 안에서만 쓸 수 있음
}
