package bitcamp.myapp;

public class MemberHandler {

  static final int MAX_SIZE = 100;
  static int[] no = new int[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static String[] email = new String[MAX_SIZE];
  static String[] password = new String[MAX_SIZE];
  static char[] gender = new char[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  static final char MALE = 'M';
  static final char FEMALE = 'W';

  static void inputMember() {
    name[length] = Prompt.inputString("이름? ");
    email[length] = Prompt.inputString("이메일? ");
    password[length] = Prompt.inputString("암호? ");

    loop: while (true) {
      String menuNo = Prompt.inputString("성별:\n " +
          " 1. 남자\n " +
          " 2. 여자\n " +
          " >");

      switch (menuNo) {
        case "1":
          gender[length] = MALE;
          break loop;
        case "2":
          gender[length] = FEMALE;
          break loop;
        default:
          System.out.println("무효한 번호 입니다");

      }
    }

    no[length] = userId++;
    length++;
  }

  static void printMembers() {
    System.out.println("============================");
    System.out.printf("번호, 이름, 이메일 , 성별 \n");
    System.out.println("============================");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %c\n", no[i], name[i], email[i], gender[i]);
    }
  }

}
