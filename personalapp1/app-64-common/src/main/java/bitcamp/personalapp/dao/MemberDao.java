package bitcamp.personalapp.dao;


import java.util.List;
import bitcamp.personalapp.vo.Member;

public interface MemberDao {

  void insert(Member member);

  List<Member> findAll();

  Member findBy(int no);

  Member findByIdAndPassword(Member m);

  int update(Member member);

  int delete(int no);



}
