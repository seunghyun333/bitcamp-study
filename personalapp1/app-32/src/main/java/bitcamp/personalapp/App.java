package bitcamp.personalapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		loadDiary("diary.data2", diaryList);
		loadBoard("board.data2", boardList);
	  }

	  private void saveData() {
		saveDiary("diary.data2", diaryList);
		saveBoard("board.data2", boardList);
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

	  private void loadDiary(String filename, List<Diary> list) {
		try {
			FileInputStream in0 = new FileInputStream("diary.data");
			BufferedInputStream in1 = new BufferedInputStream(in0);
			ObjectInputStream in = new ObjectInputStream(in1);

			int size = in.readShort();

			for (int i = 0; i < size; i++) {
				list.add((Diary) in.readObject());
			} 
			
			if (list.size() > 0) {
			Diary.turn = diaryList.get(diaryList.size() -1).getNo() +1;
			}
			
			in.close();

		} catch (Exception e) {
			System.out.println(filename + " 파일을 읽는 중 오류 발생!");
		}
	  } 

	  private void loadBoard(String filename, List<Board> list) {
    	try {
      		FileInputStream in0 = new FileInputStream(filename);
			BufferedInputStream in1 = new BufferedInputStream(in0);
			ObjectInputStream in = new ObjectInputStream(in1);

     	 int size = in.readShort();

        for (int i = 0; i < size; i++) {
        list.add((Board) in.readObject());
        }
      
      if(list.size() > 0) {
      Board.boardNo = Math.max(
          Board.boardNo,
          list.get(list.size() - 1).getNo() + 1);
      }
      
      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void saveDiary(String filename, List<Diary> list) {
	try {
		FileOutputStream out0 = new FileOutputStream(filename);
		BufferedOutputStream out1 = new BufferedOutputStream(out0);
		ObjectOutputStream out = new ObjectOutputStream(out1);

		out.writeShort(list.size());

		for (Diary diary : list){
			out.writeObject(diary);
			}
		
			out.close();

		}  catch (Exception e)	{
			System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
		}
  	  }
	 
  private void saveBoard(String filename, List<Board> list) {
	    try {
	      FileOutputStream out0 = new FileOutputStream(filename);
	      BufferedOutputStream out1 = new BufferedOutputStream(out0); // <== Decorator(장식품) 역할 수행!
	      ObjectOutputStream out = new ObjectOutputStream(out1); // <== Decorator(장식품) 역할 수행!

	      out.writeShort(list.size());

	      for (Board board : list) {
	        out.writeObject(board);
	      }
	      out.close();

	    } catch (Exception e) {
	      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
	    }
	  }
	}
