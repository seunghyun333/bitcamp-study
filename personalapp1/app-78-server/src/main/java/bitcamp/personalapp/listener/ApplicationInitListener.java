package bitcamp.personalapp.listener;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import bitcamp.personalapp.config.AppConfig;

public class ApplicationInitListener implements ServletContextListener {

  public ApplicationInitListener() {
    System.out.println("ApplicationInitListener 생성됨");
  }


  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext sc = sce.getServletContext();

    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(AppConfig.class);

    DispatcherServlet servlet = new DispatcherServlet(context);
    ServletRegistration.Dynamic registration = sc.addServlet("app", servlet);
    registration.setLoadOnStartup(1);
    registration.addMapping("/app/*");
    registration
        .setMultipartConfig(new MultipartConfigElement("temp", 10000000, 15000000, 1000000));
  }
}
