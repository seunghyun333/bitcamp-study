package bitcamp.myapp.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyWebApplicationInitializer3 extends AbstractDispatcherServletInitializer {

  @Override
  public void onStartup(ServletContext sc) throws ServletException {
    System.out.println("MyWebApplicationInitializer3.onStartup() 호출됨 ");

//    //수퍼 클래스에 정의된 작업은 그대로 수해하고,
//    // => DispactherServlet 을 준비하는 작업
    //super.onStartup(sc);


  }

  //ioc컨테이너 리턴 받기위해서
  @Override
  protected WebApplicationContext createServletApplicationContext() {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(AppConfig.class);
    return context;
  }
  //매핑
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/app/*"};
  }

  //멀티파트 + 알파 설정
  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement("temp", 1000000, 15000000, 1000000));
  }

  //컨텍스트 로더 리스터맏르때
  @Override
  protected WebApplicationContext createRootApplicationContext() {
    return null;
  }

}
