package bitcamp.personalapp.dao;

import java.util.List;
import bitcamp.personalapp.vo.Board;

public interface BoardDao {
  void insert(Board board);

  List<Board> findAll();

  Board findBy(int no);

  int update(Board board);

  int updateCount(Board board);

  int delete(Board board);

}
