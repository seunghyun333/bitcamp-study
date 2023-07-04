package bitcamp.personalapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
import bitcamp.personalapp.handler.VisitListListener;
import bitcamp.personalapp.vo.AutoIncrement;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Diary;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;
 
public class App { 

	  LinkedList<Board> boardList = new LinkedList<>();
	  LinkedList<Diary> diaryList = new LinkedList<>();
	  LinkedList<Visit> visitList = new LinkedList<>();
	  
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
		  loadJson("diary.csv", diaryList, Diary.class);
		  loadJson("board.csv", boardList, Board.class);
		  //loadJson("visit.csv", visitList, Visit.class);
	  }

	  private void saveData() {
		  saveJson("diary.csv", diaryList);
		  saveJson("board.csv", boardList);
		  saveJson("visit.csv", visitList);
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
		
		Menu helloMenu = new Menu("방명록");
		helloMenu.addActionListener(new HeaderListener());
		helloMenu.addActionListener(new HelloListener());
		helloMenu.addActionListener(new FooterListener());
		mainMenu.add(helloMenu);
		
		MenuGroup manageMenu = new MenuGroup("방문자 관리");
		manageMenu.add(new Menu("방문자", new VisitListListener(visitList)));
		mainMenu.add(manageMenu);
		
		
	  }


	private <T> void loadJson(String filename, List<T> list, Class<T> clazz) {
		try {			
			FileReader in0 = new FileReader(filename);
			BufferedReader in = new BufferedReader(in0);
			
			StringBuilder strBuilder = new StringBuilder();
			String line = null;

			while ((line = in.readLine()) != null) {
				strBuilder.append(line);
			}  
			
			in.close();
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Collection<T> objects = gson.fromJson(strBuilder.toString(),
					TypeToken.getParameterized(Collection.class, clazz).getType());
			
			list.addAll(objects);
			
			Class<?>[] interfaces = clazz.getInterfaces();
			for (Class<?> info : interfaces) {
				if (info == AutoIncrement.class) {
					AutoIncrement autoIncrement = (AutoIncrement) list.get(list.size()-1);
					autoIncrement.updateKey();
					break;
				}
			}

		} catch (Exception e) {
			System.out.println(filename + " 파일을 읽는 중 오류 발생!");
		}
	  } 


  private void saveJson(String filename, List<?> list) {
	try {
		FileWriter out0 = new FileWriter(filename);
		BufferedWriter out = new BufferedWriter(out0);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
		out.write(gson.toJson(list));
		out.close();

		}  catch (Exception e)	{
			System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
		}
  	  }

	}
