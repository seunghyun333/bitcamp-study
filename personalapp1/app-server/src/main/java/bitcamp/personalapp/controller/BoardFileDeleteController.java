package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bitcamp.personalapp.service.BoardService;
import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Member;

@Controller("/board/fileDelete")
public class BoardFileDeleteController implements PageController {

  @Autowired
  BoardService boardService;


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/login";
    }

    Board board = null;

    try {
      int fileNo = Integer.parseInt(request.getParameter("no"));

      AttachedFile attachedFile = boardService.getAttachedFile(fileNo);
      board = boardService.get(attachedFile.getBoardNo());
      if (board.getMno().getNo() != loginUser.getNo()) {
        throw new Exception("게시글 변경 권한이 없습니다!");
      }


      if (boardService.deleteAttachedFile(fileNo) == 0) {
        throw new Exception("해당 번호의 첨부파일이 없거나 삭제 권한이 없습니다.");
      } else {
        return "redirect:detail?&no=" + board.getNo();
      }

    } catch (Exception e) {
      request.setAttribute("refresh", "2;url=detail?no=" + board.getNo());
      throw e;
    }
  }
}


