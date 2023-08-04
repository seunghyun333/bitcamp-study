package bitcamp.personalapp.handler;

import java.io.PrintWriter;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/diary/detail")
public class DiaryDetailServlet implements Servlet {

  DiaryDao diaryDao;


  public DiaryDetailServlet(DiaryDao diaryDao) {
    this.diaryDao = diaryDao;
  }


  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Diary diary = diaryDao.findBy(Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>일기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>일기</h1>");


    if (diary == null) {
      out.println("<p>해당 번호의 일기가 없습니다.</p>");

    } else {
      out.println("<form action='/diary/update' method='post'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n",
          diary.getNo());

      out.printf("<tr><th>날짜</th>" + " <td><input type='text' name='date' value='%s'></td></tr>\n",
          diary.getDate());

      out.printf(
          "<tr><th>날씨</th>" + " <td><input type='text' name='weather' value='%s'></td></tr>\n",
          diary.getWeather());


      out.printf("<tr><th>제목</th>" + " <td><input type='text' name='title' value='%s'></td></tr>\n",
          diary.getTitle());

      out.printf(
          "<tr><th>내용</th>" + " <td><input type='text' name='content' value='%s'></td></tr>\n",
          diary.getContent());

      out.printf(
          "<tr><th>모닝커피</th>\n" + " <td><select name='coffee'>\n"
              + " <option value='O' %s>마심</option>\n"
              + " <option value='X' %s>안 마심</option></select></td></tr>\n",
          (diary.getCoffee() == 'O' ? "selected" : ""),
          (diary.getCoffee() == 'X' ? "selected" : ""));

      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/diary/delete?no=%d'>삭제</a>\n", diary.getNo());
      out.println("<a href='/diary/list'>목록</a>\n");
      out.println("</div>");
      out.println("</form>");
    }
    out.println("</body>");
    out.println("</html>");


  }
}


