package bitcamp.personalapp;

import bitcamp.personalapp.handler.BoardAddListener;
import bitcamp.personalapp.handler.BoardDeleteListener;
import bitcamp.personalapp.handler.BoardDetailListener;
import bitcamp.personalapp.handler.BoardListListener;
import bitcamp.personalapp.handler.BoardUpdateListener;
import bitcamp.personalapp.handler.DiaryAddListener;
import bitcamp.personalapp.handler.DiaryDeleteListener;
import bitcamp.personalapp.handler.DiaryDetailListener;
import bitcamp.personalapp.handler.DiaryListListener;
import bitcamp.personalapp.handler.DiaryUpdateListener;
import bitcamp.personalapp.handler.FooterListener;
import bitcamp.personalapp.handler.HeaderListener;
import bitcamp.personalapp.handler.HelloListener;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.LinkedList;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;
 
public class App {

  public static void main(String[]args){
	   
	  LinkedList<Board> boardList = new LinkedList<>();
	  LinkedList<Diary> diaryList = new LinkedList<>();
	  
	  BreadcrumbPrompt prompt = new BreadcrumbPrompt();
	  
	  MenuGroup mainMenu = new MenuGroup("메인");
	 
	  MenuGroup diaryMenu = new MenuGroup("오늘의 일기");
	  diaryMenu.add(new Menu("등록", new DiaryAddListener(diaryList)));
	  diaryMenu.add(new Menu("목록", new DiaryListListener(diaryList)));
	  diaryMenu.add(new Menu("조회", new DiaryDetailListener(diaryList)));
	  diaryMenu.add(new Menu("변경", new DiaryUpdateListener(diaryList)));
	  diaryMenu.add(new Menu("삭제", new DiaryDeleteListener(diaryList)));
	  mainMenu.add(diaryMenu);
	  
	  
	  MenuGroup boardMenu = new MenuGroup("응원의 한마디");
	  boardMenu.add(new Menu("등록", new BoardAddListener(boardList)));
	  boardMenu.add(new Menu("목록", new BoardListListener(boardList)));
	  boardMenu.add(new Menu("조회", new BoardDetailListener(boardList)));
	  boardMenu.add(new Menu("변경", new BoardUpdateListener(boardList)));
	  boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardList)));
	  mainMenu.add(boardMenu);
	  
	  Menu helloMenu = new Menu("안녕!");
	  helloMenu.addActionListener(new HeaderListener());
	  helloMenu.addActionListener(new HelloListener());
	  helloMenu.addActionListener(new FooterListener());
	  mainMenu.add(helloMenu);
	  
	  
	  
      
    printTitle();
    mainMenu.execute(prompt);

    prompt.close();
  }

  static void printTitle() {
    System.out.println("나의 개발자 도전기");
    System.out.println("-------------------------");   
  }
}