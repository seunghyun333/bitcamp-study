package bitcamp.personalapp.handler;

import bitcamp.util.Prompt;

public class DiaryHandler {

    static final int MAX_SIZE = 100;
    static int[] no = new int[MAX_SIZE];
    static String[] date = new String[MAX_SIZE];
    static String[] title = new String[MAX_SIZE];
    static String[] weather = new String[MAX_SIZE];
    static String[] contents = new String[MAX_SIZE];
    static char[] coffee = new char[MAX_SIZE];
    static int turn = 1;
    static int length = 0;
  
    static final char DRINK = 'O';
    static final char NONCOFFEE = 'X';

    public static void inputContents() {
      if (!available()) {
        System.out.println("더 이상 입력할 수 없습니다!");
        return;
      }

      date[length] = Prompt.inputString("날짜 ?");
      weather[length] = Prompt.inputString("날씨 ?");
      title[length] = Prompt.inputString("제목 ?");
      contents[length] = Prompt.inputString("내용 ?");
      coffee[length] = inputCoffee((char) 0);

      no[length] = turn++;
      length++;
    }

    public static String toCoffeeString(char coffee) {
      return coffee == 'O' ? "마심" : "안 마심";
    }

    private static char inputCoffee(char coffee) {
      String label;
      if (coffee == 0) {
        label = "모닝커피? \n";
      } else {
        label = String.format("모닝커피(%s)?\n", toCoffeeString(coffee));
      }

      loop: while (true) {
        String select = Prompt.inputString(label +
        " 1. 마심\n"+
        " 2. 안 마심\n"+
        "> ");
      
        switch (select) {
            case "1":
                return DRINK;
            case "2":
                return NONCOFFEE;
            default:
                System.out.println("1, 2 中 선택");
            }
          }
    }
    public static void printDiary(){
      System.out.println("--------------------------------------------");
      System.out.println("번호, 날짜, 날씨, 제목, 내용, 모닝커피 유무");      
      System.out.println("--------------------------------------------");

      for (int i = 0; i < length; i++) {
        System.out.printf("%d, %s, %s, %s, %s, %c\n", no[i], date[i], weather[i], title[i], contents[i], coffee[i]);
      }
    }  
    public static void viewDiary() {
      String diaryNo = Prompt.inputString("일기번호? ");
      for (int i = 0; i < length; i++) {
        if (no[i] == Integer.parseInt(diaryNo)) {
          System.out.printf("날짜 : %s \n", date[i]);
          System.out.printf("날씨 : %s \n", weather[i]);
          System.out.printf("제목 : %s \n", title[i]);
          System.out.printf("내용 : %s \n", contents[i]);
          System.out.printf("모닝커피 : %s \n", toCoffeeString(coffee[i]));
          return;
        }
      }
      System.out.println("해당 번호의 일기가 없습니다.");
    }

    public static void updateDiary(){
      String diaryNo = Prompt.inputString("수정할 일기 번호? ");
      for (int i = 0; i < length; i++){
        if (no[i] == Integer.parseInt(diaryNo)) {
          System.out.printf("날짜(%s)? >", date[i]);
          date[i] = Prompt.inputString("");
          System.out.printf("날씨(%s)? >", weather[i]);
          weather[i] = Prompt.inputString("");
          System.out.printf("제목(%s)? >", title[i]);
          title[i] = Prompt.inputString("");
          System.out.printf("내용(%s)? >", contents[i]);
          contents[i] = Prompt.inputString("");
          coffee[i] = inputCoffee(coffee[i]);
          return;
        }
      }
      System.out.println("해당 번호의 일기가 없습니다. ");
    }

    public static void deleteDiary(){
      int diaryNo = Prompt.inputInt("삭제할 번호?") ;

      int deletedDiary = indexOf(diaryNo);
      if (deletedDiary == -1) {
        System.out.println("무효한 번호입니다.");
        return;
      }

      for (int i = deletedDiary; i < length -1; i++) {
        no[i] = no[i + 1];
        date[i] = date[i + 1];
        weather[i] = weather[i + 1];
        title[i] = title[i + 1];
        contents[i] = contents[i + 1];
        coffee[i] = coffee[i + 1];
      }

      no[length -1] = 0;
      date[length -1] = null;
      weather[length -1] = null;
      title[length -1] = null;
      contents[length -1] = null;
      coffee[length -1] = (char) 0;

      length--;
    }


    private static int indexOf(int diaryNo) {
      for (int i = 0; i < length; i++){
        if (no[i] == diaryNo){
          return i;
        }
      }
      return -1;
    }

    public static boolean available() {
      return length < MAX_SIZE;
  }
  }
