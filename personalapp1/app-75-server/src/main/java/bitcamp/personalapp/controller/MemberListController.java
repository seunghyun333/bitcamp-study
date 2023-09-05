package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bitcamp.personalapp.service.MemberService;

@Controller("/member/list")
public class MemberListController {

  @Autowired
  MemberService memberservice;


  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("list", memberservice.list());
    return "/WEB-INF/jsp/member/list.jsp";

  }
}


