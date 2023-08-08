package bitcamp.personalapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.vo.Visit;

@WebServlet("/visit/detail")
public class VisitDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Visit visit = InitServlet.visitDao.findBy(Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>방문자</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>방문자</h1>");

    if (visit == null) {
      out.println("<p>해당 번호의 회원이 없습니다!</p>");

    } else {
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n",
          visit.getNo());
      out.printf("<tr><th>이름</th> <td>%s</td></tr>\n", visit.getName());

      out.printf("<tr><th>등록일</th> <td>%tY-%1$tm-%1$td</td></tr>\n", visit.getCreatedDate());
      out.println("</table>");

      out.println("<div>");

      out.printf("<a href='/visit/delete?no=%d'>삭제</a>\n", visit.getNo());
      out.println("<a href='/visit/list'>목록</a>\n");
      out.println("</div>");

    }

    out.println("</body>");
    out.println("</html>");

  }
}
