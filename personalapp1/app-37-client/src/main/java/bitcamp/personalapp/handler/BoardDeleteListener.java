package bitcamp.personalapp.handler;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardDeleteListener implements ActionListener {

	BoardDao boardDao;
	
	public BoardDeleteListener(BoardDao boardDao) {
		this.boardDao = boardDao;
	}



	
	public void service(BreadcrumbPrompt prompt) {
		if(boardDao.delete(prompt.inputInt("삭제할 번호?")) ==0) {
		System.out.println("무효한 번호입니다.");
		}
	}
	
	}



