package bitcamp.personalapp.handler;

import bitcamp.personalapp.ClientApp;
import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;
import bitcamp.util.BreadcrumbPrompt;

public class LoginListener implements BoardActionListener{
	
	BoardDao boardDao;
	
 
	public LoginListener(BoardDao boardDao) {
		this.boardDao = boardDao;
	}


	
	public void service(BreadcrumbPrompt prompt) {
		
		while(true) {
		
		Board b = new Board();
		b.setPassword(prompt.inputString("암호? "));
		
		Board loginUser = boardDao.findByIdAndPassword(b);
		
		if (loginUser == null) {
			System.out.println("방문자 정보가 일치하지 않습니다!");
		} else {
			ClientApp.loginUser = loginUser;
			break;
		}
	}
	}
}



