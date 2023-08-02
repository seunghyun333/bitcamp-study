package bitcamp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import bitcamp.personalapp.dao.BoardDao;

public class DispatcherServlet implements Servlet {

  ApplicationContext iocContainer;

  public DispatcherServlet(ApplicationContext iocContainer) throws Exception {
    this.iocContainer = iocContainer;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      // 지정한 경로의 기본 문서를 요청할 경우,
      if (request.getServletPath().endsWith("/")) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(getStaticContent(request.getServletPath() + "index.html"));
        return;
      }

      // HTML 문서를 요청할 경우,
      if (request.getServletPath().endsWith(".html")) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(getStaticContent(request.getServletPath()));
        return;
      }

      // 서블릿의 실행을 요구할 경우,
      Servlet servlet = (Servlet) iocContainer.getBean(request.getServletPath());
      if (servlet == null) {
        throw new Exception("요청한 URL이 유효하지 않습니다.");
      }
      BoardDao boardDao = iocContainer.getBean(BoardDao.class);
      request.setAttribute("loginUser", boardDao.findBy(1));
      servlet.service(request, response);
    } catch (Exception e) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>게시글</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>실행 오류</h1>");
      out.printf("<p>%s</p>\n", e.getMessage());
      out.println("</body>");
      out.println("</html>");
    }
  }

  private String getStaticContent(String url) throws Exception {
    StringBuilder strBuf = new StringBuilder();

    String realPath = getRealPath(url);
    try (BufferedReader in = new BufferedReader(new FileReader(realPath))) {
      String line = null;
      while ((line = in.readLine()) != null) {
        strBuf.append(line);
      }
    }
    return strBuf.toString();
  }

  private String getRealPath(String urlPath) throws Exception {
    String staticResourcePath = "/static" + urlPath;
    Class<?> clazz = DispatcherServlet.class;
    URL fileURL = clazz.getResource(staticResourcePath);
    return fileURL.getFile();
  }
}
