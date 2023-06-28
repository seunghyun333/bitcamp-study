package bitcamp.personalapp;

import bitcamp.util.Prompt;
import bitcamp.personalapp.handler.BoardHandler;
import bitcamp.personalapp.handler.DiaryHandler;

public class App {

  public static void main(String[]args){
	  
	  Prompt prompt = new Prompt();
	  DiaryHandler diaryHandler = new DiaryHandler(prompt);
	  BoardHandler boardHandler = new BoardHandler(prompt);
	  
    
    printTitle();
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("99")){
        break;
      } else if(menuNo.equals("menu")) {
        printMenu();
      } else if(menuNo.equals("1")){
    	  diaryHandler.inputContents();
      } else if(menuNo.equals("2")){
    	  diaryHandler.printDiary();
      } else if(menuNo.equals("3")){
    	  diaryHandler.viewDiary();
      } else if(menuNo.equals("4")){
    	  diaryHandler.updateDiary();
      } else if(menuNo.equals("5")){
    	  diaryHandler.deleteDiary();
      }  else if (menuNo.equals("6")) {
    	boardHandler.inputBoard();
      } else if (menuNo.equals("7")) {
    	boardHandler.prinBoards();
      } else if (menuNo.equals("8")) {
    	boardHandler.viewBoard();
      } else if (menuNo.equals("9")) {
    	boardHandler.updateBoard();
      } else if (menuNo.equals("10")) {
    	boardHandler.deleteBoard();
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
    System.out.println("1. 일기 등록 ");
    System.out.println("2. 일기 목록 ");
    System.out.println("3. 일기 조회 ");
    System.out.println("4. 일기 변경 ");
    System.out.println("5. 일기 삭제 ");
    System.out.println("6. 게시글 등록 ");
    System.out.println("7. 게시글 목록 ");
    System.out.println("8. 게시글 조회 ");
    System.out.println("9. 게시글 변경 ");
    System.out.println("10. 게시글 삭제 ");
    System.out.println("99. 종료 ");
  }

  }
