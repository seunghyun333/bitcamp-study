package bitcamp.personalapp;

import bitcamp.personalapp.handler.BoardHandler;
import bitcamp.personalapp.handler.DiaryHandler;
import bitcamp.personalapp.handler.Handler;
import bitcamp.util.ArrayList;
import bitcamp.util.LinkedList;
import bitcamp.util.MenuPrompt;
 
public class App {

  public static void main(String[]args){
	   
	  MenuPrompt prompt = new MenuPrompt();
	  prompt.appendBreadcrumb("메인", getMenu());
	  
	  Handler diaryHandler = new DiaryHandler(prompt, "일기", new ArrayList());
	  Handler boardHandler = new BoardHandler(prompt,"응원의 한 마디", new LinkedList());
	  
      
    printTitle();
    prompt.printMenu();

    loop: while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
      case "0" : break loop;
      case "1" : diaryHandler.excute(); break;
      case "2" : boardHandler.excute(); break;
      }
    }
    prompt.close();
  }

  static void printTitle() {
    System.out.println("나의 개발자 도전기");
    System.out.println("-------------------------");   
  }

  static String getMenu() {
	  StringBuilder menu = new StringBuilder();
    menu.append("1. 일기\n");
    menu.append("2. 응원의 한마디\n");
    menu.append("0. 종료\n");
    return menu.toString();
  }
  }
