package bitcamp.personalapp.handler;

import java.util.List;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardListListener implements ActionListener{

	BoardDao boardDao;
	
	public BoardListListener(BoardDao boardDao) {
		this.boardDao = boardDao;
	}


	public void service(BreadcrumbPrompt prompt) {
	    System.out.println("============================");
	    System.out.printf("번호, 제목, 작성자 , 조회수, 등록일 \n");
	    System.out.println("============================");
	    
	   List<Board> list = boardDao.list();
	   
	    for (Board board : list) {
	      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", 
	      		  board.getNo(), 
	      		  board.getTitle(), 
	      		  board.getWriter(), 
	      		  board.getViewCount(), 
	      		  board.getCreatedDate());
	    }
	}
	}



