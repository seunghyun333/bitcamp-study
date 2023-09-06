package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController{
  public String home() throws Exception {
    return "/WEB-INF/jsp/index.jsp";
  }
}

