package bitcamp.personalapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Member;
import bitcamp.util.NcpObjectStorageService;


@WebServlet("/board/add")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class BoardAddController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/board/form.jsp").include(request, response);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    SqlSessionFactory sqlSessionFactory =
        (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService =
        (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");


    try {
      Board board = new Board();
      board.setMno(loginUser);
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setSecret(Boolean.parseBoolean(request.getParameter("secret")));

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

      for (Part part : request.getParts()) {
        // System.out.println(part.getName());
        if (part.getName().equals("files") && part.getSize() > 0) {
          String uploadFileUrl =
              ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);


      boardDao.insert(board);
      if (attachedFiles.size() > 0) {
        boardDao.insertFiles(board);
      }
      sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("list");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "2,url=list");
      throw new ServletException(e);
    }
  }
}


