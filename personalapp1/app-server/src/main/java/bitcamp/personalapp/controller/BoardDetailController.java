package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bitcamp.personalapp.service.BoardService;
import bitcamp.personalapp.vo.Board;

@Controller("/board/detail")
public class BoardDetailController implements PageController {

  @Autowired
  BoardService boardService;


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Board board = boardService.get(no);

      if (board != null) {
        board.setV_count(board.getV_count() + 1);
        boardService.increaseViewCount(no);
        request.setAttribute("board", board);
      }

      // HttpSession session = request.getSession();
      // session.setAttribute("currentBoard", board);

      return "/WEB-INF/jsp/board/detail.jsp";


    } catch (Exception e) {
      request.setAttribute("refresh", "5;url=list");
      throw e;
    }
  }
}


