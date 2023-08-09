package bitcamp.personalapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.vo.Diary;

@WebServlet("/diary/update")
public class DiaryUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;


  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    Diary diary = new Diary();
    diary.setNo(Integer.parseInt(request.getParameter("no")));
    diary.setDate(request.getParameter("date"));
    diary.setWeather(request.getParameter("weather"));
    diary.setTitle(request.getParameter("title"));
    diary.setContent(request.getParameter("content"));
    diary.setCoffee(request.getParameter("coffee").charAt(0));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/diary/list'>");
    out.println("<title>일기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>일기 변경</h1>");


    try {
      if (InitServlet.diaryDao.update(diary) == 0) {
        out.println("<p>해당 번호의 일기가 없습니다.</p>");
      } else {
        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }
    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}


