package bitcamp.personalapp.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Member;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {


  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    Board b = new Board();
    b.setNo(Integer.parseInt(request.getParameter("no")));
    b.setMno(loginUser);

    try {
      if (InitServlet.boardDao.delete(b) == 0) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
    	  InitServlet.sqlSessionFactory.openSession(false).commit();
    	  response.sendRedirect("list");
      }
      

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("error", e);
      request.setAttribute("message", e.getMessage());
      request.setAttribute("refresh", "2;url=list");
      
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}


