package bitcamp.personalapp;

import java.util.Scanner;

public class App {
  public static void main(String[]args){
    System.out.println("나의 개발자 도전기");
    System.out.println("-------------------------");

    Scanner scanner = new Scanner(System.in);

    final int MAX_SIZE = 100;
    int turn = 1;
    int length = 0;

    int[] no = new int[MAX_SIZE];
    String[] date = new String[MAX_SIZE];
    String[] title = new String[MAX_SIZE];
    String[] weather = new String[MAX_SIZE];
    String[] contents = new String[MAX_SIZE];
    char[] coffee = new char[MAX_SIZE];


    //일기 등록
    for (int i = 0; i < MAX_SIZE; i++) {

    System.out.print("날짜? ");
    date[i] = scanner.next();

    System.out.print("제목? ");
    title[i] = scanner.next();

    System.out.print("날씨? ");
    weather[i] = scanner.next();

    System.out.print("내용? ");
    contents[i] = scanner.next();

    loop: while (true) {
      System.out.println("모닝커피 유무: ");
      System.out.println(" 1. 마심");
      System.out.println(" 2. 안 마심");
      System.out.print("> ");
      String select = scanner.next();

      switch (select) {
        case "1":
          coffee[i] = 'O';
          break loop;
        case "2":
          coffee[i] = 'X';
          break loop;
        default:
          System.out.println("1, 2 中 선택");
      }
    }
    
      no[i] = turn++;

      length++;

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      scanner.nextLine();
      String response = scanner.nextLine();
      if (!response.equals("") && !response.equalsIgnoreCase("Y")){
        break;
      }
    }

    System.out.println("---------------------------------------");
    System.out.println("번호, 날짜, 날씨, 제목, 내용, 모닝커피 유무");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %s, %s, %c\n", no[i], date[i], weather[i], title[i], contents[i], coffee[i]);
    }
    scanner.close();
   
  }
}