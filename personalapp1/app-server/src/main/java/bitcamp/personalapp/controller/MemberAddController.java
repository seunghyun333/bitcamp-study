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

@WebServlet("/member/add")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class MemberAddController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/WEB-INF/jsp/member/form.jsp").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    SqlSessionFactory sqlSessionFactory =
        (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService =
        (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

    try {
      Member m = new Member();
      m.setName(request.getParameter("name"));
      m.setEmail(request.getParameter("email"));
      m.setPw(request.getParameter("pw"));
      m.setTel(request.getParameter("tel"));

      // member.setName(request.getParameter("이름을 적어주세요 ♡"));
      // prompt.printf("%s아~ 고마워♡\n", member.getName());

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl =
            ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07", "member/", photoPart);
        m.setPhoto(uploadFileUrl);
      }


      memberDao.insert(m);
      sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("list");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "2;url=list");
      throw new ServletException(e);
    }
  }
}


