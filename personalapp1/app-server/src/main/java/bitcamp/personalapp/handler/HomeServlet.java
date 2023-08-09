package bitcamp.personalapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.vo.Visit;

@WebServlet("/index.html")
public class HomeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>My-Diary♥</title>");
    out.println("<style>");
    out.println("  h1 { color: pink; }");
    out.println("  ul { list-style-type: none; }");
    out.println("  li { margin-bottom: 10px; cursor: pointer; list-style-type: square; }");
    out.println("</style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>My-Diary♥</h1>");
    out.println("<ul>");
    out.println("  <li><a href='/diary/list'>일기</a></li>");
    out.println("  <li><a href='/board/list'>응원의 한마디</a></li>");
    out.println("  <li><a href='/visit/list'>방문자</a></li>");
    // out.println(" <li><a href='/auth/form.html'>로그인</a></li>");

    out.println("</body>");
    out.println("</html>");


    Visit loginUser = (Visit) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("  <li><a href='/auth/form.html'>로그인</a></li>");
    } else {
      out.printf("  <li>♡ %s ♡ <a href='/auth/logout'>로그아웃</a></li>", loginUser.getName());
    }
    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");
  }
}
