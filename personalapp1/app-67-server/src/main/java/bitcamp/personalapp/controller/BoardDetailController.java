package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;

public class BoardDetailController implements PageController {

	BoardDao boardDao;
	SqlSessionFactory sqlSessionFactory;
	
	public BoardDetailController(BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {
		this.boardDao = boardDao;
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	    try {
	      int no = Integer.parseInt(request.getParameter("no"));
	
	      Board board = boardDao.findBy(no);
	
	      if (board != null) {
	        board.setV_count(board.getV_count() + 1);
	        boardDao.updateCount(board);
	        sqlSessionFactory.openSession(false).commit();
	        request.setAttribute("board", board);
	      }
	      
	      HttpSession session = request.getSession();
	      session.setAttribute("currentBoard", board);
	      return "/WEB-INF/jsp/board/detail.jsp";
	
	
	    } catch (Exception e) {
	      sqlSessionFactory.openSession(false).rollback();
	      request.setAttribute("refresh", "5;url=/board/list");
	      throw e;
	    }
	  }
	}

