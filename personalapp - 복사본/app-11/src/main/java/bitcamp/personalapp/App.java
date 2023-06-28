package bitcamp.personalapp;

import bitcamp.util.Prompt;
import bitcamp.personalapp.handler.DiaryHandler;

public class App {

  public static void main(String[]args){
    
    printTitle();
    printMenu();

    while (true) {
      String menuNo = Prompt.inputString("메인> ");
      if (menuNo.equals("6")){
        break;
      } else if(menuNo.equals("menu")) {
        printMenu();
      } else if(menuNo.equals("1")){
        DiaryHandler.inputContents();
      } else if(menuNo.equals("2")){
        DiaryHandler.printDiary();
      } else if(menuNo.equals("3")){
        DiaryHandler.viewDiary();
      } else if(menuNo.equals("4")){
        DiaryHandler.updateDiary();
      } else if(menuNo.equals("5")){
          DiaryHandler.deleteDiary();
      } else {
        System.out.println(menuNo);
      }
    }


    Prompt.close();
  }

  static void printTitle() {
    System.out.println("나의 개발자 도전기");
    System.out.println("-------------------------");   
  }

  static void printMenu() {
    System.out.println("1. 일기 등록 ");
    System.out.println("2. 일기 목록 ");
    System.out.println("3. 일기 조회 ");
    System.out.println("4. 일기 변경 ");
    System.out.println("5. 일기 삭제 ");
    System.out.println("6. 종료 ");
  }

 
  static boolean promptContinue() {
    String response = Prompt.inputString("계속 입력하시겠습니까?(Y/n) ");
    if (!response.equals("") && !response.equalsIgnoreCase("Y")){
        return false;
      }
    return true;
    }
  }
