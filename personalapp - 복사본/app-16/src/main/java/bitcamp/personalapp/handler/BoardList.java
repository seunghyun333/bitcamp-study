package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Board;

public class BoardList {
	
	private static final int MAX_SIZE = 100;
	private Board[] boards = new Board[MAX_SIZE];
	private int length;
	
	public void add(Board board) {
		if(this.length == boards.length) {
			increase();
		}
	this.boards[this.length++] = board;
	}
	
	private void increase() {
		Board[] arr = new Board[boards.length + (boards.length >> 1)];
		
		for (int i = 0; i < boards.length; i++) {
			arr[i] = boards[i];
		}
		boards = arr; 
	   //System.out.println("배열확장:  " + boards.length);		
	}
}
