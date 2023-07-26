package bitcamp.personalapp.handler;

import java.io.IOException;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardAddListener implements ActionListener{
	
	BoardDao boardDao;
	
 
	public BoardAddListener(BoardDao boardDao) {
		this.boardDao = boardDao;
	}


	
	public void service(BreadcrumbPrompt prompt) throws IOException{
		
		Board board = new Board();
		board.setTitle(prompt.inputString("제목? "));
		board.setContent(prompt.inputString("내용? "));
		
		Visit writer = new Visit();
		writer.setNo(prompt.inputInt("작성자? "));
		board.setWriter(writer);
		
		board.setPassword(prompt.inputString("암호? "));
		
		boardDao.insert(board);	
	}
}



