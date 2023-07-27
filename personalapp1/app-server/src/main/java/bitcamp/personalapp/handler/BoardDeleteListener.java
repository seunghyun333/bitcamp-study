package bitcamp.personalapp.handler;

import java.io.IOException;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class BoardDeleteListener implements ActionListener {

	BoardDao boardDao;
	DataSource ds;
	
	public BoardDeleteListener(BoardDao boardDao, DataSource ds) {
		this.boardDao = boardDao;
		this.ds = ds;
	}



	@Override
	public void service(BreadcrumbPrompt prompt) throws IOException{
		
		Board b = new Board();
		b.setNo(prompt.inputInt("번호?"));
		b.setPassword(prompt.inputString("암호?"));
		
		try {
		if (boardDao.delete(b) == 0) {
		prompt.println("해당 번호의 게시글이 없거나 암호가 일치하지 않습니다.");
		}else {
			prompt.println("삭제했습니다.");
		}
		ds. getConnection().commit();
		
		} catch (Exception e) {
			try {ds.getConnection().rollback();} catch (Exception e2) {}
			throw new RuntimeException(e);
		}
		
	}
	
	}



