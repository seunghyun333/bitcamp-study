package bitcamp.personalapp.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class AdminWebApplicationInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer {

  public AdminWebApplicationInitializer() {
    System.out.println("AdminWebApplicationInitializer 생성됨!");
  }

  @Override
  protected String getServletName() {
    return "admin";
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // DispatcherServlet의 IoC 컨테이너가 사용할 java config 클래스를 지정한다.
    return new Class[] {AdminConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    // DispatcherServlet의 URL을 지정한다.
    return new String[] {"/admin/*"};
  }

  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement(null, 10000000, 15000000, 1000000));
  }

  @Override
  protected Filter[] getServletFilters() {
    return new Filter[] {new CharacterEncodingFilter("UTF-8")};
  }
}
