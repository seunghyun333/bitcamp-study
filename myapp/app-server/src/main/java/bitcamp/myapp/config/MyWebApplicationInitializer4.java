package bitcamp.myapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyWebApplicationInitializer4 extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  public void onStartup(ServletContext sc) throws ServletException {
    System.out.println("MyWebApplicationInitializer4.onStartup() 호출됨 ");
//
//    //수퍼 클래스에 정의된 작업은 그대로 수해하고,
//    // => DispactherServlet이 사용할 IoC 컨테이너를 준비한다.
    super.onStartup(sc);
  }


  @Override
  protected Class<?>[] getRootConfigClasses() {
    // ContextLoaderListener의 IoC컨테이너가 사용할 java config 클래스를 지정한다.
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    //// DispatcherServlet의  IoC컨테이너가 사용할 java config 클래스를 지정한다.
    return new Class[] {AppConfig.class};
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


}
