package bitcamp.personalapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.vo.Visit;

@WebServlet("/visit/list")
public class VisitListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");


    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>방문자</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>방문자 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/visit/form.html'>새 방문자</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>ID번호</th> <th>이름</th></tr>");
    out.println("</thead>");

    List<Visit> list = InitServlet.visitDao.findAll();
    for (Visit visit : list) {
      out.printf("<tr>" + " <td>%d</td>" + " <td><a href='/visit/detail?no=%d'>%s</a></td></tr>\n",
          visit.getNo(), visit.getNo(), visit.getName());

    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}


