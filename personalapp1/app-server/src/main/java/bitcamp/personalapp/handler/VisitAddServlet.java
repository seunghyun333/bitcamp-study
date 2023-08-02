package bitcamp.personalapp.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/visit/add")
public class VisitAddServlet implements Servlet {

  VisitDao visitDao;
  SqlSessionFactory sqlSessionFactory;


  public VisitAddServlet(VisitDao visitDao, SqlSessionFactory sqlSessionFactory) {
    this.visitDao = visitDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Visit visit = new Visit();
    visit.setName(request.getParameter("name"));

    // visit.setName(request.getParameter("이름을 적어주세요 ♡"));
    // prompt.printf("%s아~ 고마워♡\n", visit.getName());

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/visit/list'>");
    out.println("<title>방문자</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>방문자 등록</h1>");

    try {
      visitDao.insert(visit);
      sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }

}


