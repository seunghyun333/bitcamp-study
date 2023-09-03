package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import bitcamp.personalapp.dao.MemberDao;
import bitcamp.personalapp.service.NcpObjectStorageService;
import bitcamp.personalapp.vo.Member;

@Component("/member/update")
public class MemberUpdateController implements PageController {

	MemberDao memberDao;
	PlatformTransactionManager txManager;
	NcpObjectStorageService ncpObjectStorageService;
	
	public MemberUpdateController(MemberDao memberDao, PlatformTransactionManager txManager, NcpObjectStorageService ncpObjectStorageService) {
		this.memberDao = memberDao;
		this.ncpObjectStorageService = ncpObjectStorageService;
		this.txManager = txManager;
	}
	
	@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    def.setName("tx1");
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = txManager.getTransaction(def);

    try {
      Member member = new Member();
      member.setNo(Integer.parseInt(request.getParameter("no")));
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPw(request.getParameter("pw"));


      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl =
            ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07", "member/", photoPart);
        member.setPhoto(uploadFileUrl);
      }


      if (memberDao.update(member) == 0) {
        throw new Exception("<p>회원이 없습니다.</p>");
      } else {
        txManager.commit(status);
        return "redirect:list";

      }

    } catch (Exception e) {
      txManager.rollback(status);
      request.setAttribute("refresh", "2;url=list");
      throw e;

    }
  }
}
