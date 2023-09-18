package bitcamp.personalapp.controller;


import bitcamp.personalapp.service.BoardService;
import bitcamp.personalapp.service.NcpObjectStorageService;
import bitcamp.personalapp.vo.AttachedFile;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


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
      return "redirect:/auth/form";
    }

    board.setMno(loginUser);


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
      return "redirect:/board/list";

  }


    @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("list", boardService.list());
  }

  @GetMapping("delete")
  public String delete(int no, Model model, HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:/auth/form";
    }

    Board b = boardService.get(no);

    if (b == null || b.getMno().getNo() != loginUser.getNo()) {
      throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
    } else {
      boardService.delete(b.getNo());
      return "redirect:/board/list";
    }

  }

  @GetMapping("detail/{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {


      Board board = boardService.get(no);

      if (board != null) {
        board.setV_count(board.getV_count() + 1);
        boardService.increaseViewCount(no);
        model.addAttribute("board", board);
      }

      // HttpSession session = request.getSession();
      // session.setAttribute("currentBoard", board);

      return "board/detail";

  }

  @GetMapping("userlist")
  public String userlist(Model model, HttpSession session) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");


    // 로그인 정보가 없는 경우 로그인 폼으로 이동
    if (loginUser == null) {
      return "redirect:/auth/form";
    }

    // 로그인 정보가 있는 경우 게시글 목록을 표시
    model.addAttribute("loginUser.no", loginUser.getNo());
    model.addAttribute("list", boardService.list());
    return "board/userlist";
  }

  @GetMapping("searchlist")
  public String searchlist(Model model, HttpSession session, @RequestParam String option, @RequestParam String keyword) throws Exception {

    model.addAttribute("option", option);
    model.addAttribute("keyword", keyword);

    List<Board> searchResult = boardService.searchlist(option, keyword);
    model.addAttribute("searchResult", searchResult);
    System.out.println("Search List: " + searchResult);

    return "board/searchlist";
  }



  @PostMapping("update")
  public String update(Board board, MultipartFile[] files, Model model, HttpSession session)
      throws Exception {


    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:/auth/form";
    }

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
      return "redirect:/board/list";
  }


  @GetMapping("fileDelete/{attachedfile}")
  public String fileDelete(
		  @MatrixVariable("no") int no,
          Model model, HttpSession session) throws Exception {


    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:/auth/form";
    }

    Board board = null;

      AttachedFile attachedFile = boardService.getAttachedFile(no);
      board = boardService.get(attachedFile.getBoardNo());
      if (board.getMno().getNo() != loginUser.getNo()) {
        throw new Exception("게시글 변경 권한이 없습니다!");
      }


      if (boardService.deleteAttachedFile(no) == 0) {
        throw new Exception("해당 번호의 첨부파일이 없거나 삭제 권한이 없습니다.");
      } else {
        return "redirect:/board/detail/" + board.getNo();
      }

  }
}


