package bitcamp.personalapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.vo.Board;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글</title>");
    out.println("</head>");

    request.getRequestDispatcher("/header").include(request, response);

    out.println("<body>");
    out.println("<h1>게시글 목록</h1>");
    out.println("<div sytle='margin:5px;'>");
    out.println("<a href='/board/form'>새 글</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록일</th></tr>");
    out.println("</thead>");


    List<Board> list = InitServlet.boardDao.findAll();

    out.println("<tbody>");

    for (Board board : list) {
      out.printf(
          "<tr>" + "<td>%d</td>" + " <td><a href='/board/detail?no=%d'>%s</a></td>" + " <td>%s</td>"
              + "<td>%d</td> " + "<td>%s</td></tr>\n",
          board.getNo(), board.getNo(), board.getTitle(), board.getMno().getName(),
          board.getV_count(), dateFormatter.format(board.getW_date()));
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }

}


