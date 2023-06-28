package bitcamp.personalapp;

import java.util.LinkedList;

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
		new App().execute;
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
			FileInputStream in = new FileInputStream("diary.data");
			int size = in.read() << 8;
			size |= in.read();

			byte[] buf = new byte[1000];

			for (int i = 0; i < size; i++) {
				Diary diary = new Diary();
				diary.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

				int length = in.read() << 8 | in.read();
				in.read(buf, 0, length);
				diary.setDate(new String(buf, 0, length, "UTF-8"));

				int length = in.read() << 8 | in.read();
				in.read(buf, 0, length);
				diary.setTitle(new String(buf, 0, length, "UTF-8"));

				int length = in.read() << 8 | in.read();
				in.read(buf, 0, length);
				diary.setWeather(new String(buf, 0, length, "UTF-8"));

				int length = in.read() << 8 | in.read();
				in.read(buf, 0, length);
				diary.setContents(new String(buf, 0, length, "UTF-8"));

				diary.setCoffee((char)(in.read() << 8 | in.read()));

				diaryList.add(diary);
			}

			Diary.turn = diaryList.get(diaryList.size() -1).getNo() +1;

			in.close();
		} catcth (Exception e) {
			System.out.println("회원 정보를 읽는 중 오류 발생!");
		}
	  }

	  private void loadBoard(String filename, List<Board> list) {
    	try {
      FileInputStream in = new FileInputStream(filename);
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setTitle(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setContent(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setWriter(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setPassword(new String(buf, 0, length, "UTF-8"));

        board.setViewCount(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        board.setCreatedDate(
            (long)in.read() << 56
            | (long)in.read() << 48
            | (long)in.read() << 40
            | (long)in.read() << 32
            | (long)in.read() << 24
            | (long)in.read() << 16
            | (long)in.read() << 8
            | in.read());

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
		FileOutputStream out = new FileOutputStream("diary.data");

		int size = diaryList.size();
		out.write(size >> 8);
		out.write(size);

		for (Diary diary : diaryList){
			int no = diary.getNo();
			out.write(no >> 24);
			out.write(no >> 16);
			out.write(no >> 8);
			out.write(no);

			byte[] bytes = diary.getDate().getBytes("UTF-8");
			out.write(bytes.length >> 8);
			out.write(bytes.length);
			out.write(bytes);

			bytes = diary.getTitle().getBytes("UTF-8");
			out.write(bytes.length >> 8);
			out.write(bytes.length);
			out.write(bytes);

			bytes = diary.getWeather().getBytes("UTF-8");
			out.write(bytes.length >> 8);
			out.write(bytes.length);
			out.write(bytes);

			bytes = diary.getContents().getBytes("UTF-8");
			out.write(bytes.length >> 8);
			out.write(bytes.length);
			out.write(bytes);

			char coffee = diary.getCoffee();
			out.write(coffee >> 8);
			out.write(coffee);
			}
			out.close();
		}  catch (Exception e)	{
			System.out.println("회원 정보를 저장하는 중 오류 발생!");
		}
  	  }
	 
 private void saveBoard(String filename, List<Board> list) {
    try {
      FileOutputStream out = new FileOutputStream(filename);

      // 저장할 데이터의 개수를 먼저 출력한다.
      int size = list.size();
      out.write(size >> 8);
      out.write(size);

      for (Board board : list) {
        int no = board.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = board.getTitle().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);


        bytes = board.getContent().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = board.getWriter().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = board.getPassword().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        int viewCount = board.getViewCount();
        out.write(viewCount >> 24);
        out.write(viewCount >> 16);
        out.write(viewCount >> 8);
        out.write(viewCount);

        long createdDate = board.getCreatedDate();
        out.write((int)(createdDate >> 56));
        out.write((int)(createdDate >> 48));
        out.write((int)(createdDate >> 40));
        out.write((int)(createdDate >> 32));
        out.write((int)(createdDate >> 24));
        out.write((int)(createdDate >> 16));
        out.write((int)(createdDate >> 8));
        out.write((int)createdDate);
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }	  

  }
