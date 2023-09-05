package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bitcamp.personalapp.service.MemberService;
import bitcamp.personalapp.service.NcpObjectStorageService;
import bitcamp.personalapp.vo.Member;

@Controller("/member/update")
public class MemberUpdateController {

  @Autowired
  MemberService memberService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      Member member = new Member();
      member.setNo(Integer.parseInt(request.getParameter("no")));
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPw(request.getParameter("pw"));


      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl =
            ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07", "member/", photoPart);
        member.setPhoto(uploadFileUrl);
      }


      if (memberService.update(member) == 0) {
        throw new Exception("<p>회원이 없습니다.</p>");
      } else {
        return "redirect:list";

      }

    } catch (Exception e) {
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
