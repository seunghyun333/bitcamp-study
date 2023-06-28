package bitcamp.personalapp;

import bitcamp.personalapp.handler.BoardHandler;
import bitcamp.personalapp.handler.DiaryHandler;
import bitcamp.personalapp.handler.Handler;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[]args){
	   
	  Prompt prompt = new Prompt();
	  Handler diaryHandler = new DiaryHandler(prompt, "일기");
	  Handler boardHandler = new BoardHandler(prompt,"응원의 한 마디");
	  
      
    printTitle();
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("0")){
        break;
      } else if(menuNo.equals("menu")) {
        printMenu();
      } else if(menuNo.equals("1")){
    	  diaryHandler.excute();
      } else if(menuNo.equals("2")){
    	  boardHandler.excute();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다.");
      }
    }

    prompt.close();
  }

  static void printTitle() {
    System.out.println("나의 개발자 도전기");
    System.out.println("-------------------------");   
  }

  static void printMenu() {
    System.out.println("1. 일기 ");
    System.out.println("2. 응원의 한마디 ");
    System.out.println("0. 종료 ");
  }
  }
