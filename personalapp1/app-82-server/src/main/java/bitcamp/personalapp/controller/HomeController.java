package bitcamp.personalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
  {
    System.out.println("HomeController 생성됨!");
  }

  @GetMapping("/")

  public String home() throws Exception {
    return "index";
  }



}
