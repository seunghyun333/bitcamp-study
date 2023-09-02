package bitcamp.personalapp.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.service.NcpObjectStorageService;
import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Member;


@Component("/board/update")
public class BoardUpdateController implements PageController {

	BoardDao boardDao;
	PlatformTransactionManager txManager;
	NcpObjectStorageService ncpObjectStorageService;
	
	public BoardUpdateController(BoardDao boardDao,
			PlatformTransactionManager txManager,
	NcpObjectStorageService ncpObjectStorageService
	) {
		this.boardDao = boardDao;
		this.txManager = txManager;
		this.ncpObjectStorageService = ncpObjectStorageService;
	}
	
	@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
    	request.getParts();
      return "redirect:../auth/login";
    }
    
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = txManager.getTransaction(def);
    


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


      if (boardDao.update(board) == 0) {
        throw new Exception("게시글이 없거나 변경 권한이 없습니다.");
      } else {
        if (attachedFiles.size() > 0) {
          boardDao.insertFiles(board);
        }
        txManager.commit(status);
        return "redirect:list";
      }

    } catch (Exception e) {
      txManager.rollback(status);
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }

  }
}


