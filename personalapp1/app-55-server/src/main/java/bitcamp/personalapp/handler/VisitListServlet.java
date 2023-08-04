package bitcamp.personalapp.handler;

import java.io.PrintWriter;
import java.util.List;
import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/visit/list")
public class VisitListServlet implements Servlet {

  VisitDao visitDao;

  public VisitListServlet(VisitDao visitDao) {
    this.visitDao = visitDao;
  }



  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
    out.println("  <tr><th>ID번호</th> <th>이름</th> <th>날짜</th></tr>");
    out.println("</thead>");

    List<Visit> list = visitDao.findAll();
    for (Visit visit : list) {
      out.printf("<tr><td>%d</td> <td>%s</td>, <td>%tY-%3$tm-%3$td</td></tr>\n", visit.getNo(),
          visit.getName(), visit.getCreatedDate());
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}


