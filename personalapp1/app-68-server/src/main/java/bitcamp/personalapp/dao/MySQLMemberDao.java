package bitcamp.personalapp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import bitcamp.personalapp.vo.Member;


@Component
public class MySQLMemberDao implements MemberDao {

  SqlSessionFactory sqlSessionFactory;


  public MySQLMemberDao(SqlSessionFactory sqlSessionFactory) {
	  System.out.println("MySQLMemberDao() 호출됨!");
    this.sqlSessionFactory = sqlSessionFactory;

  }

  @Override
  public void insert(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("bitcamp.personalapp.dao.MemberDao.insert", member);
  }

  @Override
  public Member findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("bitcamp.personalapp.dao.MemberDao.findBy", no);
  }

  @Override
  public List<Member> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("bitcamp.personalapp.dao.MemberDao.findAll");
  }

  @Override
  public Member findByIdAndPassword(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("bitcamp.personalapp.dao.MemberDao.findByIdAndPassword", member);
  }

  @Override
  public int update(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.personalapp.dao.MemberDao.update", member);
  }

  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("bitcamp.personalapp.dao.MemberDao.delete", no);
  }



}

