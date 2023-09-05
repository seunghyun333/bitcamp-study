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


@Controller("/board/update")
public class BoardUpdateController {

  @Autowired
  BoardService boardService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      request.getParts();
      return "redirect:../auth/login";
    }

    try {
      Board board = new Board();
      board.setMno(loginUser);
      board.setNo(Integer.parseInt(request.getParameter("no")));
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      String secretParam = request.getParameter("secret");
      boolean isSecret = Boolean.parseBoolean(secretParam);
      board.setSecret(isSecret);

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

      for (Part part : request.getParts()) {
        if (part.getName().equals("files") && part.getSize() > 0) {
          String uploadFileUrl =
              ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07", "/board", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);


      boardService.update(board);
      return "redirect:list";

    } catch (Exception e) {
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }

  }
}


