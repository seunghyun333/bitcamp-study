package bitcamp.personalapp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import bitcamp.personalapp.vo.Comment;


@Component
public class MySQLCommentDao implements CommentDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLCommentDao(SqlSessionFactory sqlSessionFactory) {
	  System.out.println("MySQLDiarydDao() 호출됨!");
    this.sqlSessionFactory = sqlSessionFactory;

  }

  @Override
  public void insert(Comment comment) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("bitcamp.personalapp.dao.CommentDao.insert", comment);
  }


  @Override
  public int update(Comment comment) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.personalapp.dao.CommentDao.update", comment);
  }


  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("bitcamp.personalapp.dao.CommentDao.delete", no);
  }

  @Override
  public List<Comment> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("bitcamp.personalapp.dao.CommentDao.findAll");
  }

  @Override
  public List<Comment> findAllByCno(int cno) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("bitcamp.personalapp.dao.CommentDao.findAllByCno", cno);
  }

}
