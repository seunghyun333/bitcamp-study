package bitcamp.myapp.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class AppWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  public AppWebApplicationInitializer() {
    System.out.println("AppWebApplicationInitializer 생성됨 ");
  }

  @Override
  protected String getServletName() {
    return "app";
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    // ContextLoaderListener의 IoC컨테이너가 사용할 java config 클래스를 지정한다.
    return new Class[] {RootConfig.class, DbConfig.class, MybatisConfig.class};
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
    registration.setMultipartConfig(new MultipartConfigElement(null, 1000000, 15000000, 1000000));
  }

  @Override
  protected Filter[] getServletFilters() {
    return new Filter[] {new CharacterEncodingFilter("UTF-8")};
  }
}
