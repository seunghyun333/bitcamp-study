package bitcamp.personalapp;

import java.util.Scanner;

public class App {
  public static void main(String[]args){
    System.out.println("나의 개발자 도전기");
    System.out.println("---------------------");

    Scanner scanner = new Scanner(System.in);

    System.out.print("날짜 ");
    String date = scanner.next();

    System.out.print("제목 ");
    String title = scanner.next();

    System.out.print("날씨 ");
    String weather = scanner.next();

    System.out.print("내용 ");
    String contents = scanner.next();
    
    System.out.print("모닝커피 유무(마심(1), 안 마심(2)) ");
    String str = scanner.next();
    char coffee = str.charAt(0);


    System.out.println("---------------------------------------");

    System.out.printf("날짜: %s\n", date);
    System.out.printf("제목: %s\n", title);
    System.out.printf("날씨: %s\n", weather);
    System.out.printf("내용: %s\n", contents);
    System.out.printf("모닝커피 유무(마심(1), 안 마심(2)): %c\n", coffee);

    scanner.close();
   
  }
}