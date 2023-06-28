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
    date[length] = Prompt.inputString("날짜 ?");
    title[length] = Prompt.inputString("날씨 ?");
    contents[length] = Prompt.inputString("내용 ?");
      
    loop: while (true) {
        String select = Prompt.inputString("모닝커피:\n"+
        " 1. 마심\n"+
        " 2. 안 마심\n"+
        "> ");
      
        switch (select) {
            case "1":
                coffee[length] = DRINK;
                break loop;
            case "2":
                coffee[length] = NONCOFFEE;
                break loop;
            default:
                System.out.println("1, 2 中 선택");
            }
          }
          
            no[length] = turn++;
            length++;
        }
    public static void printTotal(){
      System.out.println("---------------------------------------");
      System.out.println("번호, 날짜, 날씨, 제목, 내용, 모닝커피 유무");      
      System.out.println("---------------------------------------");

      for (int i = 0; i < length; i++) {
        System.out.printf("%d, %s, %s, %s, %s, %c\n", no[i], date[i], weather[i], title[i], contents[i], coffee[i]);
      }
    }  

    public static boolean available() {
        return length < MAX_SIZE;
    }
}
