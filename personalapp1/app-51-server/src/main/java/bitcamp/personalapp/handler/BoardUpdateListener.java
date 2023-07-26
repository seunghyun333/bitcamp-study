package bitcamp.personalapp.handler;

import java.io.IOException;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardUpdateListener implements ActionListener{

	BoardDao boardDao;
	
	public BoardUpdateListener(BoardDao boardDao) {
		this.boardDao = boardDao;
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
		
		

		if (boardDao.update(board) == 0) {
			prompt.println("암호가 일치하지 않습니다.");
		} else {
			prompt.println("변경했습니다.");
		}
	}
	}



