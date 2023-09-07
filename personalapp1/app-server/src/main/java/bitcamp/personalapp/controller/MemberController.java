package bitcamp.personalapp.controller;

import java.util.Map;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import bitcamp.personalapp.service.MemberService;
import bitcamp.personalapp.service.NcpObjectStorageService;
import bitcamp.personalapp.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

  @Autowired
  MemberService memberService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @RequestMapping("form")
  public String form() {
    return "/WEB-INF/jsp/member/form.jsp";
  }


  @RequestMapping("add")
  public String add(Member member, Part photofile, Map<String, Object> model) throws Exception {

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
      model.put("message", "회원 등록 오류!");
      model.put("refresh", "2;url=list");
      throw e;
    }
  }


  @RequestMapping("delete")
  public String delete(int no, Map<String, Object> model) throws Exception {

    try {
      if (memberService.delete(no) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다!! ");
      } else {
        return "redirect:list";
      }

    } catch (Exception e) {
      model.put("refresh", "2;url=list");
      throw e;
    }
  }

  @RequestMapping("detail")
  public String detail(int no, Map<String, Object> model) throws Exception {

    model.put("member", memberService.get(no));
    return "/WEB-INF/jsp/member/detail.jsp";
  }

  @RequestMapping("list")
  public String list(Map<String, Object> model) throws Exception {
    model.put("list", memberService.list());
    return "/WEB-INF/jsp/member/list.jsp";
  }


  @RequestMapping("update")
  public String update(Member member, Part photofile, Map<String, Object> model) throws Exception {

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
      model.put("refresh", "2;url=list");
      throw e;
    }
  }
}


