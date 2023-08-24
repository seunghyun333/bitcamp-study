package bitcamp.personalapp.handler;

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

@WebServlet("/member/update")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class MemberUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  
	  MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
	  SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
	  NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");


    Member member = new Member();
    member.setNo(Integer.parseInt(request.getParameter("no")));
    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPw(request.getParameter("pw"));


    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07",
          "member/", photoPart);
      member.setPhoto(uploadFileUrl);
    }

    try {
      if (memberDao.update(member) == 0) {
        throw new Exception("<p>회원이 없습니다.</p>");
      } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list");
      }
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      
      request.setAttribute("error", e);
      request.setAttribute("message", e.getMessage());
      request.setAttribute("refresh", "2;url=list");
      
      request.getRequestDispatcher("/error").forward(request, response);

    }
  }
}
