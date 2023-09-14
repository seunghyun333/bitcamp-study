package bitcamp.personalapp.service;

import java.util.List;
import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;

public interface BoardService {
  int add(Board board) throws Exception;

  List<Board> list() throws Exception;

  Board get(int boardNo) throws Exception;

  int update(Board board) throws Exception;

  int delete(int boardNo) throws Exception;

  int increaseViewCount(int boardNo) throws Exception;

  AttachedFile getAttachedFile(int fileNo) throws Exception;

  int deleteAttachedFile(int fileNo) throws Exception;

}
