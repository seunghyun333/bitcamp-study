package bitcamp.personalapp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.personalapp.vo.Visit;

public class MySQLVisitDao implements VisitDao {

  SqlSessionFactory sqlSessionFactory;


  public MySQLVisitDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;

  }

  @Override
  public void insert(Visit visit) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("bitcamp.personalapp.dao.VisitDao.insert", visit);
  }

  @Override
  public List<Visit> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("bitcamp.personalapp.dao.VisitDao.findAll");
  }

  @Override
  public Visit findByIdAndPassword(Visit visit) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);    
    return sqlSession.selectOne("bitcamp.personalapp.dao.VisitDao.findByIdAndPassword", visit);
  }
  
  
}

