package bitcamp.personalapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.vo.Member;


@WebServlet("/header")
public class HeaderServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<div style='height:50px;background-color:pink;'>");
    out.println("<img src='https://www.ncloud.com/public/img/logo-m.png' style='height:40px'>");
    out.println("<a href='/diary/list'>일기</a></li>");
    out.println("<a href='/board/list'>자유게시판</a></li>");
    out.println("<a href='/member/list'>회원</a></li>");

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<a href='/auth/form.html'>로그인</a>");
    } else {
      out.printf("%s %s <a href='/auth/logout'>로그아웃</a>\n", (loginUser.getPhoto() == null? 
    		  "<img style='height:40px' src='/images/avatar.png'>": 
    			  String.format("<img src='http://rilqiqaqfxro19010722.cdn.ntruss.com/member/%s?type=f&w=30&h=40&faceopt=true&ttype=jpg'>",
              loginUser.getPhoto())),
          loginUser.getName());
    }

    out.println("</div>");

  }

}

