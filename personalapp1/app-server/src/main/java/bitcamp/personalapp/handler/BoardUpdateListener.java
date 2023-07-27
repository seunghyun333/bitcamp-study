package bitcamp.personalapp.handler;

import java.io.IOException;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class BoardUpdateListener implements ActionListener{

	BoardDao boardDao;
	DataSource ds;
	
	public BoardUpdateListener(BoardDao boardDao, DataSource ds) {
		this.boardDao = boardDao;
		this.ds = ds;
	}

	@Override
	public void service(BreadcrumbPrompt prompt) throws IOException {
		int boardNo = prompt.inputInt("수정할 번호?");
		
		Board board = boardDao.findBy(boardNo);
		if (board == null) {
			prompt.println("해당 번호의 게시글이 없습니다!");
			return;
		}
		
		board.setTitle(prompt.inputString("제목(%s)?", board.getTitle()));
		board.setContent(prompt.inputString("내용(%s)?", board.getContent()));
		board.setPassword(prompt.inputString("암호?"));
		
		

		try {
		if (boardDao.update(board) == 0) {
			prompt.println("암호가 일치하지 않습니다.");
		} else {
			prompt.println("변경했습니다.");
		}
		ds.getConnection().commit();
		
		}catch (Exception e) {
			try {ds.getConnection().rollback();} catch (Exception e2) {}
			throw new RuntimeException(e);
		}
	}
	}



