package bitcamp.personalapp.dao;

<<<<<<< HEAD
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;
=======
import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
>>>>>>> 8d4aec26b256cdc53da40d3b7d957f02eb29c836

@Mapper
public interface BoardDao {
  int insert(Board board);

  List<Board> findAll();

  Board findBy(int no);

  int update(Board board);

  int updateCount(int no);

  int delete(int no);

  int insertFiles(Board board);

  AttachedFile findFileBy(int no);

  int deleteFile(int fileNo);

  int deleteFiles(int boardNo);


}
