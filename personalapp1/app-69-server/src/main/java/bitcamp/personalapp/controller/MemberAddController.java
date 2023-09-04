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

@Component("/member/add")
public class MemberAddController implements PageController {
	
	MemberDao memberDao;
	PlatformTransactionManager txManager;
	NcpObjectStorageService ncpObjectStorageService;
	
	public MemberAddController(MemberDao memberDao, PlatformTransactionManager txManager, NcpObjectStorageService ncpObjectStorageService) {
		this.memberDao = memberDao;
		this.txManager = txManager;
		this.ncpObjectStorageService = ncpObjectStorageService;
	}

	@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			if(request.getMethod().equals("GET")) {
				return "/WEB-INF/jsp/member/form.jsp";
			}

		    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		    def.setName("tx1");
		    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		    TransactionStatus status = txManager.getTransaction(def);
		    
		    try {
		      Member m = new Member();
		      m.setName(request.getParameter("name"));
		      m.setEmail(request.getParameter("email"));
		      m.setPw(request.getParameter("pw"));
		      m.setTel(request.getParameter("tel"));
		
		      // prompt.printf("%s아~ 고마워♡\n", member.getName());
		
		      Part photoPart = request.getPart("photo");
		      if (photoPart.getSize() > 0) {
		        String uploadFileUrl =
		            ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07", "member/", photoPart);
		        m.setPhoto(uploadFileUrl);
		      }
		
		
		      memberDao.insert(m);
		      txManager.commit(status);
		      return "redirect:list";
		
		    } catch (Exception e) {
		      txManager.rollback(status);
		      request.setAttribute("message", "회원 등록 오류!");
		      request.setAttribute("refresh", "2;url=list");
		      throw e;
		    }
		  }
		}


