package bitcamp.personalapp.service;

import java.util.List;
import bitcamp.personalapp.vo.Member;

public interface MemberService {
  int add(Member member) throws Exception;

  List<Member> list() throws Exception;

  Member get(int memberNo) throws Exception;

  Member get(String email, String pw) throws Exception;

  int update(Member member) throws Exception;

  int delete(int memberNo) throws Exception;
}
