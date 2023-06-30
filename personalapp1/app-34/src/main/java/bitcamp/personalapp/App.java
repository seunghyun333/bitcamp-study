package bitcamp.personalapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

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
import bitcamp.personalapp.vo.CsvObject;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;
 
public class App { 

	  LinkedList<Board> boardList = new LinkedList<>();
	  LinkedList<Diary> diaryList = new LinkedList<>();
	  
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

		loadData();
		mainMenu.execute(prompt);
		saveData();

		prompt.close();
	  }

	  private void loadData() {
		  loadCsv("diary.csv", diaryList, Diary.class);
		  loadCsv("board.csv", boardList, Board.class);
	  }

	  private void saveData() {
		  saveCsv("diary.csv", diaryList);
		  saveCsv("board.csv", boardList);
	  }

	  private void prepareMenu() {
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
	  }

	  @SuppressWarnings("unchecked")
	private <T extends CsvObject> void loadCsv(String filename, List<T> list, Class<T> clazz) {
		try {
			Method factoryMethod = clazz.getDeclaredMethod("fromCsv", String.class);
			
			FileReader in0 = new FileReader(filename);
			BufferedReader in = new BufferedReader(in0);
			
			String line = null;

			while ((line = in.readLine()) != null) {
				list.add((T)factoryMethod.invoke(null, line));
				//list.add(Diary.fromCsv(line));
			} 
			
			in.close();

		} catch (Exception e) {
			System.out.println(filename + " 파일을 읽는 중 오류 발생!");
		}
	  } 


  private void saveCsv(String filename, List<? extends CsvObject> list) {
	try {
		FileWriter out0 = new FileWriter(filename);
		BufferedWriter out1 = new BufferedWriter(out0);
		PrintWriter out = new PrintWriter(out1);

		for (CsvObject obj : list){
			out.println(obj.toCsvString());
			}
			out.close();

		}  catch (Exception e)	{
			System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
		}
  	  }

	}
