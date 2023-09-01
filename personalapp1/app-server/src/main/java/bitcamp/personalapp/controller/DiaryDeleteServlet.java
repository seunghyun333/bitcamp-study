//package bitcamp.personalapp.handler;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//
//import bitcamp.personalapp.dao.DiaryDao;
//
//@WebServlet("/diary/delete")
//public class DiaryDeleteServlet extends HttpServlet {
//
//  private static final long serialVersionUID = 1L;
//
//
//  @Override
//  protected void doGet(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//	  
//	  DiaryDao diaryDao = (DiaryDao) this.getServletContext().getAttribute("diaryDao");
//	  SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
//
//
//    try {
//      if (diaryDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
//        throw new Exception("해당 번호의 일기가 없습니다!! ");
//      } else {
//        response.sendRedirect("/diary/list");
//      }
//      sqlSessionFactory.openSession(false).commit();
//
//    } catch (Exception e) {
//      sqlSessionFactory.openSession(false).rollback();
//      throw new RuntimeException(e);
//
//    }
//  }
//}
//
//
