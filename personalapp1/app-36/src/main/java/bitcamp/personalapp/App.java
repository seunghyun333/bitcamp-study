package bitcamp.personalapp;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.dao.BoardListDao;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.dao.DiaryListDao;
import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.dao.VisitListDao;
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
import bitcamp.personalapp.handler.VisitAddListener;
import bitcamp.personalapp.handler.VisitListListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;
 
public class App { 

	  DiaryDao diaryDao = new DiaryListDao("diary.json");
	  BoardDao boardDao = new BoardListDao("board.json");
	  VisitDao visitDao = new VisitListDao("visit.json");
	  
	  BreadcrumbPrompt prompt = new BreadcrumbPrompt();
	  
	  MenuGroup mainMenu = new MenuGroup("메인");

	  public App() {
		prepareMenu();
	  } 

	  public static void main(String[] args) {
		new App().execute();
	  }
	  
	  static void printTitle() {
		System.out.println("나의 개발자 도전기");
		System.out.println("-------------------------");   
	  }
	  
	  public void execute()  {
		printTitle();
		mainMenu.execute(prompt);
		prompt.close();
	  }

//	  private void loadData() {
//		  loadJson("diary.csv", diaryList, Diary.class);
//		  loadJson("board.csv", boardList, Board.class);
//		  loadJson("visit.csv", visitList, Visit.class);
//	  }
//
//	  private void saveData() {
//		  saveJson("diary.csv", diaryList);
//		  saveJson("board.csv", boardList);
//		  saveJson("visit.csv", visitList);
//	  }

	  private void prepareMenu() {
		MenuGroup diaryMenu = new MenuGroup("오늘의 일기");
		diaryMenu.add(new Menu("등록", new DiaryAddListener(diaryDao)));
		diaryMenu.add(new Menu("목록", new DiaryListListener(diaryDao)));
		diaryMenu.add(new Menu("조회", new DiaryDetailListener(diaryDao)));
		diaryMenu.add(new Menu("변경", new DiaryUpdateListener(diaryDao)));
		diaryMenu.add(new Menu("삭제", new DiaryDeleteListener(diaryDao)));
		mainMenu.add(diaryMenu); 

		MenuGroup boardMenu = new MenuGroup("응원의 한마디");
		boardMenu.add(new Menu("등록", new BoardAddListener(boardDao)));
		boardMenu.add(new Menu("목록", new BoardListListener(boardDao)));
		boardMenu.add(new Menu("조회", new BoardDetailListener(boardDao)));
		boardMenu.add(new Menu("변경", new BoardUpdateListener(boardDao)));
		boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardDao)));
		mainMenu.add(boardMenu);
		
//		Menu helloMenu = new Menu("방명록");
//		helloMenu.addActionListener(new HeaderListener());
//		helloMenu.addActionListener(new HelloListener());
//		helloMenu.addActionListener(new FooterListener());
//		mainMenu.add(helloMenu);
		
		MenuGroup manageMenu = new MenuGroup("방명록");
		manageMenu.add(new Menu("이름을 적어주세요", new VisitAddListener(visitDao)));
		manageMenu.add(new Menu("방문자", new VisitListListener(visitDao)));
		mainMenu.add(manageMenu);
		

		
	  }
	}
