package bitcamp.personalapp.controller;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bitcamp.personalapp.service.BoardService;
import bitcamp.personalapp.service.NcpObjectStorageService;
import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Member;


@Controller("/board/add")
public class BoardAddController {

  @Autowired
  BoardService boardService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/board/form.jsp";
    }

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      request.getParts(); // 일단 클라이언트가 보낸 파일을 읽는다. 그래야 응답 가능!
      return "redirect:../auth/login";
    }

    try {
      Board board = new Board();
      board.setMno(loginUser);
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      for (Part part : request.getParts()) {
        if (part.getName().equals("files") && part.getSize() > 0) {
          String uploadFileUrl =
              ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);

      boardService.add(board);
      return "redirect:list";

    } catch (Exception e) {
      request.setAttribute("message", "게시글 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}


