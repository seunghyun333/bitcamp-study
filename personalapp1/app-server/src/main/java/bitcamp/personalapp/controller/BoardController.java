package bitcamp.personalapp.controller;


import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import bitcamp.personalapp.service.BoardService;
import bitcamp.personalapp.service.NcpObjectStorageService;
import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Member;


@Controller
@RequestMapping("/board")
public class BoardController {

  {
    System.out.println("BoardController 생성됨!");
  }

  @Autowired
  BoardService boardService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @GetMapping("form")
  public void form() {}

  @PostMapping("add")
  public String add(Board board, MultipartFile[] files, Model model, HttpSession session)
      throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/form";
    }

    board.setMno(loginUser);

    try {
      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      for (MultipartFile part : files) {
        if (part.getSize() > 0) {
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
      model.addAttribute("message", "게시글 등록 오류!");
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }


  @GetMapping("delete")
  public String delete(int no, Model model, HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/form";
    }

    try {
      Board b = boardService.get(no);

      if (b == null || b.getMno().getNo() != loginUser.getNo()) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        boardService.delete(b.getNo());
        return "redirect:list";
      }

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }

  @GetMapping("detail/{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {

    try {
      Board board = boardService.get(no);

      if (board != null) {
        board.setV_count(board.getV_count() + 1);
        boardService.increaseViewCount(no);
        model.addAttribute("board", board);
      }

      // HttpSession session = request.getSession();
      // session.setAttribute("currentBoard", board);

      return "board/detail";


    } catch (Exception e) {
      model.addAttribute("refresh", "5;url=list");
      throw e;
    }
  }


  @GetMapping("list")
  public void list(Model model) throws Exception {
    try {
      model.addAttribute("list", boardService.list());
    } catch (Exception e) {
      model.addAttribute("refresh", "1;url=/");
      throw e;
    }
  }


  @PostMapping("update")
  public String update(Board board, MultipartFile[] files, Model model, HttpSession session)
      throws Exception {


    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/form";
    }

    try {
      Board b = boardService.get(board.getNo());
      if (b == null || b.getMno().getNo() != loginUser.getNo()) {
        throw new Exception("게시글이 존재하지 않거나 변경 권한이 없습니다.");
      }

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      for (MultipartFile part : files) {
        if (part.getSize() > 0) {
          String uploadFileUrl =
              ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);

      boardService.update(board);
      return "redirect:list";

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }


  @GetMapping("fileDelete")
  public String fileDelete(int no, Model model, HttpSession session) throws Exception {


    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/form";
    }

    Board board = null;

    try {
      AttachedFile attachedFile = boardService.getAttachedFile(no);
      board = boardService.get(attachedFile.getBoardNo());
      if (board.getMno().getNo() != loginUser.getNo()) {
        throw new Exception("게시글 변경 권한이 없습니다!");
      }


      if (boardService.deleteAttachedFile(no) == 0) {
        throw new Exception("해당 번호의 첨부파일이 없거나 삭제 권한이 없습니다.");
      } else {
        return "redirect:detail?&no=" + board.getNo();
      }

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=detail?no=" + board.getNo());
      throw e;
    }
  }
}


