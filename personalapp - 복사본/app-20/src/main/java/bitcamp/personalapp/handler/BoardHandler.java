package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Board;
import bitcamp.util.LinkedList;
import bitcamp.util.Prompt;

public class BoardHandler implements Handler{
	
  private LinkedList list = new LinkedList();
  private Prompt prompt; 
  private String title;

	public BoardHandler(Prompt prompt, String title) {
		this.prompt = prompt;
		this.title = title;
	}

  public void excute() {
    printMenu();
    
    while (true) {
      String menuNo = prompt.inputString("%s> ", this.title);
      if (menuNo.equals("0")){
        return;
      } else if(menuNo.equals("menu")) {
        printMenu();
      } else if(menuNo.equals("1")){
    	  this.inputBoard();
      } else if(menuNo.equals("2")){
    	  this.prinBoards();
      } else if(menuNo.equals("3")){
    	  this.viewBoard();
      } else if(menuNo.equals("4")){
    	  this.updateBoard();
      } else if(menuNo.equals("5")){
    	  this.deleteBoard();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다.");
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 응원 등록");
    System.out.println("2. 응원 목록");
    System.out.println("3. 응원 조회");
    System.out.println("4. 응원 변경");
    System.out.println("5. 응원 삭제");
    System.out.println("0. 메인");
  }    
	
	public void inputBoard() {
		
		Board board = new Board();
		board.setTitle(this.prompt.inputString("제목? "));
		board.setContent(this.prompt.inputString("내용? "));
		board.setWriter(this.prompt.inputString("작성자? "));
		board.setPassword(this.prompt.inputString("암호? "));
		
		this.list.add(board);	
	}
	
	public void prinBoards() {
	    System.out.println("============================");
	    System.out.printf("번호, 제목, 작성자 , 조회수, 등록일 \n");
	    System.out.println("============================");
	    
	    Object[] arr = this.list.getList();
	    for (Object obj : arr) {
	    	//obj변수가 arr(보드 배열)을 도는 동안 
	    	Board board = (Board) obj;
	      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", 
	      		  board.getNo(), board.getTitle(), board.getWriter(), board.getViewCount(), board.getCreatedDate());
	    }
	}
	
	public void viewBoard() {
		int boardNo = this.prompt.inputInt("게시글 번호?");
		
		Board board = (Board) this.list.retrieve(new Board(boardNo));
		if (board == null) {
			System.out.println("해당 번호의 게시글이 없습니다!");
			return;
		}
		 System.out.printf("제목 : %s \n", board.getTitle());
		 System.out.printf("내용 : %s \n", board.getContent());
		 System.out.printf("작성자 : %s \n", board.getWriter());
	     System.out.printf("조회수 : %s \n", board.getViewCount());
	     System.out.printf("등록일 : %tY-%1$tm-%1$td\n", board.getCreatedDate());
		 board.setViewCount(board.getViewCount()+1); 
			}

	
	public void updateBoard() {
		int boardNo = this.prompt.inputInt("수정할 번호?");
		
		Board board = (Board) this.list.retrieve(new Board(boardNo));
		if (board == null) {
			System.out.println("해당 번호의 게시글이 없습니다!");
			return;
		}
			board.setTitle(this.prompt.inputString("제목(%s)? >", board.getTitle()));
			board.setContent(this.prompt.inputString("내용(%s)? >", board.getContent()));
	    }

	
	public void deleteBoard() {
		if(!this.list.remove(new Board(this.prompt.inputInt("삭제할 번호?")))) {
		System.out.println("무효한 번호입니다.");
		}
	}
	}



