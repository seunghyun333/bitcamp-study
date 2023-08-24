package bitcamp.personalapp.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

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



    Member loginUser = InitServlet.memberDao.findByIdAndPassword(m);
    if (loginUser != null) {
      request.getSession().setAttribute("loginUser", loginUser);
      response.sendRedirect("/");
      return;
    }


    request.setAttribute("message", "회원 정보가 일치하지 않습니다.");
    request.setAttribute("refresh", "1;url=/auth/form");

    request.getRequestDispatcher("/error").forward(request, response);
  }
}


