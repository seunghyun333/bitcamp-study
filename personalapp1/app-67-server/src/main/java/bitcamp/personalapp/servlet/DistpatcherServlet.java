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

import bitcamp.personalapp.controller.BoardAddController;
import bitcamp.personalapp.controller.BoardDeleteController;
import bitcamp.personalapp.controller.BoardDetailController;
import bitcamp.personalapp.controller.BoardFileDeleteController;
import bitcamp.personalapp.controller.BoardListController;
import bitcamp.personalapp.controller.BoardUpdateController;
import bitcamp.personalapp.controller.HomeController;
import bitcamp.personalapp.controller.LoginController;
import bitcamp.personalapp.controller.LogoutController;
import bitcamp.personalapp.controller.MemberAddController;
import bitcamp.personalapp.controller.MemberDeleteController;
import bitcamp.personalapp.controller.MemberDetailController;
import bitcamp.personalapp.controller.MemberListController;
import bitcamp.personalapp.controller.MemberUpdateController;
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
    controllerMap.put("/member/list", new MemberListController(memberDao));
    controllerMap.put("/member/update", new MemberUpdateController(memberDao, sqlSessionFactory, ncpObjectStorageService));
    controllerMap.put("/member/add", new MemberAddController(memberDao, sqlSessionFactory, ncpObjectStorageService));
    controllerMap.put("/member/delete", new MemberDeleteController(memberDao, sqlSessionFactory));
    controllerMap.put("/board/detail", new BoardDetailController(boardDao, sqlSessionFactory));
    controllerMap.put("/board/list", new BoardListController(boardDao));
    controllerMap.put("/board/update", new BoardUpdateController(boardDao, sqlSessionFactory, ncpObjectStorageService));
    controllerMap.put("/board/add", new BoardAddController(boardDao, sqlSessionFactory, ncpObjectStorageService));
    controllerMap.put("/board/fileDelete", new BoardFileDeleteController(boardDao, sqlSessionFactory));
    controllerMap.put("/board/Delete", new BoardDeleteController(boardDao, sqlSessionFactory));
    
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

	
	// 페이지 컨트롤러를 실행한다.
	try {
	  String viewUrl = pageController.execute(request, response);
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