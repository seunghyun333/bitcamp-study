package bitcamp.personalapp.service;

import bitcamp.personalapp.dao.MemberDao;
import bitcamp.personalapp.vo.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DefaultMemberService implements MemberService {
  {
    System.out.println("DefaultMemberService 생성됨!");
  }
  MemberDao memberDao;


  public DefaultMemberService(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Transactional
  @Override
  public int add(Member member) throws Exception {
    return memberDao.insert(member);
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public Member get(int memberNo) throws Exception {
    return memberDao.findBy(memberNo);
  }

  @Override
  public Member get(String email, String pw) throws Exception {
    return memberDao.findByIdAndPassword(email, pw);
  }

  @Transactional
  @Override
  public int update(Member member) throws Exception {
    return memberDao.update(member);
  }

  @Transactional
  @Override
  public int delete(int memberNo) throws Exception {
    return memberDao.delete(memberNo);
  }
}
