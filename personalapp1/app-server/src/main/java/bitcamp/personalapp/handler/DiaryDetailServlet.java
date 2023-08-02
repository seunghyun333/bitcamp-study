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


    if (board == null) {
      out.println("<p>해당 번호의 일기가 없습니다.</p>");
      
    } else {

      out.println
    prompt.printf("날짜 : %s \n", diary.getDate());
    prompt.printf("날씨 : %s \n", diary.getWeather());
    prompt.printf("제목 : %s \n", diary.getTitle());
    prompt.printf("내용 : %s \n", diary.getContent());
    prompt.printf("모닝커피 : %s \n", diary.getCoffee() == 'O' ? "마심" : "안 마심");
  }


}


