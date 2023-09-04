package bitcamp.personalapp.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import bitcamp.personalapp.vo.Member;

public interface MemberDao {

  int insert(Member member);

  List<Member> findAll();

  Member findBy(int no);

  Member findByIdAndPassword(@Param("email") String email, @Param("pw") String pw);

  int update(Member member);

  int delete(int no);



}
