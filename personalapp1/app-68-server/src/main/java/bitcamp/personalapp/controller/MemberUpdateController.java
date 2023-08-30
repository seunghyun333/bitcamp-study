package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import bitcamp.personalapp.dao.MemberDao;
import bitcamp.personalapp.service.NcpObjectStorageService;
import bitcamp.personalapp.vo.Member;

@Component("/member/update")
public class MemberUpdateController implements PageController {

	MemberDao memberDao;
	SqlSessionFactory sqlSessionFactory;
	NcpObjectStorageService ncpObjectStorageService;
	
	public MemberUpdateController(MemberDao memberDao, SqlSessionFactory sqlSessionFactory, NcpObjectStorageService ncpObjectStorageService) {
		this.memberDao = memberDao;
		this.ncpObjectStorageService = ncpObjectStorageService;
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


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
        sqlSessionFactory.openSession(false).commit();
        return "redirect:list";

      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "2;url=list");
      throw e;

    }
  }
}
