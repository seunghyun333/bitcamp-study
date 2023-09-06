package bitcamp.myapp.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class AdminWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  public AdminWebApplicationInitializer() {
    System.out.println("AdminWebApplicationInitializer 생성됨 ");
  }

  @Override
  protected String getServletName() {
    return "admin";
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    // ContextLoaderListener의 IoC컨테이너가 사용할 java config 클래스를 지정한다.
    //=> AppWebApplicationInitializer에서 RootConfig를 가지고 ContextLoaderListener를 만들었기 때문에
    // 여기서는 설정하지 않는다.  한 군데서 설정하면 다른 곳들에서는 할 필요 없다.
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    //// DispatcherServlet의  IoC컨테이너가 사용할 java config 클래스를 지정한다.
    return new Class[] {AdminConfig.class};
  }

  //매핑
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/admin/*"};
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
