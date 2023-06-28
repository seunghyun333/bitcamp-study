package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Board;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class BoardDeleteListener extends AbstractBoardListener{

	public BoardDeleteListener(List<Board> list) {
		super(list);
	}



	
	public void service(BreadcrumbPrompt prompt) {
		if(!this.list.remove(new Board(prompt.inputInt("삭제할 번호?")))) {
		System.out.println("무효한 번호입니다.");
		}
	}
	
	}



