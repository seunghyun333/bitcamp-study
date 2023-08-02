package bitcamp.personalapp.handler;

import java.io.PrintWriter;
import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/auth/login")
public class LoginListener implements Servlet {

  VisitDao visitDao;

  public LoginListener(VisitDao visitDao) {
    this.visitDao = visitDao;
  }


  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Visit v = new Visit();
    v.setName(request.getParameter("name"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>로그인</h1>");

    Visit loginUser = visitDao.findByIdAndPassword(v);
    if (loginUser == null) {
      out.println("방문자 정보가 일치하지 않습니다!");
    } else {
      out.println("<p>로그인 성공입니다!</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }
}


