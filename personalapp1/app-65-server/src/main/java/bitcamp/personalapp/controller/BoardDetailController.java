package bitcamp.personalapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;

@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    SqlSessionFactory sqlSessionFactory =
        (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");


    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Board board = boardDao.findBy(no);

      if (board != null) {
        board.setV_count(board.getV_count() + 1);
        boardDao.updateCount(board);
        sqlSessionFactory.openSession(false).commit();
        request.setAttribute("board", board);
      }
      response.setContentType("text/html;charset=UTF-8");
      HttpSession session = request.getSession();
      session.setAttribute("currentBoard", board);
      request.getRequestDispatcher("/board/detail.jsp").include(request, response);


    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "5;url=/board/list");
    }
  }
}


