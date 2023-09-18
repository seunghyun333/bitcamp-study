package bitcamp.personalapp.service;

<<<<<<< HEAD
import java.util.List;
import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;

=======
import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;

import java.util.List;

>>>>>>> 8d4aec26b256cdc53da40d3b7d957f02eb29c836
public interface BoardService {
  int add(Board board) throws Exception;

  List<Board> list() throws Exception;

  Board get(int boardNo) throws Exception;

<<<<<<< HEAD
=======

>>>>>>> 8d4aec26b256cdc53da40d3b7d957f02eb29c836
  int update(Board board) throws Exception;

  int delete(int boardNo) throws Exception;

  int increaseViewCount(int boardNo) throws Exception;

  AttachedFile getAttachedFile(int fileNo) throws Exception;

  int deleteAttachedFile(int fileNo) throws Exception;

}
