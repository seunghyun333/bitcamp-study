package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Member;


public class BoardDeleteController implements PageController {
	
	BoardDao boardDao;
	SqlSessionFactory sqlSessionFactory;
	
	public BoardDeleteController(BoardDao boardDao,
	SqlSessionFactory sqlSessionFactory) {
		this.boardDao = boardDao;
		this.sqlSessionFactory = sqlSessionFactory;
		
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/login";

    }


    try {
      Board b = new Board();
      b.setNo(Integer.parseInt(request.getParameter("no")));
      b.setMno(loginUser);

      boardDao.deleteFiles(b.getNo());

      if (boardDao.delete(b) == 0) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        sqlSessionFactory.openSession(false).commit();
        return "redirect:list";
      }


    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}


