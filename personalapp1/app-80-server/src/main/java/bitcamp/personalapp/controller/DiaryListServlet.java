//package bitcamp.personalapp.handler;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//
//import bitcamp.personalapp.dao.DiaryDao;
//import bitcamp.personalapp.vo.Diary;
//
//@WebServlet("/diary/list")
//public class DiaryListServlet extends HttpServlet {
//
//  private static final long serialVersionUID = 1L;
//
//  @Override
//  protected void doGet(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//
//	  DiaryDao diaryDao = (DiaryDao) this.getServletContext().getAttribute("diaryDao");
//
//
//    response.setContentType("text/html;charset=UTF-8");
//    PrintWriter out = response.getWriter();
//
//    out.println("<!DOCTYPE html>");
//    out.println("<html>");
//    out.println("<head>");
//    out.println("<meta charset='UTF-8'>");
//    out.println("<title>일기</title>");
//    out.println("</head>");
//    out.println("<body>");
//    out.println("<h1>일기 목록</h1>");
//    out.println("<div style='margin:5px;'>");
//    out.println("<a href='/diary/form.html'>새 글</a>\n");
//    out.println("</div>");
//    out.println("<table border='1'>");
//    out.println("<thead>");
//    out.println("  <tr><th>번호</th> <th>날짜</th> <th>날씨</th> <th>제목</th></tr>");
//    out.println("</thead>");
//
//
//    List<Diary> list = diaryDao.findAll();
//    for (Diary diary : list) {
//      out.printf(
//          "<tr><td>%d</td>, " + "<td>%s</td>, " + "<td>%s</td>, "
//              + "<td><a href='/diary/detail?no=%d'>%s</a></td>\n",
//          diary.getNo(), diary.getDate(), diary.getWeather(), diary.getNo(),
//          diary.getTitle().length() > 0 ? diary.getTitle() : "제목없음");
//    }
//    out.println("</tbody>");
//    out.println("</table>");
//    out.println("<a href='/'>메인</a>");
//    out.println("</body>");
//    out.println("</html>");
//  }
//
//
//
//}
//
//
