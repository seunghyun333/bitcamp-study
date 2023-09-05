package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bitcamp.personalapp.service.BoardService;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Member;

@Controller("/board/delete")
public class BoardDeleteController {

  @Autowired
  BoardService boardSerivce;


  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/login";
    }

    try {
      Board b = boardSerivce.get(Integer.parseInt(request.getParameter("no")));

      if (b == null || b.getMno().getNo() != loginUser.getNo()) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        boardSerivce.delete(b.getNo());
        return "redirect:list";
      }

    } catch (Exception e) {
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}


