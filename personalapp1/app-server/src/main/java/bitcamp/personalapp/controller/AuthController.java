package bitcamp.personalapp.controller;

import java.util.Map;
<<<<<<< HEAD
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
=======

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

>>>>>>> af9fe71ea72e61fa4ce6618418f1555e4f4340b0
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bitcamp.personalapp.service.MemberService;
import bitcamp.personalapp.vo.Member;

@Controller
public class AuthController {

  @Autowired
  MemberService memberService;
<<<<<<< HEAD


  @RequestMapping("/auth/form")
  public String form() {
    return "/WEB-INF/jsp/auth/form.jsp";
  }

  @RequestMapping("/auth/login")
  public String login(@RequestParam("email") String email, @RequestParam("pw") String pw,
      @RequestParam("saveEmail") String saveEmail, HttpSession session, Map<String, Object> model,
      HttpServletResponse response) throws Exception {


=======
  
  @RequestMapping("/auth/form")
  public String form() {
      return "/WEB-INF/jsp/auth/form.jsp";
    }

  @RequestMapping("/auth/login")
  public String login(
		  @RequestParam("email") String email,
		  @RequestParam("pw") String pw,
		  @RequestParam("saveEmail") String saveEmail,
		  HttpSession session,
		  Map<String, Object> model,
		  HttpServletResponse response) throws Exception {

>>>>>>> af9fe71ea72e61fa4ce6618418f1555e4f4340b0
    if (saveEmail != null) {
      Cookie cookie = new Cookie("email", email);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }


    Member loginUser = memberService.get(email, pw);
    if (loginUser == null) {
<<<<<<< HEAD
      model.put("refresh", "2;url=/app/auth/login");
=======
      model.put("refresh", "2;url=form");
>>>>>>> af9fe71ea72e61fa4ce6618418f1555e4f4340b0
      throw new Exception("회원 정보가 일치하지 않습니다.");
    }

    session.setAttribute("loginUser", loginUser);
    return "redirect:/";
  }

  @RequestMapping("/auth/logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:/";
  }
}


