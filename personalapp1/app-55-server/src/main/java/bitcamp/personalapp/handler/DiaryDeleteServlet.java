package bitcamp.personalapp.handler;

import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/diary/delete")
public class DiaryDeleteServlet implements Servlet {

  DiaryDao diaryDao;
  SqlSessionFactory sqlSessionFactory;

  public DiaryDeleteServlet(DiaryDao diaryDao, SqlSessionFactory sqlSessionFactory) {
    this.diaryDao = diaryDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }


  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    try {
      if (diaryDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 일기가 없습니다!! ");
      } else {
        response.sendRedirect("/diary/list");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);

    }
  }
}


