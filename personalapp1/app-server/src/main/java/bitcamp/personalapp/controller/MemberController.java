package bitcamp.personalapp.controller;

import bitcamp.personalapp.service.MemberService;
import bitcamp.personalapp.service.NcpObjectStorageService;
import bitcamp.personalapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

  {
    System.out.println("MemberController 생성됨!");
  }


  @Autowired
  MemberService memberService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @GetMapping("form")
  public void form() {}


  @PostMapping("add")
  public String add(Member member, MultipartFile photofile, Model model) throws Exception {

    try {
      System.out.println(member);

      if (photofile.getSize() > 0) {
        String uploadFileUrl =
            ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07", "member/", photofile);
        member.setPhoto(uploadFileUrl);
      }

      memberService.add(member);
      return "redirect:list";

    } catch (Exception e) {
      model.addAttribute("message", "회원 등록 오류!");
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }


  @GetMapping("delete")
  public String delete(int no, Model model) throws Exception {

    try {
      if (memberService.delete(no) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다!! ");
      } else {
        return "redirect:list";
      }

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }

  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {

    model.addAttribute("member", memberService.get(no));
    return "member/detail";
  }

  @GetMapping(value = {"list", "photolist"})
  public void list(Model model, HttpSession session) throws Exception {
    model.addAttribute("list", memberService.list());
  }




  @PostMapping("update")
  public String update(Member member, MultipartFile photofile, Model model) throws Exception {

    try {
      if (photofile.getSize() > 0) {
        String uploadFileUrl =
            ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07", "member/", photofile);
        member.setPhoto(uploadFileUrl);
      }

      if (memberService.update(member) == 0) {
        throw new Exception("<p>회원이 없습니다.</p>");
      } else {
        return "redirect:list";
      }

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}


