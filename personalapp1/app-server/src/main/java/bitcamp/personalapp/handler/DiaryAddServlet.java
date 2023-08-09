package bitcamp.personalapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.vo.Diary;

@WebServlet("/diary/add")
public class DiaryAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String enteredPassword = request.getParameter("password");

    if ("0318".equals(enteredPassword)) {
      Diary diary = new Diary();
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
      out.println("<h1>일기 등록</h1>");

      try {

        InitServlet.diaryDao.insert(diary);
        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>등록 성공입니다!</p>");


      } catch (Exception e) {
        InitServlet.sqlSessionFactory.openSession(false).rollback();
        out.println("<p>등록 실패입니다!</p>");
        e.printStackTrace();
      }

      out.println("</body>");
      out.println("</html>");
    } else {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>비밀번호 입력</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>비밀번호 입력</h1>");
      out.println("<form action='/diary/add'>");
      out.println("비밀번호: <input type='password' name='password'><br>");
      out.println("<input type='submit' value='확인'>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
    }
  }

}
