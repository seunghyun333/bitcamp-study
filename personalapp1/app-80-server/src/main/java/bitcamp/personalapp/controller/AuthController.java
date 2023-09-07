package bitcamp.personalapp.controller;

import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import bitcamp.personalapp.service.MemberService;
import bitcamp.personalapp.vo.Member;

@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  MemberService memberService;

  @RequestMapping("form")
  public String form() {
    return "/WEB-INF/jsp/auth/form.jsp";
  }

  @RequestMapping("login")
  public String login(String email, String pw, String saveEmail, HttpSession session,
      Map<String, Object> model, HttpServletResponse response) throws Exception {

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
      model.put("refresh", "2;url=form");
      throw new Exception("회원 정보가 일치하지 않습니다.");
    }

    session.setAttribute("loginUser", loginUser);
    return "redirect:/";
  }

  @RequestMapping("logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:/";
  }
}


