package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bitcamp.personalapp.service.MemberService;

@Controller("/member/detail")
public class MemberDetailController {

  @Autowired
  MemberService memberSerivce;

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    request.setAttribute("member", memberSerivce.get(Integer.parseInt(request.getParameter("no"))));
    return "/WEB-INF/jsp/member/detail.jsp";


  }
}
