package bitcamp.personalapp.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/diary/add")
public class DiaryAddServlet implements Servlet {

  DiaryDao diaryDao;
  SqlSessionFactory sqlSessionFactory;

  public DiaryAddServlet(DiaryDao diaryDao, SqlSessionFactory sqlSessionFactory) {
    this.diaryDao = diaryDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {


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
      diaryDao.insert(diary);
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


