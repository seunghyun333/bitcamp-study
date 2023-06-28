package bitcamp.personalapp;

import java.util.Scanner;

public class App {
  public static void main(String[]args){
    System.out.println("나의 개발자 도전기");
    System.out.println("-------------------------");

    Scanner scanner = new Scanner(System.in);

    final int SIZE = 100;

    String[] date = new String[SIZE];
    String[] title = new String[SIZE];
    String[] weather = new String[SIZE];
    String[] contents = new String[SIZE];
    char[] coffee = new char[SIZE];



    for (int i = 0; i < SIZE; i++) {
    System.out.print("날짜? ");
    date[i] = scanner.next();

    System.out.print("제목? ");
    title[i] = scanner.next();

    System.out.print("날씨? ");
    weather[i] = scanner.next();

    System.out.print("내용? ");
    contents[i] = scanner.next();
    
    System.out.print("모닝커피 유무(마심(1), 안 마심(2))? ");
    String str = scanner.next();
    coffee[i] = str.charAt(0);
    }

    System.out.println("---------------------------------------");

    for (int i = 0; i < SIZE; i++) {
    System.out.printf("번호: %s\n", date[i]);
    System.out.printf("제목: %s\n", title[i]);
    System.out.printf("날씨: %s\n", weather[i]);
    System.out.printf("내용: %s\n", contents[i]);
    System.out.printf("모닝커피 유무(마심(1), 안 마심(2)): %c\n", coffee[i]);
    }

    scanner.close();
   
  }
}