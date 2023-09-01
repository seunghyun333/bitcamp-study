package bitcamp.personalapp.servlet;

<<<<<<< HEAD
import bitcamp.personalapp.config.AppConfig;
import bitcamp.personalapp.config.NcpConfig;
import bitcamp.personalapp.controller.PageController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

=======
import java.io.IOException;
>>>>>>> b3bba5088185532d3ec7440c22cbd82ba5729fb1
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import java.io.IOException;


@WebServlet(
        value = "/app/*",
        loadOnStartup = 1)
=======
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import bitcamp.personalapp.config.AppConfig;
import bitcamp.personalapp.config.NcpConfig;
import bitcamp.personalapp.controller.PageController;


@WebServlet(value = "/app/*", loadOnStartup = 1)
>>>>>>> b3bba5088185532d3ec7440c22cbd82ba5729fb1
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  AnnotationConfigApplicationContext iocContainer;

  @Override
  public void init() throws ServletException {
<<<<<<< HEAD
    System.out.println("DispatcherServlet.init() 호출됨!");
    iocContainer = new AnnotationConfigApplicationContext(AppConfig.class, NcpConfig.class);
=======
    System.out.println("DistpatcherServlet.init() 호출됨!");
    iocContainer = new AnnotationConfigApplicationContext(AppConfig.class, NcpConfig.class);

    SqlSessionFactory sqlSessionFactory = iocContainer.getBean(SqlSessionFactory.class);
    this.getServletContext().setAttribute("sqlSessionFactory", sqlSessionFactory);
>>>>>>> b3bba5088185532d3ec7440c22cbd82ba5729fb1
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
<<<<<<< HEAD
          throws ServletException, IOException {
=======
      throws ServletException, IOException {
>>>>>>> b3bba5088185532d3ec7440c22cbd82ba5729fb1
    String pageControllerPath = request.getPathInfo();
    System.out.println(pageControllerPath);

    response.setContentType("text/html;charset=UTF-8");

    // 클라이언트가 요청한 페이지 컨트롤러를 찾는다.
    PageController pageController = (PageController) iocContainer.getBean(pageControllerPath);
<<<<<<< HEAD

    // 페이지 컨트롤러를 실행한다.
    try {
      String viewUrl = pageController.execute(request, response);
=======
    System.out.println(pageController);
    // 페이지 컨트롤러를 실행한다.
    try {
      String viewUrl = pageController.execute(request, response);
      System.out.println(viewUrl);
>>>>>>> b3bba5088185532d3ec7440c22cbd82ba5729fb1
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9)); // 예) redirect:/app/board/list
      } else {
        request.getRequestDispatcher(viewUrl).include(request, response);
      }

    } catch (Exception e) {
      // 페이지 컨트롤러 실행 중 오류가 발생했다면, 예외를 던진다.
      throw new ServletException("요청 처리 중 오류 발생!", e);
    }

  }
}
