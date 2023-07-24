package bitcamp.personalapp.dao;

import java.util.List;

import bitcamp.personalapp.vo.Board;

public interface BoardDao {
	void insert(Board board);
	List<Board> list();
	Board findBy(int no);
	Board findByIdAndPassword(Board board);
	int update(Board board);
	int delete(Board board);

}
