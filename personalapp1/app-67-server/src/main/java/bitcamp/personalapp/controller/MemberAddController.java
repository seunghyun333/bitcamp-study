package bitcamp.personalapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.MemberDao;
import bitcamp.personalapp.vo.Member;
import bitcamp.util.NcpObjectStorageService;


public class MemberAddController implements PageController {
	
	MemberDao memberDao;
	SqlSessionFactory sqlSessionFactory;
	NcpObjectStorageService ncpObjectStorageService;
	
	public MemberAddController(MemberDao memberDao, SqlSessionFactory sqlSessionFactory, NcpObjectStorageService ncpObjectStorageService) {
		this.memberDao = memberDao;
		this.sqlSessionFactory = sqlSessionFactory;
		this.ncpObjectStorageService = ncpObjectStorageService;
	}

	@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			if(request.getMethod().equals("GET")) {
				return "/WEB-INF/jsp/member/form.jsp";
			}

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
		      sqlSessionFactory.openSession(false).commit();
		      return "redirect:list";
		
		    } catch (Exception e) {
		      sqlSessionFactory.openSession(false).rollback();
		      request.setAttribute("message", "회원 등록 오류!");
		      request.setAttribute("refresh", "2;url=list");
		      throw e;
		    }
		  }
		}


