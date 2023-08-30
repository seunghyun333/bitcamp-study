package bitcamp.personalapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.dao.MemberDao;
import bitcamp.personalapp.vo.Member;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/WEB-INF/jsp/auth/form.jsp").include(request, response);

  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member m = new Member();
    m.setEmail(request.getParameter("email"));
    m.setPw(request.getParameter("pw"));

    if (request.getParameter("saveEmail") != null) {
      Cookie cookie = new Cookie("email", m.getEmail());
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }


    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    Member loginUser = memberDao.findByIdAndPassword(m);

    if (loginUser != null) {
      request.getSession().setAttribute("loginUser", loginUser);
      response.sendRedirect("/");
      return;
    }

    request.setAttribute("refresh", "1;url=/auth/form");
    throw new ServletException("회원 정보가 일치하지 않습니다.");

  }
}


