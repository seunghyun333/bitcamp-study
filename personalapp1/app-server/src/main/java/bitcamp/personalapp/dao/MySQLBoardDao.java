package bitcamp.personalapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.vo.Board;
import bitcamp.util.Component;

@Component
public class MySQLBoardDao implements BoardDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLBoardDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;

  }

  @Override
  public void insert(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("bitcamp.personalapp.dao.BoardDao.insert", board);
  }

  @Override
  public List<Board> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("bitcamp.personalapp.dao.BoardDao.findAll");
  }

  @Override
  public Board findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);

    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("boardNo", no);

    return sqlSession.selectOne("bitcamp.personalapp.dao.BoardDao.findBy", paramMap);
  }



  @Override
  public int update(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.personalapp.dao.BoardDao.update", board);
  }

  @Override
  public int updateCount(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.personalapp.dao.BoardDao.updateCount", board);
  }

  @Override
  public int delete(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("bitcamp.personalapp.dao.BoardDao.delete", board);
  }

}
