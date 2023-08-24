package bitcamp.personalapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class HomeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>My-Diary♥</title>");
    out.println("</head>");
    out.println("<body>");

    request.getRequestDispatcher("/header").include(request, response);


    out.println("<h1>My-Diary♥</h1>");
    out.println("<p>오늘의 기분을 소통해보자</p>");


    request.getRequestDispatcher("/footer").include(request, response);


    out.println("</body>");
    out.println("</html>");

  }
}
