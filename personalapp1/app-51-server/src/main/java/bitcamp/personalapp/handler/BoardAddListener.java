package bitcamp.personalapp.handler;

import java.io.IOException;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class BoardAddListener implements ActionListener{
	
	BoardDao boardDao;
	DataSource ds;
	
 
	public BoardAddListener(BoardDao boardDao, DataSource ds) {
		this.boardDao = boardDao;
		this.ds = ds;
	}


	
	public void service(BreadcrumbPrompt prompt) throws IOException{
		
		Board board = new Board();
		board.setTitle(prompt.inputString("제목? "));
		board.setContent(prompt.inputString("내용? "));
		
		Visit writer = new Visit();
		writer.setNo(prompt.inputInt("작성자? "));
		board.setWriter(writer);
		
		board.setPassword(prompt.inputString("암호? "));
		
		try {
		boardDao.insert(board);	
		
		ds.getConnection().commit();
		} catch (Exception e) {
			try {ds.getConnection().rollback();} catch (Exception e2) {}
			throw new RuntimeException(e);
	}
 }
}



