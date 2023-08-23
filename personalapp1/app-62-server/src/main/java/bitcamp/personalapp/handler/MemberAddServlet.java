package bitcamp.personalapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import bitcamp.personalapp.vo.Member;

@WebServlet("/member/add")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class MemberAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member m = new Member();
    m.setName(request.getParameter("name"));
    m.setEmail(request.getParameter("email"));
    m.setPw(request.getParameter("pw"));
    m.setTel(request.getParameter("tel"));

    // member.setName(request.getParameter("이름을 적어주세요 ♡"));
    // prompt.printf("%s아~ 고마워♡\n", member.getName());

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = InitServlet.ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07",
          "member/", photoPart);
      m.setPhoto(uploadFileUrl);
    }

    try {
      InitServlet.memberDao.insert(m);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("list");
      PrintWriter out = response.getWriter();
      out.println("<p>등록 성공입니다!</p>");
      out.printf("%s님 환영합니다♡\n", m.getName());
    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      
      request.setAttribute("error", e);
      request.setAttribute("message", "회원등록오류");
      request.setAttribute("refresh", "2;url=list");
    }
  }
}


