package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.vo.Board;

@Component("/board/detail")
public class BoardDetailController implements PageController {

  BoardDao boardDao;
  PlatformTransactionManager txManager;

  public BoardDetailController(BoardDao boardDao,  PlatformTransactionManager txManager) {
    this.boardDao = boardDao;
    this.txManager = txManager;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    def.setName("tx1");
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = txManager.getTransaction(def);
	    
    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Board board = boardDao.findBy(no);

      if (board != null) {
        board.setV_count(board.getV_count() + 1);
        boardDao.updateCount(board);
        txManager.commit(status);
        request.setAttribute("board", board);
      }

      // HttpSession session = request.getSession();
      // session.setAttribute("currentBoard", board);

      return "/WEB-INF/jsp/board/detail.jsp";


    } catch (Exception e) {
      txManager.rollback(status);
      request.setAttribute("refresh", "5;url=list");
      throw e;
    }
  }
}


