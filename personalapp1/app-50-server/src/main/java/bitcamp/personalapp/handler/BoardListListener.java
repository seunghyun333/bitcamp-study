package bitcamp.personalapp.handler;

import java.text.SimpleDateFormat;
import java.util.List;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardListListener implements ActionListener{

	BoardDao boardDao;
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public BoardListListener(BoardDao boardDao) {
		this.boardDao = boardDao;
	}


	public void service(BreadcrumbPrompt prompt) {
	    prompt.println("============================");
	    prompt.printf("번호, 제목, 작성자 , 조회수, 등록일 \n");
	    prompt.println("============================");
	    
	   List<Board> list = boardDao.list();
	   
	    for (Board board : list) {
	      prompt.printf("%d, %s, %s, %d, %s\n", 
	      		  board.getNo(), 
	      		  board.getTitle(), 
	      		  board.getWriter().getName(), 
	      		  board.getViewCount(), 
	      		 dateFormatter.format(board.getCreatedDate()));
	    }
	}
	}



