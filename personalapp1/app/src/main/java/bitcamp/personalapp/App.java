package bitcamp.personalapp;

import java.util.LinkedList;
import java.util.List;

import bitcamp.io.DataInputStream;
import bitcamp.io.DataOutputStream;
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
		loadDiary();
		loadBoard("board.data", boardList);
	  }

	  private void saveData() {
		saveDiary();
		saveBoard("board.data", boardList);
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

	  private void loadDiary() {
		try {
			DataInputStream in = new DataInputStream("diary.data");
			int size = in.readShort();

			for (int i = 0; i < size; i++) {
				Diary diary = new Diary();
				diary.setNo(in.readInt());
				diary.setDate(in.readUTF());
				diary.setTitle(in.readUTF());
				diary.setWeather(in.readUTF());
				diary.setContents(in.readUTF());
				diary.setCoffee(in.readChar());
				diaryList.add(diary);
			}

			Diary.turn = diaryList.get(diaryList.size() -1).getNo() +1;

			in.close();

		} catch (Exception e) {
			System.out.println("다이어리 정보를 읽는 중 오류 발생!");
		}
	  }

	  private void loadBoard(String filename, List<Board> list) {
    	try {
      DataInputStream in = new DataInputStream(filename);
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.readInt());
        board.setTitle(in.readUTF());
		board.setContent(in.readUTF());
		board.setWriter(in.readUTF());
		board.setPassword(in.readUTF());
		board.setViewCount(in.readInt());
        board.setCreatedDate(in.readLong());
        list.add(board);
      }

      Board.boardNo = Math.max(
          Board.boardNo,
          list.get(list.size() - 1).getNo() + 1);

      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void saveDiary() {
	try {
		DataOutputStream out = new DataOutputStream("diary.data");

		out.writeShort(diaryList.size());

		for (Diary diary : diaryList){
			out.writeInt(diary.getNo());
			out.writeUTF(diary.getDate());
			out.writeUTF(diary.getTitle());
			out.writeUTF(diary.getWeather());
			out.writeUTF(diary.getContents());
			out.writeChar(diary.getCoffee());
			}
			out.close();

		}  catch (Exception e)	{
			System.out.println("다이어리 정보를 저장하는 중 오류 발생!");
		}
  	  }
	 
 private void saveBoard(String filename, List<Board> list) {
    try {
      DataOutputStream out = new DataOutputStream(filename);

      out.writeShort(list.size());

      for (Board board : list) {
        out.writeInt(board.getNo());
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getContent());
        out.writeUTF(board.getWriter());
        out.writeUTF(board.getPassword());
        out.writeInt(board.getViewCount());
        out.writeLong(board.getCreatedDate());

      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }	  

  }
