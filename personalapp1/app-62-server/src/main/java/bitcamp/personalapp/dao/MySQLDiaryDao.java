package bitcamp.personalapp.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.vo.Diary;



public class MySQLDiaryDao implements DiaryDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLDiaryDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Diary diary) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("bitcamp.personalapp.dao.DiaryDao.insert", diary);
  }

  @Override
  public List<Diary> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("bitcamp.personalapp.dao.DiaryDao.findAll");
  }

  @Override
  public Diary findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("bitcamp.personalapp.dao.DiaryDao.findBy", no);
  }

  @Override
  public int update(Diary diary) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.personalapp.dao.DiaryDao.update", diary);
  }

  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("bitcamp.personalapp.dao.DiaryDao.delete", no);
  }

}
