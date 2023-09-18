package bitcamp.personalapp.service;

import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;

import java.util.List;

public interface BoardService {
  int add(Board board) throws Exception;

  List<Board> list() throws Exception;

  Board get(int boardNo) throws Exception;


  int update(Board board) throws Exception;

  int delete(int boardNo) throws Exception;

  int increaseViewCount(int boardNo) throws Exception;

  AttachedFile getAttachedFile(int fileNo) throws Exception;

  int deleteAttachedFile(int fileNo) throws Exception;

  List<Board> searchlist(String option, String keyword) throws Exception;

}
