package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

import java.util.Date;

public class BoardHandler {

  static final int MAX_SIZE = 100;
  static Board[] boards = new Board[MAX_SIZE];
  static int length = 0;
  static int boardNo = 1;


  public static void inputBoard() {
    if (!available()) {
      System.out.println("더 이상 입력할 수 없습니다!");
      return;
    }
    Board board = new Board();
    board.setTitle(Prompt.inputString("제목? "));
    board.setContent(Prompt.inputString("내용? "));
    board.setWriter(Prompt.inputString("작성자? "));
    board.setPassword(Prompt.inputString("암호? "));
 
    boards[length++] = board; 
  }

  public static void printBoards() {
    System.out.println("============================");
    System.out.printf("번호, 제목, 작성자 , 조회수, 등록일 \n");
    System.out.println("============================");
    
    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      
      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", 
    		  board.getNo(), board.getTitle(), board.getWriter(), board.getViewCount(), board.getCreateDate());
    }
  }

  public static void viewBoard() {
    String boardNo = Prompt.inputString("게시글 번호? ");
    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      if (board.getNo() == Integer.parseInt(boardNo)) {
        System.out.printf("제목 : %s \n", board.getTitle());
        System.out.printf("작성자 : %s \n", board.getWriter());
        System.out.printf("내용 : %s \n", board.getContent());
        System.out.printf("조회수 : %s \n", board.getViewCount());
        System.out.printf("등록일 : %tY-%1$tm-%1$td\n", board.getCreateDate());
        System.out.printf("내용 : %s \n", board.getContent());
        return;
      }
    }
    System.out.println("해당 번호의 게시글이 없습니다.");
  }

  public static void updateBoard() {
    String boardNo = Prompt.inputString("수정할 번호? ");
    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      if (board.getNo() == Integer.parseInt(boardNo)) {
        System.out.printf("제목(%s)? >", board.getTitle());
        board.setTitle(Prompt.inputString(""));
        System.out.printf("작성자(%s)? >", board.getWriter());
        board.setWriter(Prompt.inputString(""));
        System.out.printf("내용(%s)? >", board.getContent());
        board.setContent(Prompt.inputString(""));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다.");
  }



  public static void deletBoard() {
    int boardNo = Prompt.inputInt("삭제할 번호? ");

    int delectedIndex = indexOf(boardNo);
    if (delectedIndex == -1) {
      System.out.println("무효한 번호입니다.");
      return;
    }

    for (int i = delectedIndex; i < length - 1; i++) {
      boards[i] = boards[i + 1];
    }

    boards[--length] = null;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      Board m = boards[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() {
    return length < MAX_SIZE;
  } // 외부에서 import 하지 않는 메서드이기 때문에 public 취소하기, private 붙이면 이 클래스 안에서만 쓸 수 있음
}
