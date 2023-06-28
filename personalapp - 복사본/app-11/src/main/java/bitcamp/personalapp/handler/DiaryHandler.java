package bitcamp.personalapp.handler;

import bitcamp.util.Prompt;
import bitcamp.personalapp.vo.Diary;

public class DiaryHandler {

    static final int MAX_SIZE = 100;
    static Diary[] diarys = new Diary[MAX_SIZE];
    static int turn = 1;
    static int length = 0;
  
    static final char DRINK = 'O';
    static final char NONCOFFEE = 'X';

    public static void inputContents() {
      if (!available()) {
        System.out.println("더 이상 입력할 수 없습니다!");
        return;
      }
      Diary d = new Diary();
      d.date = Prompt.inputString("날짜 ? ");
      d.weather = Prompt.inputString("날씨 ? ");
      d.title = Prompt.inputString("제목 ? ");
      d.contents = Prompt.inputString("내용 ? ");
      d.coffee = inputCoffee((char) 0);
      d.no = turn++;

      diarys[length++] = d;
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
      System.out.println("번호, 날짜, 날씨, 제목, 내용, 모닝커피");      
      System.out.println("--------------------------------------------");

      for (int i = 0; i < length; i++) {
        Diary d = diarys[i];
        System.out.printf("%d, %s, %s, %s, %s, %s\n", 
        d.no, d.date, d.weather, d.title, d.contents, toCoffeeString(d.coffee));
      }
    }  

    public static void viewDiary() {
      String diaryNo = Prompt.inputString("일기번호? ");
      for (int i = 0; i < length; i++) {
        Diary d = diarys[i];
        if (d.no == Integer.parseInt(diaryNo)) {
          System.out.printf("날짜 : %s \n", d.date);
          System.out.printf("날씨 : %s \n", d.weather);
          System.out.printf("제목 : %s \n", d.title);
          System.out.printf("내용 : %s \n", d.contents);
          System.out.printf("모닝커피 : %s \n", toCoffeeString(d.coffee));
          return;
        }
      }
      System.out.println("해당 번호의 일기가 없습니다.");
    }

    public static void updateDiary(){
      String diaryNo = Prompt.inputString("수정할 일기 번호? ");
      for (int i = 0; i < length; i++){
        Diary d = diarys[i];
        if (d.no == Integer.parseInt(diaryNo)) {
          System.out.printf("날짜(%s)? >", d.date);
          d.date = Prompt.inputString("");
          System.out.printf("날씨(%s)? >", d.weather);
          d.weather = Prompt.inputString("");
          System.out.printf("제목(%s)? >", d.title);
          d.title = Prompt.inputString("");
          System.out.printf("내용(%s)? >", d.contents);
          d.contents = Prompt.inputString("");
          d.coffee = inputCoffee(d.coffee);
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
        diarys[i] = diarys[i + 1];
      }

      diarys[--length] = null;
    }


    private static int indexOf(int diaryNo) {
      for (int i = 0; i < length; i++){
        Diary d = diarys[i];
        if (d.no == diaryNo){
          return i;
        }
      }
      return -1;
    }

    public static boolean available() {
      return length < MAX_SIZE;
  }
  }
