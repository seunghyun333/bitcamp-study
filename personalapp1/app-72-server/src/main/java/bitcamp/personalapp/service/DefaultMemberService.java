package bitcamp.personalapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import bitcamp.personalapp.dao.MemberDao;
import bitcamp.personalapp.vo.Member;


@Service
public class DefaultMemberService implements MemberService {

  MemberDao memberDao;
  TransactionTemplate txTemplate;

  public DefaultMemberService(MemberDao memberDao, PlatformTransactionManager txManager) {
    this.memberDao = memberDao;
    txTemplate = new TransactionTemplate(txManager);
  }

  @Override
  public int add(Member member) throws Exception {
    return txTemplate.execute(status -> memberDao.insert(member));
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

  @Override
  public int update(Member member) throws Exception {
    return txTemplate.execute(status -> memberDao.update(member));
  }

  @Override
  public int delete(int memberNo) throws Exception {
    return txTemplate.execute(status -> memberDao.delete(memberNo));
  }
}