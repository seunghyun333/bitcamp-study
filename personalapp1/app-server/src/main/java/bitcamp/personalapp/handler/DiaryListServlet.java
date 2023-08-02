package bitcamp.personalapp.handler;

import java.io.PrintWriter;
import java.util.List;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/diary/list")
public class DiaryListServlet implements Servlet {

  DiaryDao diaryDao;


  public DiaryListServlet(DiaryDao diaryDao) {
    this.diaryDao = diaryDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {


    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>일기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>일기 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/diary/form.html'>새 글</a>\n");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>날짜</th> <th>날씨</th> <th>제목</th> <th>모닝커피</th></tr>");
    out.println("</thead>");


    List<Diary> list = diaryDao.findAll();

    out.println("<tbody>");
    for (Diary diary : list) {
      out.printf(
          "<tr><td>%d</td>, <td>%s</td>, <td>%s</td>, <td><a href='/diary/detail'>%s</a></td>, <td>%s</td></tr>\n",
          diary.getNo(), diary.getDate(), diary.getWeather(),
          diary.getTitle().length() > 0 ? diary.getTitle() : "제목없음",
          diary.getCoffee() == 'O' ? "마심" : "안 마심");
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }



}


