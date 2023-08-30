package bitcamp.personalapp.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.controller.HomeController;
import bitcamp.personalapp.controller.LoginController;
import bitcamp.personalapp.controller.LogoutController;
import bitcamp.personalapp.controller.MemberDetailController;
import bitcamp.personalapp.controller.PageController;
import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.dao.CommentDao;
import bitcamp.personalapp.dao.MemberDao;
import bitcamp.util.NcpObjectStorageService;

@WebServlet("/app/*")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class DistpatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  Map<String, PageController> controllerMap = new HashMap<>();

  @Override
  public void init() throws ServletException {
    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    CommentDao commentDao = (CommentDao) this.getServletContext().getAttribute("commentDao");
    SqlSessionFactory sqlSessionFactory =
        (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService =
        (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");


    controllerMap.put("/", new HomeController());
    controllerMap.put("/auth/login", new LoginController(memberDao));
    controllerMap.put("/auth/logout", new LogoutController());
    controllerMap.put("/member/detail", new MemberDetailController(memberDao));
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String pageControllerPath = request.getPathInfo();

    response.setContentType("text/html;charset=UTF-8");

    PageController pageController = controllerMap.get(pageControllerPath);
    if (pageController == null) {
      throw new ServletException("해당 요청을 처리할 수 없습니다! ");
    }

    try {
      String viewUrl = (String) request.getAttribute("viewUrl");
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
      } else {
        request.getRequestDispatcher(viewUrl).include(request, response);
      }
    } catch (Exception e) {
      throw new ServletException("요청 처리 중 오류 발생!", e);
    }
  }
}
