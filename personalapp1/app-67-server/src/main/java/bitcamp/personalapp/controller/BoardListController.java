package bitcamp.personalapp.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.personalapp.dao.BoardDao;


public class BoardListController implements PageController {
	
	BoardDao boardDao;
	
	public BoardListController(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

 @Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


    try {
      request.setAttribute("list", boardDao.findAll());
      return "/WEB-INF/jsp/board/list.jsp";

    } catch (Exception e) {
      request.setAttribute("refresh", "1;url=/");
      throw e;
    }
  }
}


